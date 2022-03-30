package utils.clocks;

public class Clock extends Thread{
	private ClockListener clockListener;
	private int mills;
	
	public Clock(ClockListener clockListener, int mills) {
		this.clockListener=clockListener;
		this.mills=mills;
	}
	
	private long millsOld;
	private long millsNow;
	@Override
	public void run() {
		millsNow=System.currentTimeMillis(); //needed
		
		while(true) {
			try {
				Thread.sleep(mills);
			}catch(InterruptedException err) {}
			
			clockListener.clockUpdate(getDifferenceMills());
		}
		
	}
	
	public int getDifferenceMills() {
		millsOld=millsNow;
		millsNow=System.currentTimeMillis();
		
		return (int) (millsNow-millsOld);
	}
	
	
	
	

}
