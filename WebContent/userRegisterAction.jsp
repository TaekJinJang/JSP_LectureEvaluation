<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO"%>
<%@ page import="user.userDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="java.io.PrintWriter"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	if (userID != null) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('로그인이 된 상태입니다.')");
	script.println("location.href = 'index.jsp';");
	script.println("</script>");
	script.close();
	}
	String userPW = null;
	String userEmail = null;
	if(request.getParameter("userID") != null) {
		userID = (String)request.getParameter("userID"); 
	}
	if(request.getParameter("userPW") != null) {
		userPW = (String)request.getParameter("userPW"); 
	}
	if(request.getParameter("userEmail") != null) {
		userEmail = (String) request.getParameter("userEmail"); 
	}
	if(userID.equals("")|| userPW.equals("")|| userEmail.equals("")) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	userDAO UserDAO = new userDAO();
	int result = UserDAO.join(new UserDTO(userID,userPW,userEmail,SHA256.getSHA256(userEmail),false));
	if (result == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 존재하는 아이디입니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	} else {
		session.setAttribute("userID",userID);
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = 'emailSendAction.jsp';");
		script.println("</script>");
		script.close();
	}
%>