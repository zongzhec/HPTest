package foo.zongzhe.hpresearch.action;

public class AngleCalAction {

	public static void main(String[] args) {
		// TODO 计算角度

		AngleCalAction aca = new AngleCalAction();

		double x = 0;
		double y = 3;
		double p = 0.0;
		double angle = 0.0;

		System.out.println(aca.getPolarRadiusFromRightAngel(x, y));
		System.out.println("getPolarAngleFromRightAngel: " + aca.getPolarAngleFromRightAngel(x, y));
	}

	// 从直角坐标转换为极坐标 并获得极径
	public double getPolarRadiusFromRightAngel(double x, double y) {

		double p = 0.0;

		p = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

		return p;
	}

	// 从直角坐标转换为极坐标 并获得极角
	public double getPolarAngleFromRightAngel(double x, double y) {

		double angle = 0.0;

		// 用atan2 的时候无需额外考虑x=0 的情况
		angle = Math.atan2(y, x);

		return angle;
	}

	// 将角度转化为弧度
	public double angleToRadians(double angle) {
		double radian = 0.0;

		radian = angle * Math.PI / 180;
		;

		return radian;
	}

}
