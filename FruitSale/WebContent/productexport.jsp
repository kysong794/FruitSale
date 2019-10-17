<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<fruitVo> list = (List<fruitVo>)request.getAttribute("list");
%>
<html>
<body>
	<h1>물품 출고</h1>
	<form id="frm" action="export" method="post" onsubmit='return che();'>
		<table>
			<tr>
				<th>물품 목록</th>
				<td><select name="p_no">
				<option value="">--품목 선택 --</option>
				<%for (fruitVo vo : list){ %>
				<option value="<%=vo.getP_no() %>"><%=vo.getP_name() %></option>
				<%} %>
				</select></td>
			</tr>
			<tr>
				<th>출고 수량</th>
				<td><input type="number" name="e_cnt"></td>
			</tr>
			<tr>
				<th><input type="submit" value="출고"></th>
				<th><a href="productlist"><button type="button">조회</button></a></th>
			</tr>
		</table>
	</form>
</body>
</html>
<script>
	function che(){
		if(frm.p_no.value == 0 || frm.p_no.value ==''){
			alert('품목을 선택하세요.');
			return false;
		}
		if(frm.e_cnt.value == 0 || frm.e_cnt.value == ''){
			alert('1이상의 숫자를 입력하세요');
			return false;
		}
		return true;
	}
</script>