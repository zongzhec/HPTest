package foo.zongzhe.hpsearch.test;

import foo.zongzhe.hpresearch.action.AngleCalAction;

public class AngleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AngleCalAction aca = new AngleCalAction();
		// 764。84 1420。224 + 30 -> 107.95297
		// 1580。212 , 1588。1448 + -15 -> 165.3708413
		// 528。576	436。1148 + 5 -> 175.8628
		// 810。646	605。720 + -23 -> 86.8483
		double x1 = 764.0, y1 = 84.0;
		double x2 = 1420.0, y2 = 224.0;
		double rotate = -30;
		double p1 = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
		double ra1 = Math.atan2(y1, x1);
		double p2 = Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2));
		double ra2 = Math.atan2(y2, x2);

		System.out.println("p1=" + p1 + ", ra1=" + ra1);
		System.out.println("p2=" + p2 + ", ra2=" + ra2);

		double finalRa = rotate * Math.PI / 180;
		ra1 = ra1 + finalRa;
		ra2 = ra2 + finalRa;

		x1 = aca.getXFromPolar(p1, ra1);
		x2 = aca.getXFromPolar(p2, ra2);
		y1 = aca.getYFromPolar(p1, ra1);
		y2 = aca.getYFromPolar(p2, ra2);

		System.out.println("final x1=" + x1 + ", y1=" + y1);
		System.out.println("final x2=" + x2 + ", y2=" + y2);

		double result = aca.calAngleWithVertiLine(x1, y1, x2, y2);

		System.out.println("result=" + result);

	}

}
