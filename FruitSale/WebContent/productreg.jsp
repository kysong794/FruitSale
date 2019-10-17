<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<html>
<body>
	<h1>물품 등록</h1>
	<form id="frm" action="reg" method="post" onsubmit="return che();">
		<div>물품 이름 : <input type="text" name="p_name"></div>
		<div><input type="submit" value="등록"></div>
	</form>
</body>
</html>