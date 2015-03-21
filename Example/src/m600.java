
public class m600 extends racer {

	private int time=20;
	private int dis=600;
	
	public float speed(int i, int j) {
		float avg_spd=super.speed(i, j);
		return avg_spd;
	}
	
	public void getter() {
		float avg_spd=speed(time,dis);
		System.out.println("SPEED of 600 mtr racer="+avg_spd);
	}
}
