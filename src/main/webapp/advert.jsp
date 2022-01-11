<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Advert</title>
</head>
<body>
    <h1>${advert.name}</h1>
    <br/>
    Section: ${sectionName}
    <br/>
    Author:
    <a href="?command=OPEN_ADVERTS_OF_USER&userID=${advert.userId}&userName=${userName}" >${userName} </a>
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
            textarea {
                width: 60%;
                height: 50%;
                resize: none;
            }
        </style>
    </head>
    <body>
    <form>
        <textarea readonly> ${advert.content}</textarea>
    </form>
    </body>
    <c:choose>
        <c:when test="${advert.userId == currentUser.id}">
            <form action="Controller" method="POST">
                <input type="hidden" name="command" value="OPEN_SECTION">
                <input type="hidden" name="sectionID" value=${advert.sectionId}>
                <table style="with: 50%">
                </table>
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
