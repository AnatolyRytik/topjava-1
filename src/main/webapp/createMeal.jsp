<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>${meal.id == 0 ? 'Add meal' : 'Edit meal'}</title>
    <meta charset="utf-8">
</head>
<body>
<P>Add Meal </P>
    <c:if test="${meal.id==0}">
        <form name="meal" action="meals?action=add" method="post" accept-charset="utf-8">
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

    <c:if test="${meal.id!=0}">
    <form name="meal" action="meals?mealsId=${meal.id}" method="post" accept-charset="utf-8">
        <p>Id</p>
        <input value="<c:out value="${meal.id}"/>" type="text" name="mealsId" disabled>
        <p>description</p>
        <input value="<c:out value="${meal.description}"/>" type="text" name="description">
        <p>calories</p>
        <input value="<c:out value="${meal.calories}"/>" type="text" name="calories">
        <p>dateTime</p>
        <p><c:out value="${meal.dateTime}"/></p>
        <input value="<c:out value="${meal.dateTime}"/>" type="datetime-local" name="dateTime">
        <input type="submit" value="OK">
    </form>
    </c:if>

