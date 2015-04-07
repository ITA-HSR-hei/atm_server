package ch.cnlab.atm.web.controller.v0;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.cnlab.atm.domain.MeasureStation;
import ch.cnlab.atm.service.MeasureStationService;

public class ChartDataControllerV0Test {
    ChartDataControllerV0 controller;
    MeasureStationService mss = new MeasureStationService() {

	@Override
	public void saveMeasureStation(MeasureStation measureStation) {

	}


	@Override
	public MeasureStation loadMeasureStaionById(Long id) {
	    return null;
	}

	@Override
	public List<MeasureStation> loadMeasureStations() {
	    List<MeasureStation> ret = new LinkedList<MeasureStation>();
	    MeasureStation mStation = new MeasureStation();
	    mStation.setId(42L);
	    mStation.setStationName("test");
	    ret.add(mStation);
	    return ret;
	}



	@Override
	public void createMeasureStationWithMAC(String macAddress) {
	    // TODO Auto-generated method stub
	    
	}


	@Override
	public MeasureStation loadMeasureStaionByMacAdress(String macAddress) {
		// TODO Auto-generated method stub
		return null;
	}
    };

    @Before
    public void setUp() throws Exception {
	controller = new ChartDataControllerV0();
	controller.setMeasureStationService(mss);
    }

    @After
    public void tearDown() throws Exception {
    }

    /*
    @Test
    public void testSendStationsList() throws Exception {
	int i = 0;
	for (MeasureStation m : mss.getMeasureStations()) {
	    JSONStation genStation = controller.sendStationsList().call().getStationList().get(i);
	    Assert.assertEquals(m.getStationName(), genStation.getName());
	    Assert.assertEquals((Long) m.getId(), (Long) genStation.getId());

	}
    }
*/
    @Test
    public void testRetrievData() {
	// todo controller.retrievData(7L, 23, 42);
    }

    @Test
    public void testSendStationsData() {
	// todo
    }

}
