package haksa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MajorDAO {
	DBConn conn;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MajorDAO() {
		conn = new DBConn();
		con = conn.conn;
	}
	
	public void close() {
		try {
			if(rs != null) rs.close(); 
			if(pstmt != null) pstmt.close();
			if(conn.conn != null) conn.conn.close();
		} catch (Exception e) {
			System.out.println("���� ����");
		}
	}
	
	public ArrayList<MajorDTO> selectMajor() {
		ArrayList<MajorDTO> allMajorDatas = new ArrayList<MajorDTO>();
		
		try {
			String sql = "select * from major";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MajorDTO dto = new MajorDTO(rs.getString(1), rs.getString(2), rs.getString(3));
				allMajorDatas.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("Major�� ��ü �����͸� ���� ���� ���߽��ϴ�");
		}
		return allMajorDatas;
	}
	
	public boolean inserMajor(String[] majorValue) {
		boolean check = false;
		
		try {
			String sql = "insert into major values(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, majorValue[0]);
			pstmt.setString(2, majorValue[1]);
			pstmt.setString(3, majorValue[2]);
			int num = pstmt.executeUpdate();
			
			if(num == 1) check = true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "�̹� �����ϴ� �а� �Դϴ�");
		}
		return check;
	}
	
	public boolean updateMajor(String[] majorValue) {
		boolean check = false;
		
		try {
			String sql = "update major set department = ?, major = ? where code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, majorValue[1]);
			pstmt.setString(2, majorValue[2]);
			pstmt.setString(3, majorValue[0]);
			int num = pstmt.executeUpdate();
			if(num == 1) check = true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "�������� �ʴ� �а� �Դϴ�");
		}
		return check;
	}
	
	public boolean deleteMajor(String code) {
		boolean check = false;
		
		try {
			String sql = "delete from major where code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			int i = pstmt.executeUpdate();
			if(i == 1) {
				check = true;
			}
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "�������� �ʴ� �а� �Դϴ�!");
			e.printStackTrace();
		}
		return check;
	}
}
