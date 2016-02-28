package foo.zongzhe.hpresearch.view;

import java.awt.*;
import java.io.File;

import javax.swing.*;

import foo.zongzhe.hpresearch.action.DirectoryAction;

public class RotateView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double xuanzhuan = 0;
	public Image image;
	final int IMAGE_WIDTH = 600;
	final int IMAGE_HEGHT = 600;

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 200, 200);
		g2d.rotate(xuanzhuan, IMAGE_WIDTH/2, IMAGE_HEGHT/2);

//		String inputPath2 = "C:/hptest/input/images/group1/4.jpg";
//		File inputText = new File(inputPath2);
//		image = new ImageIcon(inputPath2).getImage();
//		DirectoryAction da = new DirectoryAction();
//		System.out.println("image exist: "
//				+ da.whetherDirectoryExists(inputText));
		image = getImage();
		g2d.drawImage(image, 0, 0, IMAGE_WIDTH,IMAGE_HEGHT, this);
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