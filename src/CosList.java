
public class CosList extends QuadraticList {

	protected double recur_x;
	
	public CosList(int start, double end, int nSteps) {
		super(start, end, nSteps);
		head = new DataNode();
		tail = new DataNode();
		head.setNext(tail);
		tail.setPrevious(head);
		recur_x = delta_x / 1000;
		// TODO Auto-generated constructor stub
	}
	
	public CosList(int start, int end, int nSteps, int rSteps) {
		super(start, end, nSteps, rSteps);
		head = new DataNode();
		tail = new DataNode();
		head.setNext(tail);
		tail.setPrevious(head);
		recur_x = delta_x / 1000;
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
	
	public double recursiveSolution(double x, double stop) {
		if (x <= stop) {
			return Math.cos(x);
		}
		return (-Math.sin(x) * recur_x) + recursiveSolution(x-recur_x, stop);
	}
	
	public void calculate(DataNode d) {
		if (d.previous != head) {
			d.setInitX(d.previous.getFinX());
			d.setInitY(d.previous.getFinY());
		}
		else {
			d.setInitX(startX);
			d.setInitY(Math.cos(d.getInitX()));
		}
		d.setSlope(-Math.sin(d.getInitX()));
		double y = d.getSlope() * delta_x + d.getInitY();
		double x = d.getInitX() + delta_x;
		d.setFinX(x);
		d.setFinY(y);
	}
	
	public void reportData(DataNode date) {
		System.out.printf("%.4f\t%.4f\t%.4f\t%.4f\t%.4f\n", date.getInitX(), date.getInitY(), date.getSlope(), date.getFinX(), date.getFinY());
	}
	
	public static void main (String [] args) {
		System.out.println("Xo\tYo\tm\tX\tY");
		int nSteps = 100;
		int begin = 0;
		double end = Math.PI / 2;
		CosList q = new CosList(begin, end, nSteps);
		//q.createList();
		//DataNode cur = q.head.next;
//		
//		for (int i = 0; i < nSteps; i++) {
//			q.calculate(cur);
//			q.reportData(cur);
//			cur = cur.next;
//		}
		
		q.append(new DataNode());
		DataNode cursor = q.head;
		
		double x = begin;
		cursor = cursor.next;
		cursor.setInitX(begin);
		cursor.setInitY(Math.cos(begin));
		x += q.getDeltaX();
		cursor.setFinX(x);
		double solve = q.recursiveSolution(cursor.getFinX(), cursor.getInitX());
		cursor.setFinY(solve);
		q.reportData(cursor);
		
		for (int i = 1; i < nSteps; i++) {
			q.append(new DataNode());
			cursor = cursor.next;
			cursor.setInitX(x);
			cursor.setInitY(cursor.previous.getFinY());
			x += q.getDeltaX();
			cursor.setFinX(x);
			double temp = q.recursiveSolution(cursor.getFinX(), cursor.getInitX());
			cursor.setFinY(temp - (2 * cursor.getInitX() * q.recur_x));
			if (i % 10 == 0) {
				q.reportData(cursor);
			}
		}
	}
}
