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
import routing.community.CommunityDetectionEngine;
import routing.community.ConnListDecisionEngine;
import routing.community.Duration;

public class Burstiness2InterContactNodeReportIterator extends Report{
	
	public Burstiness2InterContactNodeReportIterator(){
		init();
	}
	
	@Override
    public void done() {
        List<DTNHost> nodes = SimScenario.getInstance().getHosts();
        String print="";
        for (DTNHost h : nodes) {
        	print += "\n"+h+""; 
            MessageRouter r = h.getRouter();
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
            	double mean = Double.parseDouble(getAverage(durationList));
            	double sd = Math.sqrt(Double.parseDouble(getVariance(durationList)));
            	double B = (sd-mean)/(sd+mean);
            	print+=", " + B;
            	
            }

        }
        write(print);
        super.done();
	}
}
