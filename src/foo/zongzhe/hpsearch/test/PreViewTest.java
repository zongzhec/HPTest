package foo.zongzhe.hpsearch.test;

import foo.zongzhe.hpresearch.view.AfterTestView;
import foo.zongzhe.hpresearch.view.PreWelcomeView;

public class PreViewTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		test2();
		Thread.sleep(2000);
		test1();
		Thread.sleep(2000);
		test3();
		System.exit(0);
	}

	private static void test2() throws InterruptedException {
		// TODO Auto-generated method stub
		PreWelcomeView preWelView2 = new PreWelcomeView();
		preWelView2.showPage("正在读取文件");
		Thread.sleep(2000);
		preWelView2.hidePage();
		
	}

	private static void test1() throws InterruptedException {
		// TODO Auto-generated method stub
		AfterTestView preWelView2 = new AfterTestView();
		preWelView2.showPage("正在将结果写入文件2");
		Thread.sleep(2000);
		preWelView2.hidePage();
	}
	
	private static void test3() throws InterruptedException {
		// TODO Auto-generated method stub
		AfterTestView preWelView2 = new AfterTestView();
		preWelView2.showPage("正在将结果写入文件3");
		Thread.sleep(2000);
		preWelView2.hidePage();
		Thread.sleep(2000);
		preWelView2.showPage("正在将结果写入文件4");
		preWelView2.repaint();
		Thread.sleep(2000);
		preWelView2.hidePage();
	}

}
