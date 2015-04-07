package ch.cnlab.atm.web.json.model;

import java.util.LinkedList;
import java.util.List;

public class JSONStationList{
	
	private List<JSONStation> stationList = new LinkedList<JSONStation>();


	public List<JSONStation> getStationList() {
		return stationList;
	}

	public void setStationList(List<JSONStation> stationList) {
		this.stationList = stationList;
	}

	public void add(JSONStation jsonStation) {
		stationList.add(jsonStation);
	}

}
