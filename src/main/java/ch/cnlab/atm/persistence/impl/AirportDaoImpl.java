package ch.cnlab.atm.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.cnlab.atm.domain.Airport;
import ch.cnlab.atm.persistence.AirportDao;

public class AirportDaoImpl implements AirportDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveAirport(Airport airport) {
	em.persist(airport);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Airport> loadAllAirports() {
	return em.createQuery("SELECT a FROM Airport a").getResultList();

    }

    @Override
    public Airport updateAirport(Airport airport) {
	return em.merge(airport);
    }

    @Override
    public boolean deleteAirport(Airport airport) {
	int id = airport.getId();
	em.remove(airport);
	return em.createQuery("SELECT a FROM Airport a WHERE a.id = :aiportid").setParameter("aiportid", id).getResultList().isEmpty();
    }

}
