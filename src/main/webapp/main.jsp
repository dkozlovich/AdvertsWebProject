<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="header.jsp"></jsp:include>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="lang"/>

    <table width="20%" border="1" cellpadding="7" cellspacing="0">
        <tr>
            <th><fmt:message key="Section" bundle="${lang}"></fmt:message></th><th><fmt:message key="Adverts_quantity" bundle="${lang}"></fmt:message></th>
        </tr>
    <c:forEach items="${sections}" var="s">
        <tr>
            <td> <a href="?command=OPEN_SECTION&sectionID=${s.id}" > ${s.name}</a> </td>
            <td width="10%">${s.totalAdvertsOfSectionNumber}</td>
        </tr>
    </c:forEach>
    </table>

<br/>
<form action="Controller" method="POST">
    <input type="hidden" name="command" value="OPEN_ADVERT_CREATE_PAGE">
    <input type="hidden" name="sectionID" value="">
    <input type="submit" value="<fmt:message key="Create_new_advert" bundle="${lang}"></fmt:message>"/>
</form>
<form action="Controller" method="POST">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="<fmt:message key="Logout" bundle="${lang}"></fmt:message>"/>
</form>
</body>
</html>
