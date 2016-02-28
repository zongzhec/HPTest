package foo.zongzhe.hpsearch.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MenuBar {

	JFrame frame = new JFrame();
	Container content = frame.getContentPane();
	JMenuBar menubar = new JMenuBar();
	JMenu LoginMenu = new JMenu("ϵͳ��¼");
	JMenu UserMangeMenu = new JMenu("�û�����");
	JMenu SchoolMangeMenu = new JMenu("ѧ������");
	JMenu HelpMenu = new JMenu("����");

	public MenuBar() {
		JMenuItem userLoginMenu = new JMenuItem("�û���¼");
		userLoginMenu.addActionListener(new LoginActionListener());
		JMenuItem exitLoginMenu = new JMenuItem("�˳�");
		LoginMenu.add(userLoginMenu);
		LoginMenu.add(exitLoginMenu);
		menubar.add(LoginMenu);
		menubar.add(UserMangeMenu);
		menubar.add(SchoolMangeMenu);
		menubar.add(HelpMenu);
		frame.setTitle("ѧ������ϵͳ");
		content.add(menubar, BorderLayout.NORTH);
		content.add(new JLabel(
				"<html><font size='6' color='red'>��ӭʹ��ѧ������ϵͳ</font></html>",
				JLabel.CENTER), BorderLayout.CENTER);
		// content.add(new
		// JLabel("��ӭʹ��ѧ������ϵͳ",JLabel.CENTER),BorderLayout.CENTER);
		frame.setBounds(450, 200, 400, 400);
		frame.setVisible(true);
	}

	public class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFrame frame01 = new JFrame();
			frame01.setTitle("�û���¼");
			frame01.setBounds(450, 200, 400, 150);
			Container content01 = frame01.getContentPane();
			JPanel panel = new JPanel();
			JPanel panel01 = new JPanel();
			JPanel panel02 = new JPanel();
			JLabel label01 = new JLabel("�������û���: ");
			JLabel label02 = new JLabel("����������:      ");
			JTextField text01 = new JTextField(20);
			JPasswordField text02 = new JPasswordField(20);
			panel01.add(label01);
			panel01.add(text01);
			panel02.add(label02);
			panel02.add(text02);
			panel.add(panel01);
			panel.add(panel02);
			content01.add(panel, BorderLayout.CENTER);
			// frame01.pack();
			frame01.setVisible(true);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MenuBar();
	}
}