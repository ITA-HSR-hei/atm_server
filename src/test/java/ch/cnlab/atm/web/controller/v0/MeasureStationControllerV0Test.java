package ch.cnlab.atm.web.controller.v0;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ch.cnlab.atm.domain.DataServer;
import ch.cnlab.atm.domain.MeasureStation;
import ch.cnlab.atm.domain.MeasureStationConfiguration;
import ch.cnlab.atm.service.MeasureStationService;
import ch.cnlab.atm.service.MeasurementService;
import ch.cnlab.atm.web.json.model.JSONDataServer;
import ch.cnlab.atm.web.json.model.JSONStationConfig;

public class MeasureStationControllerV0Test {
	MeasureStationControllerV0 controller;
	MeasureStation ms = new MeasureStation();
	MeasureStationConfiguration config = new MeasureStationConfiguration();
	DataServer dataserver = new DataServer();

	final static String DS_NAME = "test";
	final static String DS_URL = "http://www.test.ch";

	@Before
	public void setUp() throws Exception {
		ms.setId(1l);
		ms.setStationName("muster");

		List<DataServer> list = new LinkedList<DataServer>();
		dataserver.setId(1l);
		dataserver.setName(DS_NAME);
		dataserver.setUrl(DS_URL);
		list.add(dataserver);
		config.setDataServers(list);
		ms.setConfiguration(config);
		MeasureStationService measureStationService = mock(MeasureStationService.class);
		when(measureStationService.loadMeasureStaionById(1l)).thenReturn(ms);
		MeasurementService measurementService = mock(MeasurementService.class);
		controller = new MeasureStationControllerV0(measureStationService, measurementService);
	}

	@Test
	public void testGetConfigForStation() {
		JSONStationConfig conf = controller.getConfigForStation(1l);
		JSONDataServer ds = conf.getDataServers().get(0);
		Assert.assertEquals(DS_NAME, ds.getName());
		Assert.assertEquals(DS_URL, ds.getUrl());
	}

}
