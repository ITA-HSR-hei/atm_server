package ch.cnlab.atm.domain;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeographicalCoordinatesTest {
GeographicalCoordinates coor;
	@Before
	public void setUp() throws Exception {
		coor =new GeographicalCoordinates();
		coor.setLatitude("47.22370692");
		coor.setLongitude("8.81762266");
		coor.setMetersAboveSea(403);
	}

	@After
	public void tearDown() throws Exception {
	}
@Test
public void testFactory(){
	
	GeographicalCoordinates coordinates = new GeographicalCoordinates();
	coordinates.setLongitude("long");
	coordinates.setLatitude("lat");
	coordinates.setMetersAboveSea(42);
	
	Assert.assertEquals("long", coor.getLongitude());
	Assert.assertEquals("lat", coor.getLatitude());
	Assert.assertEquals(42, coor.getMetersAboveSea());
	
}
	@Test
	public void testGetLongitude() {
		Assert.assertEquals("8.81762266", coor.getLongitude());
	}

	@Test
	public void testGetLatitude() {
		Assert.assertEquals("47.22370692", coor.getLatitude());
	}

	@Test
	public void testGetMetersAboveSea() {
		Assert.assertEquals(403, coor.getMetersAboveSea());
	}

	@Test
	public void testSetLongitude() {
		coor.setLongitude("34.508523");
		Assert.assertEquals("34.508523", coor.getLongitude());
	}

	@Test
	public void testSetLatitude() {
		coor.setLatitude("-8.783195");
		Assert.assertEquals("-8.783195", coor.getLatitude());
	}

	@Test
	public void testSetMetersAboveSea() {
		coor.setMetersAboveSea(8848);
		Assert.assertEquals(8848, coor.getMetersAboveSea());
	}

}
