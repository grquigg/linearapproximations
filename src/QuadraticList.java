
public class QuadraticList implements List {
	
	protected int startX;
	protected double endX;
	protected double delta_x;
	protected static double recur_x = 1000; //this is the maximum power of 10 number of recursive calls that we can have without getting into stack overflow
	protected int steps;
	protected int reportSize;
	DataNode head;
	DataNode tail;
	
	public QuadraticList(int start, double end, int nSteps) {
		head = new DataNode();
		tail = new DataNode();
		head.setNext(tail);
		tail.setPrevious(head);
		startX = start;
		endX = end;
		steps = nSteps;
		reportSize = steps;
		delta_x = (double) (endX-startX) / steps;
		System.out.println(delta_x);
	}
	
	public QuadraticList(int start, int end, int nSteps, int rSteps) {
		head = new DataNode();
		tail = new DataNode();
		head.setNext(tail);
		tail.setPrevious(head);
		startX = start;
		endX = end;
		steps = nSteps;
		reportSize = rSteps;
		delta_x = (double) (endX-startX) / steps;
	}
	
	/**
	 * Developing a recursive solution to the Euler approximation method will significantly decrease the amount of time that we are in garbage collection. 
	 * @param x
	 * @return
	 */
	public double recursiveSolution(double x, double stop) {
		//given an initial end point, we can recur backwards to calculate the slope at each point in time
		//System.out.println(x);
		if (x <= stop) {
			if (stop == 0) {
				return 0;
			}
			return x + 2 * delta_x;
		}
		return (recursiveSolution(x-delta_x, stop) + (2 * (x) * delta_x));
	}
	
	public double getDeltaX() {
		return delta_x;
	}
	
	public void append(DataNode d) {
		d.setPrevious(tail.previous);
		tail.previous.setNext(d);
		tail.setPrevious(d);
		d.setNext(tail);
	}
	
	public void calculate(DataNode d) {
		if (d.previous != head) {
			d.setInitX(d.previous.getFinX());
			d.setInitY(d.previous.getFinY());
		}
		else {
			d.setInitX(startX);
			d.setInitY(startX*startX);
		}
		d.setSlope(2 * d.getInitX());
		double y = d.getSlope() * delta_x + d.getInitY();
		double x = d.getInitX() + delta_x;
		d.setFinX(x);
		d.setFinY(y);
	}
	
	public void rungeKutta(DataNode d) {
		if (d.previous != head) {
			d.setInitX(d.previous.getFinX());
			d.setInitY(d.previous.getFinY());
		}
		else {
			d.setInitX(startX);
			d.setInitY(startX*startX);
		}
		d.setSlope(2 * d.getInitX());
		//calculations for the Runge-Kutta approximation
		//y=x^2
		double k1 = delta_x * (2 * d.getInitX());
		double k2 = 2 * delta_x * (d.getInitX() + (k1 / 2));
		double k3 = 2 * delta_x * (d.getInitX() + (k2 / 2));
		double k4 = 2 * delta_x * (d.getInitX() + k3);
		double x = d.getInitX() + delta_x;
		double y = d.getInitY() + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
		d.setFinX(x);
		d.setFinY(y);
	}
	public void reportData(DataNode date) {
		System.out.printf("%.4f\t%.4f\t%.4f\t%.4f\t%.4f\n", date.getInitX(), date.getInitY(), date.getSlope(), date.getFinX(), date.getFinY());
	}
	
	public static void main (String [] args) {
		int nSteps = 1000;
		System.out.println("Xo\tYo\tm\tX\tY");
		double end = 2.0;
		int begin = 0;
		QuadraticList array = new QuadraticList(begin, end, nSteps);
		DataNode cur = array.head;
		
		for (int i = 0; i < nSteps; i++) {
			array.append(new DataNode());
			cur = cur.next;
			array.calculate(cur);
			array.reportData(cur);
		}
		double solve = array.recursiveSolution(end, begin) - (2 * end * array.getDeltaX());
		System.out.println("Solution " + solve);
		
		
	}
}


