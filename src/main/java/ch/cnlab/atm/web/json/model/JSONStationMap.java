package ch.cnlab.atm.web.json.model;

public class JSONStationMap {
	
	private long stationId;
	private String stationName;
	private String latitude;
	private String longitude;
	private int metersAboveSea;
	
	public long getStationId() {
		return stationId;
	}
	public void setStationId(long stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getMetersAboveSea() {
		return metersAboveSea;
	}
	public void setMetersAboveSea(int metersAboveSea) {
		this.metersAboveSea = metersAboveSea;
	}
	
}
