package foo.zongzhe.hpresearch.view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PreWelcomeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ���
	JFrame frame = new JFrame();

	// �ܵĿ�ܺͲ���
	JPanel jpPreWel;

	// ����ϵ�����
	JLabel jl1;

	public PreWelcomeView() {

		//showPage();
	}

	public void showPage() {

		// ���ÿ��
		frame.setSize(400, 300);
		frame.setLocation(600, 300);

		// �ܵĿ�ܺͲ���
		jpPreWel = new JPanel();
		jpPreWel.setBounds(5, 5, 5, 5);

		// ��������ϵ�ͨ�ò���
		Font fontForFillingText = new Font("TimesRoman", Font.PLAIN, 20);

		jl1 = new JLabel(
				"<html><body><p></p><p><center>���ڶ�ȡͼƬ������</center></p><p><center>�������Ҫ������</center></p><body></html>");
		jl1.setFont(fontForFillingText);
		jl1.setHorizontalAlignment(JLabel.CENTER);

		jpPreWel.add(jl1, BorderLayout.CENTER);
		frame.add(jpPreWel);

		frame.setTitle("ͷ��λ���о�����");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void hidePage(){
		frame.setVisible(false);;
	}

	public static void main(String[] args) {
		new PreWelcomeView();

	}

}
