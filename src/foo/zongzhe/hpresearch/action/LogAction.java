package foo.zongzhe.hpresearch.action;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogAction {

	public void logStd(String logCate, String content) {
		// Log into std file
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			// Set time format
			SimpleDateFormat dfFileName = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat dfLogStamp = new SimpleDateFormat("HH:mm:ss");
			// new Date()为获取当前系统时间
			String sysTimeFileName = dfFileName.format(new Date()).toString();
			String sysTimeLogStamp = dfLogStamp.format(new Date()).toString();
			String fileName = "C:/hpresearch/hptest" + sysTimeFileName + ".out";
			FileWriter writer = new FileWriter(fileName, true);
			content = sysTimeLogStamp + ": -" + logCate + "- " + content + "\n";
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void logErr(String timestamp, String content) {
		// Log into std file
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			String fileName = "C:/hpresearch/hptest" + timestamp + ".err";
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Log Stuff
		String fileName = "C:/temp/newTemp.txt";
		String content = "new append!";
		// 按方法A追加文件
		appendMethodA(fileName, content);
		appendMethodA(fileName, "A append end. \n");
		// 显示文件内容

		// 按方法B追加文件
		appendMethodB(fileName, content);
		appendMethodB(fileName, "B append end. \n");
		// 显示文件内容

	}

	/**
	 * A方法追加文件：使用RandomAccessFile
	 */
	public static void appendMethodA(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * B方法追加文件：使用FileWriter
	 */
	public static void appendMethodB(String fileName, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
