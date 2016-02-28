package foo.zongzhe.hpsearch.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import foo.zongzhe.hpresearch.view.RotateView;

public class DrawImage implements ActionListener {
	JFrame frame; // ������
//	MyPanel panel;
	JPanel jpanel;
	RotateView panel;
	JButton rotate; // ������ת�İ�ť

	public DrawImage() {
		frame = new JFrame("��ͼDemo");
		frame.setSize(400, 500);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
//		panel = new MyPanel();
		jpanel=new JPanel();
		panel = new RotateView();
		panel.setBounds(0, 0, 20, 20);
		jpanel.add(panel);
		frame.getContentPane().add(jpanel);
		rotate = new JButton("����"); // ÿ��һ����ʱ����ת90��
		rotate.setBounds(5, 405, 390, 90);
		frame.getContentPane().add(rotate);
		rotate.addActionListener(this);
	}

	public static void main(String[] args) {
		new DrawImage();
	}

	public void actionPerformed(ActionEvent e) {
		panel.setXuanzhuan(panel.getXuanzhuan() - Math.PI / 2);
		panel.repaint();
	}
}