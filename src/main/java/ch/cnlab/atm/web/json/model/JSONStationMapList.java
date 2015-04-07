package ch.cnlab.atm.web.json.model;

import java.util.LinkedList;
import java.util.List;

public class JSONStationMapList {
	
	private List<JSONStationMap> stationMapList = new LinkedList<JSONStationMap>();

	public List<JSONStationMap> getStationMapList() {
		return stationMapList;
	}

	public void setStationMapList(List<JSONStationMap> stationMapList) {
		this.stationMapList = stationMapList;
	}

	public void add(JSONStationMap jsonMapData) {
		stationMapList.add(jsonMapData);
		
	}
	
	
	
	

}
