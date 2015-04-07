package ch.cnlab.atm.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Entity
@Table(name = "airports")
public class Airport extends DomainObject<Integer> {

	// --------------------------------------
	// Constants
	// --------------------------------------
	private static final long serialVersionUID = 6813736863623150556L;

	// --------------------------------------
	// Members
	// --------------------------------------

	@Column(name = "description", nullable = false)
	private String description;

	@Column(nullable = false)
	@Embedded
	private GeographicalCoordinates coordinates;

	@OneToMany(mappedBy = "airport", fetch = FetchType.EAGER)
	private List<Flightroute> flightrouteList;

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public String getDescription() {
		return Strings.nullToEmpty(description);
	}

	public GeographicalCoordinates getCoordinates() {
		return coordinates;
	}

	public Optional<List<Flightroute>> getFlightroutes() {
		return Optional.fromNullable(flightrouteList);
	}

	public void setDescription(String description) {
		Preconditions.checkNotNull(description);
		this.description = description;
	}

	public void setCoordinates(GeographicalCoordinates coordinates) {
		Preconditions.checkNotNull(coordinates);
		this.coordinates = coordinates;
	}

	public boolean addFlightroute(Flightroute flightroute) {
		Preconditions.checkNotNull(flightroute);
		return flightrouteList.add(flightroute);
	}

	public boolean removeFlightroute(Flightroute flightroute) {
		Preconditions.checkNotNull(flightroute);
		return flightrouteList.remove(flightroute);
	}
}
