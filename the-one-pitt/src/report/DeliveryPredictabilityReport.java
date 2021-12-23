package report;


import java.util.*;
import core.*;
import routing.*;
import routing.community.*;

public class DeliveryPredictabilityReport extends Report{
	public DeliveryPredictabilityReport() {
		init();
	}
	@Override
	public void done()
	{
		List<DTNHost> nodes = SimScenario.getInstance().getHosts();
		String println = "";
		for(DTNHost h : nodes)
			
		{	
			println += "\n" + h +" ";
			MessageRouter r = h.getRouter();
			if(!(r instanceof DecisionEngineRouter) )
				continue;
			RoutingDecisionEngine de = ((DecisionEngineRouter)r).getDecisionEngine();
			if(!(de instanceof DeliveryPredictabilityDecisionEngine))
				continue;
			DeliveryPredictabilityDecisionEngine dp = (DeliveryPredictabilityDecisionEngine)de;
			
			Map <DTNHost, Double> nilaiDP = dp.getPreds();
	                    
	                    
	                   for (Map.Entry<DTNHost, Double> entry :  nilaiDP.entrySet()) {
						println +=  Double.toString(entry.getValue()) +" ";
					}
		}
		write(println);
		super.done();
		}
}
