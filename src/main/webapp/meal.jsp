<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h2>Add/Edit Meal</h2>
<form method="POST" action="meals" name="formAddUser">
    <c:if test="${!empty meal.id}">
        Meal ID: <input type="text" readonly="readonly" name="id"
                        value="${meal.id}" /> <br/>
    </c:if>
    Description: <input type="text" name="description" value="${meal.description}" /> <br/>
    Calories: <input type="text" name="calories" value="${meal.calories}" /> <br/>
    <input type="text" name="dateTime" value="${meal.dateTime}" hidden="hidden" />
    <input type="submit" value="Submit" />
</form>
</body>
</html>
