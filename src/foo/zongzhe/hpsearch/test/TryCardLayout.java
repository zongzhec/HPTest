package foo.zongzhe.hpsearch.test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TryCardLayout extends JFrame {

	private JButton button1 = new JButton("进入");
	private JButton button2 = new JButton("返回");
	private JPanel cardPane = new JPanel();
	private JPanel firstPane = new JPanel();
	private JPanel nextPane = new JPanel();
	private static final String card1 = "1";
	private static final String card2 = "2";
	private CardLayout cardLayout = new CardLayout();

	public TryCardLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		cardPane.setLayout(cardLayout);
		// card1 是对应 firstPane 的标记，要通过这个来切换界面
		cardPane.add(firstPane, card1);
		// card2 是对应 nextPane 的标记，要通过这个来切换界面
		cardPane.add(nextPane, card2);
		firstPane.add(button1);
		firstPane.add(new JLabel("first"));
		firstPane.setBackground(Color.CYAN);
		nextPane.add(button2);
		nextPane.add(new JLabel("second"));
		nextPane.setBackground(Color.RED);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 通过 card2 标记切换到 nextPane
				cardLayout.show(cardPane, card2);
			}

		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 通过 card1 标记切换到 firstPane
				cardLayout.show(cardPane, card1);
			}

		});
		add(cardPane);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new TryCardLayout();
			}
		});
	}

}