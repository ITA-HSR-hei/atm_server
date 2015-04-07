package ch.cnlab.atm.service;

import java.util.List;

import ch.cnlab.atm.domain.Airport;

public interface AirportService {

	public void saveAirport(Airport airport);

	public List<Airport> loadAllAirports();

	public Airport updateAirport(Airport airport);

	public boolean deleteAirport(Airport airport);

}
