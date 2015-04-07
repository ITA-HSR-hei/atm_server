package ch.cnlab.atm.service;

import java.util.List;

import ch.cnlab.atm.domain.MeasureStation;

public interface MeasureStationService {

	public void saveMeasureStation(MeasureStation measureStation);

	public MeasureStation loadMeasureStaionById(Long id);

	public List<MeasureStation> loadMeasureStations();

	/**
	 * Searches the id for specific mac
	 * 
	 * @param macAddress
	 * @return -1 on error
	 */

	public void createMeasureStationWithMAC(String macAddress);

	public MeasureStation loadMeasureStaionByMacAdress(String macAddress);

}
