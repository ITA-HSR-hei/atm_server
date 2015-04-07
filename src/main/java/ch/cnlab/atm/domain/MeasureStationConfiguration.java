package ch.cnlab.atm.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Entity implementation class for Entity: MesurestationConfiguration
 * 
 */
@Entity
@Table(name = "measurestation_configurations")
public class MeasureStationConfiguration extends DomainObject<Long> {

	// --------------------------------------
	// Constants
	// --------------------------------------
	private static final long serialVersionUID = 4942873757087330208L;

	// --------------------------------------
	// Members
	// --------------------------------------
	@Column
	private int sendIntervalinMinutes;

	@OneToMany(mappedBy = "measureStationConfiguration")
	private List<MicrophonePort> microphonePorts;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "measureStationConfiguration")
	private List<DataServer> dataServers;

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public int getSendIntervalinMinutes() {
		return sendIntervalinMinutes;
	}

	public List<MicrophonePort> getMicrophonePorts() {
		return new ArrayList<MicrophonePort>(microphonePorts);
	}

	public Optional<List<DataServer>> getDataServers() {
		return Optional.of(dataServers);
	}

	public void setSendIntervalinMinutes(int sendIntervallinMinutes) {
		Preconditions.checkArgument(sendIntervallinMinutes >= 0);
		this.sendIntervalinMinutes = sendIntervallinMinutes;
	}

	public void setDataServers(List<DataServer> dataServers) {
		Preconditions.checkNotNull(dataServers);
		this.dataServers = dataServers;
	}

}
