package ch.cnlab.atm.scheduling;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.cnlab.atm.domain.MeasureStation;
import ch.cnlab.atm.domain.Measurement;

public class CronServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetLastHour() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(GregorianCalendar.MINUTE, 0);
		cal.set(GregorianCalendar.SECOND, 0);
		cal.set(GregorianCalendar.MILLISECOND, 0);
		cal.set(GregorianCalendar.HOUR, 0);
		Assert.assertEquals(cal.getTimeInMillis() - 3600 * 1000, CronServiceImpl.getLastHour());

	}

	@Test
	public void testgetMeasurement() {
		MeasureStation ms = new MeasureStation();
		ms.setId(-5L);
		Measurement m = CronServiceImpl.getMeasurement(0, ms, 1);
		Assert.assertEquals(-5L, (long) m.getMeasureStation().getId());
		Assert.assertEquals(1, m.getPort());
		Assert.assertEquals(0, m.getDate());
	}
}