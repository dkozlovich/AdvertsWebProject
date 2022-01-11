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
        <c:forEach items="${sections}" var="s">
            <option value=${s.id}>${s.name}</option>
        </c:forEach>

</select>
    <br>
    <textarea name="content"></textarea>
    <br>
    <input type="hidden" name="userID" value=${currentUser.id}>
    <input type="submit" value="Create"/>
</form>
</body>
</html>
