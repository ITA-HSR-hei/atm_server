package ch.cnlab.atm.service.impl;

import java.util.List;

import org.jboss.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ch.cnlab.atm.domain.Airport;
import ch.cnlab.atm.persistence.AirportDao;
import ch.cnlab.atm.service.AirportService;

public class AirportServiceImpl implements AirportService {

	private static Logger LOGGER = Logger.getLogger(AirportServiceImpl.class);

	@Autowired
	private AirportDao airportDao;

	@Override
	@Transactional
	public void saveAirport(Airport airport) {
		try {
			airportDao.saveAirport(airport);
		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<Airport> loadAllAirports() {
		return airportDao.loadAllAirports();
	}

	@Override
	@Transactional
	public Airport updateAirport(Airport airport) {
		return airportDao.updateAirport(airport);
	}

	@Override
	@Transactional
	public boolean deleteAirport(Airport airport) {
		return airportDao.deleteAirport(airport);
	}

}
