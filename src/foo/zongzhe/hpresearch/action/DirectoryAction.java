package foo.zongzhe.hpresearch.action;

import java.io.File;

public class DirectoryAction {

	public boolean whetherDirectoryExists(File dir) {

		// System.out.println("Dir is: " + dir);
		if (!dir.exists() && !dir.isDirectory()) {
			System.out.println("������");
			return false;
		}
		return true;
	}

	public void createDir(File dir) {
		System.out.println("��ʼ����·��");
		dir.mkdir();
	}

	public int getFileAmount(int groupNum) {
		// TODO ��Ӧÿ����һ�������ͼƬ���
		LogAction la = new LogAction();
		la.logStd("Info", "Start to choose random pic num");
		int fileCount;
		File dir = new File("C:/hptest/input/text");
		switch (groupNum) {
		case 0:
			// ��һ��
			dir = new File("C:/hptest/input/images/group1");
			break;
		case 1:
			// �ڶ���
			dir = new File("C:/hptest/input/images/group2");
			break;
		case 2:
			// ������
			dir = new File("C:/hptest/input/images/group3");
			break;
		default:
			break;
		}
		
		fileCount = dir.listFiles().length;		
		return fileCount;
	}

}
