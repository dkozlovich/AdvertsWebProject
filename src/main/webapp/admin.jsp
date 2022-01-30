<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<caption>Users</caption>
<c:forEach items="${users}" var="u">
    <table border="1">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.password}</td>
            <td><input type="button" value="Delete"></td>
        </tr>
    </table>
</c:forEach>
<hr>
<form name = "Create section" method="POST" action="Controller">
    <input type="hidden" name="command" value="CREATE_SECTION">
    Section name:<br/>
    <input type="text" name="name" value=""/>
    <br/>
    <input type="submit" value="Create section"/>
</form>
<hr>
<caption>Sections</caption>
<c:forEach items="${sections}" var="u">
    <table border="1">
        <tr>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="DELETE_SECTION">
                    <input type="hidden" name="id" value="${u.id}">
                    <table style="with: 50%">
                    </table>
                    <input type="submit" value="Delete" />
                </form>
            </td>
            <td>
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="UPDATE_SECTION">
                    <input type="hidden" name="id" value="${u.id}">
                    <input type="text" name="name" value="${u.name}">
                    <input type="submit" value="Update" />
                </form>
            </td>
        </tr>
    </table>
</c:forEach>

<a href="${pageContext.request.contextPath}/Controller?command=logout">Logout</a>
</body>
</html>
