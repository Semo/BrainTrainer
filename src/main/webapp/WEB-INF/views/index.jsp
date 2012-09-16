<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="utf8"%>
<html>
<head>
	<title>Übersicht der vorhandenen Personen</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/smoothness/jquery-ui-1.8.17.custom.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">  
</head>
<body>
	<h1>Personen in der Datenbank</h1>
	<p>${message}</p>

	<div id="mainContainer">
	
		<div id="datatableContainer">
			<table id="datatable">
				<thead>
					<tr>
						<td>Name der Stadt</td>
						<td>Länderkürzel</td>
						<td>Bezirk</td>
						<td>Bevölkerung</td>
						<td>Aktionen</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cities}" var="currentcity">
						<tr>
							<td>${currentcity.name}</td>
							<td>${currentcity.country}</td>
							<td>${currentcity.district}</td>
							<td>${currentcity.population}</td>
							<td><a href="/cities/city/delete/${currentcity.id}" class="deleteLink">Löschen</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<fieldset>
			<legend>Neue Person hinzufügen</legend>
			<form:form modelAttribute="city" method="POST" action='/cities/city/add'>
				<label for="name">Name:</label>
				<form:input id="cityname" path="name" value="" />
				<label for="country">Länderkürzel:</label>
				<form:input id="country" path="country" value="${country}" />
				<br />
				<label for="district">Bezirk:</label>
				<form:input id="district" path="district" value="${district}" />
				<label for="population">Bevölkerung:</label>
				<form:input id="population" path="population" value="${population}" />
				<br />
				<input id="submitNewCity" type="submit" value="Eintragen" />
			</form:form>
		</fieldset>
	</div>
	
	<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-1.7.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-ui-1.8.17.custom.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-datatables-1.9.0.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/lib/log4javascript.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/script.js" />"></script>
	
</body>
</html>