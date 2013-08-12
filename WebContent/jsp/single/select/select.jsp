<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索</title>
</head>
<body>
	<c:if test="${!empty message}"><font color="red"><c:out value="${message}"/></font><br><br></c:if>
	検索したいデータ情報を入力して下さい。<br>
	※全て空白の場合は全検索を行います。<br><br>
	<form action="${pageContext.request.contextPath}/single/select/select" method="post">
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" name="userId" value="<c:out value="${param.userId}"/>"></td>
			</tr>
			<tr>
				<th>名前</th>
				<td><input type="text" name="name" value="<c:out value="${param.name}"/>"></td>
			</tr>
			<tr>
				<th>TEL</th>
				<td><input type="text" name="tel" value="<c:out value="${param.tel}"/>"></td>
			</tr>
		</table>
		<input type="submit" value="検索">
	</form>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/menu.jsp">メニューに戻る</a>
</body>
</html>