
public class CircleNode extends DataNode {

	private double x;
	private double x1;
	private double y;
	private double y1;
	private double slope_x;
	private double slope_y;
	private double t;
	
	CircleNode next;
	CircleNode previous;
	
	public CircleNode() {
		super();
		next = null;
		previous = null;
	}
	
	public void setT(double time) {
		t = time;
	}
	
	public double getT() {
		return t;
	}
	
	public void setInitX(double a) {
		x = a;
		slope_x = -2 * Math.sin(t);
	}
	
	public double getInitX() {
		return x;
	}
	
	public void setInitY(double b) {
		y = b;
		slope_y = 2 * Math.cos(t);
	}
	
	public double getInitY() {
		return y;
	}
	
	public void setNext(CircleNode nextSet) {
		next = nextSet;
	}
	
	public void setPrevious(CircleNode previousNode) {
		previous = previousNode;
	}
	
	public double getSlopeX() {
		return slope_x;
	}
	
	public double getSlopeY() {
		return slope_y;
	}
	
	public void setFinX(double c) {
		x1 = c;
	}
	
	public double getFinX() {
		return x1;
	}
	
	public void setFinY(double d) {
		y1 = d;
	}
	
	public double getFinY() {
		return y1;
	}
}
