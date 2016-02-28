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
	final int TEST_PER_GROUP = 5;
	final int GROUP_NUMBER = 1;
	final int FULL_TEST_AMOUNT = TEST_PER_GROUP * GROUP_NUMBER;
	final int IMAGE_WIDTH = 600;
	final int IMAGE_HEGHT = 600;
	final int RIGHR_PANEL_WIDTH = 400;
	final int DOTS = 4;
	final int PICS = 5;
	final int ANGLE_NUMBER_ALL = DOTS * (DOTS - 1) / 2; // 结果的角度总数，4个点的话就是6个。每张图。

	static int group = 0; // 记录当前是在测试第几组
	static int pic = 0; // 记录当前是在测试组里的第几个图
	// 测试第一组：每组7张图片，每张图片4个点。
	Double dotsXInputGroupOne[][] = new Double[TEST_PER_GROUP][DOTS * PICS];
	Double dotsYInputGroupOne[][] = new Double[TEST_PER_GROUP][DOTS * PICS];
	// 测试里面的坐标使用极坐标存储
	Double dotsPRadiusGroupOne[][] = new Double[TEST_PER_GROUP][DOTS * PICS];
	Double dotsPAngleGroupOne[][] = new Double[TEST_PER_GROUP][DOTS * PICS];

	// 图片文件位置
	String inputPathImageGroup[][] = new String[GROUP_NUMBER][TEST_PER_GROUP];
	String INPUT_PATH_TEXT[] = new String[3];

	// String ImageNumber[][] = new String[GROUP_NUMBER][TEST_PER_GROUP];
	Image image[][] = new Image[GROUP_NUMBER][TEST_PER_GROUP];
	// 存放图片中旋转角度信息
	Double rotateAngle[][] = new Double[GROUP_NUMBER][TEST_PER_GROUP];

	// 存放结果的角度
	Double resultAngleGroupOne[][] = new Double[TEST_PER_GROUP][ANGLE_NUMBER_ALL];

	public TestView() {
		initial();
		showPage();
	}

	public void initial() {

		// 初始化
		for (int i = 0; i < GROUP_NUMBER; i++) {
			for (int j = 0; j < TEST_PER_GROUP; j++) {
				// 创建图片的路径
				inputPathImageGroup[i][j] = "C:/hptest/input/images/group" + (i + 1) + "/" + (j + 1) + ".jpg";
				// 角度
				rotateAngle[i][j] = 0.00;
			}
		}
		System.out.println(inputPathImageGroup[0][0]);

		// 读取点的坐标信息
		INPUT_PATH_TEXT[0] = "C:/hptest/input/text/position_point_group1.txt";
		INPUT_PATH_TEXT[1] = "C:/hptest/input/text/position_point_group2.txt";
		INPUT_PATH_TEXT[2] = "C:/hptest/input/text/position_point_group3.txt";
		String filename = INPUT_PATH_TEXT[0];
		FileIOAction fa = new FileIOAction();
		Double dotsAngle[] = new Double[PICS * DOTS * 2];
		dotsAngle = fa.readDotPosition(filename, PICS * DOTS * 2);

		// 开始添加每个测试的X 和 Y 坐标
		int dotNumber = 0;
		for (int j = 0; j < TEST_PER_GROUP; j++) {
			for (int i = 0; i < DOTS; i++) {
				dotsXInputGroupOne[j][i] = dotsAngle[dotNumber];
				dotsYInputGroupOne[j][i] = dotsAngle[dotNumber + 1];
				System.out.println("dotX, pic " + j + " x coor " + i + " added as " + dotsXInputGroupOne[j][i]);
				System.out.println("dotY , pic " + j + " y coor " + i + " added as " + dotsYInputGroupOne[j][i]);
				dotNumber += 2;
			}
		}

		// 转化原始坐标为极坐标
		AngleCalAction aca = new AngleCalAction();
		// 第一组
		for (int j = 0; j < TEST_PER_GROUP; j++) {
			for (int i = 0; i < DOTS; i = i + 2) {
				dotsPRadiusGroupOne[j][i] = aca.getPolarRadiusFromRightAngel(dotsXInputGroupOne[j][i], dotsYInputGroupOne[j][i]);
				dotsPAngleGroupOne[j][i] = aca.getPolarRadiusFromRightAngel(dotsYInputGroupOne[j][i], dotsXInputGroupOne[j][i]);
				System.out.println("polar radius, pic " + j + " radius " + i + " added as " + dotsPRadiusGroupOne[j][i]);
				System.out.println("polar angle, pic " + j + " angel " + i + " added as " + dotsPAngleGroupOne[j][i]);
			}
		}

		// 初始化随机旋转角度

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

		// 全局变量
		image[group][pic] = new ImageIcon(inputPathImageGroup[group][pic]).getImage();

		// 设置左边的面板
		jpTestLeft = new JPanel();
		jpTestLeft.setSize(IMAGE_WIDTH, IMAGE_HEGHT);
		rv = new RotateView();
		// jpTestLeft.add(rv);
		DirectoryAction da = new DirectoryAction();
		File inputText = new File(inputPathImageGroup[group][pic]);
		System.out.println("image exist2: " + da.whetherDirectoryExists(inputText));
		rv.image = new ImageIcon(inputPathImageGroup[group][pic]).getImage();
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
		jbBigLeft = new JButton("左转10°");
		jbBigLeft.setFont(fontForFillingText);
		gbcJpRight.gridwidth = 1;
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jbBigLeft.addActionListener(this);
		jpTestRight.add(jbBigLeft, gbcJpRight);

		gridJpRightX++;
		jbBigRight = new JButton("右转10°");
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
		jbSmallLeft = new JButton("左转1° ");
		jbSmallLeft.setFont(fontForFillingText);
		gbcJpRight.gridwidth = 1;
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jbSmallLeft.addActionListener(this);
		jpTestRight.add(jbSmallLeft, gbcJpRight);

		gridJpRightX++;
		jbSmallRight = new JButton("右转1° ");
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

		checkButtonAvailbility(testCount);

	}

	private void checkButtonAvailbility(int testCount2) {
		// TODO Auto-generated method stub
		System.out.println("Entered into checkButtonAvailbility");
		System.out.println("testCount: " + testCount);
		if (0 == testCount) {
			// 第一张图片没有上一张
			jbPrev.setEnabled(false);

		} else {
			jbPrev.setEnabled(true);
		}

		if (FULL_TEST_AMOUNT == testCount + 1) {
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

			if (FULL_TEST_AMOUNT == testCount + 1) {
				// 最后一张图片结束后是结束测试的按钮
				int replaced = JOptionPane.showConfirmDialog(null, "确认结束测试？", "测试即将结束", JOptionPane.YES_NO_OPTION);
				switch (replaced) {
				// 如果选择了“Yes”
				case JOptionPane.YES_OPTION:
					System.out.println("Yes is chosen");
					sumupRotateAngle();
					calVeriticalAngle();
					break;
				default:
					System.out.println("Others is chosen");
					break;
				}
			} else {

				testCount++;
				System.out.println("testCount: " + testCount);
				checkButtonAvailbility(testCount);

				System.out.println("Saving angle info Pic.");
				System.out.println("group: " + group + ", pic: " + pic);
				System.out.println(rv.getXuanzhuan());
				// rotateAngle[group][pic] = rv.getXuanzhuan();

				System.out.println("Next Pic.");
				rollOverPicNumber(1);
				System.out.println("group: " + group + ", pic: " + pic);
				rv.image = new ImageIcon(inputPathImageGroup[group][pic]).getImage();
				DirectoryAction da = new DirectoryAction();
				File inputText = new File(inputPathImageGroup[group][pic]);
				System.out.println("image exist2: " + da.whetherDirectoryExists(inputText));
				rv.setXuanzhuan(-rotateAngle[group][pic]);
				rv.repaint();
			}
		} else if (e.getSource() == jbPrev) {

			testCount--;

			System.out.println("testCount: " + testCount);
			checkButtonAvailbility(testCount);

			System.out.println("Saving angle info Pic.");
			// rotateAngle[group][pic] = rv.getXuanzhuan();

			System.out.println("Prev Pic.");
			rollOverPicNumber(-1);
			rv.image = new ImageIcon(inputPathImageGroup[group][pic]).getImage();
			DirectoryAction da = new DirectoryAction();
			File inputText = new File(inputPathImageGroup[group][pic]);
			System.out.println("image exist2: " + da.whetherDirectoryExists(inputText));
			rv.setXuanzhuan(-rotateAngle[group][pic]);
			rv.repaint();
		} else if (jbBigLeft == e.getSource()) {

			System.out.println("Turn left by 10");
			// String inputPath2 = "C:/hptest/input/images/group1/3.jpg";
			// File inputText = new File(inputPath2);
			//
			// rv.image = new ImageIcon(inputPath2).getImage();
			// DirectoryAction da = new DirectoryAction();
			// System.out.println("image exist2: "
			// + da.whetherDirectoryExists(inputText));
			System.out.println("Rotating group " + group + " pic " + pic + " by +10 degrees.");
			// rotateAngle[group][pic] = rv.getXuanzhuan();
			rotateAngle[group][pic] += Math.PI / 18;
			// 旋转和角度计算方向相反
			rv.setXuanzhuan(-rotateAngle[group][pic]);
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
			System.out.println("Rotating group " + group + " pic " + pic + " by -10 degrees.");
			// rotateAngle[group][pic] = rv.getXuanzhuan();
			rotateAngle[group][pic] -= Math.PI / 18;
			// 旋转和角度计算方向相反
			rv.setXuanzhuan(-rotateAngle[group][pic]);
			rv.repaint();
		}

	}

	private void calVeriticalAngle() {
		// TODO 计算点两两相连后和中心线的夹角
		for (int i = 0; i < ANGLE_NUMBER_ALL; i++) {
			asdasd
		}
		// dotsPRadiusGroupOne  resultAngleGroupOne

	}

	private void sumupRotateAngle() {
		// TODO 在结束测试之后汇总旋转的角度
		for (int i = 0; i < GROUP_NUMBER; i++) {
			for (int j = 0; j < TEST_PER_GROUP; j++) {
				while (rotateAngle[i][j] < 0.00) {
					rotateAngle[i][j] += (2 * Math.PI);
				}
				rotateAngle[i][j] = rotateAngle[i][j] % (2 * Math.PI);
			}
		}

		// 将rotateAngle 一组一组的转入 Polar Angle
		AngleCalAction aca = new AngleCalAction();
		for (int i = 0; i < TEST_PER_GROUP; i++) {
			for (int j = 0; j < DOTS * PICS; j++) {
				// 转换角度为弧度
				dotsPAngleGroupOne[i][j] = aca.angleToRadians(rotateAngle[0][i]);
				System.out.println("dotsPAngleGroupOne[" + i + "][" + j + "]: " + dotsPAngleGroupOne[i][j]);
			}
		}

	}

	private void rollOverPicNumber(int roll) {
		// TODO 当用户点击“上一张”或者“下一张”时，测试图片的编号对应的往后或者往前。
		pic += roll;
		if (pic < 0) {
			group--;
			pic += TEST_PER_GROUP;
		}
		if (pic > TEST_PER_GROUP) {
			group++;
			pic -= TEST_PER_GROUP;
		}

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
