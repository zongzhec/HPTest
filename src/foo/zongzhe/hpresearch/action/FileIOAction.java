package foo.zongzhe.hpresearch.action;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
		System.out.println(dotsAngle[0]);
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
					if (chosenPicPerGroup.contains(lineNum - 1)) {
						// 是选中的组，准备进一步读取
						dot = 0;
						str = br.readLine();
						String[] dotsStr = str.split(" ");
						for (int j = 0; j < dotsStr.length; j = j + 2) {
							inputX[pic][dot] = Double.valueOf(dotsStr[j]);
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputX;
	}

	public Double[][] readInputY(Integer group, List<Integer> chosenPicPerGroup) {
		// TODO 读取X坐标
		INPUT_PATH_TEXT[0] = "C:/hptest/input/text/position_point_group1.txt";
		INPUT_PATH_TEXT[1] = "C:/hptest/input/text/position_point_group2.txt";
		INPUT_PATH_TEXT[2] = "C:/hptest/input/text/position_point_group3.txt";
		Double inputY[][] = new Double[PIC_PER_GROUP][DOTS];

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
					if (chosenPicPerGroup.contains(lineNum - 1)) {
						// 是选中的组，准备进一步读取
						dot = 0;
						str = br.readLine();
						String[] dotsStr = str.split(" ");
						for (int j = 1; j < dotsStr.length; j = j + 2) {
							inputY[pic][dot] = Double.valueOf(dotsStr[j]);
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputY;
	}

	public void writeExcel(String fileName, int column, int row, String outputMsg, boolean create) throws RowsExceededException, WriteException {

		// 根据index来判断写入的阶段
		File file = new File("C:/hptest/output/result_output/" + fileName);
		System.out.println("Ready to write in excel: " + column + ", " + row + ", " + outputMsg);
		try {
			if (!file.exists()) {
				jxl.write.WritableWorkbook wwb;
				wwb = Workbook.createWorkbook(file);
				jxl.write.WritableSheet ws = wwb.createSheet("HP_Test", 0);
				// ws.setColumnView(0, 20); // 设置列宽
				// jxl.write.NumberFormat nf = new
				// jxl.write.NumberFormat("0.0000000000000000"); // 定义数值格式
				// WritableCellFormat wcfN = new WritableCellFormat(nf);
				// String str2 = "第" + 1 + "次试验";
				Label label = new Label(column, row, outputMsg);
				ws.addCell(label);
				// 写入Exel工作表
				wwb.write();
				// 关闭Excel工作薄对象
				wwb.close();
			} else {
				System.out.println("file exists, ready to add in " + file.toString());
				Workbook rwb = Workbook.getWorkbook(file);
				System.out.println("workboog get");
				File tempfile = file;
				WritableWorkbook wwb = Workbook.createWorkbook(tempfile, rwb);
				System.out.println("WritableWorkbook get");
				WritableSheet ws = wwb.getSheet(0);
				System.out.println("WritableSheet get");
				// Label label = new Label(column, row, outputMsg);
				Label label = new Label(column, row, outputMsg);
				System.out.println("writing in excel: " + column + ", " + row + ", " + outputMsg);
				ws.addCell(label);
				System.out.println("cell added");
				wwb.write();
				System.out.println("wwb wrote");
				wwb.close();
				System.out.println("wwb closed");
				rwb.close();
				System.out.println("rwb closed");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getRowStart(String fileName) throws RowsExceededException, WriteException {

		int rows = 0;
		File file = new File("C:/hptest/output/result_output/" + fileName);
		System.out.println("file is " + file.toString());
		Workbook rwb;

		try {
			rwb = Workbook.getWorkbook(file);
			System.out.println("workbook get.");
			File tempfile = file;
			WritableWorkbook wwb = Workbook.createWorkbook(tempfile, rwb);
			System.out.println("WritableWorkbook get.");
			WritableSheet ws = wwb.getSheet(0);
			rows = rwb.getSheet(0).getRows();
			System.out.println("rows: " + String.valueOf(rows));
			ws.setColumnView(rows, 20); // 设置列宽
			wwb.write();
			wwb.close();
			System.out.println("wwb closed.");
			rwb.close();
			System.out.println("rwb closed");
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;

		// 根据index来判断写入的阶段
		// System.out.println("Ready to get row start.");
		// WritableWorkbook wwb = null;
		// Sheet ws = null;
		// int rows = 0;
		// try {
		// // 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
		//
		// Workbook wb = Workbook.getWorkbook(new
		// File("C:/hptest/output/result_output\\" + fileName));
		// wwb = Workbook.createWorkbook(new
		// File("C:/hptest/output/result_output\\" + fileName), wb);
		// ws = wb.getSheet(0);
		// System.out.println("row start at " + ws.getRows());
		// rows = ws.getRows();
		// wwb.close();
		// wb.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (BiffException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return rows;

	}

	public int getTestStart(String fileName) throws RowsExceededException, WriteException {

		// 根据index来判断写入的阶段
		WritableWorkbook wwb = null;
		try {
			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象

			Workbook wb = Workbook.getWorkbook(new File("C:/hptest/output/result_output\\" + fileName));
			wwb = Workbook.createWorkbook(new File("C:/hptest/output/result_output\\" + fileName), wb);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 创建一个可写入的工作表
		// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
		WritableSheet ws;
		ws = wwb.getSheet(0);
		int testStart = (ws.getRows() - 1) / 15 + 1;
		return testStart;

	}

}
