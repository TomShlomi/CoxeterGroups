
public class DataPoint {
	private int d;
	private int[] c;
	private String n;
	public DataPoint(int dimensions, int[] coordinates, String name) {
		d = dimensions;
		if (coordinates.length == d) {
			c = coordinates;
		}
		else {int a = 1/0;}
		n = name;
	}
	
	public int getDim() {
		return d;
	}
	
	public int[] getCord() {
		return c;
	}
	
	public String getName() {
		return n;
	}
	
}
