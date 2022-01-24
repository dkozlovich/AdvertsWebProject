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
            #advert {
                width: 60%;
                height: 25%;
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

    <table >
        <tr>
            <th>Author</th></tf><th>Created</th><th>Content</th>
            <c:forEach items="${messages}" var="m">
        </tr>
        <tr>
            <td> ${m.author}</td>
            <td> ${m.created}</td>
            <td> ${m.content}</td>
        </tr>
        </c:forEach>
    </table>
    <br>
    Add new message:
    <form action="Controller" method="POST">
    <input type="hidden" name="command" value="CREATE_MESSAGE">
    <textarea id="message" name="content"></textarea>
    <br>
    <input type="hidden" name="advertID" value=${advert.id}>
    <input type="hidden" name="author" value=${currentUser.username}>
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
