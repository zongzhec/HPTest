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
	JLabel label = new JLabel("�����ΰ����ˣ�");

	public MyCheckBox() throws HeadlessException {
		super("��ѡ�����");
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
	// ���²��ɿ�����ʱִ��
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		textField.setText(textField.getText() + " , " + key);
	}

	@Override
	// ��������ʱִ��
	public void keyPressed(KeyEvent e) {
	}

	@Override
	// �����ɿ�ʱִ��
	public void keyReleased(KeyEvent e) {
	}
}
