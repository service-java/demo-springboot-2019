package com.book.chapter.one;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GeneralPathDemo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GeneralPathDemo() {
		super();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// �����
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		// ����ǵ����������
		int x1 = this.getWidth() / 2;
		int y1 = 20;
		int x2 = this.getWidth() / 5;
		int y2 = this.getHeight() - 20;
		int x3 = x2 * 4;
		int y3 = this.getHeight() - 20;
		int x4 = 20;
		int y4 = this.getHeight() / 3;
		int x5 = this.getWidth() - 20;
		int y5 = y4;

		// ���廭���˳��
		int x1Points[] = { x1, x2, x5, x4, x3 };
		int y1Points[] = { y1, y2, y5, y4, y3 };

		// ���������ɫ
		g2d.setPaint(Color.RED);

		// ʵ����GeneralPath����
		GeneralPath polygon = new GeneralPath(
				GeneralPath.WIND_EVEN_ODD,
				x1Points.length);
		// ê�ؿ�ʼ��һ����
		polygon.moveTo(x1Points[0], y1Points[0]);

		// ˳�򻭳�ʣ�µ�
		for (int i = 1; i < x1Points.length; i++) {
			polygon.lineTo(x1Points[i], y1Points[i]);
		}

		// ����closePath�γ�һ����ռ�����״
		polygon.closePath();

		// ������
		g2d.draw(polygon);

		// �ͷ���Դ
		g2d.dispose();
	}

	public static void main(String[] args) {
		JFrame ui = new JFrame("Demo Graphics");
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.getContentPane().setLayout(new BorderLayout());
		ui.getContentPane().add(new GeneralPathDemo(), BorderLayout.CENTER);
		ui.setPreferredSize(new Dimension(380, 380));
		ui.pack();
		ui.setVisible(true);
	}

}
