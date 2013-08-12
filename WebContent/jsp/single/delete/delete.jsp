<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>削除</title>
</head>
<body>
	<c:if test="${!empty message}"><font color="red"><c:out value="${message}"/></font><br><br></c:if>
	削除を行うデータのIDを入力して下さい。<br>
	<font color="red">※</font>は必須です。<br><br>
	<form action="${pageContext.request.contextPath}/single/delete/delete" method="post">
		<table border="1">
			<tr>
				<th><font color="red">※</font>ID</th>
				<td><input type="text" name="userId" value="<c:out value="${param.userId}"/>"></td>
			</tr>
		</table>
		<input type="submit" value="確認">
	</form>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/menu.jsp">メニューに戻る</a>
</body>
</html>