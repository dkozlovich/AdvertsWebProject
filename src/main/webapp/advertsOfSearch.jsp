<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="lang"/>
<head>
    <title><fmt:message key="Search_result" bundle="${lang}"></fmt:message></title>
</head>
<body>

<h3><fmt:message key="Search_by_part_of_word_in_name_or_content" bundle="${lang}"></fmt:message>:</h3>
<form>
    <input type="hidden" name="command" value="SEARCH_ADVERTS">
    <c:choose>
        <c:when test="${searchParameters.get('key') != ''}">
            <input type="search" id="query" name="searchKey" value="${searchParameters.get('key')}">
        </c:when>
        <c:otherwise>
            <input type="search" id="query" name="searchKey" placeholder="<fmt:message key="Search" bundle="${lang}"></fmt:message>">
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${searchParameters.get('dateFrom') != ''}">
            <p><fmt:message key="Date_from" bundle="${lang}"></fmt:message>: <input type="date" name="dateFrom" value="${searchParameters.get('dateFrom')}"> </p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="Date_from" bundle="${lang}"></fmt:message>: <input type="date" name="dateFrom"> </p>
        </c:otherwise>
    </c:choose>

    <c:choose>
    <c:when test="${searchParameters.get('dateTo') != ''}">
    <p><fmt:message key="Date_until" bundle="${lang}"></fmt:message>: <input type="date" name="dateTo" value="${searchParameters.get('dateTo')}"> </p>
        </c:when>
        <c:otherwise>
    <p><fmt:message key="Date_until" bundle="${lang}"></fmt:message>: <input type="date" name="dateTo"> </p>
        </c:otherwise>
    </c:choose>
    <br>

    <fmt:message key="Search_in_section" bundle="${lang}"></fmt:message>:
    <select name="sectionID" size="1">
                <option value=""><fmt:message key="Any_section" bundle="${lang}"></fmt:message></option>
        <c:forEach items="${sections}" var="s">
            <c:choose>
                <c:when test="${s.id == searchParameters.get('sectionId')}">
                    <option selected value=${s.id}>${s.name}</option>
                </c:when>
                <c:otherwise>
                    <option value=${s.id}>${s.name}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <br/>
    <p> <input type="submit" value="<fmt:message key="Search" bundle="${lang}"></fmt:message>"></p>
</form>
<h1> <fmt:message key="Search_result" bundle="${lang}"></fmt:message> (${advertsOfSearch.size()} <fmt:message key="Found" bundle="${lang}"></fmt:message>):
    <table width="35%" border="1" cellpadding="7" cellspacing="0" style="margin-top: 10px">
        <tr>
            <th> <fmt:message key="Name" bundle="${lang}"></fmt:message></th>
            <th> <fmt:message key="Cost" bundle="${lang}"></fmt:message></th>
            <th> <fmt:message key="Created" bundle="${lang}"></fmt:message></th>
            <th> <fmt:message key="Modified" bundle="${lang}"></fmt:message></th>
            <c:forEach items="${advertsOfSearch}" var="a">
        </tr>
        <tr>
            <td> <a href="?command=OPEN_ADVERT&id=${a.id}" > ${a.name} </a>  </td>
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
    <br/>
    <form action="Controller" method="POST">
        <input type="hidden" name="command" value="OPEN_MAIN_PAGE">
        <table style="with: 50%">
        </table>
        <input type="submit" value="<fmt:message key="Back_to_sections" bundle="${lang}"></fmt:message>"/>
    </form>
</body>
</html>