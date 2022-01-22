<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 22.01.2022
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to AdvertsWebProject</title>
</head>
<body>
<h1>Welcome to AdvertsWebProject!</h1>
<form action="Controller" method="POST">
    <input type="hidden" name="command" value="OPEN_LOGIN_PAGE">
    <table style="with: 50%">
    </table>
    <input type="submit" value="Login" />
</form>
<form action="Controller" method="POST">
    <input type="hidden" name="command" value="OPEN_SIGNUP_PAGE">
    <table style="with: 50%">
    </table>
    <input type="submit" value="Sign Up" />
</form>
</body>
</html>
