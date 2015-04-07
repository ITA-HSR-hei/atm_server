package ch.cnlab.atm.util;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.cnlab.atm.domain.Measurement;

public class MeasurementsToDoubleArray {
    List<Measurement> mList;

    @Before
    public void setUp() throws Exception {
	mList = new LinkedList<Measurement>();
	for (short i = 1000; i >= 0; i--) {
	    Measurement m = new Measurement();
	    m.setSoundLevel(i);
	    mList.add(m);
	}
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMeasurementsToDoubleArray() {
	Double[] arr = GUI.measurementsToDoubleArray(mList);
	for (int i = 0; i < arr.length - 1; i++) {
	    Assert.assertEquals("at entry " + i, mList.get(i).getSoundLevel(), ((Double) (arr[i] * 10d)).intValue());
	}
	Assert.assertEquals(null, arr[1000]);
    }
}
