package ch.cnlab.atm.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.NonUniqueResultException;

import ch.cnlab.atm.domain.MeasureStation;
import ch.cnlab.atm.persistence.MeasureStationDao;

public class MeasureStationDaoImpl implements MeasureStationDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void saveMeasureStation(MeasureStation measureStation) {

		em.persist(measureStation);

	}

	@Override
	public MeasureStation loadMeasureStationById(Long id) {
		return em.find(MeasureStation.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MeasureStation> loadAllMeasureStations() {
		return em.createQuery("SELECT ma FROM MeasureStation ma").getResultList();
	}

	@Override
	public MeasureStation loadMeasureStationByMac(String macAddress) throws NoResultException, NonUniqueResultException {
		MeasureStation m = (MeasureStation) em
				.createQuery("Select ma FROM MeasureStation AS ma WHERE ma.macAddressOrPort = :macAddress")
				.setParameter("macAddress", macAddress).getSingleResult();
		return m;

	}

}
