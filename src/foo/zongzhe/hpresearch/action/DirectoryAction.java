package foo.zongzhe.hpresearch.action;

import java.io.File;

public class DirectoryAction {

	public boolean whetherDirectoryExists(File dir) {

		System.out.println("Dir is: " + dir);
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

}
