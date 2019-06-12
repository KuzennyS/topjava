<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add new meal</title>
</head>
<body>
<form method="POST" action='meals' name="frmAddMeal">

    <input type="hidden" name="idMeal" value=<%= request.getParameter("mealId") %>/>
    <p></p>

    Time meal : <input type="text" name="timeMeal"
        <fmt:parseDate value="${meal.getDateTime()}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="parsedDateTime"/>
                       value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}" />"/> <br/>
    <p></p>

    Description : <input type="text" name="description"
                         value="<c:out value="${meal.description}" />"/> <br/>
    <p></p>

    Calories : <input type="text" name="calories"
                      value="<c:out value="${meal.calories}" />"/> <br/>
    <p></p>
    <input type="submit" value="Ok"/>
</form>
</body>
</html>
