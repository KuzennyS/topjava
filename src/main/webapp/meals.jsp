<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Список еды</title>
</head>
<body>

<style type="text/css">
    TABLE {
        border-collapse: collapse; /* Убираем двойные границы между ячейками */
        background: white; /* Цвет фона таблицы */
        border: 5px double #000; /* Рамка вокруг таблицы */
    }
    TD, TH {
        padding: 5px; /* Поля вокруг текста */
        border: 1px solid black; /* Рамка вокруг ячеек */
    }
</style>
<h3><a href="index.jsp">Home</a></h3>
<table>
    <tr align="center">
        <td>Дата/Время</td>
        <td>Описание</td>
        <td>Калории</td>
        <td>Редактирование</td>
        <td>Удалить</td>
    </tr>

<c:forEach var="mealTo" items="${mealsToList}">
        <tr align="center" style="color:${mealTo.isExcess() ? 'red' : 'green'}">
            <td>
                <fmt:parseDate value="${mealTo.getDateTime()}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="parsedDateTime"/>
                <fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd HH:mm"/>
            </td>
            <td>${mealTo.getDescriptionMt()}</td>
            <td>${mealTo.getCalories()}</td>
            <td><a href="meals?action=edit&mealId=<c:out value="${mealTo.getId()}"/>">Update</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${mealTo.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<p></p>
<p><a href="meals?action=insert">Add Meal</a></p>
</body>
</html>
