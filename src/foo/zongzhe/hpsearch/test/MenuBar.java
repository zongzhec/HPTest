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
	JMenu LoginMenu = new JMenu("系统登录");
	JMenu UserMangeMenu = new JMenu("用户管理");
	JMenu SchoolMangeMenu = new JMenu("学籍管理");
	JMenu HelpMenu = new JMenu("关于");

	public MenuBar() {
		JMenuItem userLoginMenu = new JMenuItem("用户登录");
		userLoginMenu.addActionListener(new LoginActionListener());
		JMenuItem exitLoginMenu = new JMenuItem("退出");
		LoginMenu.add(userLoginMenu);
		LoginMenu.add(exitLoginMenu);
		menubar.add(LoginMenu);
		menubar.add(UserMangeMenu);
		menubar.add(SchoolMangeMenu);
		menubar.add(HelpMenu);
		frame.setTitle("学籍管理系统");
		content.add(menubar, BorderLayout.NORTH);
		content.add(new JLabel(
				"<html><font size='6' color='red'>欢迎使用学籍管理系统</font></html>",
				JLabel.CENTER), BorderLayout.CENTER);
		// content.add(new
		// JLabel("欢迎使用学籍管理系统",JLabel.CENTER),BorderLayout.CENTER);
		frame.setBounds(450, 200, 400, 400);
		frame.setVisible(true);
	}

	public class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFrame frame01 = new JFrame();
			frame01.setTitle("用户登录");
			frame01.setBounds(450, 200, 400, 150);
			Container content01 = frame01.getContentPane();
			JPanel panel = new JPanel();
			JPanel panel01 = new JPanel();
			JPanel panel02 = new JPanel();
			JLabel label01 = new JLabel("请输入用户名: ");
			JLabel label02 = new JLabel("请输入密码:      ");
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