package foo.zongzhe.hpresearch.action;

import java.io.File;

public class DirectoryAction {

	public boolean whetherDirectoryExists(File dir) {

		// System.out.println("Dir is: " + dir);
		if (!dir.exists() && !dir.isDirectory()) {
			System.out.println("不存在");
			return false;
		}
		return true;
	}

	public void createDir(File dir) {
		System.out.println("开始生成路径");
		dir.mkdir();
	}

	public int getFileAmount(int groupNum) {
		// TODO 对应每组获得一个随机的图片编号
		LogAction la = new LogAction();
		la.logStd("Info", "Start to choose random pic num");
		int fileCount;
		File dir = new File("C:/hptest/input/text");
		switch (groupNum) {
		case 0:
			// 第一组
			dir = new File("C:/hptest/input/images/group1");
			break;
		case 1:
			// 第二组
			dir = new File("C:/hptest/input/images/group2");
			break;
		case 2:
			// 第三组
			dir = new File("C:/hptest/input/images/group3");
			break;
		default:
			break;
		}
		
		fileCount = dir.listFiles().length;		
		return fileCount;
	}

}
