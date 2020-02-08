
public class SinList extends QuadraticList {

	public SinList(int start, int end, int nSteps) {
		super(start, end, nSteps);
		head = new DataNode();
		tail = new DataNode();
		head.setNext(tail);
		tail.setPrevious(head);
		// TODO Auto-generated constructor stub
	}
	
	public SinList(int start, int end, int nSteps, int rSteps) {
		super(start, end, nSteps, rSteps);
		head = new DataNode();
		tail = new DataNode();
		head.setNext(tail);
		tail.setPrevious(head);
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
			d.setInitY(Math.sin(d.getInitX()));
		}
		d.setSlope(Math.cos(d.getInitX()));
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
		int nSteps = 60;
		SinList q = new SinList(0, 2, nSteps);
		q.createList();
		DataNode cur = q.head.next;
		
		for (int i = 0; i < nSteps; i++) {
			q.calculate(cur);
			q.reportData(cur);
			cur = cur.next;
		}
		
		
	}
}
