package foo.zongzhe.hpsearch.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
	}

}
