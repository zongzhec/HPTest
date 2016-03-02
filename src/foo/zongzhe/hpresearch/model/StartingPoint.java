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

		// ��ʾ׼������
		PreWelcomeView preWelView = new PreWelcomeView();
		preWelView.showPage();

		// ������Ϣ���������쳣���˳�
		boolean itWorks = true;
		int errorCode = -1;

		while (itWorks) {

			// ���������ı��Ƿ���ڣ��������ڣ��򱨴���˳�
//			DirectoryAction da = new DirectoryAction();
			for (int i = 0; i < NUMBER_OF_GROUP; i++) {
				File inputText = new File(INPUT_PATH_TEXT[i]);
				if (!da.whetherDirectoryExists(inputText)) {
					errorCode = ERROR_CODE_INPUT_FILE_NOT_EXIST;
					itWorks = false;
					break;
				}
			}

			// ���м�鶼����Ҫ��
			itWorks = true;
			break;
		}

		if (!itWorks) {
			switch (errorCode) {
			case ERROR_CODE_INPUT_FILE_NOT_EXIST:
				la.logStd("ERROR", "ָ���������ļ��������ڣ������ļ��������˳���!");
				preWelView.setVisible(false);
				System.exit(0);
				break;

			default:
				break;
			}
		}

		// ׼����ϣ��ر�׼�����棬����ʾ��ӭ����
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
