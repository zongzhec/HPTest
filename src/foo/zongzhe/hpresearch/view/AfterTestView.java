package foo.zongzhe.hpresearch.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AfterTestView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ���
	JFrame frame2 = new JFrame();

	// �ܵĿ�ܺͲ���
	JPanel jpPreWel2;

	// ����ϵ�����
	JLabel jl2;

	public AfterTestView() {

//		 showPage("���ڽ����д���ļ�");
	}

	public void showPage(String title) {

		// ���ÿ��
		frame2.setSize(500, 300);
		frame2.setLocation(600, 300);

		// �ܵĿ�ܺͲ���
		jpPreWel2 = new JPanel();
		jpPreWel2.setBounds(5, 5, 5, 5);

		// ��������ϵ�ͨ�ò���
		Font fontForFillingText = new Font("TimesRoman", Font.PLAIN, 20);

		jl2 = new JLabel("<html><body><p></p><p><center>" + title + "</center></p><p><center>�������Ҫ������</center></p><body></html>");
		jl2.setFont(fontForFillingText);
		jl2.setHorizontalAlignment(JLabel.CENTER);

		jpPreWel2.add(jl2, BorderLayout.CENTER);
		jpPreWel2.setBackground(Color.blue);
		frame2.add(jpPreWel2);

		frame2.setTitle("���ڼ����������Ժ�");
		
		frame2.setResizable(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame2.setVisible(true);
	}

	public void hidePage() {
		frame2.setVisible(false);
	}

	public static void main(String[] args) {
		new AfterTestView();

	}

}
