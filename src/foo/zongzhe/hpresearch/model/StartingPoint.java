package foo.zongzhe.hpresearch.model;

import java.io.File;
import foo.zongzhe.hpresearch.action.DirectoryAction;
import foo.zongzhe.hpresearch.action.LogAction;
import foo.zongzhe.hpresearch.view.PreWelcomeView;
import foo.zongzhe.hpresearch.view.WelcomeView;

public class StartingPoint {

	final static int ERROR_CODE_INPUT_FILE_NOT_EXIST = 101;
	final static int NUMBER_OF_GROUP = 1;

	public static String INPUT_PATH_TEXT[] = new String[3];

	public static void main(String[] args) {

		/**
		 * ----------------------- Initialization Phase -----------------------
		 */
		// Variables in the initialization phase
		

		// Check required directory and start to log
		DirectoryAction da = new DirectoryAction();
		File dir = new File("C:/hpresearch/");
		if (!da.whetherDirectoryExists(dir)) {
			da.createDir(dir);
		}

		LogAction la = new LogAction();
		la.logStd("Info", "Link Start!");
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
//			DirectoryAction da = new DirectoryAction();
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
				la.logStd("ERROR", "指定的输入文件并不存在，请检查文件并重启此程序!");
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
