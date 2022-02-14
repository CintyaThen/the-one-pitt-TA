package report;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.DTNHost;
import core.SimClock;
import core.SimScenario;
import routing.DecisionEngineRouter;
import routing.MessageRouter;
import routing.RoutingDecisionEngine;
import routing.community.BubbleRap;
import routing.community.CommunityDetectionEngine;
import routing.community.ConnListDecisionEngine;
import routing.community.DeliveryPredictabilityDecisionEngine;
import routing.community.DistributedBubbleRap;
import routing.community.Duration;

public class MeanInterContactReportTA extends Report{
	
	public MeanInterContactReportTA(){
		init();
	}
	
	@Override
    public void done() {
		 int currentHourCount;
		 int currentHour;
		 
		 
		   List<DTNHost> nodes = SimScenario.getInstance().getHosts();
	        String println="";
	        for (DTNHost h : nodes) {
	        	println += "\n" + h +" ";
	        	MessageRouter r = h.getRouter();
	        	if(!(r instanceof DecisionEngineRouter) )
	    			continue;
	    		RoutingDecisionEngine de = ((DecisionEngineRouter)r).getDecisionEngine();
	    		if(!(de instanceof ConnListDecisionEngine))
	    			continue;
	    		ConnListDecisionEngine dp = (ConnListDecisionEngine)de;
	    		 
	    		Map <DTNHost, List<Duration>> contactList = dp.getConnList();//Bongkar disini
	    		
	    		
	    		
	    		for (Map.Entry<DTNHost, List<Duration>> entry : contactList.entrySet()) {
					  
					List<Duration> nodeDuration = entry.getValue(); //Bongkar disini
					
					Iterator<Duration> i = nodeDuration.iterator() ; //Bongkar disini
					double endTimebefore = 0;
					boolean first = true;
					
					
					List<Double> durationList = new LinkedList();
					while (i.hasNext() ) {
						Duration d = i.next();
						if (first == false) {
							durationList.add(d.start - endTimebefore);
						} else {
							first = false;
						}
						endTimebefore = d.end;
					}
					if (durationList.size() == 0) {
						continue;
					} // Map

					println += " " + getAverage(durationList);
				 
	            }

	        }
	        write(println);
	        super.done();
	}
	private double getAverageInterContact (List<Double> durationList) {
		Iterator<Double> i = durationList.iterator();
		while (i.hasNext() ) {
			//Duration d = i.next();
			if (first == false) {
				durationList.add(d.start - endTimebefore);
			} else {
				first = false;
			}
			endTimebefore = d.end;
		}
		if (durationList.size() == 0) {
			continue;
		}
		return getAverage(durationList);
	}
}
