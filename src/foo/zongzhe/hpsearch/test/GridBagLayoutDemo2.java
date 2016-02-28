package foo.zongzhe.hpsearch.test;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class GridBagLayoutDemo2 extends JFrame {

	/**
     * 
     */
	private static final long serialVersionUID = -4481121176026056530L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GridBagLayoutDemo2 frame = new GridBagLayoutDemo2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GridBagLayoutDemo2() {
		setTitle("�����鲼��");// ���ô���ı���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô����˳�ʱ����
		setBounds(100, 100, 800, 600);// ���ô���λ�úʹ�С
		contentPane = new JPanel();// �����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������ı߿�
		setContentPane(contentPane);// Ӧ���������
		GridBagLayout gbl_contentPane = new GridBagLayout();// ���������鲼��
		contentPane.setLayout(gbl_contentPane);// ʹ�������鲼��

		JButton button1 = new JButton("A");// ������ť
		GridBagConstraints gbc_button1 = new GridBagConstraints();// ���������鲼��Լ������
		gbc_button1.insets = new Insets(0, 0, 5, 5);// ���ÿؼ��Ŀհ�
		gbc_button1.fill = GridBagConstraints.HORIZONTAL;// ������䷽ʽ
		gbc_button1.weightx = 10.0;// ��һ�еķֲ���ʽΪ10%
		gbc_button1.gridx = 0;// ��ʼ��Ϊ��1��
		gbc_button1.gridy = 0;// ��ʼ��Ϊ��1��
		contentPane.add(button1, gbc_button1);// ���Ӱ�ť����Լ������

		JButton button2 = new JButton("B");// ������ť
		GridBagConstraints gbc_button2 = new GridBagConstraints();// ���������鲼��Լ������
		gbc_button2.insets = new Insets(0, 5, 5, 5);// ���ÿؼ��Ŀհ�
		gbc_button2.fill = GridBagConstraints.HORIZONTAL;// ������䷽ʽ
		gbc_button2.weightx = 10.0;// ��һ�еķֲ���ʽΪ20%
		gbc_button2.gridx = 1;// ��ʼ��Ϊ��2��
		gbc_button2.gridy = 0;// ��ʼ��Ϊ��1��
		contentPane.add(button2, gbc_button2);// ���Ӱ�ť����Լ������

		JButton button3 = new JButton("C");// ������ť
		GridBagConstraints gbc_button3 = new GridBagConstraints();// ���������鲼��Լ������
		gbc_button3.gridheight = 2;// ռ��2��
		gbc_button3.fill = GridBagConstraints.BOTH;// ������䷽ʽ
		gbc_button3.weightx = 30.0;// ��һ�еķֲ���ʽΪ30%
		gbc_button3.insets = new Insets(0, 0, 5, 5);// ���ÿؼ��Ŀհ�
		gbc_button3.gridx = 2;// ��ʼ��Ϊ��3��
		gbc_button3.gridy = 0;// ��ʼ��Ϊ��1��
		contentPane.add(button3, gbc_button3);// ���Ӱ�ť����Լ������

		JButton button4 = new JButton("D");// ������ť
		GridBagConstraints gbc_button4 = new GridBagConstraints();// ���������鲼��Լ������
		gbc_button4.weightx = 40.0;// ��һ�еķֲ���ʽΪ40%
		gbc_button4.fill = GridBagConstraints.BOTH;// ������䷽ʽ
		gbc_button4.gridheight = 4;// ռ��4��
		gbc_button4.insets = new Insets(0, 5, 0, 0);// ���ÿؼ��Ŀհ�
		gbc_button4.gridx = 3;// ��ʼ��Ϊ��4��
		gbc_button4.gridy = 0;// ��ʼ��Ϊ��1��
		contentPane.add(button4, gbc_button4);// ���Ӱ�ť����Լ������

		JButton button5 = new JButton("E");// ������ť
		GridBagConstraints gbc_button5 = new GridBagConstraints();// ���������鲼��Լ������
		gbc_button5.fill = GridBagConstraints.HORIZONTAL;// ������䷽ʽ
		gbc_button5.gridwidth = 2;// ռ��2��
		gbc_button5.insets = new Insets(5, 0, 5, 5);// ���ÿؼ��Ŀհ�
		gbc_button5.gridx = 0;// ��ʼ��Ϊ��1��
		gbc_button5.gridy = 1;// ��ʼ��Ϊ��2��
		contentPane.add(button5, gbc_button5);// ���Ӱ�ť����Լ������

		JButton button6 = new JButton("F");// ������ť
		GridBagConstraints gbc_button6 = new GridBagConstraints();// ���������鲼��Լ������
		gbc_button6.fill = GridBagConstraints.HORIZONTAL;// ������䷽ʽ
		gbc_button6.insets = new Insets(0, 0, 5, 5);// ���ÿؼ��Ŀհ�
		gbc_button6.gridx = 0;// ��ʼ��Ϊ��1��
		gbc_button6.gridy = 2;// ��ʼ��Ϊ��3��
		contentPane.add(button6, gbc_button6);// ���Ӱ�ť����Լ������

		JButton button7 = new JButton("G");// ������ť
		GridBagConstraints gbc_button7 = new GridBagConstraints();// ���������鲼��Լ������
		gbc_button7.fill = GridBagConstraints.BOTH;// ������䷽ʽ
		gbc_button7.gridheight = 2;// ռ��2��
		gbc_button7.gridwidth = 2;// ռ��2��
		gbc_button7.insets = new Insets(5, 5, 0, 5);// ���ÿؼ��Ŀհ�
		gbc_button7.gridx = 1;// ��ʼ��Ϊ��2��
		gbc_button7.gridy = 2;// ��ʼ��Ϊ��3��
		contentPane.add(button7, gbc_button7);// ���Ӱ�ť����Լ������

		JButton button8 = new JButton("H");// ������ť
		GridBagConstraints gbc_button8 = new GridBagConstraints();// ���������鲼��Լ������
		gbc_button8.fill = GridBagConstraints.HORIZONTAL;// ������䷽ʽ
		gbc_button8.insets = new Insets(5, 0, 0, 0);// ���ÿؼ��Ŀհ�
		gbc_button8.gridx = 0;// ��ʼ��Ϊ��1��
		gbc_button8.gridy = 3;// ��ʼ��Ϊ��4��
		contentPane.add(button8, gbc_button8);// ���Ӱ�ť����Լ������

	}

}
