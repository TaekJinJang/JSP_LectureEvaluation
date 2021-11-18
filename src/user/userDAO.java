package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import util.DatabaseUtil;

public class userDAO {

	public int login(String userID,String userPW)  {
		String SQL = "SELECT userPW FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(userPW)) {
					return 1; // �α��� ����
				}
				else {
					return 0; // ��й�ȣ Ʋ��
				}
			}
			return -1; // ���̵� ����
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (conn != null) conn.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (pstmt != null) pstmt.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (rs != null) rs.close();}  catch (Exception e) {e.printStackTrace();}		
			}
	
		return -2; // �����ͺ��̽� ����
	}
	public int join(UserDTO user)  {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, false)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPW());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			return pstmt.executeUpdate(); // 1
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (conn != null) conn.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (pstmt != null) pstmt.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (rs != null) rs.close();}  catch (Exception e) {e.printStackTrace();}		
			}
	
		return -1; // ȸ������ ����
	}
	
	public String getUserEmail(String userID) {
		String SQL = "SELECT userEmail FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getString(1); // �̸��� �ּ� ��ȯ
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // �����ͺ��̽� ����
	}
	
	public boolean getUserEmailChecked(String userID)  {
		String SQL = "SELECT userEmailChecked FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				return rs.getBoolean(1);  // �̸��� ��� ���� ��ȯ
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {if (conn != null) conn.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (pstmt != null) pstmt.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (rs != null) rs.close();}  catch (Exception e) {e.printStackTrace();}		
			}
	
		return false; // �����ͺ��̽� ����
	}
	public boolean setUserEmailChecked(String userID)  {
		String SQL = "UPDATE USER SET userEmailChecked = true WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			return true; // �̸��� ��� ���� ����
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {if (conn != null) conn.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (pstmt != null) pstmt.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (rs != null) rs.close();}  catch (Exception e) {e.printStackTrace();}		
			}
	
		return false; // �̸��� ��� ���� ����
	}
}