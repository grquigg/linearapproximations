
public class PhysicsNode {

	public static double k = 9 * Math.pow(10, 9);
	private double Xo;
	private double X;
	private double Vxo;
	private double Vx;
	private double Vyo;
	private double Vy;
	private double Vzo;
	private double Vz;
	private double Yo;
	private double Y;
	private double Fx;
	private double Fy;
	private double Fz;
	private double Bx;
	private double By;
	private double Bz;
	private double aX;
	private double aY;
	private double aZ;
	private double m;
	private double q;
	private double delta_t;
	private double init_t;
	PhysicsNode next;
	PhysicsNode previous;
	
	public PhysicsNode(double mass, double charge, int [] B) {
		m = mass;
		q = charge;
		setField(B[0], B[1], B[2]);
		next = null;
		previous = null;
		
		
	}
	
	public PhysicsNode() {
		next = null;
		previous = null;
	}
	public void setT(double t) {
		init_t = t;
	}
	
	public double getFinalT() {
		double final_t = init_t + delta_t;
		return final_t;
	}
	public void setInitX(double x) {
		Xo = x;
	}
	
	public void setInitY(double y) {
		Yo = y;
	}
	
	public void setDeltaT(double t) {
		delta_t = t;
	}
	
	public double getInitX() {
		return Xo;
	}
	
	public double getInitY() {
		return Yo;
	}
	
	public double getXF() {
		return X;
	}
	
	public double getYF() {
		return Y;
	}
	
	public void setVX(double v) {
		Vxo = v;
	}
	
	public double getVx() {
		return Vx;
	}
	
	public double getVy() {
		return Vy;
	}
	
	public double getVz() {
		return Vz;
	}
	
	public void setVY(double v) {
		Vyo = v;
	}
	
	public void setVZ(double v) {
		Vzo = v;
	}
	
	public void setField(double magX, double magY, double magZ) {
		Bx = magX;
		By = magY;
		Bz = magZ;
	}
	
	public void getFx() {
		Fx = q * (Vyo * Bz - Vzo * By);
	}
	
	public void getFy() {
		Fy =  -q * (Vxo * Bz - Vzo * Bx);
	}
	
	public void getFz() {
		Fz = q * (Vxo * By - Vyo * Bx);
	}
	
	public void calculate() {
		getFx();
		getFy();
		getFz();
		//pointCharge(Math.pow(10, -9));
		getAx();
		getAy();
		getAz();
		Vx = Vxo + delta_t * aX;
		Vy = Vyo + delta_t * aY;
		Vz = Vzo + delta_t * aZ;
		X = Xo + (Vxo * delta_t) + (0.5 * aX * delta_t * delta_t);
		Y = Yo + (Vyo * delta_t) + (0.5 * aY * delta_t * delta_t);
		
	}
	
	public void pointCharge(double Q) {
		/* Point charge at (-1, 0, 0) of charge 1 C
		 * The force applied to the particle in the x direction is Cq1q2/x^2
		 * 
		 */
		double d = Math.sqrt((Yo * Yo) + ((Xo + 1) * (Xo + 1)));
		double Ft = (k * q * Q) / (d * d);
		double theta = Math.atan((Xo + 1) / Yo);
		Fx += Ft * Math.sin(theta);
		Fy += Ft * Math.cos(theta);
	}
	
	public void getAx() {
		aX = Fx / m;
	}
	
	public void getAy() {
		aY = Fy / m;
	}
	
	public void getAz() {
		aZ = Fz / m;
	}
	
	public void setNext(PhysicsNode nextSet) {
		next = nextSet;
	}
	
	public void setPrevious(PhysicsNode previousNode) {
		previous = previousNode;
	}
	
	public void reportData() {
//		System.out.printf("%.4f\t%.4f\t%.4f\t%.4f\t%.4f\t%.4f\t%.4f\t", init_t, q, Bx, By, Bz, Xo, Yo);
//		System.out.printf("%.4f\t%.4f\t%.4f\t%.4f\t%.4f\t%.4f\t", Vxo, Vyo, Vzo, Fx, Fy, Fz);
		System.out.printf("%.4f\t%.4f\t\n", X, Y);
	}
	
	
	
}
