package ch.cnlab.atm.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ch.cnlab.atm.domain.MeasureStation;
import ch.cnlab.atm.persistence.MeasureStationDao;
import ch.cnlab.atm.service.MeasureStationService;

public class MeasureStationServiceImpl implements MeasureStationService {
	private static final Logger LOGGER = Logger.getLogger(MeasureStationServiceImpl.class.getName());
	@Autowired
	private MeasureStationDao measureStationDao;

	@Override
	@Transactional
	public void createMeasureStationWithMAC(String macAddress) {
		MeasureStation ms = new MeasureStation();
		ms.setMAC(macAddress);
		ms.setCnlabStation(true);
		try {
			measureStationDao.saveMeasureStation(ms);
		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	@Override
	@Transactional
	public void saveMeasureStation(MeasureStation measureStation) {
		measureStationDao.saveMeasureStation(measureStation);

	}

	@Override
	@Transactional(readOnly = true)
	public MeasureStation loadMeasureStaionById(Long id) {
		return measureStationDao.loadMeasureStationById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MeasureStation> loadMeasureStations() {
		return measureStationDao.loadAllMeasureStations();
	}

	@Override
	@Transactional(readOnly = true)
	public MeasureStation loadMeasureStaionByMacAdress(String macAddress) {
		try {
			return measureStationDao.loadMeasureStationByMac(macAddress);
		} catch (NoResultException e) {
			LOGGER.error(e);
			return null;
		} catch (NonUniqueResultException e) {
			LOGGER.error(e);
			return null;
		}

	}

}
