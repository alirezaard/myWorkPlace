package paint_application;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ShapeEntityManager {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/paint";
	static final String USER = "root";
	static final String PASS = "";

	protected static void addShape(int user, int p1x, int p1y, int p2x,
			int p2y, String color, int kind) throws ClassNotFoundException,
			SQLException {

		Connection conn = null;
		Statement stmt = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();

		String sql;
		sql = "INSERT INTO `paint`.`shape` (`sh_usr`, `shp_kind`, `shp_p1x`, `shp_p1y`, `shp_p2x`, `shp_p2y`, `shp_color`) "
				+ "VALUES ('"
				+ user
				+ "', '"
				+ kind
				+ "', '"
				+ p1x
				+ "', '"
				+ p1y + "', '" + p2x + "', '" + p2y + "', '" + color + "');";
		stmt.executeUpdate(sql);
		conn.close();
	}

	static protected void deleteShape(Shape sh1) throws ClassNotFoundException,
			SQLException {

		Connection conn = null;
		Statement stmt = null;

		Class.forName("com.mysql.jdbc.Driver");
		// STEP 3: Open a connection
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		// STEP 4: Execute a query
		stmt = conn.createStatement();
		String sql;
		sql = "DELETE FROM  `paint`.`shape` where shp_id = " + sh1.shpId;
		stmt.executeUpdate(sql);
		conn.close();
	}

	static protected ArrayList<Shape> getAllShapes(int usrId)
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		Class.forName("com.mysql.jdbc.Driver");
		// STEP 3: Open a connection
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		// STEP 4: Execute a query
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT * FROM `paint`.`shape` where sh_usr = " + usrId + ";";
		ResultSet rs = stmt.executeQuery(sql);

		ArrayList<Shape> mylist = new ArrayList<Shape>();
		while (rs.next()) {
			int sh_usr = rs.getInt(2);
			int kind = rs.getInt(3);
			int shp_p1x = rs.getInt(4);
			int shp_p1y = rs.getInt(5);
			int shp_p2x = rs.getInt(6);
			int shp_p2y = rs.getInt(7);
			Color shp_color = new Color(Integer.parseInt(rs.getString(8)));

			Shape sh = null;
			switch (kind) {
			case 1:
				sh = new Line();
				break;
			case 2:
				sh = new Circle();
				break;
			case 3:
				sh = new Rectangle();
				break;
			default:
				break;
			}
			sh.kind = kind;
			sh.setColor(shp_color);
			sh.setP1(shp_p1x, shp_p1y);
			sh.setP2(shp_p2x, shp_p2y);
			sh.setUserId(sh_usr);
			mylist.add(sh);
		}

		rs.close();
		stmt.close();
		conn.close();
		return mylist;
	}
}
