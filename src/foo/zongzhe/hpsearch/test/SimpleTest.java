package foo.zongzhe.hpsearch.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	}

}
