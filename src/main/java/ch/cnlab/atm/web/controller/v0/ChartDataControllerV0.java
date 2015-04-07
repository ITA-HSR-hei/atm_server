package ch.cnlab.atm.web.controller.v0;

import java.util.LinkedList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.cnlab.atm.domain.Airport;
import ch.cnlab.atm.domain.MeasureStation;
import ch.cnlab.atm.domain.Measurement;
import ch.cnlab.atm.service.AirportService;
import ch.cnlab.atm.service.MeasureStationService;
import ch.cnlab.atm.service.MeasurementService;
import ch.cnlab.atm.util.Constants;
import ch.cnlab.atm.util.GUI;
import ch.cnlab.atm.web.json.model.JSONAirport;
import ch.cnlab.atm.web.json.model.JSONStationMap;
import ch.cnlab.atm.web.json.model.JSONStation;
import ch.cnlab.atm.web.json.model.JSONStationDataWithTimestamp;
import ch.cnlab.atm.web.json.model.JSONStationData;
import ch.cnlab.atm.web.json.model.JSONStationMapList;
import ch.cnlab.atm.web.json.model.JSONStationsForNavigationBar;
import ch.cnlab.atm.web.json.model.JSONStationList;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Controller
@RequestMapping(value = "/public/" + Version.VERSION)
public class ChartDataControllerV0 {
	private static final Logger LOGGER = Logger.getLogger(ChartDataControllerV0.class);

	private final static long GFS_INTERVALL = 600;
	@Autowired
	private AirportService airportService;

	@Autowired
	private MeasurementService measurementService;

	@Autowired
	private MeasureStationService measureStationService;

	@RequestMapping(value = "getStations", method = RequestMethod.GET)
	public @ResponseBody JSONStationList getStations() {
		
		JSONStationList stationList = new JSONStationList();
		for (MeasureStation ms : measureStationService.loadMeasureStations()) {
			if (!Strings.isNullOrEmpty(ms.getStationName())) {
				stationList.add(new JSONStation(ms.getId(), ms.getStationName()));
			}
		}
		
		return stationList;
	}

	@RequestMapping(value = "getChartDataWithTimestamp", method = RequestMethod.GET)
	public @ResponseBody JSONStationDataWithTimestamp getChartDataWithTimestamp(@RequestParam long stationId, @RequestParam long timestampFrom, @RequestParam long timestampTo) {

		Preconditions.checkArgument(timestampFrom <= timestampTo, "from: " + timestampFrom + " has to be befor to: " + timestampTo);

		MeasureStation measureStation = measureStationService.loadMeasureStaionById(stationId);

		List<Measurement> measurements = measurementService.loadMeasurements(stationId, timestampFrom, timestampTo);
		
		Object[][] data = GUI.measurementsToObjectArray(measurements);

		JSONStationDataWithTimestamp stationData = new JSONStationDataWithTimestamp();
		stationData.setId(measureStation.getId());
		stationData.setName(measureStation.getStationName());
		stationData.setDateFrom(timestampFrom);
		stationData.setDateTo(timestampTo);
		stationData.setData(data);

		return stationData;
	}
	
	
	
	@RequestMapping(value = "getChartData", method = RequestMethod.GET)
	public @ResponseBody
	JSONStationData getChartData(@RequestParam long stationId, @RequestParam long timestampFrom, @RequestParam long timestampTo) {

		Preconditions.checkArgument(timestampFrom <= timestampTo, "from: " + timestampFrom + " has to be befor to: " + timestampTo);

		MeasureStation measureStation = measureStationService.loadMeasureStaionById(stationId);

		List<Measurement> measurements = measurementService.loadMeasurements(stationId, timestampFrom, timestampTo);
		
		Double[] newData = GUI.convertMeasurementsToIntervalArray(measurements, timestampFrom, timestampTo);

		JSONStationData stationData = new JSONStationData();
		stationData.setId(measureStation.getId());
		stationData.setName(measureStation.getStationName());
		stationData.setPointInterval(1000);
		stationData.setPointStart(timestampFrom);
		stationData.setData(newData);
		
		

		return stationData;
	}
	
	
	
	
	@RequestMapping(value = "getMapList", method = RequestMethod.GET)
	public @ResponseBody JSONStationMapList getMapList(){
		
		JSONStationMapList stationMapList = new JSONStationMapList();
		
		List<MeasureStation> stations = measureStationService.loadMeasureStations();
		
		for(MeasureStation m: stations){
			
			JSONStationMap jsonMapData = new JSONStationMap();
			jsonMapData.setStationId(m.getId());
			jsonMapData.setStationName(m.getStationName());
			
			
			if(m.getCoordinates()!=null){
				jsonMapData.setLatitude(m.getCoordinates().getLatitude());
				jsonMapData.setLongitude(m.getCoordinates().getLongitude());
				jsonMapData.setMetersAboveSea(m.getCoordinates().getMetersAboveSea());
				stationMapList.add(jsonMapData);
			}
		}
		
		return stationMapList;
	}
	
	
	@RequestMapping(value = "getStationsForNavigationBar", method = RequestMethod.GET)
	public @ResponseBody
	JSONStationsForNavigationBar sendStationsForNavigationBar() {

		LinkedList<JSONAirport> tempList = new LinkedList<JSONAirport>();

		for (Airport airport : airportService.loadAllAirports()) {
			tempList.add(new JSONAirport(airport));
		}

		JSONStationsForNavigationBar naviBar = new JSONStationsForNavigationBar();
		naviBar.setAirports(tempList.toArray(new JSONAirport[tempList.size()]));
		return naviBar;
	}

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------
	public void setMeasureStationService(MeasureStationService service) {
		measureStationService = service;
	}

}
