<%@page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>web-auth</title>
</head>
<body>
<form name="form" method="post" action="/auth/authc">
    <label for="username">用户姓名:</label>
    <input name="username" id="username" />
    <label for="password">用户密码:</label>
    <input name="password" id="password" />
    <button type="submit">提交</button>
</form>
</body>
</html>