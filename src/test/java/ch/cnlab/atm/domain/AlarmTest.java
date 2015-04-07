package ch.cnlab.atm.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AlarmTest {

	Alarm alarm;
	User u = new User();
	MeasureStation ms = new MeasureStation();

	@Before
	public void setUp() throws Exception {
		alarm = new Alarm();
		alarm.setDelayInMinutes(10);
		alarm.setName("name");
		alarm.setPriority(0);
		alarm.setUser(u);
		alarm.setMeasureStation(ms);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("name", alarm.getName());
	}

	@Test
	public void testSetName() {
		Assert.assertEquals("name", alarm.getName());
		alarm.setName("foo");
		Assert.assertEquals("foo", alarm.getName());
	}

	@Test
	public void testGetDelayInMinutes() {
		Assert.assertEquals(10, alarm.getDelayInMinutes());
	}

	@Test
	public void testSetDelayInMinutes() {
		Assert.assertEquals(10, alarm.getDelayInMinutes());
		alarm.setDelayInMinutes(42);
		Assert.assertEquals(42, alarm.getDelayInMinutes());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDelayInMinutesNegativ() {
		alarm.setDelayInMinutes(-1);
		Assert.fail("should throw Exception");
	}

	@Test
	public void testGetPriority() {
		Assert.assertEquals(0, alarm.getPriority());
	}

	@Test
	public void testSetPriority() {
		Assert.assertEquals(0, alarm.getPriority());
		alarm.setPriority(1);
		Assert.assertEquals(1, alarm.getPriority());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPriorityNegativ() {
		alarm.setPriority(-1);
		Assert.fail("should throw Exception");
	}

	@Test
	public void testGetUser() {
		Assert.assertEquals(u, alarm.getUser().get());
	}

	@Test
	public void testSetUser() {
		Assert.assertEquals(u, alarm.getUser().get());
		User newUser = new User();
		alarm.setUser(newUser);
		Assert.assertEquals(newUser, alarm.getUser().get());
	}

	@Test
	public void testGetMeasureStation() {
		Assert.assertEquals(ms, alarm.getMeasureStation());
	}

	@Test
	public void testSetMeasureStation() {
		Assert.assertEquals(ms, alarm.getMeasureStation());
		MeasureStation newMs = new MeasureStation();
		alarm.setMeasureStation(newMs);
		Assert.assertEquals(newMs, alarm.getMeasureStation());
	}
}
