
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
		recur_x = delta_x;
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
			return 2 * x * x;
		}
		return ((2 * (x) * recur_x) + recursiveSolution(x-recur_x, stop));
	}
	
	public double recursiveSolution2(double x, double stop) {
		if (x <= stop) {
			return 2 * x * x;
		} else if (x <= stop+recur_x) {
			return ((2 * (x) * recur_x) + (2 * x * x));
		}
		return ((2 * (x) * recur_x) + (2 * (x-recur_x) * recur_x) + recursiveSolution2(x-(2*recur_x), stop));
	}
	
	public double recursiveSolution3(double x, double stop) {
		if (x <= stop) {
			return 2 * x * x;
		} else if (x <= stop+recur_x) {
			return ((2 * (x) * recur_x) + (2 * x * x));
		} else if (x <= stop+(2*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * x));
		}
		return ((2 * (x) * recur_x) + (2 * (x-recur_x) * recur_x) + (2 * (x- (2*recur_x)) * recur_x) + recursiveSolution3(x-(3*recur_x), stop));
	}
	
	public double recursiveSolution4(double x, double stop) {
		if (x <= stop) {
			return 2 * x * x;
		} else if (x <= stop+recur_x) {
			return ((2 * (x) * recur_x) + (2 * x * x));
		} else if (x <= stop+(2*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * x));
		} else if (x <= stop+(3*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * (x-(2*recur_x))) + (2 * x * x));
		}
		return ((2 * (x) * recur_x) + (2 * (x-recur_x) * recur_x) + (2 * (x- (2*recur_x)) * recur_x) + (2 * (x- (3*recur_x)) * recur_x) + recursiveSolution4(x-(4*recur_x), stop));
	}
	
	public double recursiveSolution5(double x, double stop) {
		if (x <= stop) {
			return 2 * x * x;
		} else if (x <= stop+recur_x) {
			return ((2 * (x) * recur_x) + (2 * x * x));
		} else if (x <= stop+(2*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * x));
		} else if (x <= stop+(3*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * (x-(2*recur_x))) + (2 * x * x));
		} else if (x <= stop+(4*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * (x-(2*recur_x))) + (2 * x * (x-(3*recur_x))) + (2 * x * x));
		}
		return ((2 * (x) * recur_x) + (2 * (x-recur_x) * recur_x) + (2 * (x- (2*recur_x)) * recur_x) + (2 * (x- (3*recur_x)) * recur_x) + (2 * (x- (4*recur_x)) * recur_x) + recursiveSolution5(x-(5*recur_x), stop));
	}
	
	public double recursiveSolution6(double x, double stop) {
		if (x <= stop) {
			return 2 * x * x;
		} else if (x <= stop+recur_x) {
			return ((2 * (x) * recur_x) + (2 * x * x));
		} else if (x <= stop+(2*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * x));
		} else if (x <= stop+(3*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * (x-(2*recur_x))) + (2 * x * x));
		} else if (x <= stop+(4*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * (x-(2*recur_x))) + (2 * x * (x-(3*recur_x))) + (2 * x * x));
		} else if (x <= stop+(5*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * (x-(2*recur_x))) + (2 * x * (x-(3*recur_x))) + (2 * x * (x-(4*recur_x))) + (2 * x * x));
		}
		return ((2 * (x) * recur_x) + (2 * (x-recur_x) * recur_x) + (2 * (x- (2*recur_x)) * recur_x) + (2 * (x- (3*recur_x)) * recur_x) + (2 * (x- (4*recur_x)) * recur_x) + (2 * (x - (5*recur_x)) * recur_x) + recursiveSolution6(x-(6*recur_x), stop));
	}
	
	public double recursiveSolution7(double x, double stop) {
		if (x <= stop) {
			return 2 * x * x;
		} else if (x <= stop+recur_x) {
			return ((2 * (x) * recur_x) + (2 * x * x));
		} else if (x <= stop+(2*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * x));
		} else if (x <= stop+(3*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * (x-(2*recur_x))) + (2 * x * x));
		} else if (x <= stop+(4*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * (x-(2*recur_x))) + (2 * x * (x-(3*recur_x))) + (2 * x * x));
		} else if (x <= stop+(5*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * (x-(2*recur_x))) + (2 * x * (x-(3*recur_x))) + (2 * x * (x-(4*recur_x))) + (2 * x * x));
		} else if (x <= stop+(6*recur_x)) {
			return ((2 * x * recur_x) + (2 * x * (x-recur_x)) + (2 * x * (x-(2*recur_x))) + (2 * x * (x-(3*recur_x))) + (2 * x * (x-(4*recur_x))) + (2 * x * (x-(5*recur_x))) + (2 * x * x));
		}
		return ((2 * (x) * recur_x) + (2 * (x-recur_x) * recur_x) + (2 * (x- (2*recur_x)) * recur_x) + (2 * (x- (3*recur_x)) * recur_x) + (2 * (x- (4*recur_x)) * recur_x) + (2 * (x - (5*recur_x)) * recur_x) + (2 * (x - (6*recur_x)) * recur_x) + recursiveSolution7(x-(7*recur_x), stop));
	}
	
	public double linearSummation(double nSteps) {
		double summation = 0;
		for (int i = 0; i < nSteps; i++) {
			double sum = 0;
			
			for (int j = 1000 * i; j < 1000 *(i+1); j++) {
				sum += (delta_x * j / 1000);
			}
			sum = sum * 2 * (delta_x / 1000);
			summation += sum;
			System.out.printf("%.4f\n", summation);
		}
		
		return summation;
	}
	
	public double summation(double nSteps) {
		double sum = 0;
		for (int i = 0; i < nSteps; i++) {
			double link = 0;
			long num = 1000000*i + 500000;
			link = 2 * (delta_x / 1000) * (delta_x / 1000) * num;
			sum += link;
			System.out.printf("%.4f\n", sum);
		}
		return sum;
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
		System.out.printf("%.4f\t%.4f\t%.4f\t%.4f\t%f\n", date.getInitX(), date.getInitY(), date.getSlope(), date.getFinX(), date.getFinY());
	}
	
	public static void main (String [] args) {
		int begin = 0;
		double end = 2;
		
		QuadraticList arr = new QuadraticList(begin, end, 5000);
		arr.append(new DataNode());
		DataNode cursor = arr.head;
		
		double x = begin;
		cursor = cursor.next;
		cursor.setInitX(begin);
		cursor.setInitY(begin * begin);
		x += arr.getDeltaX();
		cursor.setFinX(x);
		System.out.println(arr.recur_x);
		long start = System.nanoTime();
		double solve = arr.recursiveSolution7(end, cursor.getInitX());
		long finish = System.nanoTime();
		long elapsed = (finish - start);
		cursor.setFinY(solve);
		System.out.println(elapsed);
		System.out.println("Xo\tYo\tm\tX\tY");
		arr.reportData(cursor);
		
		
		//arr.linearSummation(10000);
//		arr.summation(100);
		
		
		
		
		
		
	}
}


