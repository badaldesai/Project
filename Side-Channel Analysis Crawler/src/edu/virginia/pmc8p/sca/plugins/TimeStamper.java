package edu.virginia.pmc8p.sca.plugins;

public class TimeStamper {

		private long time;
		
		public TimeStamper(){
			
			time = System.currentTimeMillis();
			
		}
		
		public void updateTime(){
			time = System.currentTimeMillis();
			
		}
		
		public long getTimeStamp(){
			
			return time;
			
		}
	
	
}
