<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setTimeZone value="Asia/Tokyo" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Request Recorder</title>
<style>
body {
color: #333
}
table, td {
border: 1px #333 solid;
border-spacing: 0px;
}
td {
padding:10px
}
</style>
</head>
<body>
	<h3>User Agent一覧</h3>
	<table>
	<tr style="background:#97a791">
		<td>User Agent</td>
		<td>Count</td>
		<td>Last Access</td>
		<td>First Access</td>
	</tr>
	<c:forEach var="requestRecord" items="${requestRecordList}">
		<tr>
			<td>
				<c:out value="${requestRecord.userAgent}" />
			</td>
			<td>
				<c:out value="${requestRecord.count }" />回
			</td>
			<td>
				<fmt:formatDate value="${requestRecord.updateAtAsDate}" pattern="yyyy/MM/dd HH:mm:ss" /><br>
			</td>
			<td>
				<fmt:formatDate value="${requestRecord.createAtAsDate}" pattern="yyyy/MM/dd HH:mm:ss" /><br>
			</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>