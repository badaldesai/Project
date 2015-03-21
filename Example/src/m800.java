
public class m800 extends racer {
	private int time=30;
	private int dis=800;
	
	public float speed(int i, int j) {
		float avg_spd=super.speed(i, j);
		return avg_spd;
	}
	
	public void getter() {
		float avg_spd=speed(time,dis);
		System.out.println("SPEED of 800 mtr racer="+avg_spd);
	}
}
