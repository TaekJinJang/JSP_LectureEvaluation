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
					return 1; // 로그인 성공
				}
				else {
					return 0; // 비밀번호 틀림
				}
			}
			return -1; // 아이디 없음
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (conn != null) conn.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (pstmt != null) pstmt.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (rs != null) rs.close();}  catch (Exception e) {e.printStackTrace();}		
			}
	
		return -2; // 데이터베이스 오류
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
	
		return -1; // 회원가입 실패
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
				return rs.getString(1); // 이메일 주소 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 데이터베이스 오류
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
				return rs.getBoolean(1);  // 이메일 등록 여부 반환
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {if (conn != null) conn.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (pstmt != null) pstmt.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (rs != null) rs.close();}  catch (Exception e) {e.printStackTrace();}		
			}
	
		return false; // 데이터베이스 오류
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
			return true; // 이메일 등록 설정 성공
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {if (conn != null) conn.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (pstmt != null) pstmt.close();}  catch (Exception e) {e.printStackTrace();}		
			try {if (rs != null) rs.close();}  catch (Exception e) {e.printStackTrace();}		
			}
	
		return false; // 이메일 등록 설정 실패
	}
}
