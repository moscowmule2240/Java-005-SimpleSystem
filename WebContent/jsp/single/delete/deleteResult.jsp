<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>削除結果</title>
</head>
<body>
	<c:if test="${!empty loginUser.userName}"><c:out value="${loginUser.userName}"/>さんが</c:if>
	<c:if test="${!empty user.userName}"><c:out value="${user.userName}"/>さんの</c:if>
	削除を完了しました。<br><br>
	<form action="${pageContext.request.contextPath}/jsp/single/select/select.jsp" method="post">
		<input type="hidden" value="<c:out value="${user.userId}"/>" name="userId">
		<input type="submit" value="検索画面">
	</form>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/menu.jsp">メニューに戻る</a>
</body>
</html>