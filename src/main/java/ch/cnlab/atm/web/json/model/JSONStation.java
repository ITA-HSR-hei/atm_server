package ch.cnlab.atm.web.json.model;

import ch.cnlab.atm.domain.MeasureStation;

/**
 * Modell for JSON
 * 
 * @author stone
 * 
 */
public class JSONStation extends JSONClass {
	private static final long serialVersionUID = -7307355666466035812L;
	public final static JSONStation EMPTY = new JSONStation();
	
	private long id;
	private String name;

	public JSONStation() {
	}

	public JSONStation(long id, String name) {
		this.name = name;
		this.id = id;
	}

	public JSONStation(MeasureStation m) {
		this.name = m.getStationName();
		this.id = m.getId();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
