package ch.cnlab.atm.web.json.model;

public class JSONStationData extends JSONClass {
	private static final long serialVersionUID = 3285793797474061354L;

	private Long id;
	private String name;
	private long pointInterval;
	private long pointStart;
	
	private Double[] data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double[] getData() {
		return data;
	}

	public void setData(Double[] data) {
		this.data = data;
	}

	public long getPointInterval() {
		return pointInterval;
	}

	public void setPointInterval(long pointInterval) {
		this.pointInterval = pointInterval;
	}

	public long getPointStart() {
		return pointStart;
	}

	public void setPointStart(long pointStart) {
		this.pointStart = pointStart;
	}


}