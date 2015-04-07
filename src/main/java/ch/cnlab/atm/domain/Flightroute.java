package ch.cnlab.atm.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * Class representing a flightroute
 * 
 * @author plangens
 */
@Entity
@Table(name = "flightroutes")
public class Flightroute extends DomainObject<Integer> {

	// --------------------------------------
	// Constants
	// --------------------------------------
	private static final long serialVersionUID = 7972680104229271042L;

	// --------------------------------------
	// Members
	// --------------------------------------

	@Column(name = "description", nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<MeasureStation> measureStation;

	@ManyToOne
	private Airport airport;

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	/**
	 * Get description or the name of the Flightroute.
	 * 
	 * @return the description or the name
	 */
	public String getDescription() {
		return Strings.nullToEmpty(description);
	}

	/**
	 * Get description or the name of the Flightroute.
	 * 
	 * @param description
	 *            the description or the name
	 */
	public void setDescription(String description) {
		Preconditions.checkNotNull(description);
		this.description = description;
	}

	public List<MeasureStation> getMeasureStation() {
		if (measureStation == null) {
			return new LinkedList<MeasureStation>();
		}
		return new LinkedList<MeasureStation>(measureStation);
	}

}
