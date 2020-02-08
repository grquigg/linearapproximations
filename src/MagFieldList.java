
public class MagFieldList {
	protected double start;
	protected int Xo;
	protected int Yo;
	protected int Vox;
	protected int Voy;
	protected int Voz;
	protected double end;
	protected double delta_t;
	protected int steps;
	protected int reportSize;
	protected static double mass;
	protected static double charge;
	protected static int mag;
	PhysicsNode head;
	PhysicsNode tail;
	
	public MagFieldList(double startT, int startX, int startY, int Vx, int Vy, int Vz, double endX, int nSteps, double m, double q, int B) {
		start = startT;
		Xo = startX;
		Yo = startY;
		Vox = Vx;
		Voy = Vy;
		Voz = Vz;
		end = endX;
		steps = nSteps;
		mass = m;
		charge = q;
		mag = B;
		delta_t = (double) (end - start) / nSteps;
		head = new PhysicsNode();
		tail = new PhysicsNode();
		head.setNext(tail);
		tail.setPrevious(head);
	}
	
	
	public void createList() {
		int [] magnet = {0, 0, mag};
		for (int i = 0; i < steps; i++) {
			append(new PhysicsNode(mass, charge, magnet));
		}
	}
	
	public void append(PhysicsNode d) {
		d.setPrevious(tail.previous);
		tail.previous.setNext(d);
		tail.setPrevious(d);
		d.setNext(tail);
	}
	
	public void calculate(PhysicsNode d) {
		if (d.previous != head) {
			d.setT(d.previous.getFinalT());
			d.setInitX(d.previous.getXF());
			d.setInitY(d.previous.getYF());
			d.setVX(d.previous.getVx());
			d.setVY(d.previous.getVy());
		}
		else {
			d.setT(start);
			d.setInitX(Xo);
			d.setInitY(Yo);
			d.setVX(Vox);
			d.setVY(Voy);
			d.setVZ(Voz);
		}
		d.setDeltaT(delta_t);
		d.calculate();
	}
	
	//public void rungeKutta(PhysicsNode d) {
	/*
	 * if (d.previous != head) { d.setInitX(d.previous.getXF());
	 * d.setInitY(d.previous.getYF()); } else { d.setInitX(Xo); d.setInitY(Xo*Xo); }
	 * d.setSlope(2 * d.getInitX()); //calculations for the Runge-Kutta
	 * approximation //y=x^2 double k1 = delta_x * (2 * d.getInitX()); double k2 = 2
	 * * delta_x * (d.getInitX() + (k1 / 2)); double k3 = 2 * delta_x *
	 * (d.getInitX() + (k2 / 2)); double k4 = 2 * delta_x * (d.getInitX() + k3);
	 * double x = d.getInitX() + delta_x; double y = d.getInitY() + (k1 + 2 * k2 + 2
	 * * k3 + k4) / 6; d.setFinX(x); d.setFinY(y); }
	 */
	
	public static void main(String [] args) {
		System.out.println("t\tq\tBx\tBy\tBz\tXo\tYo\tVxo\tVyo\tVzo\tFx\tFy\tFz\taX\taY\taZ\tVx\tVy\tVz\tX\tY");
		int nSteps = 60;
		MagFieldList q = new MagFieldList(0, 0, 0, 0, 5, 0, 1, nSteps, 1, 1, 1);
		q.createList();
		PhysicsNode cur = q.head.next;
		
		for (int i = 0; i < nSteps; i++) {
			q.calculate(cur);
			cur.reportData();
			cur = cur.next;
		}
		
		
	}
}
