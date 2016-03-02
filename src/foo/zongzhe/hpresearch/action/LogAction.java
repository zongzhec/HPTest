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
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
			// Set time format
			SimpleDateFormat dfFileName = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat dfLogStamp = new SimpleDateFormat("HH:mm:ss");
			// new Date()Ϊ��ȡ��ǰϵͳʱ��
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
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
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
		// ������A׷���ļ�
		appendMethodA(fileName, content);
		appendMethodA(fileName, "A append end. \n");
		// ��ʾ�ļ�����

		// ������B׷���ļ�
		appendMethodB(fileName, content);
		appendMethodB(fileName, "B append end. \n");
		// ��ʾ�ļ�����

	}

	/**
	 * A����׷���ļ���ʹ��RandomAccessFile
	 */
	public static void appendMethodA(String fileName, String content) {
		try {
			// ��һ����������ļ���������д��ʽ
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// �ļ����ȣ��ֽ���
			long fileLength = randomFile.length();
			// ��д�ļ�ָ���Ƶ��ļ�β��
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * B����׷���ļ���ʹ��FileWriter
	 */
	public static void appendMethodB(String fileName, String content) {
		try {
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
