package foo.zongzhe.hpsearch.test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TryCardLayout extends JFrame {

	private JButton button1 = new JButton("����");
	private JButton button2 = new JButton("����");
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
		// card1 �Ƕ�Ӧ firstPane �ı�ǣ�Ҫͨ��������л�����
		cardPane.add(firstPane, card1);
		// card2 �Ƕ�Ӧ nextPane �ı�ǣ�Ҫͨ��������л�����
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
				// ͨ�� card2 ����л��� nextPane
				cardLayout.show(cardPane, card2);
			}

		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ͨ�� card1 ����л��� firstPane
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