<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一括管理画面</title>
</head>
<body>
	<c:if test="${!empty message}"><font color="red"><c:out value="${message}"/></font><br><br></c:if>
	<c:if test="${!empty loginUser.userName}">実行者：<c:out value="${loginUser.userName}"/><br></c:if>
	一括更新を行います。<br>
	必要項目を入力して下さい。<br><br>
	<form action="${pageContext.request.contextPath}/multi/lump" method="post">
		<c:if test="${!empty users && !users.isEmpty()}">
			<table border="1">
				<thead>
					<tr>
						<th>ID</th>
						<th>名前</th>
						<th>TEL</th>
						<th>PASS</th>
						<th>削除</th>
					</tr>
				</thead>
				<c:forEach var="item" items="${users}">
					<tr>
						<td><input type="text" name="userIds" value="<c:out value="${item.userId}"/>" readonly="readonly" style="background:gainsboro;"></td>
						<td><input type="text" name="userNames" maxlength="12" value="<c:out value="${item.userName}"/>"></td>
						<td><input type="text" name="tels" maxlength="12" value="<c:out value="${item.tel}"/>"></td>
						<td><input type="text" name="passes" maxlength="12" value="<c:out value="${item.pass}"/>"></td>
						<td><input type="checkbox" name="deletes" value="<c:out value="${item.userId}"/>"></td>
					</tr>
				</c:forEach>
			</table>
		<br>
		</c:if>
		新規登録<br>
		<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>名前</th>
					<th>TEL</th>
					<th>PASS</th>
				</tr>
			</thead>
			<tr>
				<td>自動入力されます</td>
				<td><input type="text" name="newUserName" maxlength="12"></td>
				<td><input type="text" name="newTel" maxlength="12"></td>
				<td><input type="text" name="newPass" maxlength="12"></td>
			</tr>
		</table>
		<input type="submit" value="実行" >
	</form>
	<br>
	<c:if test="${(!empty insertCount && insertCount != 0) ||
					(!empty updateCount && updateCount != 0) ||
					(!empty deleteCount && deleteCount != 0)}">
		<table border="1">
			<thead>
				<tr>
					<th>登録件数</th>
					<th>更新件数</th>
					<th>削除件数</th>
				</tr>
			</thead>
			<tr>
				<td><c:out value="${insertCount}"/></td>
				<td><c:out value="${updateCount}"/></td>
				<td><c:out value="${deleteCount}"/></td>
			</tr>
		</table>
		<br>
	</c:if>
	<a href="${pageContext.request.contextPath}">トップへ戻る</a>
</body>
</html>