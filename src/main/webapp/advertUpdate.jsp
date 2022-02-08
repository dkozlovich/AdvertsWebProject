<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="lang"/>
    <title><fmt:message key="Update_advert" bundle="${lang}"></fmt:message></title>
</head>
<body>
<form name = "UpdateAdvertForm" method="POST" action="Controller">
    <input type="hidden" name="command" value="UPDATE_ADVERT">
    <fmt:message key="Name" bundle="${lang}"></fmt:message>:
    <br/>
    <input type="text" name="name" value="${advert.name}" />
    <br/>
    <fmt:message key="Cost" bundle="${lang}"></fmt:message>:
    <br/>
    <input type="text" name="cost" value="${advert.cost}" />
    <style>
        textarea {
            width: 30%;
            height: 10%;
            resize: none;
        }
    </style>
    <br>
    <fmt:message key="Section" bundle="${lang}"></fmt:message>:
    <br/>
    <select name="sectionID" size="1">
        <c:forEach items="${sections}" var="s">
            <c:choose>
            <c:when test="${s.name.equals(sectionName)}">
                <option selected value=${s.id}>${s.name}</option>
            </c:when>
            <c:otherwise>
                <option value=${s.id}>${s.name}</option>
            </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <br>
    <textarea name="content">${advert.content}</textarea>
    <br>
    <input type="hidden" name="id" value=${advert.id}>
    <br>
    <input type="submit" value="<fmt:message key="Update" bundle="${lang}"></fmt:message>"/>
</form>
<button type="button" name="back" onclick="history.back()"><fmt:message key="Back" bundle="${lang}"></fmt:message></button>
</body>
</html>