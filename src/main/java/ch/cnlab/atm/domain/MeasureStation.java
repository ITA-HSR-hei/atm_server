package ch.cnlab.atm.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Entity
@Table(name = "measurestations")
public class MeasureStation extends DomainObject<Long> {

	// --------------------------------------
	// Constants
	// --------------------------------------

	private static final long serialVersionUID = 131201806707735883L;

	// --------------------------------------
	// Members
	// --------------------------------------

	@Column
	private String stationName;

	@Column
	private String comment;
	@Column
	private String macAddressOrPort;
	@Column
	private boolean isCnlabStation;

	@Embedded
	private GeographicalCoordinates coordinates;

	@OneToOne
	private MeasureStationConfiguration measurestationconfiguration;

	@OneToMany(mappedBy = "measureStation")
	private List<Measurement> measurements;

	@OneToMany(mappedBy = "measureStation")
	private List<Picture> pictureList;

	@ManyToMany(mappedBy = "measureStation")
	private List<Flightroute> flightroutes;

	@OneToMany(mappedBy = "measureStation")
	private List<Alarm> alarms;

	@ManyToOne
	private User user;

	// --------------------------------------
	// Methods
	// --------------------------------------

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public String getStationName() {
		return Strings.nullToEmpty(stationName);
	}

	public void setStationName(String stationName) {
		Preconditions.checkNotNull(stationName);
		this.stationName = stationName;
	}

	public List<Measurement> getMeasurements() {
		return Collections.unmodifiableList(measurements);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMAC() {
		return this.macAddressOrPort;
	}

	public void setMAC(String macAddress) {
		this.macAddressOrPort = macAddress;
	}

	public boolean isCnlabStation() {
		return isCnlabStation;
	}

	public void setCnlabStation(boolean isCnlabStation) {
		this.isCnlabStation = isCnlabStation;
	}

	public GeographicalCoordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(GeographicalCoordinates coordinates) {
		Preconditions.checkNotNull(coordinates);
		this.coordinates = coordinates;
	}

	public Optional<MeasureStationConfiguration> getConfiguration() {
		return Optional.fromNullable(measurestationconfiguration);
	}

	public void setConfiguration(MeasureStationConfiguration measurestationconfiguration) {
		Preconditions.checkNotNull(measurestationconfiguration);
		this.measurestationconfiguration = measurestationconfiguration;
	}
}
