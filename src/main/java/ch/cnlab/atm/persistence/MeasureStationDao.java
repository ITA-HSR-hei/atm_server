package ch.cnlab.atm.persistence;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;

import ch.cnlab.atm.domain.MeasureStation;

public interface MeasureStationDao {

	public void saveMeasureStation(MeasureStation measureStation);

	public MeasureStation loadMeasureStationById(Long id);

	public List<MeasureStation> loadAllMeasureStations();

	/**
	 * loads the measure station with the specified mac adress
	 * 
	 * @param macAddress
	 * @return
	 * @throws NoResultException
	 *             if no station was found
	 * @throws NonUniqueResultException
	 *             if two stations with the same mac where found
	 */
	public MeasureStation loadMeasureStationByMac(String macAddress) throws NoResultException, NonUniqueResultException;

}
