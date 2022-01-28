<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Adverts</title>
</head>
<body>
<h1> ${sectionName} </h1>
<table width="35%" border="1" cellpadding="7" cellspacing="0">
    <tr>
        <th>Name</th></tf><th>Cost</th><th>Created</th><th>Modified</th>
        <c:forEach items="${advertsOfSection}" var="a">
    </tr>
        <tr>
            <c:choose>
                <c:when test="${a.userId == currentUser.id}">
                    <td> <a href="?command=OPEN_ADVERT&id=${a.id}"><span style="color:red">${a.name}</span></a>  </td>
                </c:when>
                <c:otherwise>
                    <td> <a href="?command=OPEN_ADVERT&id=${a.id}" > ${a.name} </a>  </td>
                </c:otherwise>
            </c:choose>
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

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="?command=OPEN_SECTION&sectionID=${sectionID}&page=${currentPage - 1}">Previous</a></td>
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
    <td><a href="?command=OPEN_SECTION&sectionID=${sectionID}&page=${currentPage + 1}">Next</a></td>
    <br>
</c:if>
<br>

<br/>
<form action="Controller" method="POST">
    <input type="hidden" name="command" value="OPEN_ADVERT_CREATE_PAGE">
    <input type="hidden" name="sectionID" value=${sectionID}>
    <table style="with: 50%">
    </table>
    <input type="submit" value="Create new advert" />
</form>
<form action="Controller" method="POST">
    <input type="hidden" name="command" value="OPEN_MAIN_PAGE">
    <input type="hidden" name="login" value=${user}>
    <table style="with: 50%">
    </table>
    <input type="submit" value="Back to sections" />
</form>
</body>
</html>
