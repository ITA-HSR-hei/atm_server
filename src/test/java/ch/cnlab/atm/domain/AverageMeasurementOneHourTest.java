package ch.cnlab.atm.domain;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.cnlab.atm.util.Constants;

public class AverageMeasurementOneHourTest {
	AverageMeasurementOneHour avgM;
	MeasureStation ms = new MeasureStation();

	@Before
	public void setUp() throws Exception {
		avgM = new AverageMeasurementOneHour();
		avgM.setDate(123);
		avgM.setDbAinCentis((short) 980);
		avgM.setMeasureStation(ms);
		avgM.setPort((byte) 0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInterval() {
		Assert.assertEquals(Constants.HOUR, avgM.getInterval());
	}

	@Test
	public void testGetDate() {
		Assert.assertEquals(123 + Constants.HOUR, avgM.getDate());

	}

	@Test
	public void testSetDate() {
		Assert.assertEquals(123 + Constants.HOUR, avgM.getDate());
		long now = System.currentTimeMillis();
		avgM.setDate(now);
		Assert.assertEquals(now + Constants.HOUR, avgM.getDate());
	}

	@Test
	public void testGetDbAinCentis() {
		Assert.assertEquals(980, avgM.getDbAinCentis());
	}

	@Test
	public void testSetDbAinCentis() {
		Assert.assertEquals(980, avgM.getDbAinCentis());
		avgM.setDbAinCentis((short) 0);
		Assert.assertEquals(0, avgM.getDbAinCentis());
	}

	@Test
	public void testGetPort() {
		Assert.assertEquals(0, avgM.getPort());
	}

	@Test
	public void testSetPort() {
		Assert.assertEquals(0, avgM.getPort());
		avgM.setPort((byte) 3);
		Assert.assertEquals(3, avgM.getPort());
	}

	@Test
	public void testGetMeasureStation() {
		Assert.assertEquals(ms, avgM.getMeasureStation());
	}

	@Test
	public void testSetMeasureStation() {
		Assert.assertEquals(ms, avgM.getMeasureStation());
		MeasureStation newStation = new MeasureStation();
		avgM.setMeasureStation(newStation);
		Assert.assertEquals(newStation, avgM.getMeasureStation());
	}

	@Test
	public void testAverageMeasurementOneHour() {
		short soundLevel = (short) 555;
		Measurement measurment = new Measurement();
		measurment.setMeasureStation(ms);
		measurment.setPort((byte) 2);
		measurment.setSoundLevel((short) 666);

		AverageMeasurementOneHour avg = new AverageMeasurementOneHour();
		avg.setDate(measurment.getDate());
		avg.setMeasureStation(measurment.getMeasureStation());
		avg.setPort(measurment.getPort());
		avg.setDbAinCentis(soundLevel);

		AverageMeasurementOneHour avgGen = AverageMeasurementOneHour.averageMeasurementOneHour(measurment, soundLevel);
		Assert.assertEquals(avg.getDate(), avgGen.getDate());
		Assert.assertEquals(avg.getDbAinCentis(), avgGen.getDbAinCentis());
		Assert.assertEquals(avg.getInterval(), avgGen.getInterval());
		Assert.assertEquals(avg.getPort(), avgGen.getPort());
		Assert.assertEquals(avg.getMeasureStation(), avgGen.getMeasureStation());
	}

	@Test
	public void testToMeasurement() {
		Measurement m = avgM.toMeasurement();
		Assert.assertEquals(123 + Constants.HOUR, m.getDate());
		Assert.assertEquals(ms, m.getMeasureStation());
		Assert.assertEquals((byte) 0, m.getPort());
		Assert.assertEquals((short) 980, m.getSoundLevel());
	}
}
