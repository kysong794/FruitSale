<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	String title = (String) request.getAttribute("title");
	String view = (String) request.getAttribute("view");
	
	if(title==null || title == ""){
		title = "home.jsp";
	}
	if(view==null || view == ""){
		view = "홈";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title %></title>
</head>
<body>
	<header><h1>물품 재고 관리 ver1.0</h1></header>
	<nav>
		<a href="reg">물품등록</a>
		<a href="import">입고</a>
		<a href="export">출고</a>
		<a href="productlist">물품조회</a>
		<a href="importlist">입고조회</a>
		<a href="exportlist">출고조회</a>
		<a href="home.jsp">홈으로</a>
	</nav>
	<div><jsp:include page="<%=view %>"></jsp:include></div>
	<footer>SMKim Copyright&copy; 2018 All right reserved.Sungmin Kim</footer>
</body>
</html>