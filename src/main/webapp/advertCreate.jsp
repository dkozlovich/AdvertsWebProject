<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create new advert</title>
</head>
<body>
<form name = "CreateAdvertForm" method="POST" action="Controller">
    <input type="hidden" name="command" value="CREATE_ADVERT">
    Name:<br/>
    <input type="text" name="name" value=""/>
    <br/>
    Cost:
    <br/>
    <input type="text" name="cost" value=""/>
    <style>
        textarea {
            width: 60%;
            height: 50%;
            resize: none;
        }
    </style>
    <br>
    Section:
    <br/>
    <select name="sectionID" size="1">
        <option value="" selected disabled hidden>Choose section</option>
        <c:forEach items="${sections}" var="s">
            <c:choose>
                <c:when test="${s.name.equals(sectionName)}">
                    <option selected value=${s.id}>${s.name}</option>
                </c:when>
                <c:otherwise>
                    <option value=${s.id}>${s.name}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>

</select>
    <br>
    <textarea name="content"></textarea>
    <br>
    <input type="hidden" name="userID" value=${currentUser.id}>
    <br>
    <input type="submit" value="Create"/>
</form>
<button type="button" name="back" onclick="history.back()">Back</button>
</body>
</html>
