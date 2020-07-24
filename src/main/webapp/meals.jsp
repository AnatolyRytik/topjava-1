<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<div>
    <H1>Meals</H1>

    <table border="1">
        <thead>
        <tr>
            <th>Description</th>
            <th>Calories</th>
            <th>Date and time</th>
            <th colspan=2>Action</th>
        </tr>
        </thead>

        <tbody>
        <jsp:useBean id="meals" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
        <c:forEach items="${meals}" var="meal">
            <tr style="color: ${meal.excess ? 'red' : 'green'}">
                <td><c:out value="${meal.description}"/></td>
                <td><c:out value="${meal.calories}"/></td>
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/></td>
                <td><a href="meals?action=update&mealsId=<c:out value="${meal.id}"/>">Update</a></td>
                <td><a href="meals?action=delete&mealsId=<c:out value="${meal.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="meals?action=add">Add new meal</a>
</div>
</body>
</html>