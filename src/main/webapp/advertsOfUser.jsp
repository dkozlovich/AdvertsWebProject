<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="lang"/>
<head>
    <title><fmt:message key="Adverts_of_user" bundle="${lang}"></fmt:message> ${userName}</title>
</head>
<body>
<h1> <fmt:message key="Adverts_of_user" bundle="${lang}"></fmt:message> ${userName}:
<table width="35%" border="1" cellpadding="7" cellspacing="0" style="margin-top: 10px">
    <tr>
        <th> <fmt:message key="Name" bundle="${lang}"></fmt:message></th>
        <th> <fmt:message key="Cost" bundle="${lang}"></fmt:message></th>
        <th> <fmt:message key="Created" bundle="${lang}"></fmt:message></th>
        <th> <fmt:message key="Modified" bundle="${lang}"></fmt:message></th>
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