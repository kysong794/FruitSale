<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<fruitVo> list = (List<fruitVo>) request.getAttribute("list");
%>
<html>
<body>
<h1>물품 정보 수정</h1>
	<form id="frm" action="mod" method="post">
		<table>
				<%for(fruitVo vo : list){ %>
			<tr>
				<th>물품번호</th>
				<th><input type="number" name="p_no" value="<%=vo.getP_no() %>" readonly="readonly"></th>
			</tr>
			<tr>
				<th>물품이름</th>
				<th><input type="text" name="p_name" value="<%=vo.getP_name() %>"></th>
			</tr>
			<tr>
				<th>물품수량</th>
				<th><input type="number" name="p_cnt" value="<%=vo.getP_cnt() %>" readonly="readonly"></th>
			</tr>
			<tr>
				<th>등록일자</th>
				<th><input type="text" name="p_regdate" value="<%=vo.getP_regdate() %>"readonly="readonly"></th>
			</tr>
			<tr>
				<td><input type="submit" value="수정"></td>			
				<th><a href="productlist"><button type="button">조회</button></a></th>
			</tr>
				<%} %>
		</table>
	</form>
</body>
</html>