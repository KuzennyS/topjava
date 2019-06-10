<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd hh:mm"/>
            </td>
            <td>${mealTo.getDescription()}</td>
            <td>${mealTo.getCalories()}</td>
            <td>
                <form action = "updateMeal.jsp" method="post">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="hidden" name="name" value="${user.getName()}">
                    <input type="hidden" name="age" value="${user.getAge()}">
                    <input type="submit" value="Изменить">
                </form>
            </td>
            <td>
                <form action="deleteMeal.jsp" method="post">
                    <input type="hidden" name="id" value="${mealTo.getId()}">
                    <input type="submit" value="Удалить">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<p></p>
<%--<form action="addMeal.jsp">--%>
<%--    <input type="submit" value="Добавить прием пищи">--%>
<%--</form>--%>
<a href="addMeal.jsp">Добавить прием пищи</a>
</body>
</html>
