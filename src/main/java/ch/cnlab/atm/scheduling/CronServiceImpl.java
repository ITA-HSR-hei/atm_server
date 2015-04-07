package ch.cnlab.atm.scheduling;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ch.cnlab.atm.domain.AverageMeasurementOneHour;
import ch.cnlab.atm.domain.MeasureStation;
import ch.cnlab.atm.domain.Measurement;
import ch.cnlab.atm.domain.MicrophonePort;
import ch.cnlab.atm.service.MeasureStationService;
import ch.cnlab.atm.service.MeasurementService;
import ch.cnlab.atm.util.Constants;

public class CronServiceImpl implements Service {

	@Autowired
	private MeasureStationService measureStationService;
	@Autowired
	private MeasurementService measurementService;

	public void performService() {
		LinkedList<AverageMeasurementOneHour> hourly = new LinkedList<AverageMeasurementOneHour>();
		for (MeasureStation ms : measureStationService.loadMeasureStations()) {
			hourly.addAll(createHourlyAverage(ms));
		}
		// save the average
		for (AverageMeasurementOneHour avg : hourly) {
			measurementService.saveAverageMeasurementOneHour(avg);
		}
	}

	private List<AverageMeasurementOneHour> createHourlyAverage(final MeasureStation ms) {
		List<AverageMeasurementOneHour> ret = new LinkedList<AverageMeasurementOneHour>();
		long from = getLastHour();
		long to = from + Constants.HOUR;
		short avgDba;
		if (ms.getConfiguration().isPresent() && !ms.getConfiguration().get().getMicrophonePorts().isEmpty()) {
			List<MicrophonePort> ports = ms.getConfiguration().get().getMicrophonePorts();
			for (MicrophonePort p : ports) {
				int portnumber = p.getMicrophoneNumber();
				avgDba = measurementService.loadAvgMeasurements(ms.getId(), (byte) portnumber, from, to);
				Measurement m = getMeasurement(from, ms, portnumber);
				ret.add(AverageMeasurementOneHour.averageMeasurementOneHour(m, avgDba));
			}

		} else {
			avgDba = measurementService.loadAvgMeasurements(ms.getId(), (byte) 0, from, to);
			Measurement m = getMeasurement(from, ms, 0);
			ret.add(AverageMeasurementOneHour.averageMeasurementOneHour(m, avgDba));
		}
		return ret;
	}

	protected static Measurement getMeasurement(long from, final MeasureStation ms, int port) {
		Measurement m = new Measurement();
		m.setDate(from);
		m.setMeasureStation(ms);
		m.setPort((byte) port);
		return m;
	}

	protected static long getLastHour() {
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		c.set(Calendar.HOUR, 0);
		c.add(Calendar.HOUR, -1);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime().getTime();
	}
}