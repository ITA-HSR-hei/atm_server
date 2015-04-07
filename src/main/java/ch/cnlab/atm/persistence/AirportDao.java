package ch.cnlab.atm.persistence;

import java.util.List;

import ch.cnlab.atm.domain.Airport;

public interface AirportDao {
    
    public void saveAirport(Airport airport);

    public List<Airport> loadAllAirports();

    public Airport updateAirport(Airport airport);
    
    public boolean deleteAirport(Airport airport);

}
