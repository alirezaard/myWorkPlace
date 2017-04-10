package paint_application;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;

public class Rectangle extends Shape {

	@Override
	public void draws(Graphics g) {
		myPaint pa = new myPaint();
		int width = Math.abs(p2_x - p1_x);
		int height = Math.abs(p2_y - p1_y);
		int x = Math.min(p1_x, p2_x);
		int y = Math.min(p1_y, p2_y);
		pa.drawRect(super.getColor(), x, y, width, height, g);
	}

	@Override
	public void addShape() throws ClassNotFoundException, SQLException {
		ShapeEntityManager.addShape(userId, p1_x, p1_y, p2_x, p2_y, Integer.toString(color.getRGB()), kind);
	}

	@Override
	public void deleteShape(Shape sh1) {
		// TODO Auto-generated method stub

	}

}
