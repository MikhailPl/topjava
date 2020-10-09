<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html lang="ru">
<head>
    <title>Meal list</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<p><a href="meals?action=create">Add meal</a></p>

<table border=1>
    <thead>
    <tr>
        <th style="display:none;">id</th>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="me" items="${mealToList}">
        <tr style="color:${me.isExcess() ? 'red' : 'green'}">
            <td style="display:none;"><c:out value="${me.id}"/></td>
            <td><javatime:format value="${me.getDateTime()}" pattern="dd.MM.yyyy HH:mm:ss"/></td>
            <td><c:out value="${me.getDescription()}"/></td>
            <td><c:out value="${me.getCalories()}"/></td>
            <td><a href="meals?action=Edit&id=<c:out value="${me.id}" />">Edit</a></td>
            <td><a href="meals?action=Delete&id=<c:out value="${me.id}" />">Delete</a></td>
        </tr>
    </c:forEach>

    </tbody>

</table>


</body>
</html>