<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="lang"/>
<head>
    <title><fmt:message key="Advert" bundle="${lang}"></fmt:message> ${advert.name}</title>
</head>
<body>
    <h1>${advert.name}</h1>
    <fmt:message key="Section" bundle="${lang}"></fmt:message>: ${sectionName}
    <br/>
    <fmt:message key="Author" bundle="${lang}"></fmt:message>: ${userName}
    <a href="?command=OPEN_ADVERTS_OF_USER&userID=${advert.userId}&userName=${userName}">(<fmt:message key="Find_all_adverts_of_author" bundle="${lang}"></fmt:message>)</a>
    <br/>
    <fmt:message key="Cost" bundle="${lang}"></fmt:message>: ${advert.cost}
    <br/>
    <fmt:message key="Created" bundle="${lang}"></fmt:message>: <fmt:formatDate value="${advert.created}" pattern="yyyy-MM-dd HH:mm" />
    <c:if test="${advert.created != advert.modified}">
    <br/>
        <fmt:message key="Modified" bundle="${lang}"></fmt:message>: <fmt:formatDate value="${advert.modified}" pattern="yyyy-MM-dd HH:mm" />
    </c:if>
    <br/>

    <head>
        <meta charset="utf-8">
        <style>
            #advert {
                width: 30%;
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
    <c:choose>
        <c:when test="${images.size() > 0}">
            <head>
                <meta charset="utf-8">
                <title>overflow-x</title>
                <style>
                    .layer {
                        position: absolute;
                        left: 33%;
                        top: 3%;
                        overflow-x: auto;
                        height: 30%;
                        width: 65%;
                        padding: 5px;
                        border: solid 1px black;
                        white-space: nowrap;
                    }
                    .image {
                        display: inline-block;
                        margin-right: 10px;
                    }
                    .addImageButton {
                        position: absolute;
                        left: 33%;
                        top: 36%;
                    }
                </style>
            </head>
            <body>

            <div class="layer">
                <c:forEach items="${images}" var="i">
                    <div class="image">
                        <c:choose>
                            <c:when test="${advert.userId == currentUser.id}">
                                <img src="data:image/jpg;base64,${i}" height="86%" />
                                <form action="Controller" method="POST">
                                    <input type="hidden" name="command" value="DELETE_IMAGE">
                                    <input type="hidden" name="imageID" value="1">
                                    <input type="submit" value="Delete image"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <img src="data:image/jpg;base64,${i}" height="98%" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
            </div>
            <div class="addImageButton">
            <c:choose>
                <c:when test="${advert.userId == currentUser.id}">
                    <form action="Controller" enctype="multipart/form-data" method="POST">
                        <input type="hidden" name="command" value="SAVE_IMAGE">
                        <input type="hidden" name="advertId" value=${advert.id}>
                        <input type="file" name="image" value="Add image" />
                        <input type="submit" value="Send (Max size is 6 Mb)"/>
                    </form>
                </c:when>
            </c:choose>
            </div>
            </body>
            <br>
        </c:when>
    </c:choose>



    <h2><fmt:message key="Messages" bundle="${lang}"></fmt:message>:</h2>
    <c:choose>
    <c:when test="${messages.size() == 0}">
        <fmt:message key="Here_are_no_messages_yet" bundle="${lang}"></fmt:message>
        <br>
    </c:when>
    </c:choose>

        <c:forEach items="${messages}" var="m">
        <table width="30%" border="1" cellpadding="7" cellspacing="0" bgcolor="#d3d3d3" style="margin-top: 10px">
        <c:choose>
        <c:when test="${m.userDTO.id == advert.userId}">

        <tr><td width="50%">${m.userDTO.username}<span style="color:red"> (<fmt:message key="Advert's_author" bundle="${lang}"></fmt:message>)</span></td><td><fmt:formatDate value="${m.created}" pattern="yyyy-MM-dd HH:mm" /></td></tr>
        </c:when>
        <c:otherwise>
        <tr><td width="50%">${m.userDTO.username}</td><td><fmt:formatDate value="${m.created}" pattern="yyyy-MM-dd HH:mm" /></td></tr>
        </c:otherwise>
        </c:choose>
        <tr><td colspan="2"> ${m.content}</td></tr>
        </table>
        </c:forEach>


    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="?command=OPEN_ADVERT&id=${advert.id}&page=${currentPage - 1}"><fmt:message key="Previous_page" bundle="${lang}"></fmt:message></a></td>
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
        <td><a href="?command=OPEN_ADVERT&id=${advert.id}&page=${currentPage + 1}"><fmt:message key="Next_page" bundle="${lang}"></fmt:message></a></td>
        <br>
    </c:if>
    <br>

    <fmt:message key="Add_new_message" bundle="${lang}"></fmt:message>:
    <form action="Controller" method="POST">
    <input type="hidden" name="command" value="CREATE_MESSAGE">
    <textarea id="message" name="content"></textarea>
    <br>
    <input type="hidden" name="advertID" value=${advert.id}>
    <input type="hidden" name="userID" value=${currentUser.id}>
    <input type="hidden" name="totalPagesNumber" value=${totalPagesNumber}>
    <br>
    <input type="submit" value="<fmt:message key="Add" bundle="${lang}"></fmt:message>"/>
    </form>
    <br>

    <c:choose>
        <c:when test="${advert.userId == currentUser.id}">
            <form action="Controller" method="POST">
                <input type="hidden" name="command" value="OPEN_ADVERT_UPDATE_PAGE">
                <input type="hidden" name="id" value=${advert.id}>
                <input type="submit" value="<fmt:message key="Edit" bundle="${lang}"></fmt:message>" />
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
                <input type="submit" value="<fmt:message key="DELETE" bundle="${lang}"></fmt:message>" />
            </form>
        </c:when>
    </c:choose>
    <form action="Controller" method="POST">
        <input type="hidden" name="command" value="OPEN_SECTION">
        <input type="hidden" name="sectionID" value=${advert.sectionId}>
        <table style="with: 50%">
        </table>
        <input type="submit" value="<fmt:message key="Back_to_adverts" bundle="${lang}"></fmt:message>" />
    </form>
</body>
</html>
