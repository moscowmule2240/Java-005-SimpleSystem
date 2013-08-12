<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>メニュー</title>
</head>
<body>
	<c:if test="${!empty loginUser.userName}"><c:out value="${loginUser.userName}"/>さん。</c:if>こんにちは。<br><br>
	<a href="${pageContext.request.contextPath}/jsp/single/select/select.jsp">検索</a>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/insert/insert.jsp">登録</a>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/update/update.jsp">変更</a>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/delete/delete.jsp">削除</a>
	<br><br>
	<a href="${pageContext.request.contextPath}">トップへ戻る</a>
</body>
</html>