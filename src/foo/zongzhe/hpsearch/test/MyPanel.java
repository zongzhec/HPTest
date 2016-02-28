package foo.zongzhe.hpsearch.test;

import java.awt.*;
import javax.swing.*;

class MyPanel extends JPanel {
	private double xuanzhuan = 0;
	private Image image;

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(0, 0, 200, 200);
		g2d.rotate(xuanzhuan, 200, 200);
		image = new ImageIcon("src/05.jpg").getImage();
		g2d.drawImage(image, 0, 0, this);
		g.dispose();
	}

	public Image getImage() {
		return image;
	}

	public double getXuanzhuan() {
		return xuanzhuan;
	}

	public void setXuanzhuan(double xuanzhuan) {
		this.xuanzhuan = xuanzhuan;
	}
}