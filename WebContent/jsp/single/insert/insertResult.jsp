<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録結果</title>
</head>
<body>
	<c:if test="${!empty loginUser.userName}"><c:out value="${loginUser.userName}"/>さんが</c:if>
	<c:if test="${!empty insertUser.userName}"><c:out value="${insertUser.userName}"/>さんを</c:if>
	<c:if test="${!empty insertUser.userId}">ID:<c:out value="${insertUser.userId}"/>で</c:if>
	登録を完了しました。<br><br>
	<form action="${pageContext.request.contextPath}/jsp/single/select/select.jsp" method="post">
		<input type="hidden" value="<c:out value="${insertUser.userId}"/>" name="userId">
		<input type="submit" value="検索画面">
	</form>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/menu.jsp">メニューに戻る</a>
</body>
</html>