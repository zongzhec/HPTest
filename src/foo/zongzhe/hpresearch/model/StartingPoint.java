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

		// ��ʾ׼������
		PreWelcomeView preWelView = new PreWelcomeView();
		preWelView.showPage("���ڶ�������");
		
		try

		{
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ������Ϣ���������쳣���˳�
		boolean itWorks = true;

		while (itWorks) {

			// ���������ı��Ƿ���ڣ��������ڣ��򱨴���˳�
			for (int i = 0; i < NUMBER_OF_GROUP; i++) {
				File inputText = new File(INPUT_PATH_TEXT[i]);
				if (!da.whetherDirectoryExists(inputText)) {
					ErrorMsg = "Input file does not exist, exit";
					itWorks = false;
					break;
				}
			}

			// ���ÿ���ͼƬ��Ŀ�Ƿ���ڵ���5�ţ���С��5�ţ��򱨴���˳�
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

		// ׼����ϣ��ر�׼�����棬����ʾ��ӭ����
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
