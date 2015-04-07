package ch.cnlab.atm.service;

import java.util.List;

import ch.cnlab.atm.domain.AverageMeasurementOneHour;
import ch.cnlab.atm.domain.Measurement;

public interface MeasurementService {

	public void saveMeasurement(long stationId, Measurement measurement);

	public List<Measurement> loadMeasurements(long stationId, long from, long to);

	public short loadAvgMeasurements(long stationId, byte port, long from, long to);

	public void saveAverageMeasurementOneHour(AverageMeasurementOneHour avg);
}
