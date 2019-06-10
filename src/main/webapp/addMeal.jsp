<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST">
<%--<form action = "meals.jsp" method="POST">--%>
    <label>Дата/Время:
        <input type="text" name="date"><br />
    </label>

    <label>Описание:
        <input type="text" name="description"><br />
    </label>

    <label>Калории:
        <input type="text" name="calories"><br />
    </label>
    <button type="submit">Добавить</button>
</form>
</body>
</html>
