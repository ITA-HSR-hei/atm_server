package ch.cnlab.atm.domain;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FlightrouteTest {
    Flightroute route;

    @Before
    public void setUp() throws Exception {
	route = new Flightroute();
	route.setDescription("Piste 18");
	route.setId(42);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFlightrouteDescription() {
	Assert.assertEquals("Piste 18", route.getDescription());
	route.setDescription("Piste 19");
	Assert.assertEquals("Piste 19", route.getDescription());
    }

    @Test
    public void testFlightrouteId() {
	Assert.assertEquals(42, (int) route.getId());
	route.setId(23);
	Assert.assertEquals(23, (int) route.getId());
    }

    @Test
    public void testFlightrouteMeasurestation() {
	Assert.assertTrue(route.getMeasureStation().isEmpty());

    }

}
