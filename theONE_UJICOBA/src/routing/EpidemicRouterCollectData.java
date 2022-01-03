package routing;

import java.util.LinkedList;
import java.util.*;

import core.*;

public class EpidemicRouterCollectData implements RoutingDecisionEngine{
	protected LinkedList<Double> resourcesList;
    public static final String TOTAL_CONTACT_INTERVAL = "perTotalContact";
    public static final int DEFAULT_CONTACT_INTERVAL = 300;
    private Double lastRecord = Double.MIN_VALUE;
    private int interval;
    
    public EpidemicRouterCollectData(Settings s) {
        if (s.contains(TOTAL_CONTACT_INTERVAL)) {
            interval = s.getInt(TOTAL_CONTACT_INTERVAL);
        } else {
            interval = DEFAULT_CONTACT_INTERVAL;
        }
    }

    public EpidemicRouterCollectData(EpidemicRouterCollectData proto) {
        resourcesList = new LinkedList<>();
        interval = proto.interval;
        lastRecord = proto.lastRecord;
    }

	@Override
	public void connectionUp(DTNHost thisHost, DTNHost peer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionDown(DTNHost thisHost, DTNHost peer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doExchangeForNewConnection(Connection con, DTNHost peer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean newMessage(Message m) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isFinalDest(Message m, DTNHost aHost) {
		return m.getTo() == aHost;
	}

	@Override
	public boolean shouldSaveReceivedMessage(Message m, DTNHost thisHost) {
		 return !thisHost.getRouter().hasMessage(m.getId());
	}

	@Override
	public boolean shouldSendMessageToHost(Message m, DTNHost otherHost, DTNHost thisHost) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean shouldDeleteSentMessage(Message m, DTNHost otherHost) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldDeleteOldMessage(Message m, DTNHost hostReportingOld) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(DTNHost thisHost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoutingDecisionEngine replicate() {
		// TODO Auto-generated method stub
		return new EpidemicRouterCollectData(this);
	}
	
/*	public double getAverage(List<Double> values) {
		double sum = 0;
		if (values.size() == 0) {
			return 0;
		}

		for (double dValue : values) {
			sum += dValue;
		}
		
		return (sum / values.size());
	}*/
}
