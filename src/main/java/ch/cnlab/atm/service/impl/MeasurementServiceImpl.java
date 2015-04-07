package ch.cnlab.atm.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.criteria.CriteriaBuilder.Case;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ch.cnlab.atm.domain.AverageMeasurementOneHour;
import ch.cnlab.atm.domain.MeasureStation;
import ch.cnlab.atm.domain.Measurement;
import ch.cnlab.atm.domain.MicrophonePort;
import ch.cnlab.atm.persistence.MeasureStationDao;
import ch.cnlab.atm.persistence.MeasurementDao;
import ch.cnlab.atm.service.MeasurementService;
import ch.cnlab.atm.util.Constants;

public class MeasurementServiceImpl implements MeasurementService {
	private static final Logger LOGGER = Logger.getLogger(MeasurementServiceImpl.class.getName());
	@Autowired
	private MeasurementDao measurementDao;
	@Autowired
	private MeasureStationDao measureStationDao;

	@Override
	@Transactional
	public void saveMeasurement(long stationId, Measurement measurement) {
		MeasureStation measurmentStation = measureStationDao.loadMeasureStationById(stationId);
		measurement.setMeasureStation(measurmentStation);
		try {
			measurementDao.saveMeasurement(measurement);
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@Override
	@Transactional
	public void saveAverageMeasurementOneHour(AverageMeasurementOneHour avg) {
		try {
			measurementDao.saveAvgMeasurementOnehour(avg);
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Measurement> loadMeasurements(final long stationId, long dateFrom, long dateTo) {

		
		
		if ((dateTo - dateFrom) <= Constants.DAY) {
			return measurementDao.loadMeasurements(stationId, dateFrom, dateTo);
		}else{
			return getListOfAverageHours(stationId, dateFrom, dateTo);
		}
		
		
//		long spacing = 1000;
//		List<Measurement> list = new LinkedList<Measurement>();
//		if (dateFrom + spacing >= dateTo) {
//			return list;
//		}
//		if ((dateTo - dateFrom) <= Constants.DAY) {
//			list = measurementDao.loadMeasurements(stationId, dateFrom, dateTo);
//		} else {
//			
//		}

//		checkAndFixFirst(list, dateFrom, spacing);
//		checkList(list, dateFrom, spacing);
//		return list;
	}

	private List<Measurement> getListOfAverageHours(final long stationId, long dateFrom, long dateTo) {
		List<Measurement> list = new LinkedList<Measurement>();
		long spacing = 1000;
		
		List<AverageMeasurementOneHour> avgList = new LinkedList<AverageMeasurementOneHour>();
		MeasureStation station = measureStationDao.loadMeasureStationById(stationId);
		if (station.getConfiguration().isPresent() && !station.getConfiguration().get().getMicrophonePorts().isEmpty()) {
			List<MicrophonePort> mpList = station.getConfiguration().get().getMicrophonePorts();
			for (MicrophonePort mp : mpList) {
				avgList.addAll(measurementDao.loadAvgMeasurements(stationId, mp.getMicrophoneNumber(), dateFrom, dateTo));
			}
		} else {
			avgList.addAll(measurementDao.loadAvgMeasurements(stationId, (byte) 0, dateFrom, dateTo));
		}
		// Dreieck Mittelung
		if (avgList.size() < 10) {
			// keine Mittelwertsbildung
			for (AverageMeasurementOneHour avg : avgList) {
				list.add(avg.toMeasurement());
			}
		} else {
			AverageMeasurementOneHour[] avgArray = avgList.toArray(new AverageMeasurementOneHour[avgList.size()]);
			for (int i = 2; i < avgArray.length; i++) {
				short avgSoundLevel = calcAvgLevel(avgArray, i);
				Measurement m = convertAvgToNormal(avgArray[i - 1]);
				m.setSoundLevel(avgSoundLevel);
				list.add(m);
			}
		}
		if (!avgList.isEmpty()) {
			spacing = (long) (avgList.get(0).getInterval() * 1.2);

		}
		
		return list;
		
	}

	private short calcAvgLevel(AverageMeasurementOneHour[] avgArray, int i) {
		return (short) ((avgArray[i - 2].getDbAinCentis() + avgArray[i - 1].getDbAinCentis() + avgArray[i].getDbAinCentis()) / 3);
	}

	@Override
	@Transactional(readOnly = true)
	public short loadAvgMeasurements(long stationId, byte port, long from, long to) {
		return measurementDao.loadAvgMeasurementValue(stationId, port, from, to);
	}

	protected static void checkAndFixFirst(List<Measurement> list, long from, long spacing) {
		if (list.size() > 0) {
			Measurement head = list.get(0);
			if (checkValidEntry(head, from, spacing)) {
				return;
			}
		}
		// first measurement is missing
		list.add(0, createEmptyMeasurement(from));
	}

	protected static boolean checkValidEntry(Measurement m, long from, long spacing) {
		return m.getDate() <= (from + spacing);
	}

	protected void checkList(List<Measurement> list, long from, long spacing) {
		ListIterator<Measurement> it = list.listIterator();
		while (it.hasNext()) {
			if (!checkValidEntry(it.next(), from, spacing)) {
				it.previous();
				it.add(createEmptyMeasurement(from));
			}
			from += spacing;
		}
	}

	protected static Measurement createEmptyMeasurement(long timestamp) {
		Measurement m = new Measurement();
		m.setDate(timestamp);
		m.setSoundLevel((short) 0);
		return m;
	}

	protected Measurement convertAvgToNormal(AverageMeasurementOneHour avg) {
		Measurement m = new Measurement();
		m.setDate(avg.getDate());
		m.setMeasureStation(avg.getMeasureStation());
		m.setPort(avg.getPort());
		m.setSoundLevel(avg.getDbAinCentis());
		return m;

	}

}
