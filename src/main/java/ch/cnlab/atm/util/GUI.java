package ch.cnlab.atm.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import ch.cnlab.atm.domain.Measurement;

public class GUI {

	public static long getSampleratesForCharts(long from, long to) {
		long diffInSecond = (to - from);
		if (diffInSecond < 12 * Constants.MINUTE)
			return 0; //
		if (diffInSecond < Constants.HOUR)
			return 6 * Constants.SECOND;
		if (diffInSecond < 12 * Constants.HOUR)
			return Constants.MINUTE;
		if (diffInSecond < 2 * Constants.DAY)
			return 5 * Constants.MINUTE;
		if (diffInSecond < 2 * Constants.WEEK)
			return 17 * Constants.MINUTE;
		if (diffInSecond < 3 * Constants.WEEK)
			return 30 * Constants.MINUTE;
		if (diffInSecond < 2 * Constants.MONTH)
			return Constants.HOUR;
		if (diffInSecond < 3 * Constants.QUART)
			return 6 * Constants.HOUR;
		if (diffInSecond < Constants.YEAR)
			return Constants.DAY;
		if (diffInSecond < 3 * Constants.YEAR)
			return Constants.WEEK;
		return Constants.QUART;
	}

	/**
	 * Builds a List of Measurements for the HiCharts Gui, contains possible
	 * null if there is no value
	 * 
	 * @param list
	 * @return an Array of double
	 */
	public static Double[] measurementsToDoubleArray(List<Measurement> list) {

		Double[] ret = new Double[list.size()];
		for (int i = 0; i < ret.length; i++) {
			int soundLevel = list.get(i).getSoundLevel();
			if (soundLevel <= 0) {
				ret[i] = null;
			} else {
				ret[i] = soundLevel / 10.0;
			}
		}
		return ret;

	}
	
	public static Double[] convertMeasurementsToIntervalArray(List<Measurement> list, long timestampFrom, long timestampTo){
		
		ListIterator<Measurement> it = list.listIterator();
		long interval = 1000;
		
		List<Double> dataOfTimeperiod = new LinkedList<Double>();
		
		for (long currentPoint = timestampFrom; currentPoint < timestampTo ; currentPoint+= interval) {
			
			if(it.hasNext()){
				Double valueThisInterval = getValueForThisInterval(it, currentPoint, currentPoint+interval);
				dataOfTimeperiod.add(valueThisInterval);
			}else{
				dataOfTimeperiod.add(null);
			}
		}
		
		
		return dataOfTimeperiod.toArray(new Double[0]);
		
	}


	private static Double getValueForThisInterval(ListIterator<Measurement> it, long currentPoint, long endPoint) {
		Measurement measurement = null;

		double valueOfMeasurements = 0;
		int numberOfMeasurementsInThisInterval = 0;
		
		while(it.hasNext()){
			measurement = it.next();
			
			if(measurement.getDate() > currentPoint && measurement.getDate() <= endPoint){
											
				double soundLevel = measurement.getSoundLevel();
				if (valueOfMeasurements >= 0) {
					numberOfMeasurementsInThisInterval++;
					valueOfMeasurements += soundLevel;
				}
			}else{
				it.previous();
				break;
			}
		}
		
		if(numberOfMeasurementsInThisInterval == 1){
			return valueOfMeasurements /10.0;
		}else if (numberOfMeasurementsInThisInterval > 1) {
			return (valueOfMeasurements/numberOfMeasurementsInThisInterval)/10.0;
		}else{
			return null;
		}
			
			
			
			
	}


	public static Object[][] measurementsToObjectArray(List<Measurement> measurements) {
		Object[][] data = new Object[measurements.size()][2];

		for (int i = 0; i < data.length; i++) {
			Measurement measurement = measurements.get(i);
			data[i][0] = measurement.getDate();

			int soundLevel = measurement.getSoundLevel();
			if (soundLevel <= 0) {
				data[i][1] = null;
			} else {
				data[i][1] = soundLevel / 10.0;
			}

		}
		return data;
	}

}
