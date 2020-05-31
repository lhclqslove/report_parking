<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<input type="text" id="ss">
<from action="/ReportupServlet" method="post" >
    username:<input type="text" name="username" /><br>

    password:<input type="password" name="password" /> <br>

    <input type="submit" id="dl"  value="登录">
</from>
</body>
</html>

