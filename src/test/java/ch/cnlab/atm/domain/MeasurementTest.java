package ch.cnlab.atm.domain;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MeasurementTest {
	final static long timestamp = System.currentTimeMillis();
	final static short soundlevel = 150;
	Measurement m;

	@Before
	public void setUp() throws Exception {
		m = new Measurement();
		m.setDate(timestamp);
		m.setSoundLevel(soundlevel);
		m.setPort((byte) 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDate() {
		Assert.assertEquals(timestamp, m.getDate());
	}

	@Test
	public void testSetDate() {
		m.setDate(timestamp + 100);
		Assert.assertEquals(timestamp + 100, m.getDate());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetInvalidDate() {
		m.setDate(-2);
		Assert.fail("should throw exception");

	}

	@Test
	public void testGetPort() {
		Assert.assertEquals(1, m.getPort());
	}

	@Test
	public void testSetPort() {
		Assert.assertEquals(1, m.getPort());
		m.setPort((byte) 2);
		Assert.assertEquals(2, m.getPort());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNegativPort() {
		m.setPort(Byte.MIN_VALUE);
		Assert.fail("should throw exception");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetToHighPort() {
		m.setPort(Byte.MAX_VALUE);
		Assert.fail("should throw exception");
	}

	@Test
	public void testGetSoundLevel() {
		Assert.assertEquals(soundlevel, m.getSoundLevel());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNegativSoundLevel() {
		m.setSoundLevel((short) -10);
		Assert.fail("should throw exception");

	}

	@Test
	public void testSetMaxSoundLevel() {
		m.setSoundLevel(Short.MAX_VALUE);
		Assert.assertEquals(Short.MAX_VALUE, m.getSoundLevel());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMinSoundLevel() {
		m.setSoundLevel(Short.MIN_VALUE);
		Assert.fail("should throw exception");
	}

	@Test
	public void testSetSoundLevel() {
		short soundLevel = 2000;
		m.setSoundLevel(soundLevel);
		Assert.assertEquals(2000, m.getSoundLevel());
	}

	@Test
	public void testToStringLevel() {

		short soundLevel1 = 2000;
		m.setSoundLevel(soundLevel1);
		Assert.assertEquals("200.0", m.toString());

		short soundLevel2 = 234;
		m.setSoundLevel(soundLevel2);
		Assert.assertEquals("23.4", m.toString());

		short soundLevel3 = 583;
		m.setSoundLevel(soundLevel3);
		Assert.assertEquals("58.3", m.toString());
	}

	@Test
	public void testToStringLowLevel() {
		short soundLevel = 2;
		m.setSoundLevel(soundLevel);
		Assert.assertEquals("0.2", m.toString());
	}

	@Test
	public void testToStringZero() {
		short soundLevel = 0;
		m.setSoundLevel(soundLevel);
		Assert.assertEquals("0", m.toString());
	}

	@Test
	public void testToStringNull() {
		m = new Measurement();
		m.setSoundLevel(null);
		Assert.assertEquals("0", m.toString());
	}

	@Test
	public void testToStringHi() {
		short soundLevel = 10000;
		m.setSoundLevel(soundLevel);
		Assert.assertEquals("1000.0", m.toString());
	}
}
