package ch.cnlab.atm.web.json.model;

import ch.cnlab.atm.domain.Flightroute;
import ch.cnlab.atm.domain.MeasureStation;

public class JSONFlightroute {
	private final static JSONFlightroute EMPTY = new JSONFlightroute();

	private long id;
	private String description;

	private JSONStation[] measureStations;;

	public JSONFlightroute() {

	}

	public JSONFlightroute(Flightroute fl) {
		setId(fl.getId());
		description = fl.getDescription();
		measureStations = new JSONStation[fl.getMeasureStation().size()];
		int i = 0;
		for (MeasureStation m : fl.getMeasureStation()) {
			measureStations[i++] = new JSONStation(m);
		}

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JSONStation[] getMeasureStations() {
		return measureStations;
	}

	public void setMeasureStations(JSONStation[] measureStations) {
		this.measureStations = measureStations;
	}

	public static JSONFlightroute empty() {
		return EMPTY;
	}
}
