package report;

import java.util.HashMap;
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

public class FrequencyPerNodeReport extends Report{
	public FrequencyPerNodeReport(){
		init();
	}
	
	@Override
    public void done() {
        List<DTNHost> nodes = SimScenario.getInstance().getHosts();
        String print="";
        for (DTNHost h : nodes) {
        	print += "\n"+h.getAddress()+"-----------------------\n"; 
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
            	print = print + "\n" + entry.getKey();
            	List<Duration> nodeDuration = entry.getValue();
            	int f = nodeDuration.size();
            	print += "\t" + Integer.toString(f);
            }

        }
        write(print);
        super.done();
	}
}
