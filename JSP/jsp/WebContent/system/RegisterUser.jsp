<%@page import="java.awt.Window"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%  
	response.setCharacterEncoding("utf-8");
	String condition=(String)session.getAttribute("Registeradd");
	if(condition=="失败"){
		%>
		<script type="text/javascript">
		window.location.href = "./UserLogin.html";
		</script>
		<% 
	}
	else if(condition=="成功"){
		%>
		<script type="text/javascript">
		window.location.href = "./userIndex.html";
		</script>
		<% 
	}
%>
</body>
</html>