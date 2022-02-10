package report;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.DTNHost;
import core.SimScenario;
import routing.DecisionEngineRouter;
import routing.MessageRouter;
import routing.RoutingDecisionEngine;
import routing.community.BubbleRap;
import routing.community.CommunityDetectionEngine;
import routing.community.ConnListDecisionEngine;
import routing.community.DistributedBubbleRap;
import routing.community.Duration;

public class MeanInterContactReportTA extends Report{
	
	public MeanInterContactReportTA(){
		init();
	}
	
	@Override
    public void done() {
		String reportText="";
        List<DTNHost> nodes = SimScenario.getInstance().getHosts();
        
        for (DTNHost h : nodes) {
        	MessageRouter r = h.getRouter();
        	DecisionEngineRouter de = (DecisionEngineRouter) r;
        	BubbleRap br = (BubbleRap) de.getDecisionEngine();
        	InterconnectDE
        	
            if (!(r instanceof DecisionEngineRouter)) {
                continue;
            }
            RoutingDecisionEngine de = ((DecisionEngineRouter) r).getDecisionEngine();
            if (!(de instanceof ConnListDecisionEngine)) {
                continue;
            }
            ConnListDecisionEngine cld = (ConnListDecisionEngine) de;

            Map<DTNHost, List<Duration>> nodeConnList = cld.getConnList(); //Mengambil lokal komunitas yang dimiliki host tersebut
            
            for (Map.Entry<DTNHost, List<Duration>> entry : nodeConnList.entrySet()) {

            	List<Duration> nodeDuration = entry.getValue();
            	Iterator<Duration> i = nodeDuration.iterator();
            	double endTimebefore=0;
            	boolean first = true;
            	List<Double> durationList = new LinkedList();
            	while(i.hasNext()) {
            		Duration d = i.next();
            		if(first==false) {
            			durationList.add(d.start-endTimebefore);
            		}	
            		else {
            			first=false;
            		}
            		endTimebefore=d.end;
            	}
            	if(durationList.size()==0) {
            		continue;
            	}
            	print+=", "+ getAverage(durationList);
            	
            }

        }
        write(print);
        super.done();
	}
}
