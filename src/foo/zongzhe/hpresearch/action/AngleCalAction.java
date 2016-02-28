package foo.zongzhe.hpresearch.action;

public class AngleCalAction {

	public static void main(String[] args) {
		// TODO ����Ƕ�

		AngleCalAction aca = new AngleCalAction();

		double x = 0;
		double y = 3;
		double p = 0.0;
		double angle = 0.0;

		System.out.println(aca.getPolarRadiusFromRightAngel(x, y));
		System.out.println("getPolarAngleFromRightAngel: " + aca.getPolarAngleFromRightAngel(x, y));
	}

	// ��ֱ������ת��Ϊ������ ����ü���
	public double getPolarRadiusFromRightAngel(double x, double y) {

		double p = 0.0;

		p = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

		return p;
	}

	// ��ֱ������ת��Ϊ������ ����ü���
	public double getPolarAngleFromRightAngel(double x, double y) {

		double angle = 0.0;

		// ��atan2 ��ʱ��������⿼��x=0 �����
		angle = Math.atan2(y, x);

		return angle;
	}

	// ���Ƕ�ת��Ϊ����
	public double angleToRadians(double angle) {
		double radian = 0.0;

		radian = angle * Math.PI / 180;
		;

		return radian;
	}

}
