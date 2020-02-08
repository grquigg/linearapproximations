
public class QuadraticList implements List {
	
	protected int startX;
	protected double endX;
	protected double delta_x;
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
	
	
	public void createList() {
		for (int i = 0; i < steps; i++) {
			append(new DataNode());
		}
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
		System.out.println("Xo\tYo\tm\tX\tY");
		int nSteps = 100;
		QuadraticList q = new QuadraticList(0, 2, nSteps);
		q.createList();
		DataNode cur = q.head.next;
		
		for (int i = 0; i < nSteps; i++) {
			q.calculate(cur);
			q.reportData(cur);
			cur = cur.next;
		}
		
		System.out.println("Xo\tYo\tm\tX\tY");
		QuadraticList array = new QuadraticList(0, 2, nSteps);
		array.createList();
		cur = array.head.next;
		
		for (int i = 0; i < nSteps; i++) {
			array.rungeKutta(cur);
			array.reportData(cur);
			cur = cur.next;
		}
		
		
	}
}


