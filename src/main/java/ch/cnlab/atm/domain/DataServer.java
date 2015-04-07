package ch.cnlab.atm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Entity
@Table(name = "dataservers")
public class DataServer extends DomainObject<Long> {

	// --------------------------------------
	// Constants
	// --------------------------------------

	private static final long serialVersionUID = 7096841784770848099L;

	// --------------------------------------
	// Members
	// --------------------------------------

	@Column
	private String name;

	@Column
	private String url;

	@ManyToOne
	private MeasureStationConfiguration measureStationConfiguration;

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public String getName() {
		return Strings.nullToEmpty(name);
	}

	/**
	 * Set the Microphon port, starts with 1 and ends at 4.
	 * 
	 * @param microphoneNumber
	 */
	public void setName(String name) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "Name cant be null or empty");
		this.name = name;
	}

	public String getUrl() {
		return Strings.nullToEmpty(url);
	}

	/**
	 * Set the Microphones offset in 1/10 db(A). Example: for an offset of 1.5
	 * db(A) you'll pass 15.
	 * 
	 * @param offsetDbAinCentis
	 */
	public void setUrl(String url) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(url), "Url cant be null or empty");
		this.url = url;
	}

}
