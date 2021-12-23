package routing.community;
import java.util.Map;


import core.*;


public interface DeliveryPredictabilityDecisionEngine {

	public Map<DTNHost, Double>  getPreds();
}

