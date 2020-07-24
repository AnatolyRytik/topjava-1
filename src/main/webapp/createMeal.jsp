<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Add Meal</title>
    <meta charset="utf-8">
</head>
<body>
<P>Add Meal </P>
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