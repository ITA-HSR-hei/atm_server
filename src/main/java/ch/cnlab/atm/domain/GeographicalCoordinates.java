package ch.cnlab.atm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Embeddable
public class GeographicalCoordinates implements Serializable {

	// --------------------------------------
	// Constants
	// --------------------------------------

	private static final long serialVersionUID = -8195929689721551528L;

	// --------------------------------------
	// Members
	// --------------------------------------

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "metersAboveSea")
	private int metersAboveSea;

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public String getLongitude() {
		return Strings.nullToEmpty(longitude);
	}

	public String getLatitude() {
		return Strings.nullToEmpty(latitude);
	}

	public int getMetersAboveSea() {
		return metersAboveSea;
	}

	public void setLongitude(String longitude) {
		Preconditions.checkNotNull(longitude);
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		Preconditions.checkNotNull(latitude);
		this.latitude = latitude;
	}

	public void setMetersAboveSea(int metersAboveSea) {
		this.metersAboveSea = metersAboveSea;
	}

	
}
