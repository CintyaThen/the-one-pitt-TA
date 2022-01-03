package report;

import java.util.HashMap;

import core.*;


public class CollectingDataReport extends Report implements MessageListener{
	
	private HashMap<String, InfoPesan> pesan;
	
	public CollectingDataReport() {
		init();
	}
	@Override
	public void newMessage(Message m) {
		if (isWarmup()) {
			addWarmupID(m.getId());
			return;
		}
		
		this.pesan.put( m.getId(), 
				new InfoPesan( 
						m.getFrom().getLocation().clone(),
						m.getTo().getLocation().clone()) );
	
	}

	@Override
	public void messageTransferStarted(Message m, DTNHost from, DTNHost to) {}

	@Override
	public void messageDeleted(Message m, DTNHost where, boolean dropped) {}
	public void messageTransferAborted(Message m, DTNHost from, DTNHost to) {}
	public void messageTransferred(Message m, DTNHost from, DTNHost to, boolean firstDelivery) {
		// TODO Auto-generated method stub
		
	}
	
	private class InfoPesan{
		//private double 
		private Coord loc1;
		private Coord loc2;

		public InfoPesan(Coord loc1, Coord loc2) {
			
			this.loc1 = loc1;
			this.loc2 = loc2;
		}

		public Coord getLoc1() {
			return loc1;
		}

		public Coord getLoc2() {
			return loc2;
		}

		
	}
}
