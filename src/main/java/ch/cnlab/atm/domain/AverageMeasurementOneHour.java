package ch.cnlab.atm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ch.cnlab.atm.util.Constants;

@Entity
@Table(name = "measurements_average_one_hour")
public class AverageMeasurementOneHour extends DomainObject<Long> {
	private static final long serialVersionUID = 2462347476074280417L;
	@Transient
	private final static long interval = Constants.HOUR;
	@Column(nullable = false)
	private long date;

	@Column(nullable = false)
	private short dbAinCentis;

	@Column(nullable = false)
	private byte port;

	@ManyToOne
	private MeasureStation measureStation;

	public long getInterval() {
		return interval;
	}

	public long getDate() {
		return date + interval;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public short getDbAinCentis() {
		return dbAinCentis;
	}

	public void setDbAinCentis(short dbAinCentis) {
		this.dbAinCentis = dbAinCentis;
	}

	public byte getPort() {
		return port;
	}

	public void setPort(byte port) {
		this.port = port;
	}

	public MeasureStation getMeasureStation() {
		return measureStation;
	}

	public void setMeasureStation(MeasureStation measureStation) {
		this.measureStation = measureStation;
	}

	/**
	 * Method for easyier creation of Avg Measurements
	 * 
	 * @param m
	 *            a Measurement with station, date and port set
	 * @param avgDba
	 *            the avg Dba of the period
	 * @return
	 */
	public static AverageMeasurementOneHour averageMeasurementOneHour(Measurement m, short avgDba) {
		AverageMeasurementOneHour avg = new AverageMeasurementOneHour();
		avg.setDate(m.getDate());
		avg.setMeasureStation(m.getMeasureStation());
		avg.setPort(m.getPort());
		avg.setDbAinCentis(avgDba);
		return avg;
	}

	public Measurement toMeasurement() {
		Measurement m = new Measurement();
		m.setDate(getDate());
		m.setMeasureStation(getMeasureStation());
		m.setPort(getPort());
		m.setSoundLevel(getDbAinCentis());
		return m;
	}
}
