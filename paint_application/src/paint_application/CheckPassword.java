package paint_application;
import java.sql.*;

public class CheckPassword {
	int UserID;
	String Username;
	String Password;

	private void setPassword(String password) {
		Password = password;
	}

	private void setUsername(String username) {
		Username = username;
	}
	
	private void setUserID(int userID) {
		UserID = userID;
	}

	public CheckPassword(int userID,String usr, String pwd) {
		setPassword(pwd);
		setUsername(usr);
		setUserID(userID);
	}

	public static int isEquals(String usr, String psw)
			throws ClassNotFoundException, SQLException {
		CheckPassword myUser = pswEntityManager.getUser(usr, psw);
		if (myUser.Username.equals("not")) {
			return 0;
		}
		return myUser.UserID;
	}
}

class pswEntityManager {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/paint";
	static final String USER = "root";
	static final String PASS = "";

	protected static CheckPassword getUser(String username, String password)
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		Class.forName("com.mysql.jdbc.Driver");
		// STEP 3: Open a connection
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		// STEP 4: Execute a query
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT * FROM paint.users where usr_name = '" + username
				+ "' and usr_pass = '" + password + "';";
		ResultSet rs = stmt.executeQuery(sql);

		CheckPassword myUser = new CheckPassword(0,"not", "not");
		if (rs.next()) {
			myUser = new CheckPassword(rs.getInt(1),rs.getString(2), rs.getString(2));
		}
		rs.close();
		stmt.close();
		conn.close();
		return myUser;
	}
}
