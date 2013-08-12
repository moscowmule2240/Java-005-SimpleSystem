<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録確認</title>
</head>
<body>
	<c:if test="${!empty message}"><font color="red"><c:out value="${message}"/></font><br><br></c:if>
	これでよろしいですか？<br><br>
	<form name="form1" action="${pageContext.request.contextPath}/single/insert/insertConfirm" method="post">
		<input type="hidden" name="passwordPrevious" value="<c:out value="${param.password}"/>">
		<table border="1">
			<tr>
				<th>名前</th>
				<td><input type="text" name="name" value="<c:out value="${param.name}"/>" style="background:#CCCCCC;color:black;" readonly="readonly"></td>
			</tr>
			<tr>
				<th>TEL</th>
				<td><input type="text" name="tel" value="<c:out value="${param.tel}"/>" style="background:#CCCCCC;color:black;" readonly="readonly"></td>
			</tr>
			<tr>
				<th>パスワード(再入力)</th>
				<td><input type="password" name="password"></td>
			</tr>
		</table>
	</form>
	<form name="form2" action="${pageContext.request.contextPath}/jsp/single/insert/insert.jsp" method="post"></form>
	<table>
		<tr>
			<td><input type="button" value="登録" onclick="document.form1.submit()"></td>
			<td><input type="button" value="登録TOPに戻る" onclick="document.form2.submit()"></td>
		</tr>
	</table>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/menu.jsp">メニューに戻る</a>
</body>
</html>