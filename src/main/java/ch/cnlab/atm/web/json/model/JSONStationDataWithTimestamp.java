package ch.cnlab.atm.web.json.model;

public class JSONStationDataWithTimestamp extends JSONClass {
	private static final long serialVersionUID = 3285793797474061354L;

	private Long id;
	private String name;
//	private long pointInterval;
	private Long dateFrom;
	private Long dateTo;
	private Object[][] data;

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

	public Long getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Long dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Long getDateTo() {
		return dateTo;
	}

	public void setDateTo(Long dateTo) {
		this.dateTo = dateTo;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

//	public long getPointInterval() {
//		return pointInterval;
//	}
//
//	public void setPointInterval(long pointInterval) {
//		this.pointInterval = pointInterval;
//	}

}