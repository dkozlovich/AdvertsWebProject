<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="lang"/>
<html>
<form id="form">
    <input type="search" id="query" name="searchKey" placeholder="<fmt:message key="Search" bundle="${lang}"></fmt:message>...">
    <input type="hidden" name="command" value="SEARCH_ADVERTS"/>
    <button><fmt:message key="Search" bundle="${lang}"></fmt:message></button>
</form>

<c:choose>
    <c:when test="${locale == 'ru'}">
        <form action="Controller" method="POST">
            <input type="hidden" name="command" value="CHANGE_LOCALE">
            <input type="hidden" name="locale" value="en">
            <input type="submit" value="RU"/>
        </form>
    </c:when>
    <c:when test="${locale == 'en'}">
        <form action="Controller" method="POST">
            <input type="hidden" name="command" value="CHANGE_LOCALE">
            <input type="hidden" name="locale" value="ru">
            <input type="submit" value="EN"/>
        </form>
    </c:when>
</c:choose>
</body>
</html>