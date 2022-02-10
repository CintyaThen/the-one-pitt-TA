package routing.community;

import java.util.List;
import java.util.Map;
import core.DTNHost;

public interface ConnListDesicionEngine {
	public Map<DTNHost, List<Duration>> getConnList();
}
