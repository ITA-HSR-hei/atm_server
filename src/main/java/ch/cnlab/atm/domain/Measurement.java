package ch.cnlab.atm.domain;

import java.util.concurrent.Future;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.Preconditions;

@Entity
@Table(name = "measurements")
public class Measurement extends DomainObject<Long> {

	// --------------------------------------
	// Constants
	// --------------------------------------

	private static final long serialVersionUID = -4479889343742528255L;
	// --------------------------------------
	// Members
	// --------------------------------------

	@Column(nullable = false)
	private long date;

	@Column(nullable = false)
	private short dbAinCentis;

	@Column(nullable = false)
	private byte port;

	@ManyToOne
	private MeasureStation measureStation;

	// for async data filling
	@Transient
	private Future<Integer> soundLevelFuture;

	// --------------------------------------
	// Methods
	// --------------------------------------
	@Override
	/**
	 * @return a String, may null if the value is zero. if not Decibel with a . as seperator
	 */
	public String toString() {
		if (dbAinCentis < 10) {
			if (dbAinCentis <= 0) {
				return "0";
			}
			return "0." + dbAinCentis;
		}
		String ret = dbAinCentis + "";
		return ret.substring(0, ret.length() - 1) + "." + ret.substring(ret.length() - 1, ret.length());
	}

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	/**
	 * returns an optional of a date.
	 * 
	 * @return
	 */
	public long getDate() {
		return date;
	}

	/**
	 * set the date when soundlevel was captured
	 * 
	 * @param date
	 */
	public void setDate(long date) {
		Preconditions.checkArgument(0 <= date);
		this.date = date;
	}

	/**
	 * The Soundlevel in centi db(A).
	 * 
	 * @return
	 */
	public short getSoundLevel() {

		return dbAinCentis;
	}

	/**
	 * Set Soundlevel, cannot be negativ.
	 * 
	 * @param soundLevel
	 *            in centi db(A)
	 */
	public void setSoundLevel(short soundLevel) {
		Preconditions.checkArgument(0 <= soundLevel, "soundLevel can not be lower than 0");
		this.dbAinCentis = soundLevel;
	}

	/**
	 * The Soundlevel in centi db(A).
	 * 
	 * @return
	 */
	public byte getPort() {
		return port;
	}

	/**
	 * Set Soundlevel, cannot be negativ.
	 * 
	 * @param soundLevel
	 *            in centi db(A)
	 */
	public void setPort(byte port) {
		Preconditions.checkArgument(0 <= port && port <= 3);
		this.port = port;
	}

	public void setMeasureStation(MeasureStation measureStation) {
		Preconditions.checkNotNull(measureStation);
		this.measureStation = measureStation;
	}

	public MeasureStation getMeasureStation() {
		return this.measureStation;
	}

	public void setSoundLevel(Future<Integer> soundLevel) {
		this.soundLevelFuture = soundLevel;
	}

}
