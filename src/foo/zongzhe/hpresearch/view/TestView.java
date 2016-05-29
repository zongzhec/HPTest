package foo.zongzhe.hpresearch.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import foo.zongzhe.hpresearch.action.AngleCalAction;
import foo.zongzhe.hpresearch.action.DirectoryAction;
import foo.zongzhe.hpresearch.action.FileIOAction;
import foo.zongzhe.hpresearch.action.LogAction;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TestView extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 框架
	JFrame frame = new JFrame();

	// 总的框架和布局
	JPanel jpBase;
	CardLayout cl;

	// 面板上的内容
	JPanel jpTestLeft, jpTestRight;
	JLabel jlPrev, jlNext, jl3;
	JLabel jlRd1, jlRd2, jlRd3;
	JButton jbNext, jbPrev, jbBigLeft, jbBigRight, jbSmallLeft, jbSmallRight;
	JButton jbRd1;
	JTextField jtf1;
	RotateView rv;

	// 用于填充的项目
	JLabel jlfi1, jlfi2, jlfi3, jlfi4, jlfi5, jlfi6, jlfi7, jlfi8;
	JLabel jlfiRd1, jlfiRd2, jlfiRd3, jlfiRd4, jlfiRd5, jlfiRd6, jlfiRd7, jlfiRd8, jlfiRd9, jlfiRd10, jlfiRd11, jlfiRd12, jlfiRd13, jlfiRd14;

	// 全局变量
	int testerCount;
	int testCount = 0;
	// 每组里面抽选多少张图
	static final int PICS = 5;
	static final int PIC_PER_GROUP_UAT = 5;
	// 图片库里的图片数量
	static int picNumInFile = 0;
	// 有多少个组
	static final int GROUPS = 3;
	static final int GROUPS_UAT = 1;
	// 所有组合计共有多少图
	static final int FULL_TEST_AMOUNT = PICS * GROUPS;
	static final int FULL_TEST_AMOUNT_UAT = PIC_PER_GROUP_UAT * GROUPS_UAT;
	// 图片的宽和高
	static final int IMAGE_WIDTH = 600;
	static final int IMAGE_HEGHT = 600;
	static final int RIGHR_PANEL_WIDTH = 400;
	// 每张图里有几个点
	static final int DOTS = 5;
	static final int DOTS_UAT = 5;
	// 结果的角度总数，5个点的话就是10个。每张图。
	static final int ANGLES = DOTS * (DOTS - 1) / 2;

	// 每组随机抽到的图片编号，对应每一组
	static int chosenPic[][] = new int[GROUPS][PICS];
	static List<Integer> chosenPicPerGroupOne = new ArrayList<Integer>();
	static List<Integer> chosenPicPerGroupTwo = new ArrayList<Integer>();
	static List<Integer> chosenPicPerGroupThree = new ArrayList<Integer>();

	// 输入的X，Y点坐标，对应每一组，每张图，每个点
	static Double inputX[][][] = new Double[GROUPS][PICS][DOTS];
	static Double inputY[][][] = new Double[GROUPS][PICS][DOTS];

	// 输入的X，Y点坐标转换后对应的极坐标，对应每一组，每张图，每个点
	static Double inputP[][][] = new Double[GROUPS][PICS][DOTS];
	static Double inputRa[][][] = new Double[GROUPS][PICS][DOTS];
	// 输出的X，Y点坐标，对应每一组，每张图，每个点
	static Double outputX[][][] = new Double[GROUPS][PICS][DOTS];
	static Double outputY[][][] = new Double[GROUPS][PICS][DOTS];
	// 最终的角度，直角坐标。对应每一组，每张图，每个角度
	static Double finalAngle[][][] = new Double[GROUPS][PICS][ANGLES];
	static String finalAngleStr[][][] = new String[GROUPS][PICS][ANGLES];

	static int currntGroup = 0; // 记录当前是在测试第几组
	static int currntPic = 0; // 记录当前是在测试组里的第几个图
	static int picToImage = 0; // 记录当前序号是图库离的第几张图
	// 测试第一组，对应每张图，每个点
	Double dotsXInputGroupOne[][] = new Double[PICS][DOTS];
	Double dotsXInputGroupTwo[][] = new Double[PICS][DOTS];
	Double dotsXInputGroupThree[][] = new Double[PICS][DOTS];
	Double dotsYInputGroupOne[][] = new Double[PICS][DOTS];
	Double dotsYInputGroupTwo[][] = new Double[PICS][DOTS];
	Double dotsYInputGroupThree[][] = new Double[PICS][DOTS];
	// 测试里面的坐标使用极坐标存储
	Double dotsPRadiusGroupOne[][] = new Double[PICS][DOTS * PICS];
	Double dotsPAngleGroupOne[][] = new Double[PICS][DOTS * PICS];

	// 图片文件位置
	String inputPathImageGroup[][];

	// 图片，对应每个组，每张图
	Image image[][] = new Image[GROUPS][PICS];
	// 存放图片中旋转角度信息
	Double rotateAngle[][] = new Double[GROUPS][PICS];

	// 存放结果的文件，以及列与行的坐标
	static String outputFileName = "";
	static File outputFile;
	static int rowStart;
	static int testStart; // 记录这是当天的第几次测试
	static int column;
	static int row;
	static String outputMsg;
	boolean create = true;
	boolean test = true;

	// 用于输出到log
	String stdMsg = "";

	public TestView() {
		initial();
		readInput();
		covertToPolar();
		showPage();
	}

	private void calResult() {
		// TODO 根据最终的直角坐标，计算与中垂线的夹角
		AngleCalAction aca = new AngleCalAction();
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				int angleCount = 0;
				for (int k1 = 0; k1 < DOTS; k1++) {
					for (int k2 = k1 + 1; k2 < DOTS; k2++) {
						finalAngle[i][j][angleCount] = aca.calAngleWithVertiLine(outputX[i][j][k1], outputY[i][j][k1], outputX[i][j][k2], outputY[i][j][k2]);
						System.out.println("group " + i + " pic " + j + " dot " + k1 + " and " + k2 + " has an anlge of " + finalAngle[i][j][angleCount]);
						angleCount++;
					}
				}
			}
		}
		outputResultToExcel();

	}

	private void testMode() {
		// TODO 仅供测试
		if (test) {
			rotateAngle[0][0] = 30.0 * Math.PI / 180;
			rotateAngle[0][1] = -15.0 * Math.PI / 180;
			rotateAngle[0][2] = 5.0 * Math.PI / 180;
			rotateAngle[0][3] = -23.0 * Math.PI / 180;
			rotateAngle[0][4] = 10.0 * Math.PI / 180;
		}

	}

	private void outputResultToExcel() {
		// TODO 将最终结果输出到Excel
		FileIOAction fa = new FileIOAction();
		column = 10;
		row = rowStart;
		outputMsg = "";
		create = false;
		DecimalFormat df = new DecimalFormat("####0.00");
		AngleCalAction aca = new AngleCalAction();

		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				column = 4;
				double angle = aca.radianToCCAngle(-rotateAngle[i][j]);
				outputMsg = df.format(angle);
				try {
					fa.writeExcel(outputFileName, column, row, outputMsg, create);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				column = 10;
				for (int k = 0; k < ANGLES; k++) {
					outputMsg = df.format(finalAngle[i][j][k]);
					try {
						fa.writeExcel(outputFileName, column + k, row, outputMsg, create);
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				row++;
			}
		}
	}

	private void convertToCC() {
		// TODO 将实验中的弧度全部转化为角度
		// 仅供测试
		AngleCalAction aca = new AngleCalAction();
		LogAction la = new LogAction();
		String stdMsg = "";
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				for (int k = 0; k < DOTS; k++) {
					outputX[i][j][k] = aca.getXFromPolar(inputP[i][j][k], inputRa[i][j][k]);
					outputY[i][j][k] = aca.getYFromPolar(inputP[i][j][k], inputRa[i][j][k]);
					stdMsg = "PolarToCC: group " + (i + 1) + " pic " + (j + 1) + " dot " + (k + 1) + ", X is " + outputX[i][j][k] + ", Y is " + outputY[i][j][k];
					la.logStd("Info", stdMsg);
				}
			}
		}

		calResult();
	}

	private void covertToPolar() {
		// TODO 把输入的直角坐标转换成极坐标并用在测试中
		AngleCalAction aca = new AngleCalAction();
		LogAction la = new LogAction();
		String stdMsg = "";
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				for (int k = 0; k < DOTS; k++) {
					inputP[i][j][k] = aca.getPolarRadiusFromRightAngel(inputX[i][j][k], inputY[i][j][k]);
					inputRa[i][j][k] = aca.getPolarAngleFromRightAngel(inputX[i][j][k], inputY[i][j][k]);
					stdMsg = "CCToPolar: group " + (i + 1) + " pic " + (j + 1) + " dot " + (k + 1) + ", P is " + inputP[i][j][k] + ", Ra is " + inputRa[i][j][k];
					la.logStd("Info", stdMsg);
				}
			}
		}

	}

	private void readInput() {
		// TODO Auto-generated method stub

		LogAction la = new LogAction();
		la.logStd("Info", "Read input text.");

		// 读取点的坐标信息
		currntGroup = 0;
		// 读取选中的图片编号
		FileIOAction fa = new FileIOAction();
		dotsXInputGroupOne = fa.readInputX(0, chosenPicPerGroupOne);
		dotsYInputGroupOne = fa.readInputY(0, chosenPicPerGroupOne);
		stdMsg = "";
		for (int i = 0; i < PICS; i++) {
			la.logStd("Info", "Input for group " + (currntGroup + 1) + " pic " + (i + 1));
			chosenPic[0][i] = chosenPicPerGroupOne.get(i);
			stdMsg = "";
			for (int j = 0; j < DOTS; j++) {
				stdMsg += dotsXInputGroupOne[i][j] + ", ";
				stdMsg += dotsYInputGroupOne[i][j] + ". ";
				inputX[0][i][j] = dotsXInputGroupOne[i][j];
				inputY[0][i][j] = dotsYInputGroupOne[i][j];
			}
			la.logStd("Info", stdMsg);
		}

		currntGroup = 1;
		// 读取选中的图片编号
		dotsXInputGroupTwo = fa.readInputX(1, chosenPicPerGroupTwo);
		dotsYInputGroupTwo = fa.readInputY(1, chosenPicPerGroupTwo);
		stdMsg = "";
		for (int i = 0; i < PICS; i++) {
			la.logStd("Info", "Input for group " + (currntGroup + 1) + " pic " + (i + 1));
			chosenPic[1][i] = chosenPicPerGroupTwo.get(i);
			stdMsg = "";
			for (int j = 0; j < DOTS; j++) {
				stdMsg += dotsXInputGroupTwo[i][j] + ", ";
				stdMsg += dotsYInputGroupTwo[i][j] + ". ";
				inputX[1][i][j] = dotsXInputGroupTwo[i][j];
				inputY[1][i][j] = dotsYInputGroupTwo[i][j];
			}
			la.logStd("Info", stdMsg);
		}

		currntGroup = 2;
		// 读取选中的图片编号
		dotsXInputGroupThree = fa.readInputX(2, chosenPicPerGroupThree);
		dotsYInputGroupThree = fa.readInputY(2, chosenPicPerGroupThree);
		stdMsg = "";
		for (int i = 0; i < PICS; i++) {
			la.logStd("Info", "Input for group " + (currntGroup + 1) + " pic " + (i + 1));
			chosenPic[2][i] = chosenPicPerGroupThree.get(i);
			stdMsg = "";
			for (int j = 0; j < DOTS; j++) {
				stdMsg += dotsXInputGroupThree[i][j] + ", ";
				stdMsg += dotsYInputGroupThree[i][j] + ". ";
				inputX[2][i][j] = dotsXInputGroupThree[i][j];
				inputY[2][i][j] = dotsYInputGroupThree[i][j];
			}
			la.logStd("Info", stdMsg);
		}

		outputExcelDots();

	}

	private void outputExcelDots() {
		FileIOAction fa = new FileIOAction();
		column = 5;
		row = rowStart;
		outputMsg = "";
		create = false;
		// TODO 输出随机选择后每张图片的点，到Excel
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				for (int k = 0; k < DOTS; k++) {
					// 输出每张图的点
					outputMsg = String.valueOf(inputX[i][j][k]) + ", " + String.valueOf(inputY[i][j][k]);
					try {
						fa.writeExcel(outputFileName, column + k, row, outputMsg, create);
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				row++;
			}
		}

	}

	public void initial() {

		LogAction la = new LogAction();
		la.logStd("Info", "---Test View---");
		la.logStd("Info", "Initializing test view.");
		SimpleDateFormat dfFileName = new SimpleDateFormat("yyyyMMdd");
		String sysTimeFileName = dfFileName.format(new Date()).toString();
		outputFileName = "HPTest_" + sysTimeFileName + ".xls";
		outputFile = new File("C:/hptest/output/result_output/" + outputFileName);
		rowStart = 0;

		DirectoryAction da = new DirectoryAction();
		picNumInFile = da.getFileAmount(1);

		// 初始化图片的路径
		inputPathImageGroup = new String[GROUPS][picNumInFile];
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < picNumInFile; j++) {
				inputPathImageGroup[i][j] = "C:/hptest/input/images/group" + (i + 1) + "/" + (j + 1) + ".jpg";
				// image[i][j] = new
				// ImageIcon(inputPathImageGroup[i][j]).getImage();
			}
		}

		// 初始化角度
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				for (int k = 0; k < DOTS; k++) {
					inputX[i][j][k] = 0.00;
					inputY[i][j][k] = 0.00;
					inputP[i][j][k] = 0.00;
					inputRa[i][j][k] = 0.00;
					outputX[i][j][k] = 0.00;
					outputY[i][j][k] = 0.00;
				}
				rotateAngle[i][j] = 0.00;
			}

		}

		// 初始化结果坐标
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				for (int k = 0; k < ANGLES; k++) {
					finalAngle[i][j][k] = 0.00;
					finalAngleStr[i][j][k] = "";
				}
			}
		}

		// 输出Excel的框架信息
		outputExcelFrame();

		// 随机选择图片
		getRandomPic();
		// 输出选中的图片，以及它们的点和初始坐标
		outputExcelRandomPic();

		// 初始化随机旋转角度
		getRandomAngle();
		// 输出生成的角度
		outputExcelRandomAngle();

	}

	private void outputExcelRandomAngle() {
		// TODO 输出生成的角度
		LogAction la = new LogAction();
		la.logStd("Info", "Start to output random agle to Excel file.");
		FileIOAction fa = new FileIOAction();
		column = 3;
		row = rowStart;
		outputMsg = "";
		create = false;
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				outputMsg = String.valueOf(-rotateAngle[i][j]);
				try {
					fa.writeExcel(outputFileName, column, row, outputMsg, create);
					row++;
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		la.logStd("Info", "Random Angle output to Excel file.");

	}

	private void outputExcelRandomPic() {
		// TODO 输出选中的图片，以及它们的点和初始坐标
		LogAction la = new LogAction();
		FileIOAction fa = new FileIOAction();
		column = 1;
		row = rowStart;
		outputMsg = "";
		create = false;
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				// 输出每个组中随机选出的图片序号
				try {
					outputMsg = String.valueOf(i + 1);
					fa.writeExcel(outputFileName, column, row, outputMsg, create);
					column++;
					outputMsg = String.valueOf(chosenPic[i][j] + 1);
					fa.writeExcel(outputFileName, column, row, outputMsg, create);
					column--;
					row++;
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	private void outputExcelFrame() {
		// TODO 输出Excel的框架信息
		// 检查输出文档是否存在，若不存在就新建一个
		LogAction la = new LogAction();
		DirectoryAction da = new DirectoryAction();
		if (!da.whetherFileExists(outputFile)) {
			// 文件不存在，新建之，并写入标题
			la.logStd("Info", "Output file does not exist, create it.");
			FileIOAction fa = new FileIOAction();
			column = 0;
			row = 0;
			outputMsg = "";
			create = true;
			try {
				outputMsg = "测试序号";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				create = false;
				column++;
				outputMsg = "组别";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "图片序号";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "初始随机角度";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "旋转后角度";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点1";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点2";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点3";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点4";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点5";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点1点2夹角";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点1点3夹角";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点1点4夹角";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点1点5夹角";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点2点3夹角";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点2点4夹角";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点2点5夹角";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点3点4夹角";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点3点5夹角";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "点4点5夹角";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				rowStart = 1;
				testStart = 1;
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 若存在，则获得写入位置，直接追加
			FileIOAction fa = new FileIOAction();
			try {
				System.out.println("start to get row count at " + outputFileName);
				rowStart = fa.getRowStart(outputFileName);
				// rowStart = 31;
				System.out.println("rowStart: " + rowStart);
				// testStart = fa.getTestStart(outputFileName);
				testStart = (rowStart - 1) / 15 + 1;
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}
			la.logStd("Info", "Output file exists, will tarts at row " + rowStart);
		}
		FileIOAction fa = new FileIOAction();
		row = rowStart;
		column = 0;
		outputMsg = String.valueOf(testStart);
		for (int i = 0; i < GROUPS * PICS; i++) {
			// 每次测试一共3组*5图
			try {
				create = false;
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			row++;
		}
	}

	private void getRandomAngle() {
		// TODO 对每张选择的图片赋予一个随机角度
		AngleCalAction aca = new AngleCalAction();
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				rotateAngle[i][j] = Double.valueOf(aca.getRandomAngle());
			}
		}

	}

	private void getRandomPic() {
		// TODO 随机选择图片，并去除重复编号
		LogAction la = new LogAction();
		DirectoryAction da = new DirectoryAction();
		int fileCount = 0, randomNum = 0;

		currntGroup = 0;
		fileCount = da.getFileAmount(currntGroup);
		la.logStd("Info", "There are " + fileCount + " files in group " + (currntGroup + 1));
		chosenPicPerGroupOne = new ArrayList<Integer>();
		for (int j = 0; j < PICS; j++) {
			randomNum = (int) (Math.random() * fileCount);
			while (chosenPicPerGroupOne.contains(randomNum)) {
				randomNum = (randomNum + 1) % fileCount;
			}
			chosenPicPerGroupOne.add(randomNum);
		}
		Collections.sort(chosenPicPerGroupOne);
		for (int i = 0; i < PICS; i++) {
			chosenPic[currntGroup][i] = chosenPicPerGroupOne.get(i);
		}

		stdMsg = "";
		stdMsg = "Random pic chosen for group " + (currntGroup + 1) + ": ";
		for (int i : chosenPicPerGroupOne) {
			stdMsg = stdMsg + (i + 1) + ", ";
		}
		la.logStd("Info", stdMsg);

		currntGroup = 1;
		fileCount = da.getFileAmount(currntGroup);
		la.logStd("Info", "There are " + fileCount + " files in group " + (currntGroup + 1));
		chosenPicPerGroupTwo = new ArrayList<Integer>();
		for (int j = 0; j < PICS; j++) {
			randomNum = (int) (Math.random() * fileCount);
			while (chosenPicPerGroupTwo.contains(randomNum)) {
				randomNum = (randomNum + 1) % fileCount;
			}
			chosenPicPerGroupTwo.add(randomNum);
		}
		Collections.sort(chosenPicPerGroupTwo);
		for (int i = 0; i < PICS; i++) {
			chosenPic[currntGroup][i] = chosenPicPerGroupTwo.get(i);
		}
		stdMsg = "";
		stdMsg = "Random pic chosen for group " + (currntGroup + 1) + ": ";
		for (int i : chosenPicPerGroupTwo) {
			stdMsg = stdMsg + (i + 1) + ", ";
		}
		la.logStd("Info", stdMsg);

		currntGroup = 2;
		fileCount = da.getFileAmount(currntGroup);
		la.logStd("Info", "There are " + fileCount + " files in group " + (currntGroup + 1));
		chosenPicPerGroupThree = new ArrayList<Integer>();
		for (int j = 0; j < PICS; j++) {
			randomNum = (int) (Math.random() * fileCount);
			while (chosenPicPerGroupThree.contains(randomNum)) {
				randomNum = (randomNum + 1) % fileCount;
			}
			chosenPicPerGroupThree.add(randomNum);
		}
		Collections.sort(chosenPicPerGroupThree);
		for (int i = 0; i < PICS; i++) {
			chosenPic[currntGroup][i] = chosenPicPerGroupThree.get(i);
		}
		stdMsg = "";
		stdMsg = "Random pic chosen for group " + (currntGroup + 1) + ": ";
		for (int i : chosenPicPerGroupThree) {
			stdMsg = stdMsg + (i + 1) + ", ";
		}
		la.logStd("Info", stdMsg);

	}

	public void showPage() {
		// 设置框架
		frame.setLocation(300, 200);

		// 总的框架和布局
		jpBase = new JPanel();
		jpBase.setPreferredSize(new Dimension(IMAGE_WIDTH + RIGHR_PANEL_WIDTH, IMAGE_HEGHT));
		cl = new CardLayout();
		jpBase.setLayout(cl);
		jpBase.setBackground(Color.WHITE);
		frame.setContentPane(jpBase);

		// frame.setResizable(false);
		GridBagLayout gbl = new GridBagLayout();
		jpBase.setLayout(gbl);
		GridBagConstraints gbcJpBase = new GridBagConstraints();

		Font fontForFillingText = new Font("TimesRoman", Font.PLAIN, 30);

		// 设置左边的面板
		jpTestLeft = new JPanel();
		jpTestLeft.setSize(IMAGE_WIDTH, IMAGE_HEGHT);
		rv = new RotateView();
		// jpTestLeft.add(rv);
		DirectoryAction da = new DirectoryAction();
		currntGroup = 0;
		currntPic = 0;
		// 真图的编号从1开始
		picToImage = chosenPic[currntGroup][currntPic];
		File inputImage = new File(inputPathImageGroup[currntGroup][picToImage]);
		rv.image = new ImageIcon(inputPathImageGroup[currntGroup][picToImage]).getImage();
		rv.repaint();
		jpTestLeft.setBackground(Color.RED);

		gbcJpBase.weightx = 90.0;
		gbcJpBase.weighty = 100.0;
		gbcJpBase.fill = GridBagConstraints.BOTH;
		jpBase.add(rv, gbcJpBase);
		jpBase.addKeyListener(this);

		// 设置右边的面板
		jpTestRight = new JPanel();

		GridBagLayout gblJpRight = new GridBagLayout();
		jpTestRight.setLayout(gblJpRight);
		GridBagConstraints gbcJpRight = new GridBagConstraints();
		int gridJpRightX = 0;
		int gridJpRightY = 0;

		jbPrev = new JButton("上一张图片");
		jbPrev.setFont(fontForFillingText);

		gbcJpRight.insets = new Insets(5, 5, 5, 5);
		gbcJpRight.gridwidth = 2;
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jbPrev.addActionListener(this);
		jpTestRight.add(jbPrev, gbcJpRight);

		gridJpRightY++;
		gridJpRightX = 0;
		jlfi1 = new JLabel(" ");
		jlfi1.setFont(fontForFillingText);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jlfi1, gbcJpRight);

		gridJpRightY++;
		gridJpRightX = 0;
		jbBigLeft = new JButton("大幅左转");
		jbBigLeft.setFont(fontForFillingText);
		gbcJpRight.gridwidth = 1;
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jbBigLeft.addActionListener(this);
		jpTestRight.add(jbBigLeft, gbcJpRight);

		gridJpRightX++;
		jbBigRight = new JButton("大幅右转");
		jbBigRight.setFont(fontForFillingText);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jbBigRight.addActionListener(this);
		jpTestRight.add(jbBigRight, gbcJpRight);

		gridJpRightY++;
		gridJpRightX = 0;
		jlfi4 = new JLabel(" ");
		jlfi4.setFont(fontForFillingText);
		gbcJpRight.gridwidth = 2;
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jlfi4, gbcJpRight);

		gridJpRightX = 0;
		gridJpRightY++;
		jbSmallLeft = new JButton("小幅左转");
		jbSmallLeft.setFont(fontForFillingText);
		gbcJpRight.gridwidth = 1;
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jbSmallLeft.addActionListener(this);
		jpTestRight.add(jbSmallLeft, gbcJpRight);

		gridJpRightX++;
		jbSmallRight = new JButton("小幅右转");
		jbSmallRight.setFont(fontForFillingText);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jbSmallRight.addActionListener(this);
		jpTestRight.add(jbSmallRight, gbcJpRight);

		gridJpRightX = 0;
		gridJpRightY++;
		jlfi6 = new JLabel(" ");
		jlfi6.setFont(fontForFillingText);
		gbcJpRight.gridwidth = 2;
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jlfi6, gbcJpRight);

		gridJpRightX = 0;
		gridJpRightY++;
		jbNext = new JButton("下一张图片");
		jbNext.setFont(fontForFillingText);
		gbcJpRight.insets = new Insets(5, 5, 5, 5);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jbNext.addActionListener(this);
		jpTestRight.add(jbNext, gbcJpRight);

		// gbcJpBase.gridwidth = 4;
		gbcJpBase.weightx = 10.0;
		gbcJpBase.weighty = 100.0;
		gbcJpBase.fill = GridBagConstraints.BOTH;
		jpBase.add(jpTestRight, gbcJpBase);

		// jpBase.add(jpTestLeft, BorderLayout.CENTER);
		// jpBase.add(jpTestRight, BorderLayout.EAST);
		// frame.add(jpBase);
		frame.addKeyListener(this);
		frame.setTitle("头部位置研究测试");
		frame.pack();
		frame.setVisible(true);
		System.out.println("frame width: " + frame.getWidth());
		System.out.println("frame height: " + frame.getHeight());
		System.out.println("jpbase width: " + jpBase.getWidth());
		System.out.println("jpbase height: " + jpBase.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		checkButtonAvailbility(currntGroup, currntPic);

	}

	private void checkButtonAvailbility(int currentGroup, int currentPic) {
		// TODO Auto-generated method stub
		System.out.println("checkButtonAvailbility group: " + currentGroup + ", pic: " + currentPic);
		if (0 == currentGroup && 0 == currentPic) {
			// 第一张图片没有上一张
			jbPrev.setEnabled(false);

		} else {
			jbPrev.setEnabled(true);
		}

		if (GROUPS - 1 == currentGroup && PICS - 1 == currentPic) {
			// 最后一张图片结束后是结束测试的按钮
			jbNext.setText("结束测试");
		} else {
			jbNext.setText("下一张图片");
		}
	}

	public static void main(String[] args) {
		new TestView();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 如果用户在欢迎界面单击"开始测试"，则转到准备界面
		if (e.getSource() == jbNext) {
			System.out.println("Next pic");
			currntPic++;

			if (currntGroup == GROUPS - 1 && currntPic == PICS) {
				// 最后一张图片结束后是结束测试的按钮
				int replaced = JOptionPane.showConfirmDialog(null, "确认结束测试？", "测试即将结束", JOptionPane.YES_NO_OPTION);
				switch (replaced) {
				// 如果选择了“Yes”
				case JOptionPane.YES_OPTION:
					System.out.println("Yes is chosen");
					// 退出测试界面，开始计算结果
					frame.setVisible(false);
					sumupRotateAngle();
					break;
				default:
					System.out.println("Others is chosen");
					break;
				}
			} else {
				checkButtonAvailbility(currntGroup, currntPic);
				System.out.println(rv.getXuanzhuan());
				rollOverPicNumber(1);
				rv.image = new ImageIcon(inputPathImageGroup[currntGroup][picToImage]).getImage();
				DirectoryAction da = new DirectoryAction();
				File inputText = new File(inputPathImageGroup[currntGroup][picToImage]);
				System.out.println("image exist2: " + da.whetherDirectoryExists(inputText));
				rv.setXuanzhuan(-rotateAngle[currntGroup][currntPic]);
				rv.repaint();
			}
		} else if (e.getSource() == jbPrev) {
			System.out.println("Prev pic");
			currntPic--;
			checkButtonAvailbility(currntGroup, currntPic);
			rollOverPicNumber(-1);
			rv.image = new ImageIcon(inputPathImageGroup[currntGroup][picToImage]).getImage();
			DirectoryAction da = new DirectoryAction();
			File inputText = new File(inputPathImageGroup[currntGroup][picToImage]);
			System.out.println("image exist2: " + da.whetherDirectoryExists(inputText));
			rv.setXuanzhuan(-rotateAngle[currntGroup][currntPic]);
			rv.repaint();
		} else if (jbBigLeft == e.getSource()) {

			System.out.println("Turn left by 10");
			System.out.println("Rotating group " + currntGroup + " pic " + currntPic + " by +10 degrees.");
			rotateAngle[currntGroup][currntPic] += Math.PI / 18;
			// 旋转和角度计算方向相反
			rv.setXuanzhuan(-rotateAngle[currntGroup][currntPic]);
			rv.repaint();
		} else if (jbBigRight == e.getSource()) {

			System.out.println("Turn right by 10");
			// String inputPath2 = "C:/hptest/input/images/group1/3.jpg";
			// File inputText = new File(inputPath2);
			//
			// rv.image = new ImageIcon(inputPath2).getImage();
			// DirectoryAction da = new DirectoryAction();
			// System.out.println("image exist2: "
			// + da.whetherDirectoryExists(inputText));
			System.out.println("Rotating group " + currntGroup + " pic " + currntPic + " by -10 degrees.");
			// rotateAngle[group][pic] = rv.getXuanzhuan();
			rotateAngle[currntGroup][currntPic] -= Math.PI / 18;
			// 旋转和角度计算方向相反
			rv.setXuanzhuan(-rotateAngle[currntGroup][currntPic]);
			rv.repaint();
		}

	}

	private void calVeriticalAngle() {
		// TODO 计算点两两相连后和中心线的夹角
		for (int i = 0; i < ANGLES; i++) {
			// Do something
		}
		// dotsPRadiusGroupOne resultAngleGroupOne

	}

	private void sumupRotateAngle() {
		// TODO 在结束测试之后汇总旋转的角度
		// 即，每个点在转换成极坐标之后，本身就带有一个旋转角度inputRa[i][j][k]，现在将用户人为旋转的角度叠加在上面
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				for (int k = 0; k < DOTS; k++) {
					inputRa[i][j][k] += -rotateAngle[i][j];
				}
			}
		}
		convertToCC();
	}

	private void rollOverPicNumber(int roll) {
		// TODO 当用户点击“上一张”或者“下一张”时，测试图片的编号对应的往后或者往前。
		// pic 和 group 的取值范围是0~length-1
		if (currntPic < 0) {
			currntGroup--;
			currntPic += PICS;
		}
		if (currntPic > PICS - 1) {
			currntGroup++;
			currntPic -= PICS;
		}
		picToImage = chosenPic[currntGroup][currntPic];
		System.out.println("rolled over group: " + currntGroup + ", pic: " + currntPic);
		System.out.println("mapped image: " + currntGroup + ", pic: " + picToImage);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		char key = e.getKeyChar();
		System.out.println(key);
		rv.setXuanzhuan(rv.getXuanzhuan() - Math.PI / 2);
		rv.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		char key = e.getKeyChar();
		System.out.println(key);
		rv.setXuanzhuan(rv.getXuanzhuan() - Math.PI / 2);
		rv.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
