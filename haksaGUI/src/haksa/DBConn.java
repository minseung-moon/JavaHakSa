package haksa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	// jdbc:mysql://localhost:3306/database_name?serverTimezone=UTC
		String dbURL = "jdbc:mysql://localhost:3306/school?serverTimezone=UTC";
		String id = "root";
		String password = "1111";
		Connection conn;
		
		// ������
		public DBConn() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC
				System.out.println("DB ������ ...");
				conn = DriverManager.getConnection(dbURL,id,password); // DB ����
				System.out.println("������ ���̽� ���� ����");
			} catch (ClassNotFoundException e) {
				System.out.println("JDBC ����̹� ã�� �� ����");
			} catch ( SQLException e) {
				System.out.println("�����ͺ��̽� ���ῡ ����");
			}
		}
		
//		public static void main(String[] args) {
//			new DBConn();
//		}
}
