package routing.community;

import java.util.List;
import java.util.Map;
import core.DTNHost;

public interface ConnListDecisionEngine {
	public Map<DTNHost, List<Duration>> getConnList();
}
