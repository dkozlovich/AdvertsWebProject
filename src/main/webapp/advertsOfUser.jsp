<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Adverts</title>
</head>
<body>
<h1> Adverts of ${userName}:
<table width="35%" border="1" cellpadding="7" cellspacing="0">
    <tr>
        <th>Name</th></tf><th>Cost</th><th>Created</th><th>Modified</th>
        <c:forEach items="${advertsOfUser}" var="a">
    </tr>
    <tr>
        <td> <a href="?command=OPEN_ADVERT&id=${a.id}" > ${a.name} </a>  </td>
        <td> ${a.cost}</td>
        <td> ${a.created}</td>
        <c:choose>
            <c:when test="${a.created != a.modified}">
                <td>${a.modified}</td>
            </c:when>
            <c:otherwise>
                <td></td>
            </c:otherwise>
        </c:choose>
    </tr>
    </c:forEach>
</table>
<br/>
    <button type="button" name="back" onclick="history.back()">Back</button>
</body>
</html>