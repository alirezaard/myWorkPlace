package paint_application;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class myPaint extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void drawCircle(Color clStr, int center_x, int center_y,
			int raduse1, int raduse2, Graphics g) {

		g.setColor(clStr);
		g.drawOval(center_x, center_y, raduse1, raduse2);

	}

	public void drawRect(Color clStr, int p1x, int p1y, int p2x, int p2y,
			Graphics g) {
		g.setColor(clStr);
		g.drawRect(p1x, p1y, p2x, p2y);
	}

	public void drawLine(Color clStr, int p1x, int p1y, int p2x, int p2y,
			Graphics g) {
		g.setColor(clStr);
		g.drawLine(p1x, p1y, p2x, p2y);
	}
}
