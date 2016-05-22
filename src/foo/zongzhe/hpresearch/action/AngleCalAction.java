package foo.zongzhe.hpresearch.action;

public class AngleCalAction {

	public static void main(String[] args) {
		// TODO ����Ƕ�

		AngleCalAction aca = new AngleCalAction();

		double x = 0;
		double y = 3;
		double p = 0.0;
		double angle = 0.0;

		angle = aca.getRandomAngle();
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

	// �Ӽ�����ת��Ϊֱ������ �����Xֵ
	public double getXFromPolar(double p, double ra) {

		double x = 0.0;
		// ��atan2 ��ʱ��������⿼��x=0 �����
		x = Math.cos(ra) * p;
		return x;
	}

	// �Ӽ�����ת��Ϊֱ������ �����Yֵ
	public double getYFromPolar(double p, double ra) {

		double y = 0.0;
		// ��atan2 ��ʱ��������⿼��x=0 �����
		y = Math.sin(ra) * p;
		return y;
	}

	// ��ֱ������Ƕ�ת��Ϊ����
	public double angleToRadian(double angle) {

		double radian = 0.0;
		radian = angle * Math.PI / 180;
		return radian;
	}

	// ������ת��Ϊֱ������Ƕ�
	public double radianToCCAngle(double angle) {

		double radian = 0.0;
		radian = angle * 180 / Math.PI;
		return radian;
	}

	// ����һ��ֱ�����������Ƕ�
	public int getRandomAngle() {
		int angle = 0;

		angle = (int) (Math.random() * 360) - 180;

		return angle;
	}

	// ����ֱ�������У������������д��ߵļн�
	public double calAngleWithVertiLine(double ccX1, double ccY1, double ccX2, double ccY2) {

		double angle = 0.0;
		// ��Ϊ���д��ߣ�������ʹ��atan2��ʱ����x/y
		angle = Math.atan2(ccX1 - ccX2, ccY1 - ccY2);
		// �����ת��Ϊ�Ƕ�
		AngleCalAction aca = new AngleCalAction();
		angle = aca.radianToCCAngle(angle);

		// ����һЩ�Ƕ�>180������
		while (Math.abs(angle) > 180) {
			angle = Math.abs(angle - 180);
		}

		return angle;
	}

}
