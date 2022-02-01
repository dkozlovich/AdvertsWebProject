<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

<body>
<c:choose>
    <c:when test="${locale == 'ru'}">
<form action="Controller" method="POST">
        ru
    </c:when>
    <c:otherwise>
        en
    </c:otherwise>
</c:choose>
</body>
</html>