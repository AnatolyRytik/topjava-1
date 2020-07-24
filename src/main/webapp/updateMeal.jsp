<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Update Meal</title>
    <meta charset="utf-8">
</head>
<body>
<P>Update Meal </P>
<form name="meal" action="meals?mealsId=${mealToEdit.id}" method="post" accept-charset="utf-8">
    <p>Id</p>
    <input value="<c:out value="${mealToEdit.id}"/>" type="text" name="mealsId" disabled>
    <p>description</p>
    <input value="<c:out value="${mealToEdit.description}"/>" type="text" name="description">
    <p>calories</p>
    <input value="<c:out value="${mealToEdit.calories}"/>" type="text" name="calories">
    <p>dateTime</p>
    <p><c:out value="${mealToEdit.dateTime}"/></p>
    <input value="<c:out value="${mealToEdit.dateTime}"/>" type="datetime-local" name="dateTime">
    <input type="submit" value="OK">
</form>