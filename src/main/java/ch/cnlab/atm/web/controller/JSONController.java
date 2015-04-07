package ch.cnlab.atm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.cnlab.atm.service.MeasureStationService;
import ch.cnlab.atm.web.json.model.JSONStationDataWithTimestamp;

@Controller
@RequestMapping("/public/v1")
public class JSONController {

    @Autowired
    private MeasureStationService measureStationService;

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public @ResponseBody
    JSONStationDataWithTimestamp getStationDataInJSON(@PathVariable String name) {

	// Todo add which station to load data from
	// List<Measurement> measurements =
	// measureStationService.loadMeasurementsFromDateRange(null, new Date(),
	// new Date());
	JSONStationDataWithTimestamp stationData = new JSONStationDataWithTimestamp();
	stationData.setName("Zurich");

	String[] data = new String[1];

	data[1] = 23.5 + "";
//	stationData.setData(data);

	return stationData;

    }

}