package foo.zongzhe.hpresearch.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import foo.zongzhe.hpresearch.action.DirectoryAction;
import foo.zongzhe.hpresearch.view.PreWelcomeView;
import foo.zongzhe.hpresearch.view.WelcomeView;

public class StartingPoint {

	final static int ERROR_CODE_INPUT_FILE_NOT_EXIST = 101;
	final static int NUMBER_OF_GROUP = 1;

	public static String INPUT_PATH_TEXT[] = new String[3];

	// static File inputText = new File(INPUT_PATH_TEXT);

	public static void main(String[] args) {

		/**
		 * ----------------------- 准备阶段 -----------------------
		 */
		// 准备阶段的变量
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");// 设置日期格式
		String sysTime = df.format(new Date()).toString();
		System.out.println(sysTime);// new Date()为获取当前系统时间
		INPUT_PATH_TEXT[0] = "C:/hptest/input/text/position_point_group1.txt";
		INPUT_PATH_TEXT[1] = "C:/hptest/input/text/position_point_group2.txt";
		INPUT_PATH_TEXT[2] = "C:/hptest/input/text/position_point_group3.txt";

		// 显示准备界面
		PreWelcomeView preWelView = new PreWelcomeView();
		preWelView.showPage();

		// 处理信息，当出现异常后退出
		boolean itWorks = true;
		int errorCode = -1;

		while (itWorks) {

			// 检查输入的文本是否存在，若不存在，则报错后退出
			DirectoryAction da = new DirectoryAction();
			for (int i = 0; i < NUMBER_OF_GROUP; i++) {
				File inputText = new File(INPUT_PATH_TEXT[i]);
				if (!da.whetherDirectoryExists(inputText)) {
					errorCode = ERROR_CODE_INPUT_FILE_NOT_EXIST;
					itWorks = false;
					break;
				}
			}

			// 所有检查都符合要求
			itWorks = true;
			break;
		}

		if (!itWorks) {
			switch (errorCode) {
			case ERROR_CODE_INPUT_FILE_NOT_EXIST:
				System.out.println("指定的输入文件并不存在，请检查文件并重启此程序");
				preWelView.setVisible(false);
				System.exit(0);
				break;

			default:
				break;
			}
		}

		// 准备完毕，关闭准备界面，并显示欢迎界面
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		preWelView.hidePage();
		WelcomeView welView = new WelcomeView();
		welView.showPage();

	}

}
