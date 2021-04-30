
public class Steps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double Yo = 0;
		double X = 0;
		double Y = Yo;
		double deltaX = 0.000001;
		double m =  0;
		long start = System.nanoTime();
		for (int i = 0; i < 1000000; i+=2) {
			Y += (2 * X * deltaX) + (2 * (X + deltaX) * deltaX);
			X += (2 * deltaX);
		}
		for (int i = 0; i < 1000000; i+=2) {
			Y += (2 * X * deltaX) + (2 * (X + deltaX) * deltaX);
			X += (2 * deltaX);
		}
		long finish = System.nanoTime();
		long elapsed = (finish - start);
		System.out.println(elapsed);
		System.out.println(Y);
	}

}
//for (int i = 0; i < 1000000; i++) {
//m = 2 * X;
//Y += deltaX * m;
//X += deltaX;
//}