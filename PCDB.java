package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

public class PCDB {
	public static Connection conn;
	public static Statement stmt;

	public static void init() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "temp", "1234");
			stmt = conn.createStatement();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
	
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// ��ȸ
	public static ResultSet getResultSet(String sql) {
		try {
			Statement stmt = conn.createStatement();

			return stmt.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}

	}

	// ���� ,����,����
	public static void executeQuery(String sql) {
		try {
			stmt.execute(sql);
			System.out.println(sql);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);

		}
	}
}
