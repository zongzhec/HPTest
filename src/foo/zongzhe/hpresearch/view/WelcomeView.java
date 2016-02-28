package foo.zongzhe.hpresearch.view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WelcomeView extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 框架
	JFrame frame = new JFrame();

	// 总的框架和布局
	JPanel jpBase;
	CardLayout cl;

	// // 工具栏
	// JMenuBar menuBar = new JMenuBar();
	// JMenu helpMenu = new JMenu("关于");
	// JMenuItem aboutMenuItem = new JMenuItem("关于作者");

	// 面板上的内容
	JPanel jpWelcome, jpReady;
	JLabel jl1, jl2, jl3;
	JLabel jlRd1, jlRd2, jlRd3;
	JButton jb1;
	JButton jbRd1;
	JTextField jtf1;

	// 用于填充的项目
	JLabel jlfi1, jlfi2, jlfi3, jlfi4, jlfi5, jlfi6, jlfi7, jlfi8;
	JLabel jlfiRd1, jlfiRd2, jlfiRd3, jlfiRd4, jlfiRd5, jlfiRd6, jlfiRd7,
			jlfiRd8, jlfiRd9, jlfiRd10, jlfiRd11, jlfiRd12, jlfiRd13, jlfiRd14;

	int TesterCount;

	public WelcomeView() {

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
		frame.setResizable(false);

		// 全局变量
		TesterCount = 1;

		// ------------------------------------------------
		// 设置欢迎面板
		jpWelcome = new JPanel();
		jpWelcome.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板的边框

		// 设置面板上的通用部件
		Font fontForLargeText = new Font("TimesRoman", Font.PLAIN, 50);
		Font fontForFillingText = new Font("TimesRoman", Font.PLAIN, 20);
		int gridx = 0;
		int gridy = 0;

		GridBagLayout gbl = new GridBagLayout();
		jpWelcome.setLayout(gbl);

		// 开始加载面板上的内容
		// 第一行用于填充
		jlfi1 = new JLabel(" ");
		jlfi1.setFont(fontForFillingText);
		GridBagConstraints gbcJlfi1 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi1.insets = new Insets(0, 0, 5, 0);
		gbcJlfi1.fill = GridBagConstraints.BOTH;
		gbcJlfi1.gridwidth = 5;
		gbcJlfi1.gridheight = 1;
		gbcJlfi1.gridx = gridx;
		gbcJlfi1.gridy = gridy;
		jpWelcome.add(jlfi1, gbcJlfi1);

		// 第二行填写欢迎信息
		gridx = 0;
		gridy++;
		jl3 = new JLabel("欢迎参加 头部位置 研究测试");
		jl3.setFont(fontForLargeText);
		jl3.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints gbcJl3 = new GridBagConstraints();
		gbcJl3.insets = new Insets(5, 0, 5, 0);
		gbcJl3.fill = GridBagConstraints.BOTH;
		gbcJl3.gridwidth = 5;
		gbcJl3.gridheight = 1;
		gbcJl3.gridx = gridx;
		gbcJl3.gridy = gridy;
		jpWelcome.add(jl3, gbcJl3);

		// 第三行还是空行
		gridx = 0;
		gridy++;
		jlfi7 = new JLabel(" ");
		jlfi7.setFont(fontForFillingText);
		GridBagConstraints gbcJlfi7 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi7.insets = new Insets(5, 0, 5, 0);
		gbcJlfi7.fill = GridBagConstraints.BOTH;
		gbcJlfi7.gridwidth = 5;
		gbcJlfi7.gridheight = 1;
		gbcJlfi7.gridx = gridx;
		gbcJlfi7.gridy = gridy;
		jpWelcome.add(jlfi7, gbcJlfi7);

		// 第四行选择测试者人数
		gridx = 0;
		gridy++;
		jlfi2 = new JLabel(" ");
		jlfi2.setFont(fontForFillingText);
		GridBagConstraints gbcJlfi2 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi2.insets = new Insets(5, 0, 5, 5);
		gbcJlfi2.weightx = 20.0;// 第一行的分布方式为100%
		gbcJlfi2.gridwidth = 1;// 占用2列
		gbcJlfi2.gridheight = 3;// 占用2列
		gbcJlfi2.fill = GridBagConstraints.BOTH;
		gbcJlfi2.gridx = gridx;
		gbcJlfi2.gridy = gridy;
		jpWelcome.add(jlfi2, gbcJlfi2);

		gridx++;
		jl1 = new JLabel("共有");
		jl1.setFont(fontForLargeText);
		jl1.setHorizontalAlignment(JLabel.RIGHT);
		GridBagConstraints gbcJl1 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJl1.insets = new Insets(5, 5, 5, 5);
		gbcJl1.fill = GridBagConstraints.BOTH;
		gbcJl1.weightx = 20.0;
		gbcJl1.gridwidth = 1;
		gbcJl1.gridheight = 1;
		gbcJl1.gridx = gridx;
		gbcJl1.gridy = gridy;
		jpWelcome.add(jl1, gbcJl1);

		gridx++;
		jtf1 = new JTextField();
		jtf1.setFont(fontForLargeText);
		GridBagConstraints gbcJtf1 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJtf1.insets = new Insets(5, 5, 5, 5);
		gbcJtf1.fill = GridBagConstraints.BOTH;
		gbcJtf1.weightx = 20.0;
		gbcJtf1.gridwidth = 1;
		gbcJtf1.gridheight = 1;
		gbcJtf1.gridx = gridx;
		gbcJtf1.gridy = gridy;
		jpWelcome.add(jtf1, gbcJtf1);

		gridx++;
		jl2 = new JLabel("名测试者");
		jl2.setFont(fontForLargeText);
		GridBagConstraints gbcJl2 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJl2.insets = new Insets(5, 5, 5, 5);
		gbcJl2.fill = GridBagConstraints.BOTH;
		gbcJl2.weightx = 20.0;
		gbcJl2.gridwidth = 1;
		gbcJl2.gridheight = 1;
		gbcJl2.gridx = gridx;
		gbcJl2.gridy = gridy;
		jpWelcome.add(jl2, gbcJl2);

		gridx++;
		jlfi3 = new JLabel(" ");
		jlfi3.setFont(fontForFillingText);
		GridBagConstraints gbcJlfi3 = new GridBagConstraints();
		gbcJlfi3.insets = new Insets(5, 5, 0, 0);
		gbcJlfi3.gridwidth = 1;
		gbcJlfi3.gridheight = 3;
		gbcJlfi3.weightx = 20.0;
		gbcJlfi3.fill = GridBagConstraints.BOTH;
		gbcJlfi3.gridx = gridx;
		gbcJlfi3.gridy = gridy;
		jpWelcome.add(jlfi3, gbcJlfi3);

		// 第五行还是空行
		gridx = 1;
		gridy++;
		jlfi8 = new JLabel(" ");
		jlfi8.setFont(fontForFillingText);
		GridBagConstraints gbcJlfi8 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi8.insets = new Insets(5, 0, 5, 0);
		gbcJlfi8.fill = GridBagConstraints.BOTH;
		gbcJlfi8.gridwidth = 5;
		gbcJlfi8.gridheight = 1;
		gbcJlfi8.gridx = gridx;
		gbcJlfi8.gridy = gridy;
		jpWelcome.add(jlfi8, gbcJlfi8);

		// 第六行为开始测试按钮
		gridx = 1;
		gridy++;
		jlfi4 = new JLabel(" ");
		GridBagConstraints gbcJlfi4 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi4.insets = new Insets(5, 5, 5, 5);
		gbcJlfi4.fill = GridBagConstraints.BOTH;
		gbcJlfi4.gridwidth = 1;
		gbcJlfi4.gridheight = 1;
		gbcJlfi4.gridx = gridx;
		gbcJlfi4.gridy = gridy;
		jpWelcome.add(jlfi4, gbcJlfi4);

		gridx++;
		jb1 = new JButton("开始测试");
		jb1.setFont(fontForLargeText);
		GridBagConstraints gbcJb1 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJb1.insets = new Insets(5, 5, 5, 5);
		gbcJb1.fill = GridBagConstraints.BOTH;
		gbcJb1.gridwidth = 1;
		gbcJb1.gridheight = 1;
		gbcJb1.gridx = gridx;
		gbcJb1.gridy = gridy;
		jpWelcome.add(jb1, gbcJb1);
		jb1.addActionListener(this);

		gridx++;
		jlfi5 = new JLabel(" ");
		GridBagConstraints gbcJlfi5 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi5.insets = new Insets(5, 5, 5, 5);
		gbcJlfi5.fill = GridBagConstraints.BOTH;
		gbcJlfi5.gridwidth = 1;
		gbcJlfi5.gridheight = 1;
		gbcJlfi5.gridx = gridx;
		gbcJlfi5.gridy = gridy;
		jpWelcome.add(jlfi5, gbcJlfi5);

		// 第七行还是空行
		gridx = 1;
		gridy++;
		jlfi6 = new JLabel(" ");
		jlfi6.setFont(fontForFillingText);
		GridBagConstraints gbcJlfi6 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlfi6.insets = new Insets(5, 0, 5, 5);
		gbcJlfi6.fill = GridBagConstraints.BOTH;
		gbcJlfi6.gridwidth = 3;
		gbcJlfi6.gridheight = 1;
		gbcJlfi6.gridx = gridx;
		gbcJlfi6.gridy = gridy;
		jpWelcome.add(jlfi6, gbcJlfi6);

		jpBase.add(jpWelcome);

		// ------------------------------------------------
		// 设置准备界面
		jpReady = new JPanel();
		jpReady.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板的边框
		jpReady.setName("jpReady");

		// 设置面板上的通用部件
		gridx = 0;
		gridy = 0;

		GridBagLayout gblReady = new GridBagLayout();
		jpReady.setLayout(gblReady);

		// 开始加载面板上的内容
		// 第一行用于填充
		jlfiRd1 = new JLabel(" ");
		jlfiRd1.setFont(fontForFillingText);
		GridBagConstraints gbcJlfiRd1 = new GridBagConstraints(); // 创建网格组布局约束条件
		gbcJlfiRd1.insets = new Insets(0, 0, 5, 0);
		gbcJlfiRd1.fill = GridBagConstraints.BOTH;
		gbcJlfiRd1.gridwidth = 5;
		gbcJlfiRd1.gridheight = 1;
		gbcJlfiRd1.gridx = gridx;
		gbcJlfiRd1.gridy = gridy;
		jpReady.add(jlfiRd1, gbcJlfiRd1);

		// 第二行显示测试者序号
		gridx = 0;
		gridy++;
		jlfiRd9 = new JLabel(" ");
		jlfiRd9.setFont(fontForFillingText);
		GridBagConstraints gbcJlfiRd9 = new GridBagConstraints();//
		// 创建网格组布局约束条件
		gbcJlfiRd9.insets = new Insets(5, 0, 5, 5);
		gbcJlfiRd9.weightx = 20.0;
		gbcJlfiRd9.gridwidth = 1;
		gbcJlfiRd9.gridheight = 3;
		gbcJlfiRd9.fill = GridBagConstraints.BOTH;
		gbcJlfiRd9.gridx = gridx;
		gbcJlfiRd9.gridy = gridy;
		jpReady.add(jlfiRd9, gbcJlfiRd9);

		gridx++;
		jlRd1 = new JLabel("请第 " + TesterCount + " 位测试者做好准备");
		jlRd1.setFont(fontForLargeText);
		jlRd1.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints gbcJlRd1 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJlRd1.insets = new Insets(5, 5, 5, 5);
		gbcJlRd1.fill = GridBagConstraints.BOTH;
		gbcJlRd1.gridwidth = 3;
		gbcJlRd1.gridheight = 1;
		gbcJlRd1.gridx = gridx;
		gbcJlRd1.gridy = gridy;
		jpReady.add(jlRd1, gbcJlRd1);

		gridx = 4;
		jlfiRd2 = new JLabel(" ");
		jlfiRd2.setFont(fontForFillingText);
		GridBagConstraints gbcJlfiRd2 = new GridBagConstraints();//
		// 创建网格组布局约束条件
		gbcJlfiRd2.insets = new Insets(5, 5, 5, 0);
		gbcJlfiRd2.weightx = 20.0;
		gbcJlfiRd2.gridwidth = 1;
		gbcJlfiRd2.gridheight = 3;
		gbcJlfiRd2.fill = GridBagConstraints.BOTH;
		gbcJlfiRd2.gridx = gridx;
		gbcJlfiRd2.gridy = gridy;
		jpReady.add(jlfiRd2, gbcJlfiRd2);

		// 第三行用于填充
		gridx = 0;
		gridx++;
		gridy++;
		jlfiRd3 = new JLabel(" ");
		jlfiRd3.setFont(fontForFillingText);
		GridBagConstraints gbcJlfiRd3 = new GridBagConstraints(); // 创建网格组布局约束条件
		gbcJlfiRd3.insets = new Insets(5, 5, 5, 5);
		gbcJlfiRd3.fill = GridBagConstraints.BOTH;
		gbcJlfiRd3.gridwidth = 3;
		gbcJlfiRd3.gridheight = 1;
		gbcJlfiRd3.gridx = gridx;
		gbcJlfiRd3.gridy = gridy;
		jpReady.add(jlfiRd3, gbcJlfiRd3);

		// 第四行为开始测试按钮
		gridx = 0;
		gridx++;
		gridy++;
		jlfiRd4 = new JLabel(" ");
		jlfiRd4.setFont(fontForFillingText);
		GridBagConstraints gbcJlfiRd4 = new GridBagConstraints(); // 创建网格组布局约束条件
		gbcJlfiRd4.insets = new Insets(5, 0, 5, 5);
		gbcJlfiRd4.fill = GridBagConstraints.BOTH;
		gbcJlfiRd4.weightx = 20.0;
		gbcJlfiRd4.gridwidth = 1;
		gbcJlfiRd4.gridheight = 1;
		gbcJlfiRd4.gridx = gridx;
		gbcJlfiRd4.gridy = gridy;
		jpReady.add(jlfiRd4, gbcJlfiRd4);

		gridx++;
		jbRd1 = new JButton("准备好了");
		jbRd1.setFont(fontForLargeText);
		GridBagConstraints gbcJbRd1 = new GridBagConstraints();// 创建网格组布局约束条件
		gbcJbRd1.insets = new Insets(5, 5, 5, 5);
		gbcJbRd1.fill = GridBagConstraints.BOTH;
		gbcJbRd1.weightx = 20.0;
		gbcJbRd1.gridwidth = 1;
		gbcJbRd1.gridheight = 1;
		gbcJbRd1.gridx = gridx;
		gbcJbRd1.gridy = gridy;
		jpReady.add(jbRd1, gbcJbRd1);

		gridx++;
		jlfiRd5 = new JLabel(" ");
		jlfiRd5.setFont(fontForFillingText);
		GridBagConstraints gbcJlfiRd5 = new GridBagConstraints(); // 创建网格组布局约束条件
		gbcJlfiRd5.insets = new Insets(5, 0, 5, 5);
		gbcJlfiRd5.fill = GridBagConstraints.BOTH;
		gbcJlfiRd5.weightx = 20.0;
		gbcJlfiRd5.gridwidth = 1;
		gbcJlfiRd5.gridheight = 1;
		gbcJlfiRd5.gridx = gridx;
		gbcJlfiRd5.gridy = gridy;
		jpReady.add(jlfiRd5, gbcJlfiRd5);

		// 第七行还是空行
		gridx = 0;
		gridy++;
		jlfiRd6 = new JLabel(" ");
		jlfiRd6.setFont(fontForFillingText);
		GridBagConstraints gbcJlfiRd6 = new GridBagConstraints(); // 创建网格组布局约束条件
		gbcJlfiRd6.insets = new Insets(0, 5, 5, 5);
		gbcJlfiRd6.fill = GridBagConstraints.BOTH;
		gbcJlfiRd6.gridwidth = 5;
		gbcJlfiRd6.gridheight = 1;
		gbcJlfiRd6.gridx = gridx;
		gbcJlfiRd6.gridy = gridy;
		jpReady.add(jlfiRd6, gbcJlfiRd6);

		jpBase.add(jpReady.getName(), jpReady);

		frame.setTitle("头部位置研究测试");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new WelcomeView();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 如果用户在欢迎界面单击"开始测试"，则转到准备界面
		if (e.getSource() == jb1) {
			// 切换cardPanel面板中当前组件之后的一个组件
			// 若当前组件为最后添加的组件，则显示第一个组件，即卡片组件显示是循环的。
			// cl.show(jpBase, "jpReady");
			cl.show(jpBase, jpReady.getName());
		}

	}

}
