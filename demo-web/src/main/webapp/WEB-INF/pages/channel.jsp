<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Wude
  Date: 2017/7/7
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Channel</title>
    </head>
    <body>
        <h1>请选择Channel</h1>
        <div>
            <c:forEach var="channel" items="${channels}">
                <div style="width: 300px;height: 50px;padding-right: 20px;margin:5px auto;border: 2px solid;float: left;">
                    <a href="${basePath}/channel/select/${channel.channelId}">
                        <div>${channel.channelId}</div>
                        <div>${channel.name}</div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
