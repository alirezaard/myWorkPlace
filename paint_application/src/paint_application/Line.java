package paint_application;

import java.awt.Graphics;
import java.sql.SQLException;

public class Line extends Shape {

	@Override
	public void draws(Graphics g) {
		myPaint pa = new myPaint();
		pa.drawLine(super.getColor(), this.p1_x, this.p1_y, this.p2_x,
				this.p2_y, g);
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
