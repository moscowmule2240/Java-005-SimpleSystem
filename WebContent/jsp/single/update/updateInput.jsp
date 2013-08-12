<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新入力</title>
</head>
<body>
	<c:if test="${!empty message}"><font color="red"><c:out value="${message}"/></font><br><br></c:if>
	１箇所以上の項目を変更してください。<br>
	※IDは変更できません。<br><br>
	<form name="form1" action="${pageContext.request.contextPath}/single/update/updateInput" method="post">
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" name="userId" value="<c:out value="${user.userId}"/>" style="background:#CCCCCC;color:black;" readonly="readonly"></td>
			</tr>
			<tr>
				<th>名前</th>
				<td><input type="text" name="userName" value="<c:out value="${user.userName}"/>" ></td>
			</tr>
			<tr>
				<th>TEL</th>
				<td><input type="text" name="tel" value="<c:out value="${user.tel}"/>" ></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type="password" name="password" value="<c:out value="${user.pass}"/>" ></td>
			</tr>
		</table>
	</form>
	<form name="form2" action="${pageContext.request.contextPath}/jsp/single/update/update.jsp" method="post"></form>
	<table>
		<tr>
			<td><input type="button" value="更新" onclick="document.form1.submit()"></td>
			<td><input type="button" value="更新TOPに戻る" onclick="document.form2.submit()"></td>
		</tr>
	</table>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/menu.jsp">メニューに戻る</a>
</body>
</html>