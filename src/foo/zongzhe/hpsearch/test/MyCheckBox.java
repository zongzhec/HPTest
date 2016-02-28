package foo.zongzhe.hpsearch.test;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyCheckBox extends JFrame implements KeyListener {

	JTextField textField = new JTextField(80);
	JLabel label = new JLabel("您依次按下了：");

	public MyCheckBox() throws HeadlessException {
		super("复选框测试");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textField.addKeyListener(this);
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		add(label, BorderLayout.NORTH);
		add(textField, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		MyCheckBox checkBox = new MyCheckBox();
	}

	@Override
	// 按下并松开按键时执行
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		textField.setText(textField.getText() + " , " + key);
	}

	@Override
	// 按键按下时执行
	public void keyPressed(KeyEvent e) {
	}

	@Override
	// 按键松开时执行
	public void keyReleased(KeyEvent e) {
	}
}
