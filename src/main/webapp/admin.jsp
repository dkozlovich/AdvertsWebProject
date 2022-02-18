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
            <td><a href="?command=OPEN_ADVERTS_OF_USER&userID=${u.id}&userName=${u.username}"> ${u.username}</a></td>
            <td>${u.password}</td>
            <td>
                <form action="Controller" method="POST">
                <input type="hidden" name="command" value="DELETE_USER">
                <input type="hidden" name="userID" value="${u.id}">
                <input type="submit" value="Delete" />
                </form>
            </td>
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
<c:forEach items="${sections}" var="s">
    <table border="1">
        <tr>
            <td>${s.id}</td>
            <td><a href="?command=OPEN_SECTION&sectionID=${s.id}" > ${s.name}</a></td>
            <td>
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="DELETE_SECTION">
                    <input type="hidden" name="id" value="${s.id}">
                    <table style="with: 50%">
                    </table>
                    <input type="submit" value="Delete" />
                </form>
            </td>
            <td>
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="UPDATE_SECTION">
                    <input type="hidden" name="id" value="${s.id}">
                    <input type="text" name="name" value="${s.name}">
                    <input type="submit" value="Update" />
                </form>
            </td>
        </tr>
    </table>
</c:forEach>

<a href="${pageContext.request.contextPath}/Controller?command=logout">Logout</a>
</body>
</html>
