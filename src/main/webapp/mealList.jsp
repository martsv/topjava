<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<div>
    <table border="1">
        <thead>
            <tr>
                <th>Id</th>
                <th>DateTime</th>
                <th>Description</th>
                <th>Calories</th>
                <th colspan="2">Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${mealList}" var="meal">
            <tr style=<c:out value="${meal.exceed? 'color:red': 'color:green'}"/>>
                <td>${meal.id}</td>
                <td>${f:formatDateTime(meal.dateTime)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=edit&id=${meal.id}">Edit</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p><a href="meals?action=add">Add Meal</a></p>
</div>
</body>
</html>
