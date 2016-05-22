package foo.zongzhe.hpresearch.action;

public class AngleCalAction {

	public static void main(String[] args) {
		// TODO 计算角度

		AngleCalAction aca = new AngleCalAction();

		double x = 0;
		double y = 3;
		double p = 0.0;
		double angle = 0.0;

		angle = aca.getRandomAngle();
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

	// 从极坐标转换为直角坐标 并获得X值
	public double getXFromPolar(double p, double ra) {

		double x = 0.0;
		// 用atan2 的时候无需额外考虑x=0 的情况
		x = Math.cos(ra) * p;
		return x;
	}

	// 从极坐标转换为直角坐标 并获得Y值
	public double getYFromPolar(double p, double ra) {

		double y = 0.0;
		// 用atan2 的时候无需额外考虑x=0 的情况
		y = Math.sin(ra) * p;
		return y;
	}

	// 将直角坐标角度转化为弧度
	public double angleToRadian(double angle) {

		double radian = 0.0;
		radian = angle * Math.PI / 180;
		return radian;
	}

	// 将弧度转化为直角坐标角度
	public double radianToCCAngle(double angle) {

		double radian = 0.0;
		radian = angle * 180 / Math.PI;
		return radian;
	}

	// 返回一个直角坐标的随机角度
	public int getRandomAngle() {
		int angle = 0;

		angle = (int) (Math.random() * 360) - 180;

		return angle;
	}

	// 计算直角坐标中，两点连线与中垂线的夹角
	public double calAngleWithVertiLine(double ccX1, double ccY1, double ccX2, double ccY2) {

		double angle = 0.0;
		// 因为是中垂线，所以在使用atan2的时候用x/y
		angle = Math.atan2(ccX1 - ccX2, ccY1 - ccY2);
		// 将结果转换为角度
		AngleCalAction aca = new AngleCalAction();
		angle = aca.radianToCCAngle(angle);

		// 处理一些角度>180°的情况
		while (Math.abs(angle) > 180) {
			angle = Math.abs(angle - 180);
		}

		return angle;
	}

}
