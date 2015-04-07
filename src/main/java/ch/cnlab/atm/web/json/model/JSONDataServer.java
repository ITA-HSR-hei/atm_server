package ch.cnlab.atm.web.json.model;

import ch.cnlab.atm.domain.DataServer;

public class JSONDataServer {
	private final static JSONDataServer EMPTY = new JSONDataServer();
	private long id;
	private String name;
	private String url;

	public JSONDataServer() {
	}

	public JSONDataServer(DataServer ds) {
		id = ds.getId();
		name = ds.getName();
		url = ds.getUrl();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static JSONDataServer empty() {
		return EMPTY;
	}
}
