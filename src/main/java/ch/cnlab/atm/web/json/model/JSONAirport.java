package ch.cnlab.atm.web.json.model;

import java.util.LinkedList;

import ch.cnlab.atm.domain.Airport;
import ch.cnlab.atm.domain.Flightroute;
import ch.cnlab.atm.domain.GeographicalCoordinates;

public class JSONAirport extends JSONClass {
	private static final long serialVersionUID = 5189319730902346951L;
	private final static JSONAirport EMPTY = new JSONAirport();
	private int id;
	private String description;

	private GeographicalCoordinates coordinates;

	private JSONFlightroute[] fligthroutes = new JSONFlightroute[0];

	public JSONAirport() {
	}

	public JSONAirport(Airport airport) {
		setId(airport.getId());
		setDescription(airport.getDescription());
		if (airport.getCoordinates()!=null) {
			setCoordinates(airport.getCoordinates());
		}
		if (airport.getFlightroutes().isPresent()) {
			LinkedList<JSONFlightroute> tempList = new LinkedList<JSONFlightroute>();

			for (Flightroute fl : airport.getFlightroutes().get()) {
				tempList.add(new JSONFlightroute(fl));
			}
			fligthroutes = tempList.toArray(new JSONFlightroute[tempList.size()]);
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GeographicalCoordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(GeographicalCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	public JSONFlightroute[] getFligthroutes() {
		return fligthroutes;
	}

	public void setFligthroutes(JSONFlightroute[] fligthroutes) {
		this.fligthroutes = fligthroutes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static JSONAirport empty() {
		return EMPTY;
	}
}
