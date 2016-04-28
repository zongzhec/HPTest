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
import java.util.List;

import foo.zongzhe.hpresearch.model.StartingPoint;

public class FileIOAction {

	static final int GROUPS = 3;
	static final int PIC_PER_GROUP = 5;
	static final int DOTS = 5;
	static String INPUT_PATH_TEXT[] = new String[3];

	public static void main(String[] args) {
		// TODO 进行文件的读写操作
		Double dotsAngle[] = new Double[8 * 4];

		String filename = "C:/hptest/input/text/position_point_group1.txt";
		FileIOAction fa = new FileIOAction();
		dotsAngle = fa.readDotPosition(filename, 5 * 4 * 4);
	}

	// 按字符读取txt文件
	public void readTXTFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，rn这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。
				if (((char) tempchar) != 'r') {
					System.out.print((char) tempchar);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("以字符为单位读取文件内容，一次读多个字节：");
			// 一次读多个字符
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(fileName));
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉r不显示
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
		// TODO 从文件中读取点的坐标

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

	public Double[][] readInputX(Integer group, List<Integer> chosenPicPerGroup) {
		// TODO 读取X坐标
		INPUT_PATH_TEXT[0] = "C:/hptest/input/text/position_point_group1.txt";
		INPUT_PATH_TEXT[1] = "C:/hptest/input/text/position_point_group2.txt";
		INPUT_PATH_TEXT[2] = "C:/hptest/input/text/position_point_group3.txt";
		Double inputX[][] = new Double[PIC_PER_GROUP][DOTS];
		LogAction la = new LogAction();

		la.logStd("Info", "Read input to get X coord");
		int pic = 0, dot = 0;
		try {
			String filename = INPUT_PATH_TEXT[group];
			FileReader reader = new FileReader(filename);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			boolean isLineNumber = true;
			int lineNum = 0;
			pic = 0;
			dot = 0;
			while ((str = br.readLine()) != null) {
				// 是行号
				if (isLineNumber) {
					lineNum = Integer.parseInt(str);
					if (chosenPicPerGroup.contains(lineNum-1)) {
						// 是选中的组，准备进一步读取
						dot = 0;
//						la.logStd("Info", "line " + lineNum + " is contained as chosen.");
						str = br.readLine();
//						la.logStd("Info", "Following contant: " + str);
						String[] dotsStr = str.split(" ");
						for (int j = 0; j < dotsStr.length; j = j + 2) {
							inputX[pic][dot] = Double.valueOf(dotsStr[j]);
//							la.logStd("Info", dotsStr[j] + " added into input X, group " + (group + 1) + ", pic " + (pic + 1) + ", dot " + dot);
							dot++;
						}
						pic++;
					} else {
						isLineNumber = false;
					}

				} else {
					isLineNumber = true;
				}
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

		return inputX;
	}

}
