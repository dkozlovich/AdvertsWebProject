<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="lang"/>
<head>
    <title><fmt:message key="Section" bundle="${lang}"></fmt:message> ${sectionName}</title>
</head>
<body>
<h1> ${sectionName} </h1>
Sort:
<form action="Controller" method="POST">
    <select name="sortType" size="1">
        <c:choose>
            <c:when test="${sortType.equals('MODIFIED DESC')}">
                <option selected value="MODIFIED DESC">New first</option>
                <option value="MODIFIED ASC">Old first</option>
                <option value="COST ASC">Low price first</option>
                <option value="COST DESC">High price first</option>
            </c:when>
            <c:when test="${sortType.equals('MODIFIED ASC')}">
                <option value="MODIFIED DESC">New first</option>
                <option selected value="MODIFIED ASC">Old first</option>
                <option value="COST ASC">Low price first</option>
                <option value="COST DESC">High price first</option>
            </c:when>
            <c:when test="${sortType.equals('COST ASC')}">
                <option value="MODIFIED DESC">New first</option>
                <option value="MODIFIED ASC">Old first</option>
                <option selected value="COST ASC">Low price first</option>
                <option value="COST DESC">High price first</option>
            </c:when>
            <c:when test="${sortType.equals('COST DESC')}">
                <option value="MODIFIED DESC">New first</option>
                <option value="MODIFIED ASC">Old first</option>
                <option value="COST ASC">Low price first</option>
                <option selected value="COST DESC">High price first</option>
            </c:when>
        </c:choose>
    </select>
    <input type="hidden" name="command" value="CHANGE_SORT">
    <input type="hidden" name="sectionID" value=${sectionID}>
    <table style="with: 50%">
    </table>
    <input type="submit" value="Apply" />
</form>

<table width="35%" border="1" cellpadding="7" cellspacing="0" style="margin-top: 10px">
    <tr>
        <th><fmt:message key="Name" bundle="${lang}"></fmt:message></th></tf><th><fmt:message key="Cost" bundle="${lang}"></fmt:message></th>
        <th><fmt:message key="Created" bundle="${lang}"></fmt:message></th><th><fmt:message key="Modified" bundle="${lang}"></fmt:message></th>
        <c:forEach items="${advertsOfSection}" var="a">
    </tr>
        <tr>
            <c:choose>
                <c:when test="${a.userId == currentUser.id}">
                    <td> <a href="?command=OPEN_ADVERT&id=${a.id}"><span style="color:red">${a.name}</span></a>  </td>
                </c:when>
                <c:otherwise>
                    <td> <a href="?command=OPEN_ADVERT&id=${a.id}" >${a.name}</a>  </td>
                </c:otherwise>
            </c:choose>
            <td> ${a.cost}</td>
            <td> <fmt:formatDate value="${a.created}" pattern="yyyy-MM-dd HH:mm" /></td>
            <c:choose>
                <c:when test="${a.created != a.modified}">
                    <td><fmt:formatDate value="${a.modified}" pattern="yyyy-MM-dd HH:mm" /></td>
                </c:when>
                <c:otherwise>
                    <td></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="?command=OPEN_SECTION&sectionID=${sectionID}&page=${currentPage - 1}"><fmt:message key="Previous_page" bundle="${lang}"></fmt:message></a></td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<c:choose>
    <c:when test="${advertsOfSection.size() != 0}">
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:forEach begin="1" end="${totalPagesNumber}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="?command=OPEN_SECTION&sectionID=${sectionID}&page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>
    </c:when>
</c:choose>

<%--For displaying Next link --%>
<c:if test="${currentPage < totalPagesNumber}">
    <td><a href="?command=OPEN_SECTION&sectionID=${sectionID}&page=${currentPage + 1}"><fmt:message key="Next_page" bundle="${lang}"></fmt:message></a></td>
    <br>
</c:if>
<br>

<br/>
<form action="Controller" method="POST">
    <input type="hidden" name="command" value="OPEN_ADVERT_CREATE_PAGE">
    <input type="hidden" name="sectionID" value=${sectionID}>
    <input type="submit" value="<fmt:message key="Create_new_advert" bundle="${lang}"></fmt:message>"/>
</form>
<form action="Controller" method="POST">
    <input type="hidden" name="command" value="OPEN_MAIN_PAGE">
    <input type="hidden" name="login" value=${user}>
    <table style="with: 50%">
    </table>
    <input type="submit" value="<fmt:message key="Back_to_sections" bundle="${lang}"></fmt:message>"/>
</form>
</body>
</html>
