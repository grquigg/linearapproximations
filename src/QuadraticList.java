
public class QuadraticList implements List {
	
	protected int startX;
	protected double endX;
	protected double delta_x;
	protected double recur_x; //this is the maximum power of 10 number of recursive calls that we can have without getting into stack overflow
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
		recur_x = delta_x / 1000;
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
			if (stop <= 0) {
				return 0;
			}
			
			return x * x;
		}
		return ((2 * (x) * recur_x) + recursiveSolution(x-recur_x, stop));
	}
	
	public double linearSummation(double begin, double end) {
		double summation = 0;
		return summation;
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
		int begin = 0;
		double end = 1;
		System.out.println("Xo\tYo\tm\tX\tY");
		
		QuadraticList arr = new QuadraticList(begin, end, 10);
		arr.append(new DataNode());
		DataNode cursor = arr.head;
		
		double x = begin;
		cursor = cursor.next;
		cursor.setInitX(begin);
		cursor.setInitY(begin * begin);
		x += arr.getDeltaX();
		cursor.setFinX(x);
		double solve = arr.recursiveSolution(cursor.getFinX(), cursor.getInitX());
		cursor.setFinY(solve - (2 * cursor.getInitX() * arr.recur_x));
		arr.reportData(cursor);
		
		for (int i = 1; i < 10; i++) {
			arr.append(new DataNode());
			cursor = cursor.next;
			cursor.setInitX(x);
			cursor.setInitY(cursor.previous.getFinY());
			x += arr.getDeltaX();
			cursor.setFinX(x);
			double temp = arr.recursiveSolution(cursor.getFinX(), cursor.getInitX());
			cursor.setFinY(temp - (2 * cursor.getInitX() * arr.recur_x));
			if (i % 1 == 0) {
				arr.reportData(cursor);
			}
		}
		
		
		
		
		
		
	}
}


