
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
	
	public long factorial(long n) {
		if (n <= 1) {
			return 1;
		}
		else {
			return n * factorial(n-1);
		}
	}
	
	public int recursiveBinomial(int n, int k) {
		if(k == 0 || k == n) {
			return 1;
		} else {
			return (recursiveBinomial(n-1, k-1) + recursiveBinomial(n-1, k));
		}
	}
	public long binomial2(int n, int k) {
		long l = 0;
		if (k == 1 || n == k) {
			l = 1;
		} else {
			long temp = 1;
			for (int i = n; i > n-k; i--) {
				temp *= i;
			}
			l = temp / factorial(k);
		}
		return l;
	}
	public long binomial(int n, int k) { //n choose k
		long l = factorial(n) / (factorial(k) * factorial(n-k));
		return l;
	}
	
	
	public void calculateVelocity(int n, int Vxo, int Vyo) {
		double[] V = new double[2];
		double c = (charge * mag * delta_t) / mass;
		V[0] = Vxo;
		V[1] = Vyo;
		for (int i = 0; i < n; i++) {
			double temp1 = V[0] + (c * V[1]);
			double temp2 = V[1] - (c * V[0]);
			V[0] = temp1;
			V[1] = temp2;
		}
		System.out.println(V[0] + "\t" + V[1]);
		
		
	}
	
	public void calculateVy(int n, int Vxo, int Vyo) {
		System.out.println(delta_t);
		int approx = n % 4;
		int total = n - approx;
		double c = (charge * mag * delta_t) / mass;
		System.out.println(c);
		double Vy = 0;
		for (int i = 0; i < total ; i+=4) {
			int [] values = {i, i+1, i+2, i+3};
			Vy += recursiveBinomial(n, values[0]) * Vyo * Math.pow(c, values[0]);
			Vy -= recursiveBinomial(n, values[1]) * Vxo * Math.pow(c, values[1]);
			Vy -= recursiveBinomial(n, values[2]) * Vyo * Math.pow(c, values[2]);
			Vy += recursiveBinomial(n, values[3]) * Vxo * Math.pow(c, values[3]);
		}
		switch(approx) {
		case 0:
			Vy += recursiveBinomial(n, total) * Vyo * Math.pow(c, total);
			break;
		case 1:
			System.out.println("Modulo 1");
			Vy += recursiveBinomial(n, total) * Vyo * Math.pow(c, total);
			Vy -= recursiveBinomial(n, total+1) * Vxo * Math.pow(c, total+1);
			break;
		case 2:
			System.out.println("Modulo 2");
			Vy += recursiveBinomial(n, total) * Vyo * Math.pow(c, total);
			Vy -= recursiveBinomial(n, total+1) * Vxo * Math.pow(c, total+1);
			Vy -= recursiveBinomial(n, total+2) * Vyo * Math.pow(c, total+2);
			break;
		case 3:
			System.out.println("Modulo 3");
			Vy += recursiveBinomial(n, total) * Vyo * Math.pow(c, total);
			Vy -= recursiveBinomial(n, total+1) * Vxo * Math.pow(c, total+1);
			Vy -= recursiveBinomial(n, total+2) * Vyo * Math.pow(c, total+2);
			Vy += recursiveBinomial(n, total+3) * Vxo * Math.pow(c, total+3);
			break;
		}
		double t = n * delta_t;
		System.out.println(t + " s\t" + Vy);
		
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
//		System.out.println("X\tY");
		int nSteps = 100;
		MagFieldList q = new MagFieldList(0, 0, 0, 0, 5, 0, 1, nSteps, 1, 1, 1);
		q.createList();
		PhysicsNode cur = q.head.next;
		
		for (int i = 0; i < 10; i++) {
//			q.calculate(cur);
//			cur.reportData();
//			cur = cur.next;
			q.calculateVy(i+1, 0, 5);
		}
		int t = q.recursiveBinomial(21, 20);
		System.out.println(t);
		
	}
}
