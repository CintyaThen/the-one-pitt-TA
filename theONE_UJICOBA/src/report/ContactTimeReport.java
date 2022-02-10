package report;


	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.Map;

	import core.DTNHost;
	import core.SimScenario;
	import core.UpdateListener;
	import routing.ContactTimeEngine;
	import routing.DecisionEngineRouter;
	import routing.MessageRouter;
	import routing.RoutingDecisionEngine;
	import routing.community.Centrality;
	import routing.community.ConnListDecisionEngine;
	import routing.community.ConnectDetectionEngine;
	import routing.community.Duration;

	public class ContactTimeReport extends Report{

			
			public ContactTimeReport() {
				init();
			}
			
			@Override
		    public void done() {
		        
				String print = "";
		        List<DTNHost> nodes = SimScenario.getInstance().getHosts();
		        for (DTNHost h : nodes) {
		        	print += "\n"+h;
		            MessageRouter r = h.getRouter();
		            if (!(r instanceof DecisionEngineRouter)) {
		                continue;
		            }
		            RoutingDecisionEngine de = ((DecisionEngineRouter) r).getDecisionEngine();

		            ContactTimeEngine ct = (ContactTimeEngine) de;

		            int contact[] = ct.getContactTime();
		            for(int i=0; i<contact.length;i++) {
		            	print+="\t"+ contact[i];
		            }
		        }
		        
		        write(print);
		        super.done();
				
			}}