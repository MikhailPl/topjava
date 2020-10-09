<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ru">

<head>
    <title>Edit <c:out value="${empty meal ? ' new ' : ''}" /> <meal></meal></title>
</head>
<body>

<h3><a href="index.html">Home</a></h3>

<hr>
<h2>
    Edit <c:out value="${empty meal ? ' new ' : ''}" /> meal
</h2>

<form action="meals" method="post">

    <p>ID: <input type="number" name="id" value="${meal.getId()}"></p>

    <p>Date and time: <input type="datetime-local" name="dateTime" value="${meal.dateTime}"></p>

    <p>Description: <input type="text" name="description" value="${meal.description}"></p>

    <p>Calories: <input type="number" name="calories" value="${meal.calories}"></p>

    <p>
    <input type="submit" value="OK"></input>
    <input type="reset" value="Cancel" onclick="history.back();"></input>
    </p>

</form>


</body>
</html>
