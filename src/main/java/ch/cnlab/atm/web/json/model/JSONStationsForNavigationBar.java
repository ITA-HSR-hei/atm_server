package ch.cnlab.atm.web.json.model;

public class JSONStationsForNavigationBar extends JSONClass {
	private static final long serialVersionUID = -9027090369542923720L;

	private JSONAirport[] airports;

	public JSONStationsForNavigationBar() {
	}

	public JSONAirport[] getAirports() {
		return airports;
	}

	public void setAirports(JSONAirport[] airports) {
		this.airports = airports;
	}

}
