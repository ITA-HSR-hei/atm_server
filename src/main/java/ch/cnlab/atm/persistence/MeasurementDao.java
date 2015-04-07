package ch.cnlab.atm.persistence;

import java.util.List;

import ch.cnlab.atm.domain.AverageMeasurementOneHour;
import ch.cnlab.atm.domain.Measurement;

public interface MeasurementDao {

	public void saveMeasurement(Measurement measurement);

	public List<Measurement> loadMeasurements(long stationId, long from, long to);

	public List<AverageMeasurementOneHour> loadAvgMeasurements(long stationId, byte microphoneNumber, long from, long to);

	public short loadAvgMeasurementValue(long stationId, byte port, long scopeFrom, long scopeTo);

	public void saveAvgMeasurementOnehour(AverageMeasurementOneHour avg);

}
