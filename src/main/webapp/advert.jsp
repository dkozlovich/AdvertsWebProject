<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Advert</title>
</head>
<body>
    <h1>${advert.name}</h1>
    Section: ${sectionName}
    <br/>
    Author:
    ${userName}
    <a href="?command=OPEN_ADVERTS_OF_USER&userID=${advert.userId}&userName=${userName}" > (find all adverts of author)</a>
    <br/>
    Cost: ${advert.cost}
    <br/>
    Created: ${advert.created}
    <c:if test="${advert.created != advert.modified}">
    <br/>
    Modified: ${advert.modified}
    </c:if>
    <br/>

    <head>
        <meta charset="utf-8">
        <style>
            #advert {
                width: 40%;
                height: 10%;
                resize: none;
            }
            #message {
                width: 30%;
                height: 10%;
                resize: none;
            }
        </style>
    </head>
    <body>
    <form>
        <textarea id="advert" readonly>${advert.content}</textarea>
    </form>
    </body>
    <h2>Messages:</h2>
    <c:choose>
    <c:when test="${messages.size() == 0}">
        Here are no messages yet.
        <br>
    </c:when>
    </c:choose>

        <c:forEach items="${messages}" var="m">
        <table width="30%" border="1" cellpadding="7" cellspacing="0" bgcolor="#d3d3d3" style="margin-top: 10px">
        <c:choose>
        <c:when test="${m.userDTO.id == advert.userId}">

        <tr><td width="50%">${m.userDTO.username}<span style="color:red"> (Advert's author)</span></td><td>${m.created}</td></tr>
        </c:when>
        <c:otherwise>
        <tr><td width="50%">${m.userDTO.username}</td><td>${m.created}</td></tr>
        </c:otherwise>
        </c:choose>
        <tr><td colspan="2"> ${m.content}</td></tr>
        </table>
        </c:forEach>


    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="?command=OPEN_ADVERT&id=${advert.id}&page=${currentPage - 1}">Previous</a></td>
    </c:if>

    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <c:choose>
    <c:when test="${messages.size() != 0}">
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${totalPagesNumber}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="?command=OPEN_ADVERT&id=${advert.id}&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    </c:when>
    </c:choose>

    <%--For displaying Next link --%>
    <c:if test="${currentPage < totalPagesNumber}">
        <td><a href="?command=OPEN_ADVERT&id=${advert.id}&page=${currentPage + 1}">Next</a></td>
        <br>
    </c:if>
    <br>

    Add new message:
    <form action="Controller" method="POST">
    <input type="hidden" name="command" value="CREATE_MESSAGE">
    <textarea id="message" name="content"></textarea>
    <br>
    <input type="hidden" name="advertID" value=${advert.id}>
    <input type="hidden" name="userID" value=${currentUser.id}>
    <input type="hidden" name="totalPagesNumber" value=${totalPagesNumber}>
    <br>
    <input type="submit" value="Add"/>
    </form>
    <br>

    <c:choose>
        <c:when test="${advert.userId == currentUser.id}">
            <form action="advertUpdate.jsp">
                <input type="submit" value="Edit" />
            </form>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${advert.userId == currentUser.id}">
            <form action="Controller" method="POST">
                <input type="hidden" name="command" value="DELETE_ADVERT">
                <input type="hidden" name="id" value=${advert.id}>
                <input type="hidden" name="sectionID" value=${advert.sectionId}>
                <table style="with: 50%">
                </table>
                <input type="submit" value="DELETE" />
            </form>
        </c:when>
    </c:choose>
    <form action="Controller" method="POST">
        <input type="hidden" name="command" value="OPEN_SECTION">
        <input type="hidden" name="sectionID" value=${advert.sectionId}>
        <table style="with: 50%">
        </table>
        <input type="submit" value="Back to adverts" />
    </form>
</body>
</html>
