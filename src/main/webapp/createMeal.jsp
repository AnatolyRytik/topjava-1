<jsp:useBean id="mealForCustomizing" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
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
<h2>${mealForCustomizing.id == 0 ? "Add meal" : "Update"}</h2>

<c:if test="${mealForCustomizing.id==0}">
<form name="mealForCustomizing" action="meals?action=add" method="post" accept-charset="utf-8">
    <input type="hidden" name="mealsId" value="0">
    <p>description</p>
    <input value="" type="text" name="description">
    <p>calories</p>
    <input value="" type="text" name="calories">
    <p>dateTime</p>
    <input value="Date and time" type="datetime-local" name="dateTime">
    <input type="submit" value="OK">
</form>
</c:if>

<c:if test="${mealForCustomizing.id!=0}">
<form name="mealForCustomizing" action="meals?mealsId=${mealForCustomizing.id}" method="post" accept-charset="utf-8">
    <p>Id</p>
    <input value="<c:out value="${mealForCustomizing.id}"/>" type="text" name="mealsId" disabled>
    <p>description</p>
    <input value="<c:out value="${mealForCustomizing.description}"/>" type="text" name="description">
    <p>calories</p>
    <input value="<c:out value="${mealForCustomizing.calories}"/>" type="text" name="calories">
    <p>dateTime</p>
    <p><c:out value="${mealForCustomizing.dateTime}"/></p>
    <input value="<c:out value="${mealForCustomizing.dateTime}"/>" type="datetime-local" name="dateTime">
    <input type="submit" value="OK">
</form>
</c:if>


