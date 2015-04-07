package ch.cnlab.atm.web.controller.v0;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.cnlab.atm.domain.MeasureStation;
import ch.cnlab.atm.domain.Measurement;
import ch.cnlab.atm.service.MeasureStationService;
import ch.cnlab.atm.service.MeasurementService;
import ch.cnlab.atm.web.json.model.JSONMeasurement;
import ch.cnlab.atm.web.json.model.JSONStation;
import ch.cnlab.atm.web.json.model.JSONStationConfig;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Controller
@RequestMapping(value = "/public/" + Version.VERSION)
public class MeasureStationControllerV0 {

	// --------------------------------------
	// Constants
	// --------------------------------------

	private static final Logger LOGGER = Logger.getLogger(MeasureStationControllerV0.class);

	private final static Pattern PATTERN_MAC = Pattern.compile("^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$");

	// --------------------------------------
	// Members
	// --------------------------------------

	private MeasureStationService measureStationService;

	private MeasurementService measurementService;

	// Dependency Injection
	@Autowired
	public MeasureStationControllerV0(MeasureStationService measureStationService, MeasurementService measurementService) {
		this.measureStationService = measureStationService;
		this.measurementService = measurementService;
	}

	// --------------------------------------
	// Methods
	// --------------------------------------

	@RequestMapping(value = "receiveData", method = RequestMethod.POST)
	public String receiveData(@RequestBody final JSONMeasurement receivedMeasurement) {
		Preconditions.checkNotNull(receivedMeasurement, "Soundlevel was null");

		Measurement measurement = new Measurement();
		if (receivedMeasurement.getSoundlevel().contains(".")) {
			measurement.setSoundLevel(Short.parseShort(receivedMeasurement.getSoundlevel().substring(0,
					receivedMeasurement.getSoundlevel().indexOf("."))));
		} else {
			try {
				measurement.setSoundLevel(Short.parseShort(receivedMeasurement.getSoundlevel()));
			} catch (Exception e) {
				LOGGER.error(e);
			}

			if (measurement.getSoundLevel() < 0 || measurement.getSoundLevel() > 2000) {
				LOGGER.error("Soundlevel not parsable or innvalid: " + receivedMeasurement.getSoundlevel());
				throw new IllegalArgumentException("Soundlevel not parsable or invalid: " + receivedMeasurement.getSoundlevel());
			}
		}
		measurement.setDate(receivedMeasurement.getTimestamp());
		// m.setPort();
		measurementService.saveMeasurement(receivedMeasurement.getStationId(), measurement);

		return "datareceived";

	}

	@RequestMapping(value = "getIdFromMac", method = RequestMethod.GET)
	public @ResponseBody
	JSONStation getIDFromMac(@RequestParam String macAddress) {

		Preconditions.checkArgument(!Strings.isNullOrEmpty(macAddress), "MAC-address was null or empty");

		macAddress = macAddress.toUpperCase();
		Matcher m = PATTERN_MAC.matcher(macAddress);
		Preconditions.checkArgument(m.matches(), "MAC doesn't match regular expression: '" + PATTERN_MAC.toString()+"' Passed Mac Address was: '"+macAddress+"'");

		macAddress = macAddress.replaceAll("-", ":");
		MeasureStation measureStation = measureStationService.loadMeasureStaionByMacAdress(macAddress);

		if (measureStation != null) {
			return new JSONStation(measureStation.getId(), measureStation.getStationName());
		}

		measureStationService.createMeasureStationWithMAC(macAddress);
		MeasureStation newMeasureStation = measureStationService.loadMeasureStaionByMacAdress(macAddress);

		if (newMeasureStation != null) {
			return new JSONStation(newMeasureStation.getId(), newMeasureStation.getStationName());
		}

		return JSONStation.EMPTY;

	}

	@RequestMapping(value = "getConﬁgForId", method = RequestMethod.GET)
	public @ResponseBody
	JSONStationConfig getConfigForStation(@RequestParam long stationId) {
		MeasureStation ms = measureStationService.loadMeasureStaionById(stationId);

		if (ms.getConfiguration().isPresent()) {
			return new JSONStationConfig(ms.getConfiguration().get());
		}
		return new JSONStationConfig();

	}

}
