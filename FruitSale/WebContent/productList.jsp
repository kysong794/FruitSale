<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<fruitVo> list = (List<fruitVo>) request.getAttribute("list");
%>
<html>
<body>
	<h1>물품 조회</h1>
	<form id="frm" action="productlist" method="post">
		<table>
			<tr>
				<th>물품번호</th>
				<th>물품이름</th>
				<th>물품수량</th>
				<th>등록일자</th>
			</tr>
				<%for(fruitVo vo : list){ %>
			<tr>
				<th><a href="mod?p_no=<%=vo.getP_no() %>"><%=vo.getP_no() %></a></th>
				<th><%=vo.getP_name() %></th>
				<th><%=vo.getP_cnt() %></th>
				<th><%=vo.getP_regdate() %></th>
			</tr>
				<%} %>
		</table>
	</form>
</body>
</html>