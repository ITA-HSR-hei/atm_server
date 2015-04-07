package ch.cnlab.atm.domain;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AirportTest {
	Airport airport;
	GeographicalCoordinates coordinates;
	Flightroute flightrouteA;
	Flightroute flightrouteB;

	@Before
	public void setUp() throws Exception {
		airport = new Airport();
		GeographicalCoordinates coordinates = new GeographicalCoordinates();
		coordinates.setLatitude("47.22370692");
		coordinates.setLongitude("8.81762266");
		coordinates.setMetersAboveSea(403);
		
		airport.setCoordinates(coordinates);
		airport.setDescription("TestFlughafen");

		flightrouteA = new Flightroute();
		flightrouteA.setDescription("Anflug A");
		flightrouteB = new Flightroute();
		flightrouteB.setDescription("Anflug B");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDescription() {
		Assert.assertEquals("TestFlughafen", airport.getDescription());
	}

	@Test
	public void testGetCoordinates() {
		Assert.assertEquals(coordinates, airport.getCoordinates());
	}

	@Test
	public void testGetFlightroutes() {
		// TODO Assert.fail("implemnt me");
	}

	@Test
	public void testSetDescription() {
		airport.setDescription("ZHR");
		Assert.assertEquals("ZHR", airport.getDescription());
	}

	@Test
	public void testSetCoordinates() {
		
		GeographicalCoordinates coordinates = new GeographicalCoordinates();
		coordinates.setLatitude("long");
		coordinates.setLongitude("lat");
		coordinates.setMetersAboveSea(5);
		
		GeographicalCoordinates geos = airport.getCoordinates();
		Assert.assertEquals("long", geos.getLongitude());
		Assert.assertEquals("lat", geos.getLatitude());
		Assert.assertEquals(5, geos.getMetersAboveSea());
	}

	@Test
	public void testAddFlightroute() {
		// TODO Assert.fail("implemnt me"); }
	}

	@Test
	public void testRemoveFlightroute() {
		// TODO Assert.fail("implemnt me"); }
	}
}
