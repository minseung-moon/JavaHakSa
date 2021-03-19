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
			System.out.println("종료 실패");
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
			System.out.println("Major의 전체 데이터를 갖고 오지 못했습니다");
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
			JOptionPane.showMessageDialog(null, "이미 존재하는 학과 입니다");
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
			JOptionPane.showMessageDialog(null, "존재하지 않는 학과 입니다");
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
			JOptionPane.showMessageDialog(null, "존재하지 않는 학과 입니다!");
			e.printStackTrace();
		}
		return check;
	}
}
