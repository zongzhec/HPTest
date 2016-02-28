package foo.zongzhe.hpsearch.test;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter {
	public static void main(String[] args) {
		Frame mf = new Frame("MyKeyAdapter");
		TextField tf = new TextField();

		mf.setBounds(100, 100, 200, 300);
		mf.setVisible(true);

		tf.setBounds(10, 10, 30, 10);
		tf.setVisible(true);

		mf.add(tf);
		mf.pack();
		tf.addKeyListener(new MyKeyMoniter());
	}

}

class MyKeyMoniter extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		TextField tf = (TextField) e.getSource();
		System.out.println(tf.getText());
		tf.setText("");// 读完回车之后，将文本框置为空
	}
}
