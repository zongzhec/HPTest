package foo.zongzhe.hpsearch.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FrameWork extends JFrame {

	// 框架
	JFrame frame = new JFrame();
	// Container content = frame.getContentPane();

	// // 工具栏
	// JMenuBar menuBar = new JMenuBar();
	// JMenu helpMenu = new JMenu("关于");
	// JMenuItem aboutMenuItem = new JMenuItem("关于作者");

	// 面板上的内容
	JPanel jp;
	JLabel jl1, jl2;
	JButton jb1;
	JTextField jtf1;

	// Widgets to fill in the panel
	JLabel jlfi1, jlfi2, jlfi3, jlfi4, jlfi5, jlfi6;

	public FrameWork() {

		frame.setSize(800, 600);
		frame.setLocation(400, 600);

		jp = new JPanel();
		jp.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板的边框
		frame.setContentPane(jp);

		GridBagLayout gbl = new GridBagLayout();
		jp.setLayout(gbl);
		// GridBagConstraints gbc = new GridBagConstraints();
		// gbc.fill = GridBagConstraints.BOTH;
		// 该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
		// NONE：不调整组件大小。
		// HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
		// VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
		// BOTH：使组件完全填满其显示区域。
		// content.setLayout(gbl);

		// 开始加载面板上的内容
		// The first row is for filling up
		jlfi1 = new JLabel("1");
		GridBagConstraints gbcJlfi1 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi1.insets = new Insets(0, 0, 0, 0);
		gbcJlfi1.fill = GridBagConstraints.HORIZONTAL;
		gbcJlfi1.weightx = 100.0;// 第一行的分布方式为100%
		gbcJlfi1.gridx = 0;
		gbcJlfi1.gridy = 0;
		jp.add(jlfi1, gbcJlfi1);

		// The second row is for texts
		jlfi2 = new JLabel("2");
		GridBagConstraints gbcJlfi2 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi2.insets = new Insets(5, 0, 5, 5);
		gbcJlfi2.weightx = 20.0;// 第一行的分布方式为100%
		gbcJlfi2.gridheight = 3;// 占用2列
		gbcJlfi2.fill = GridBagConstraints.HORIZONTAL;
		gbcJlfi2.gridx = 0;
		gbcJlfi2.gridy = 1;
		jp.add(jlfi2, gbcJlfi2);

		jl1 = new JLabel("共有");
		GridBagConstraints gbcJl1 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJl1.insets = new Insets(5, 5, 5, 5);
		gbcJl1.fill = GridBagConstraints.BOTH;
		gbcJl1.weightx = 20.0;// 第一行的分布方式为100%
		gbcJl1.gridx = 1;
		gbcJl1.gridy = 1;
		jp.add(jl1, gbcJl1);

		jtf1 = new JTextField();
		GridBagConstraints gbcJtf1 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJtf1.insets = new Insets(5, 5, 5, 5);
		gbcJtf1.fill = GridBagConstraints.BOTH;
		gbcJtf1.weightx = 20.0;// 第一行的分布方式为100%
		gbcJtf1.gridx = 2;
		gbcJtf1.gridy = 1;
		jp.add(jtf1, gbcJtf1);

		jl2 = new JLabel("名测试者");
		GridBagConstraints gbcJl2 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJl2.insets = new Insets(5, 5, 5, 5);
		gbcJl2.fill = GridBagConstraints.BOTH;
		gbcJl2.weightx = 20.0;// 第一行的分布方式为100%
		gbcJl2.gridx = 3;
		gbcJl2.gridy = 1;
		jp.add(jl2, gbcJl2);

		jlfi3 = new JLabel("3");
		GridBagConstraints gbcJlfi3 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi3.insets = new Insets(5, 5, 5, 0);
		gbcJlfi3.weightx = 20.0;// 第一行的分布方式为100%
		gbcJlfi3.gridheight = 3;// 占用2列
		gbcJlfi3.fill = GridBagConstraints.HORIZONTAL;
		gbcJlfi3.gridx = 4;
		gbcJlfi3.gridy = 1;
		jp.add(jlfi3, gbcJlfi3);

		// The third row is for button to start
		jlfi4 = new JLabel("4");
		GridBagConstraints gbcJlfi4 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi4.insets = new Insets(5, 5, 5, 5);
		gbcJlfi4.fill = GridBagConstraints.BOTH;
		gbcJlfi4.weightx = 20.0;// 第一行的分布方式为100%
		gbcJlfi4.gridx = 1;
		gbcJlfi4.gridy = 2;
		jp.add(jlfi4, gbcJlfi4);
//		content.add(jlfi4);
//		gbc.gridwidth = 2;
//		gbc.gridheight = 1;
//		gbc.weightx = 0;
//		gbc.weighty = 0;
//		gbl.setConstraints(jlfi4, gbc);

		jb1 = new JButton("开始测试");
		GridBagConstraints gbcJb1 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJb1.insets = new Insets(5, 5, 5, 5);
		gbcJb1.fill = GridBagConstraints.BOTH;
		gbcJb1.weightx = 20.0;// 第一行的分布方式为100%
		gbcJb1.gridx = 2;
		gbcJb1.gridy = 2;
		jp.add(jb1, gbcJb1);
//		content.add(jb1);
//		gbc.gridheight = 1;
//		gbc.gridwidth = 1;
//		gbc.weightx = 0;
//		gbc.weighty = 0;
//		gbl.setConstraints(jb1, gbc);

		jlfi5 = new JLabel("5");
		GridBagConstraints gbcJlfi5 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi5.insets = new Insets(5, 5, 5, 5);
		gbcJlfi5.fill = GridBagConstraints.BOTH;
		gbcJlfi5.weightx = 20.0;// 第一行的分布方式为100%
		gbcJlfi5.gridx = 3;
		gbcJlfi5.gridy = 2;
		jp.add(jlfi5, gbcJlfi5);
		
		// The forth row is for button to start
		jlfi6 = new JLabel("6");
		GridBagConstraints gbcJlfi6 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi6.insets = new Insets(5, 0, 5, 5);
		gbcJlfi6.fill = GridBagConstraints.HORIZONTAL;
		gbcJlfi6.weightx = 60.0;// 第一行的分布方式为100%
		gbcJlfi6.gridx = 1;
		gbcJlfi6.gridy = 3;
		jp.add(jlfi6, gbcJlfi6);

		frame.setTitle("头部位置研究测试");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new FrameWork();

	}

}
