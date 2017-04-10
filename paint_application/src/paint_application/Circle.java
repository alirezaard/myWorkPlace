package paint_application;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;

public class Circle extends Shape {

	@Override
	public void draws(Graphics g) {

		int center_x = Math.min(this.p1_x, this.p2_x);
		int center_y = Math.min(this.p1_y, this.p2_y);
		int raduse1 = Math.abs(this.p1_x - this.p2_x);
		int raduse2 = Math.abs(this.p1_y - this.p2_y);
		myPaint pa = new myPaint();
		pa.drawCircle(super.getColor(), center_x, center_y, raduse1, raduse2, g);

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
