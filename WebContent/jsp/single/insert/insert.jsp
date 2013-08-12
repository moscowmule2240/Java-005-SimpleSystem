<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録</title>
</head>
<body>
	<c:if test="${!empty message}"><font color="red"><c:out value="${message}" /></font><br><br></c:if>
	必須項目を入力して下さい。<br>
	<font color="red">※</font>は必須です。<br><br>
	<form action="${pageContext.request.contextPath}/single/insert/insert" method="post">
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" value="自動で設定されます" readonly="readonly" disabled="disabled" style="background:#F5F5F5;color:black;"></td>
			</tr>
			<tr>
				<th>名前</th>
				<td><input type="text" name="name" maxlength="12" value="<c:out value="${param.name}" />"></td>
			</tr>
			<tr>
				<th>TEL</th>
				<td><input type="text" name="tel" maxlength="12" value="<c:out value="${param.tel}" />"></td>
			</tr>
			<tr>
				<th><font color="red">※</font>パスワード</th>
				<td><input type="password" name="password" maxlength="12" value="<c:out value="${param.password}" />"></td>
			</tr>
		</table>
		<input type="submit" value="確認">
	</form>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/menu.jsp">メニューに戻る</a>
</body>
</html>