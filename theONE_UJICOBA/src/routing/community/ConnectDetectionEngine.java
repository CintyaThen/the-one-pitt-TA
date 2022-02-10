package routing.community;

import java.util.Map;
import java.util.Set;

import core.DTNHost;
public interface ConnectDetectionEngine {
	public Map<DTNHost, Double> getConnectTime();
}
