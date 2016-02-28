package foo.zongzhe.hpresearch.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestView2 implements ActionListener {

	JFrame frame; // 主窗体
	// MyPanel panel;
	JPanel jpanel;
	RotateView panel;
	JButton rotate; // 控制旋转的按钮

	public TestView2() {
		frame = new JFrame("绘图Demo");
		frame.setSize(400, 500);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
		// panel = new MyPanel();
		jpanel = new JPanel();
		panel = new RotateView();
		panel.setBounds(0, 0, 20, 20);
		jpanel.add(panel);
		frame.getContentPane().add(jpanel);
		rotate = new JButton("逆旋"); // 每按一次逆时针旋转90度
		rotate.setBounds(5, 405, 390, 90);
		frame.getContentPane().add(rotate);
		rotate.addActionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestView2();
	}

	public void actionPerformed(ActionEvent e) {
		panel.setXuanzhuan(panel.getXuanzhuan() - Math.PI / 2);
		panel.repaint();
	}

}
