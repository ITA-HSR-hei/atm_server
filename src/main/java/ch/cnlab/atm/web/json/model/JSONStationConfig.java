package ch.cnlab.atm.web.json.model;

import java.util.LinkedList;
import java.util.List;

import ch.cnlab.atm.domain.DataServer;
import ch.cnlab.atm.domain.MeasureStationConfiguration;

public class JSONStationConfig extends JSONClass {
	private static final long serialVersionUID = -6474195344852140052L;
	private List<JSONDataServer> servers = new LinkedList<JSONDataServer>();

	public JSONStationConfig() {
	}

	public JSONStationConfig(MeasureStationConfiguration msc) {
		if (msc.getDataServers().isPresent()) {
			for (DataServer ds : msc.getDataServers().get()) {
				servers.add(new JSONDataServer(ds));
			}
		}
		// msc.getMicrophonePorts();
		// msc.getSendIntervalinMinutes();
	}

	public void setDataServers(List<JSONDataServer> servers) {
		this.servers = servers;
	}

	public List<JSONDataServer> getDataServers() {
		return this.servers;
	}

}
