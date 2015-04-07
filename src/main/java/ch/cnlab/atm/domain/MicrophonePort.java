package ch.cnlab.atm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Preconditions;

@Entity
@Table(name = "microphonports")
public class MicrophonePort extends DomainObject<Long> {

	// --------------------------------------
	// Constants
	// --------------------------------------

	private static final long serialVersionUID = 131201806707735883L;

	// --------------------------------------
	// Members
	// --------------------------------------

	@Column
	private byte microphoneNumber;

	@Column
	private byte offsetDbAinCentis;

	@ManyToOne
	private MeasureStationConfiguration measureStationConfiguration;

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public byte getMicrophoneNumber() {
		return microphoneNumber;
	}

	/**
	 * Set the Microphon port, starts with 0 and ends at 3.
	 * 
	 * @param microphoneNumber
	 */
	public void setMicrophoneNumber(byte microphoneNumber) {
		Preconditions.checkArgument(microphoneNumber >= 0);
		Preconditions.checkArgument(microphoneNumber <= 3);
		this.microphoneNumber = microphoneNumber;
	}

	public int getOffsetInCentiDbA() {
		return offsetDbAinCentis;
	}

	/**
	 * Set the Microphones offset in 1/10 db(A). Example: for an offset of 1.5
	 * db(A) you'll pass 15.
	 * 
	 * @param offsetDbAinCentis
	 */
	public void setOffsetinCentiDba(byte offsetDbAinCentis) {
		Preconditions.checkArgument(offsetDbAinCentis >= 0);
		this.offsetDbAinCentis = offsetDbAinCentis;
	}

}
