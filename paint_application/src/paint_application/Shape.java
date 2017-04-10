package paint_application;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Shape {
	int shpId;
	int kind;

	int p1_x;
	int p1_y;

	int p2_x;
	int p2_y;

	Color color;
	int userId;

	public Shape() {
		shpId = 0;
		p1_x = 0;
		p1_y = 0;
		p2_x = 0;
		p2_y = 0;
		userId = 0;
		color = Color.WHITE;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Color getColor() {
		return color;
	}

	public int[] getP1() {
		int p1[] = { p1_x, p1_y };
		return p1;
	}

	public int[] getP2() {
		int p2[] = { p1_x, p1_y };
		return p2;
	}

	public int getUserId() {
		return userId;
	}

	public int getKind() {
		return kind;
	}

	public void setP1(int p1_x, int p1_y1) {
		this.p1_x = p1_x;
		this.p1_y = p1_y1;
	}

	public void setP2(int p2_x, int p2_y1) {
		this.p2_x = p2_x;
		this.p2_y = p2_y1;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract void draws(Graphics g);

	public abstract void addShape() throws ClassNotFoundException, SQLException;

	public abstract void deleteShape(Shape sh1);

	public static void getAllshapes(int usrId, Graphics g) {
		try {
			ArrayList<Shape> ShapeList = ShapeEntityManager.getAllShapes(usrId);
			for (Shape sh : ShapeList) {
				if(sh.kind==1)
				{
					Line li = (Line) sh;
					li.draws(g);
				}
				if(sh.kind==2)
				{
					Circle cr = (Circle) sh;
					cr.draws(g);
				}
				if(sh.kind==3)
				{
					Rectangle re = (Rectangle) sh;
					re.draws(g);
				}
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}