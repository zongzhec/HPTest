package foo.zongzhe.hpresearch.model;

import java.io.File;
import foo.zongzhe.hpresearch.action.DirectoryAction;
import foo.zongzhe.hpresearch.action.LogAction;
import foo.zongzhe.hpresearch.view.PreWelcomeView;
import foo.zongzhe.hpresearch.view.WelcomeView;

public class StartingPoint {

	final static int ERROR_CODE_INPUT_FILE_NOT_EXIST = 101;
	final static int NUMBER_OF_GROUP = 1;
	static final int PIC_PER_GROUP = 5;

	static String ErrorMsg;
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
		la.logStd("Info", "------------------------New Run--------------------------------");
		la.logStd("Info", "Link Start!");
		INPUT_PATH_TEXT[0] = "C:/hptest/input/text/position_point_group1.txt";
		INPUT_PATH_TEXT[1] = "C:/hptest/input/text/position_point_group2.txt";
		INPUT_PATH_TEXT[2] = "C:/hptest/input/text/position_point_group3.txt";

		// 显示准备界面
		PreWelcomeView preWelView = new PreWelcomeView();
		preWelView.showPage("正在读入数据");
		
		try

		{
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 处理信息，当出现异常后退出
		boolean itWorks = true;

		while (itWorks) {

			// 检查输入的文本是否存在，若不存在，则报错后退出
			for (int i = 0; i < NUMBER_OF_GROUP; i++) {
				File inputText = new File(INPUT_PATH_TEXT[i]);
				if (!da.whetherDirectoryExists(inputText)) {
					ErrorMsg = "Input file does not exist, exit";
					itWorks = false;
					break;
				}
			}

			// 检查每组的图片数目是否大于等于5张，若小于5张，则报错后退出
			int fileCount = 0;
			for (int i = 0; i < NUMBER_OF_GROUP; i++) {
				fileCount = da.getFileAmount(i);
				la.logStd("Info", "There are " + fileCount + " files in group " + (i+1));
				if (fileCount < PIC_PER_GROUP) {
					ErrorMsg = "Group " + (i+1) + " does not have sufficient pics, exit";
					itWorks = false;
					
				}
			}

			
			
			
		}

		if (!itWorks) {
			la.logStd("ERROR", ErrorMsg);
			preWelView.setVisible(false);
			System.exit(0);
		}

		// 准备完毕，关闭准备界面，并显示欢迎界面
		try

		{
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		preWelView.hidePage();
		WelcomeView welView = new WelcomeView();
		welView.showPage();

	}

}
