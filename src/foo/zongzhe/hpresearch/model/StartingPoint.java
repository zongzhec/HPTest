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
		 * ----------------------- ׼���׶� -----------------------
		 */
		// ׼���׶εı���
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");// �������ڸ�ʽ
		String sysTime = df.format(new Date()).toString();
		System.out.println(sysTime);// new Date()Ϊ��ȡ��ǰϵͳʱ��
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
			DirectoryAction da = new DirectoryAction();
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
				System.out.println("ָ���������ļ��������ڣ������ļ��������˳���");
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
