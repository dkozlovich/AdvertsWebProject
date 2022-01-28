<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

    <table width="20%" border="1" cellpadding="7" cellspacing="0">
        <tr>
            <th>Section</th><th>Adverts quantity</th>
        </tr>
    <c:forEach items="${sections}" var="s">
        <tr>
            <td> <a href="?command=OPEN_SECTION&sectionID=${s.id}" > ${s.name}</a> </td>
            <td width="10%">${adverts.getTotalAdvertsOfSectionNumber(s.id)}</td>
        </tr>
    </c:forEach>
    </table>

    <br/>
    <form action="Controller" method="POST">
        <input type="hidden" name="command" value="OPEN_ADVERT_CREATE_PAGE">
        <input type="hidden" name="sectionID" value="">

        <table style="with: 50%">
        </table>
        <input type="submit" value="Create new advert" />
    </form>
    <form action="Controller" method="POST">
        <input type="hidden" name="command" value="logout">
        <table style="with: 50%">
        </table>
        <input type="submit" value="Logout" />
    </form>
</body>
</html>
