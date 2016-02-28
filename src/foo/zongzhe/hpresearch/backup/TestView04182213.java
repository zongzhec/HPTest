package foo.zongzhe.hpresearch.backup;

// 有cardLayout 的版本

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import foo.zongzhe.hpresearch.view.RotateView;

public class TestView04182213 extends JFrame implements ActionListener, KeyListener {

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
	JButton jbNext, jbPrev;
	JButton jbRd1;
	JTextField jtf1;
	RotateView rv;

	// 用于填充的项目
	JLabel jlfi1, jlfi2, jlfi3, jlfi4, jlfi5, jlfi6, jlfi7, jlfi8;
	JLabel jlfiRd1, jlfiRd2, jlfiRd3, jlfiRd4, jlfiRd5, jlfiRd6, jlfiRd7,
			jlfiRd8, jlfiRd9, jlfiRd10, jlfiRd11, jlfiRd12, jlfiRd13, jlfiRd14;

	// 全局变量
	int TesterCount;
	int numberOfTest = 7;
	int numberOfGroup = 3;

	// 图片文件位置
	String inputPathImageGroup[] = new String[3];
	String ImageNumber[][] = new String[3][7];
	Image image[][] = new Image[3][7];

	public TestView04182213() {
		showPage();
	}

	public void showPage() {
		// 设置框架
		frame.setSize(1000, 600);
		frame.setLocation(300, 200);

		// 总的框架和布局
		jpBase = new JPanel();
		// container = new Container();
		cl = new CardLayout();
		jpBase.setLayout(cl);
		frame.setContentPane(jpBase);

		// frame.setResizable(false);
		GridBagLayout gbl = new GridBagLayout();
		jpBase.setLayout(gbl);
		GridBagConstraints gbcJpBase = new GridBagConstraints();

		Font fontForFillingText = new Font("TimesRoman", Font.PLAIN, 30);

		// 全局变量
		inputPathImageGroup[0] = "C:/hptest/input/images/group1";
		ImageNumber[0][0] = inputPathImageGroup + "/05.jpg";
		image[0][0] = new ImageIcon(inputPathImageGroup[0]).getImage();

		// 设置左边的面板
		jpTestLeft = new JPanel();
		rv = new RotateView();
		rv.setBounds(0, 0, 400, 400);
		jpTestLeft.add(rv);

		jpTestLeft.setBackground(Color.RED);

		gbcJpBase.gridwidth = 6;
		gbcJpBase.weightx = 100.0;
		gbcJpBase.weighty = 100.0;
		gbcJpBase.fill = GridBagConstraints.BOTH;
		// jpBase.add(jpTestLeft, gbcJpBase);
		// frame.getContentPane().add(rv);
		jpBase.add(rv, gbcJpBase);
//		rv.addKeyListener(this);
		jpBase.addKeyListener(this);

		// 设置右边的面板
		jpTestRight = new JPanel();
		jpTestRight.setBackground(Color.BLUE);

		GridBagLayout gblJpRight = new GridBagLayout();
		jpTestRight.setLayout(gblJpRight);
		GridBagConstraints gbcJpRight = new GridBagConstraints();
		int gridJpRightX = 0;
		int gridJpRightY = 0;

		jbPrev = new JButton("上一张图片");
		jbPrev.setFont(fontForFillingText);
		gbcJpRight.insets = new Insets(5, 5, 5, 5);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jbPrev, gbcJpRight);

		gridJpRightY++;
		jlfi1 = new JLabel(" ");
		jlfi1.setFont(fontForFillingText);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jlfi1, gbcJpRight);

		gridJpRightY++;
		jlfi2 = new JLabel(" ");
		jlfi2.setFont(fontForFillingText);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jlfi2, gbcJpRight);

		gridJpRightY++;
		jlfi3 = new JLabel(" ");
		jlfi3.setFont(fontForFillingText);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jlfi3, gbcJpRight);

		gridJpRightY++;
		jlfi4 = new JLabel(" ");
		jlfi4.setFont(fontForFillingText);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jlfi4, gbcJpRight);

		gridJpRightY++;
		jlfi5 = new JLabel(" ");
		jlfi5.setFont(fontForFillingText);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jlfi5, gbcJpRight);

		gridJpRightY++;
		jlfi6 = new JLabel(" ");
		jlfi6.setFont(fontForFillingText);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jlfi6, gbcJpRight);

		gridJpRightY++;
		jlfi7 = new JLabel(" ");
		jlfi7.setFont(fontForFillingText);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jlfi7, gbcJpRight);

		gridJpRightY++;
		jbNext = new JButton("下一张图片");
		jbNext.setFont(fontForFillingText);
		gbcJpRight.insets = new Insets(5, 5, 5, 5);
		gbcJpRight.gridx = gridJpRightX;
		gbcJpRight.gridy = gridJpRightY;
		jpTestRight.add(jbNext, gbcJpRight);

		gbcJpBase.gridwidth = 4;
		gbcJpBase.weightx = 40.0;
		gbcJpBase.weighty = 100.0;
		gbcJpBase.fill = GridBagConstraints.BOTH;
		jpBase.add(jpTestRight, gbcJpBase);

		// jpBase.add(jpTestLeft, BorderLayout.CENTER);
		// jpBase.add(jpTestRight, BorderLayout.EAST);
		// frame.add(jpBase);
		frame.addKeyListener(this);
		frame.setTitle("头部位置研究测试");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TestView04182213();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 如果用户在欢迎界面单击"开始测试"，则转到准备界面
		if (e.getSource() == jbNext) {
			// 切换cardPanel面板中当前组件之后的一个组件
			// 若当前组件为最后添加的组件，则显示第一个组件，即卡片组件显示是循环的。
			// cl.show(jpBase, "jpReady");
			cl.show(jpBase, jpTestRight.getName());
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
