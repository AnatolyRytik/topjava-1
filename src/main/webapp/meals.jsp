<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h3><a href="index.html">Home</a></h3>
    <h1>Meals</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-blue-grey w3-opacity w3-left-align">
            <h1>MealsList</h1>
        </div>
        <%
            List<MealTo> mealToList = (List<MealTo>) request.getAttribute("meals");
            if (mealToList != null && !mealToList.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                for (MealTo mealTo : mealToList) {
                    if (!mealTo.isExcess()) {
                        out.println("<li class=\"w3-green w3-left-align\">" + mealTo.getStringValue() + "</li>");
                    } else out.println("<li class=\"w3-red w3-left-align\">" + mealTo.getStringValue() + "</li>");

                }
                out.println("</ul>");
            }
        %>
    </div>
</div>

</body>
</html>


<%--
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<ul>
    <%
        List<MealTo> mealToList = (List<MealTo>) request.getAttribute("meals");
        if (mealToList != null && !mealToList.isEmpty()) {
            for (MealTo mealTo : mealToList) {
                out.print("<li>" + mealTo.getStringValue() +"</li>");
            }
        }
    %>
</ul>
</body>
</html>--%>
