<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<fruitVo> list = (List<fruitVo>) request.getAttribute("list");
%>
<html>
<body>
	<h1>입고 조회</h1>
	<form id="frm" action="importlist" method="post">
		<table>
			<tr>
				<th>입고번호</th>
				<th>물품이름</th>
				<th>입고수량</th>
				<th>입고일자</th>
			</tr>
				<%for(fruitVo vo : list){ %>
			<tr>
				<th><%=vo.getI_no() %></th>
				<th><%=vo.getP_name() %></th>
				<th><%=vo.getI_cnt() %></th>
				<th><%=vo.getI_date() %></th>
			</tr>
				<%} %>
		</table>
	</form>
</body>
</html>