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

	// ���
	JFrame frame = new JFrame();
	// Container content = frame.getContentPane();

	// // ������
	// JMenuBar menuBar = new JMenuBar();
	// JMenu helpMenu = new JMenu("����");
	// JMenuItem aboutMenuItem = new JMenuItem("��������");

	// ����ϵ�����
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
		jp.setBorder(new EmptyBorder(5, 5, 5, 5));// �������ı߿�
		frame.setContentPane(jp);

		GridBagLayout gbl = new GridBagLayout();
		jp.setLayout(gbl);
		// GridBagConstraints gbc = new GridBagConstraints();
		// gbc.fill = GridBagConstraints.BOTH;
		// �÷�����Ϊ���������������ڵ�������������Ҫ��ʱ����ʾ���
		// NONE�������������С��
		// HORIZONTAL���ӿ������ʹ����ˮƽ��������������ʾ���򣬵��ǲ��ı�߶ȡ�
		// VERTICAL���Ӹ������ʹ���ڴ�ֱ��������������ʾ���򣬵��ǲ��ı��ȡ�
		// BOTH��ʹ�����ȫ��������ʾ����
		// content.setLayout(gbl);

		// ��ʼ��������ϵ�����
		// The first row is for filling up
		jlfi1 = new JLabel("1");
		GridBagConstraints gbcJlfi1 = new GridBagConstraints();// ���������鲼��Լ������
		gbcJlfi1.insets = new Insets(0, 0, 0, 0);
		gbcJlfi1.fill = GridBagConstraints.HORIZONTAL;
		gbcJlfi1.weightx = 100.0;// ��һ�еķֲ���ʽΪ100%
		gbcJlfi1.gridx = 0;
		gbcJlfi1.gridy = 0;
		jp.add(jlfi1, gbcJlfi1);

		// The second row is for texts
		jlfi2 = new JLabel("2");
		GridBagConstraints gbcJlfi2 = new GridBagConstraints();// ���������鲼��Լ������
		gbcJlfi2.insets = new Insets(5, 0, 5, 5);
		gbcJlfi2.weightx = 20.0;// ��һ�еķֲ���ʽΪ100%
		gbcJlfi2.gridheight = 3;// ռ��2��
		gbcJlfi2.fill = GridBagConstraints.HORIZONTAL;
		gbcJlfi2.gridx = 0;
		gbcJlfi2.gridy = 1;
		jp.add(jlfi2, gbcJlfi2);

		jl1 = new JLabel("����");
		GridBagConstraints gbcJl1 = new GridBagConstraints();// ���������鲼��Լ������
		gbcJl1.insets = new Insets(5, 5, 5, 5);
		gbcJl1.fill = GridBagConstraints.BOTH;
		gbcJl1.weightx = 20.0;// ��һ�еķֲ���ʽΪ100%
		gbcJl1.gridx = 1;
		gbcJl1.gridy = 1;
		jp.add(jl1, gbcJl1);

		jtf1 = new JTextField();
		GridBagConstraints gbcJtf1 = new GridBagConstraints();// ���������鲼��Լ������
		gbcJtf1.insets = new Insets(5, 5, 5, 5);
		gbcJtf1.fill = GridBagConstraints.BOTH;
		gbcJtf1.weightx = 20.0;// ��һ�еķֲ���ʽΪ100%
		gbcJtf1.gridx = 2;
		gbcJtf1.gridy = 1;
		jp.add(jtf1, gbcJtf1);

		jl2 = new JLabel("��������");
		GridBagConstraints gbcJl2 = new GridBagConstraints();// ���������鲼��Լ������
		gbcJl2.insets = new Insets(5, 5, 5, 5);
		gbcJl2.fill = GridBagConstraints.BOTH;
		gbcJl2.weightx = 20.0;// ��һ�еķֲ���ʽΪ100%
		gbcJl2.gridx = 3;
		gbcJl2.gridy = 1;
		jp.add(jl2, gbcJl2);

		jlfi3 = new JLabel("3");
		GridBagConstraints gbcJlfi3 = new GridBagConstraints();// ���������鲼��Լ������
		gbcJlfi3.insets = new Insets(5, 5, 5, 0);
		gbcJlfi3.weightx = 20.0;// ��һ�еķֲ���ʽΪ100%
		gbcJlfi3.gridheight = 3;// ռ��2��
		gbcJlfi3.fill = GridBagConstraints.HORIZONTAL;
		gbcJlfi3.gridx = 4;
		gbcJlfi3.gridy = 1;
		jp.add(jlfi3, gbcJlfi3);

		// The third row is for button to start
		jlfi4 = new JLabel("4");
		GridBagConstraints gbcJlfi4 = new GridBagConstraints();// ���������鲼��Լ������
		gbcJlfi4.insets = new Insets(5, 5, 5, 5);
		gbcJlfi4.fill = GridBagConstraints.BOTH;
		gbcJlfi4.weightx = 20.0;// ��һ�еķֲ���ʽΪ100%
		gbcJlfi4.gridx = 1;
		gbcJlfi4.gridy = 2;
		jp.add(jlfi4, gbcJlfi4);
//		content.add(jlfi4);
//		gbc.gridwidth = 2;
//		gbc.gridheight = 1;
//		gbc.weightx = 0;
//		gbc.weighty = 0;
//		gbl.setConstraints(jlfi4, gbc);

		jb1 = new JButton("��ʼ����");
		GridBagConstraints gbcJb1 = new GridBagConstraints();// ���������鲼��Լ������
		gbcJb1.insets = new Insets(5, 5, 5, 5);
		gbcJb1.fill = GridBagConstraints.BOTH;
		gbcJb1.weightx = 20.0;// ��һ�еķֲ���ʽΪ100%
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
		GridBagConstraints gbcJlfi5 = new GridBagConstraints();// ���������鲼��Լ������
		gbcJlfi5.insets = new Insets(5, 5, 5, 5);
		gbcJlfi5.fill = GridBagConstraints.BOTH;
		gbcJlfi5.weightx = 20.0;// ��һ�еķֲ���ʽΪ100%
		gbcJlfi5.gridx = 3;
		gbcJlfi5.gridy = 2;
		jp.add(jlfi5, gbcJlfi5);
		
		// The forth row is for button to start
		jlfi6 = new JLabel("6");
		GridBagConstraints gbcJlfi6 = new GridBagConstraints();// ���������鲼��Լ������
		gbcJlfi6.insets = new Insets(5, 0, 5, 5);
		gbcJlfi6.fill = GridBagConstraints.HORIZONTAL;
		gbcJlfi6.weightx = 60.0;// ��һ�еķֲ���ʽΪ100%
		gbcJlfi6.gridx = 1;
		gbcJlfi6.gridy = 3;
		jp.add(jlfi6, gbcJlfi6);

		frame.setTitle("ͷ��λ���о�����");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new FrameWork();

	}

}
