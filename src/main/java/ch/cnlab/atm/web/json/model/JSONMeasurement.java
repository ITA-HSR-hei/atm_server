package ch.cnlab.atm.web.json.model;

public class JSONMeasurement extends JSONClass {
	private static final long serialVersionUID = -8704658723616191433L;
	private static final JSONMeasurement EMPTY = new JSONMeasurement();

	private long stationId;
	private long timestamp;
	private String soundlevel;

	public long getStationId() {
		return stationId;
	}

	public void setStationId(long stationId) {
		this.stationId = stationId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSoundlevel() {
		return soundlevel;
	}

	public void setSoundlevel(String soundlevel) {
		this.soundlevel = soundlevel;
	}

	public static JSONMeasurement empty() {
		return EMPTY;
	}

}
