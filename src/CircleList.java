
public class CircleList {
	
	private int startT;
	private int endT;
	private double delta_t;
	private int steps;
	private int reportSize;
	CircleNode head;
	CircleNode tail;
	
	public CircleList(int start, int end, int nSteps) {
		head = new CircleNode();
		tail = new CircleNode();
		head.setNext(tail);
		tail.setPrevious(head);
		startT = start;
		endT = end;
		steps = nSteps;
		reportSize = steps;
		delta_t = (double) (endT-startT) / steps;
	}
	
	public CircleList(int start, int end, int nSteps, int rSteps) {
		head = new CircleNode();
		tail = new CircleNode();
		head.setNext(tail);
		tail.setPrevious(head);
		startT = start;
		endT = end;
		steps = nSteps;
		reportSize = rSteps;
		delta_t = (double) (endT-startT) / steps;
	}

	public void append(CircleNode d) {
		d.setPrevious(tail.previous);
		tail.previous.setNext(d);
		tail.setPrevious(d);
		d.setNext(tail);
		
	}
	
	public void createList() {
		for (int i = 0; i < steps; i++) {
			append(new CircleNode());
		}
	}

	public void calculate(CircleNode d) {
		if (d.previous != head) {
			double time = d.previous.getT() + delta_t;
			d.setT(time);
			d.setInitX(d.previous.getFinX());
			d.setInitY(d.previous.getFinY());
		}
		else {
			d.setT(startT);
			d.setInitX(2*Math.cos(startT));
			d.setInitY(2*Math.sin(startT));
		}
		
		double x = d.getInitX() + delta_t * d.getSlopeX();
		double y = d.getInitY() + delta_t * d.getSlopeY();
		d.setFinX(x);
		d.setFinY(y);
		
		
	}

	public void reportData(CircleNode d) {
		System.out.printf("%.4f\t%.4f\t%.4f\t%.4f\t%.4f\t%.4f\t%.4f\n", d.getT(), d.getInitX(), d.getInitY(), d.getSlopeX(), d.getSlopeY(), d.getFinX(), d.getFinY());
		
	}
	
	public static void main (String [] args) {
		System.out.println("t\tXo\tYo\tMx\tMy\tX\tY");
		int nSteps = 10;
		CircleList c = new CircleList(0, 1, nSteps);
		c.createList();
		CircleNode cur = c.head.next;
		
		for (int i = 0; i < nSteps; i++) {
			c.calculate(cur);
			c.reportData(cur);
			cur = cur.next;
		}
		
		
	}

}
