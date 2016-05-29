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

	// ���
	JFrame frame = new JFrame();

	// �ܵĿ�ܺͲ���
	JPanel jpBase;
	CardLayout cl;

	// ����ϵ�����
	JPanel jpTestLeft, jpTestRight;
	JLabel jlPrev, jlNext, jl3;
	JLabel jlRd1, jlRd2, jlRd3;
	JButton jbNext, jbPrev, jbBigLeft, jbBigRight, jbSmallLeft, jbSmallRight;
	JButton jbRd1;
	JTextField jtf1;
	RotateView rv;

	// ����������Ŀ
	JLabel jlfi1, jlfi2, jlfi3, jlfi4, jlfi5, jlfi6, jlfi7, jlfi8;
	JLabel jlfiRd1, jlfiRd2, jlfiRd3, jlfiRd4, jlfiRd5, jlfiRd6, jlfiRd7, jlfiRd8, jlfiRd9, jlfiRd10, jlfiRd11, jlfiRd12, jlfiRd13, jlfiRd14;

	// ȫ�ֱ���
	int testerCount;
	int testCount = 0;
	// ÿ�������ѡ������ͼ
	static final int PICS = 5;
	static final int PIC_PER_GROUP_UAT = 5;
	// ͼƬ�����ͼƬ����
	static int picNumInFile = 0;
	// �ж��ٸ���
	static final int GROUPS = 3;
	static final int GROUPS_UAT = 1;
	// ������ϼƹ��ж���ͼ
	static final int FULL_TEST_AMOUNT = PICS * GROUPS;
	static final int FULL_TEST_AMOUNT_UAT = PIC_PER_GROUP_UAT * GROUPS_UAT;
	// ͼƬ�Ŀ�͸�
	static final int IMAGE_WIDTH = 600;
	static final int IMAGE_HEGHT = 600;
	static final int RIGHR_PANEL_WIDTH = 400;
	// ÿ��ͼ���м�����
	static final int DOTS = 5;
	static final int DOTS_UAT = 5;
	// ����ĽǶ�������5����Ļ�����10����ÿ��ͼ��
	static final int ANGLES = DOTS * (DOTS - 1) / 2;

	// ÿ������鵽��ͼƬ��ţ���Ӧÿһ��
	static int chosenPic[][] = new int[GROUPS][PICS];
	static List<Integer> chosenPicPerGroupOne = new ArrayList<Integer>();
	static List<Integer> chosenPicPerGroupTwo = new ArrayList<Integer>();
	static List<Integer> chosenPicPerGroupThree = new ArrayList<Integer>();

	// �����X��Y�����꣬��Ӧÿһ�飬ÿ��ͼ��ÿ����
	static Double inputX[][][] = new Double[GROUPS][PICS][DOTS];
	static Double inputY[][][] = new Double[GROUPS][PICS][DOTS];

	// �����X��Y������ת�����Ӧ�ļ����꣬��Ӧÿһ�飬ÿ��ͼ��ÿ����
	static Double inputP[][][] = new Double[GROUPS][PICS][DOTS];
	static Double inputRa[][][] = new Double[GROUPS][PICS][DOTS];
	// �����X��Y�����꣬��Ӧÿһ�飬ÿ��ͼ��ÿ����
	static Double outputX[][][] = new Double[GROUPS][PICS][DOTS];
	static Double outputY[][][] = new Double[GROUPS][PICS][DOTS];
	// ���յĽǶȣ�ֱ�����ꡣ��Ӧÿһ�飬ÿ��ͼ��ÿ���Ƕ�
	static Double finalAngle[][][] = new Double[GROUPS][PICS][ANGLES];
	static String finalAngleStr[][][] = new String[GROUPS][PICS][ANGLES];

	static int currntGroup = 0; // ��¼��ǰ���ڲ��Եڼ���
	static int currntPic = 0; // ��¼��ǰ���ڲ�������ĵڼ���ͼ
	static int picToImage = 0; // ��¼��ǰ�����ͼ����ĵڼ���ͼ
	// ���Ե�һ�飬��Ӧÿ��ͼ��ÿ����
	Double dotsXInputGroupOne[][] = new Double[PICS][DOTS];
	Double dotsXInputGroupTwo[][] = new Double[PICS][DOTS];
	Double dotsXInputGroupThree[][] = new Double[PICS][DOTS];
	Double dotsYInputGroupOne[][] = new Double[PICS][DOTS];
	Double dotsYInputGroupTwo[][] = new Double[PICS][DOTS];
	Double dotsYInputGroupThree[][] = new Double[PICS][DOTS];
	// �������������ʹ�ü�����洢
	Double dotsPRadiusGroupOne[][] = new Double[PICS][DOTS * PICS];
	Double dotsPAngleGroupOne[][] = new Double[PICS][DOTS * PICS];

	// ͼƬ�ļ�λ��
	String inputPathImageGroup[][];

	// ͼƬ����Ӧÿ���飬ÿ��ͼ
	Image image[][] = new Image[GROUPS][PICS];
	// ���ͼƬ����ת�Ƕ���Ϣ
	Double rotateAngle[][] = new Double[GROUPS][PICS];

	// ��Ž�����ļ����Լ������е�����
	static String outputFileName = "";
	static File outputFile;
	static int rowStart;
	static int testStart; // ��¼���ǵ���ĵڼ��β���
	static int column;
	static int row;
	static String outputMsg;
	boolean create = true;
	boolean test = true;

	// ���������log
	String stdMsg = "";

	public TestView() {
		initial();
		readInput();
		covertToPolar();
		showPage();
	}

	private void calResult() {
		// TODO �������յ�ֱ�����꣬�������д��ߵļн�
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
		// TODO ��������
		if (test) {
			rotateAngle[0][0] = 30.0 * Math.PI / 180;
			rotateAngle[0][1] = -15.0 * Math.PI / 180;
			rotateAngle[0][2] = 5.0 * Math.PI / 180;
			rotateAngle[0][3] = -23.0 * Math.PI / 180;
			rotateAngle[0][4] = 10.0 * Math.PI / 180;
		}

	}

	private void outputResultToExcel() {
		// TODO �����ս�������Excel
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
		// TODO ��ʵ���еĻ���ȫ��ת��Ϊ�Ƕ�
		// ��������
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
		// TODO �������ֱ������ת���ɼ����겢���ڲ�����
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

		// ��ȡ���������Ϣ
		currntGroup = 0;
		// ��ȡѡ�е�ͼƬ���
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
		// ��ȡѡ�е�ͼƬ���
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
		// ��ȡѡ�е�ͼƬ���
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
		// TODO ������ѡ���ÿ��ͼƬ�ĵ㣬��Excel
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				for (int k = 0; k < DOTS; k++) {
					// ���ÿ��ͼ�ĵ�
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

		// ��ʼ��ͼƬ��·��
		inputPathImageGroup = new String[GROUPS][picNumInFile];
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < picNumInFile; j++) {
				inputPathImageGroup[i][j] = "C:/hptest/input/images/group" + (i + 1) + "/" + (j + 1) + ".jpg";
				// image[i][j] = new
				// ImageIcon(inputPathImageGroup[i][j]).getImage();
			}
		}

		// ��ʼ���Ƕ�
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

		// ��ʼ���������
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				for (int k = 0; k < ANGLES; k++) {
					finalAngle[i][j][k] = 0.00;
					finalAngleStr[i][j][k] = "";
				}
			}
		}

		// ���Excel�Ŀ����Ϣ
		outputExcelFrame();

		// ���ѡ��ͼƬ
		getRandomPic();
		// ���ѡ�е�ͼƬ���Լ����ǵĵ�ͳ�ʼ����
		outputExcelRandomPic();

		// ��ʼ�������ת�Ƕ�
		getRandomAngle();
		// ������ɵĽǶ�
		outputExcelRandomAngle();

	}

	private void outputExcelRandomAngle() {
		// TODO ������ɵĽǶ�
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
		// TODO ���ѡ�е�ͼƬ���Լ����ǵĵ�ͳ�ʼ����
		LogAction la = new LogAction();
		FileIOAction fa = new FileIOAction();
		column = 1;
		row = rowStart;
		outputMsg = "";
		create = false;
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				// ���ÿ���������ѡ����ͼƬ���
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
		// TODO ���Excel�Ŀ����Ϣ
		// �������ĵ��Ƿ���ڣ��������ھ��½�һ��
		LogAction la = new LogAction();
		DirectoryAction da = new DirectoryAction();
		if (!da.whetherFileExists(outputFile)) {
			// �ļ������ڣ��½�֮����д�����
			la.logStd("Info", "Output file does not exist, create it.");
			FileIOAction fa = new FileIOAction();
			column = 0;
			row = 0;
			outputMsg = "";
			create = true;
			try {
				outputMsg = "�������";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				create = false;
				column++;
				outputMsg = "���";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "ͼƬ���";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��ʼ����Ƕ�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��ת��Ƕ�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��1";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��2";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��3";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��4";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��5";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��1��2�н�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��1��3�н�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��1��4�н�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��1��5�н�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��2��3�н�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��2��4�н�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��2��5�н�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��3��4�н�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��3��5�н�";
				fa.writeExcel(outputFileName, column, row, outputMsg, create);
				column++;
				outputMsg = "��4��5�н�";
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
			// �����ڣ�����д��λ�ã�ֱ��׷��
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
			// ÿ�β���һ��3��*5ͼ
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
		// TODO ��ÿ��ѡ���ͼƬ����һ������Ƕ�
		AngleCalAction aca = new AngleCalAction();
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < PICS; j++) {
				rotateAngle[i][j] = Double.valueOf(aca.getRandomAngle());
			}
		}

	}

	private void getRandomPic() {
		// TODO ���ѡ��ͼƬ����ȥ���ظ����
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
		// ���ÿ��
		frame.setLocation(300, 200);

		// �ܵĿ�ܺͲ���
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

		// ������ߵ����
		jpTestLeft = new JPanel();
		jpTestLeft.setSize(IMAGE_WIDTH, IMAGE_HEGHT);
		rv = new RotateView();
		// jpTestLeft.add(rv);
		DirectoryAction da = new DirectoryAction();
		currntGroup = 0;
		currntPic = 0;
		// ��ͼ�ı�Ŵ�1��ʼ
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

		// �����ұߵ����
		jpTestRight = new JPanel();

		GridBagLayout gblJpRight = new GridBagLayout();
		jpTestRight.setLayout(gblJpRight);
		GridBagConstraints gbcJpRight = new GridBagConstraints();
		int gridJpRightX = 0;
		int gridJpRightY = 0;

		jbPrev = new JButton("��һ��ͼƬ");
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
		jbBigLeft = new JButton("�����ת");
		jbBigLeft.setFont(fontForFillingText);
		gbcJpRight.gridwidth = 1;
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jbBigLeft.addActionListener(this);
		jpTestRight.add(jbBigLeft, gbcJpRight);

		gridJpRightX++;
		jbBigRight = new JButton("�����ת");
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
		jbSmallLeft = new JButton("С����ת");
		jbSmallLeft.setFont(fontForFillingText);
		gbcJpRight.gridwidth = 1;
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jbSmallLeft.addActionListener(this);
		jpTestRight.add(jbSmallLeft, gbcJpRight);

		gridJpRightX++;
		jbSmallRight = new JButton("С����ת");
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
		jbNext = new JButton("��һ��ͼƬ");
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
		frame.setTitle("ͷ��λ���о�����");
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
			// ��һ��ͼƬû����һ��
			jbPrev.setEnabled(false);

		} else {
			jbPrev.setEnabled(true);
		}

		if (GROUPS - 1 == currentGroup && PICS - 1 == currentPic) {
			// ���һ��ͼƬ�������ǽ������Եİ�ť
			jbNext.setText("��������");
		} else {
			jbNext.setText("��һ��ͼƬ");
		}
	}

	public static void main(String[] args) {
		new TestView();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// ����û��ڻ�ӭ���浥��"��ʼ����"����ת��׼������
		if (e.getSource() == jbNext) {
			System.out.println("Next pic");
			currntPic++;

			if (currntGroup == GROUPS - 1 && currntPic == PICS) {
				// ���һ��ͼƬ�������ǽ������Եİ�ť
				int replaced = JOptionPane.showConfirmDialog(null, "ȷ�Ͻ������ԣ�", "���Լ�������", JOptionPane.YES_NO_OPTION);
				switch (replaced) {
				// ���ѡ���ˡ�Yes��
				case JOptionPane.YES_OPTION:
					System.out.println("Yes is chosen");
					// �˳����Խ��棬��ʼ������
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
			// ��ת�ͽǶȼ��㷽���෴
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
			// ��ת�ͽǶȼ��㷽���෴
			rv.setXuanzhuan(-rotateAngle[currntGroup][currntPic]);
			rv.repaint();
		}

	}

	private void calVeriticalAngle() {
		// TODO ���������������������ߵļн�
		for (int i = 0; i < ANGLES; i++) {
			// Do something
		}
		// dotsPRadiusGroupOne resultAngleGroupOne

	}

	private void sumupRotateAngle() {
		// TODO �ڽ�������֮�������ת�ĽǶ�
		// ����ÿ������ת���ɼ�����֮�󣬱���ʹ���һ����ת�Ƕ�inputRa[i][j][k]�����ڽ��û���Ϊ��ת�ĽǶȵ���������
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
		// TODO ���û��������һ�š����ߡ���һ�š�ʱ������ͼƬ�ı�Ŷ�Ӧ�����������ǰ��
		// pic �� group ��ȡֵ��Χ��0~length-1
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
