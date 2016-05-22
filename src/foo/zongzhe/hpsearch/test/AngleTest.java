package foo.zongzhe.hpsearch.test;

public class AngleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double p = 2;
		double ra = (60 * StrictMath.PI/ 180) ;

		double x = StrictMath.cos(ra) * p;
		double y = StrictMath.sin(ra) * p;
		System.out.println("x = " + x + ", y=" + y);

	}

}
