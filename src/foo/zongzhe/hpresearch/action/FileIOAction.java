package foo.zongzhe.hpresearch.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import foo.zongzhe.hpresearch.model.StartingPoint;

public class FileIOAction {

	public static void main(String[] args) {
		// TODO �����ļ��Ķ�д����
		Double dotsAngle[] = new Double[8 * 4];

		String filename = "C:/hptest/input/text/position_point_group1.txt";
		FileIOAction fa = new FileIOAction();
		dotsAngle = fa.readDotPosition(filename, 5 * 4 * 4);
	}

	// ���ַ���ȡtxt�ļ�
	public void readTXTFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");
			// һ�ζ�һ���ַ�
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// ����windows�£�rn�������ַ���һ��ʱ����ʾһ�����С�
				// ������������ַ��ֿ���ʾʱ���ỻ�����С�
				// ��ˣ����ε�r����������n�����򣬽������ܶ���С�
				if (((char) tempchar) != 'r') {
					System.out.print((char) tempchar);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
			// һ�ζ�����ַ�
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(fileName));
			// �������ַ����ַ������У�charreadΪһ�ζ�ȡ�ַ���
			while ((charread = reader.read(tempchars)) != -1) {
				// ͬ�����ε�r����ʾ
				if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != 'r')) {
					System.out.print(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == 'r') {
							continue;
						} else {
							System.out.print(tempchars[i]);
						}
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public Double[] readDotPosition(String filename, int numberOfDots) {
		// TODO ���ļ��ж�ȡ�������

		System.out.println(filename);

		Double[] dots = new Double[numberOfDots];
		int dotNum = 0;
		try {

			// read file content from file

			FileReader reader = new FileReader(filename);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			boolean isLineNumber = true;
			while ((str = br.readLine()) != null) {
				// Is line number
				if (isLineNumber) {
					isLineNumber = false;
				} else {
					String[] dotsStr = str.split(" ");
					for (int i = 0; i < dotsStr.length; i++) {
						dots[dotNum] = Double.valueOf(dotsStr[i]);
						// System.out.println("Dot " + dotNum + " add as " +
						// dots[dotNum]);
						dotNum++;
					}
					isLineNumber = true;
				}
			}

			for (int i = 0; i < dotNum; i++) {
				System.out.println("Dot " + i + " : " + dots[i]);
			}
			br.close();
			reader.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return dots;
	}

}
