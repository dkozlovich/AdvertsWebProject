<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Update advert</title>
</head>
<body>
<form name = "UpdateAdvertForm" method="POST" action="Controller">
    <input type="hidden" name="command" value="UPDATE_ADVERT">
    Name:<br/>
    <input type="text" name="name" value=${advert.name} />
    <br/>
    Cost:
    <br/>
    <input type="text" name="cost" value=${advert.cost} />
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
<%--    <select name="sectionID" size="1">--%>
<%--        <c:forEach items="${sections}" var="s">--%>
<%--            <option value=${s.id}>${s.name}</option>--%>
<%--        </c:forEach>--%>

<%--    </select>--%>
    <br>
    <textarea name="content"> value=${advert.content} </textarea>
    <br>
    <input type="hidden" name="id" value=${advert.id}>
    <input type="hidden" name="sectionID" value=${advert.sectionId}>
    <input type="submit" value="Update"/>
</form>
</body>
</html>