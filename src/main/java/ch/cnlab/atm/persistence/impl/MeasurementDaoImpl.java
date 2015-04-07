package ch.cnlab.atm.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.cnlab.atm.domain.AverageMeasurementOneHour;
import ch.cnlab.atm.domain.Measurement;
import ch.cnlab.atm.persistence.MeasurementDao;

public class MeasurementDaoImpl implements MeasurementDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void saveMeasurement(Measurement measurement) {
		em.persist(measurement);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Measurement> loadMeasurements(long stationId, long from, long to) {

		List<Measurement> list = em
				.createQuery(
						"SELECT ma FROM Measurement ma WHERE ma.measureStation.id = :stationId AND ma.date > :from AND ma.date <:to ORDER BY ma.date")
				.setParameter("stationId", stationId).setParameter("from", from).setParameter("to", to).getResultList();
		return list;

	}

	@Override
	public List<AverageMeasurementOneHour> loadAvgMeasurements(long stationId, byte microphoneNumber, long from, long to) {
		@SuppressWarnings("unchecked")
		List<AverageMeasurementOneHour> list = em
				.createQuery(
						"SELECT ma FROM AverageMeasurementOneHour ma WHERE ma.measureStation.id = :stationId AND ma.date > :from AND ma.date <:to ORDER BY ma.date")
				.setParameter("stationId", stationId).setParameter("from", from).setParameter("to", to).getResultList();
		return list;
	}

	public short loadAvgMeasurementValue(long stationId, long from, long to) {
		Double ret = (Double) em
				.createQuery(
						"SELECT AVG(ma.dbAinCentis) FROM Measurement ma WHERE ma.measureStation.id = :stationId AND ma.date > :from AND ma.date <:to")
				.setParameter("stationId", stationId).setParameter("from", from).setParameter("to", to).getSingleResult();
		if (null == ret) {
			return 0;
		}
		ret += 0.5; // to avoid math round
		return ret.shortValue();
	}

	@Override
	public short loadAvgMeasurementValue(long stationId, byte port, long scopeFrom, long scopeTo) {
		Double ret = (Double) em
				.createQuery(
						"SELECT AVG(ma.dbAinCentis) FROM Measurement ma WHERE ma.measureStation.id = :stationId AND ma.port =:port AND ma.date > :from AND ma.date <:to")
				.setParameter("stationId", stationId).setParameter("port", port).setParameter("from", scopeFrom)
				.setParameter("to", scopeTo).getSingleResult();
		if (null == ret) {
			return 0;
		}
		ret += 0.5; // to avoid math round
		return ret.shortValue();
	}

	@Override
	public void saveAvgMeasurementOnehour(AverageMeasurementOneHour avg) {
		em.persist(avg);
	}

}
