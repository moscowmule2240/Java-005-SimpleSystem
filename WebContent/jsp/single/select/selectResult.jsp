<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
<script type="text/javascript">
<!--
function func(x) {
	if(x == 1){
		document.form.action = "${pageContext.request.contextPath}/jsp/single/insert/insert.jsp";
	}else if(x == 2){
		document.form.action = "${pageContext.request.contextPath}/single/update/update";
	}else if(x == 3){
		document.form.action = "${pageContext.request.contextPath}/single/delete/delete";
	}
	document.form.submit();
}
// -->
</script>
</head>
<body>
	<c:if test="${!empty loginUser.userName}"><c:out value="${loginUser.userName}"/>さんが</c:if>
	検索を完了しました。<br><br>
	検索結果<br>
	<table border='1'>
		<thead>
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>TEL</th>
			</tr>
		</thead>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.userId}" /></td>
				<td><c:out value="${user.userName}" /></td>
				<td><c:out value="${user.tel}" /></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<b>実行したい処理を選んでください。</b><br>
	IDを入力し処理画面へ移動します。<br>
	<table border="1">
		<tr>
			<td>IDは自動入力です</td>
			<td>
				<form action="${pageContext.request.contextPath}/jsp/single/insert/insert.jsp" method="post">
					<input type="submit" name="submit" value="登録画面">
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<form name="formUpdate" action="${pageContext.request.contextPath}/single/update/update" method="post">
					<input type="text" name="userId">
				</form>
			</td>
			<td>
				<input type="button" onclick="document.formUpdate.submit()" value="更新画面">
			</td>
		</tr>
		<tr>
			<td>
				<form name=formDelete action="${pageContext.request.contextPath}/single/delete/delete" method="post">
					<input type="text" name="userId">
				</form>
			</td>
			<td>
				<input type="button" onclick="document.formDelete.submit()" value="削除画面">
			</td>
		</tr>
	</table>
	<br>
	IDを入力し処理画面へ移動します 。<br>
	※「登録」では入力したIDは無視されます  。<br>
	<form name="form" method="post">
		<table border="1">
			<tr>
				<td><input type="text" name="userId"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" onclick="func(1)" value="登録">
					<input type="submit" onclick="func(2)" value="更新">
					<input type="submit" onclick="func(3)" value="削除">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/single/menu.jsp">メニューへ戻る</a>
</body>
</html>