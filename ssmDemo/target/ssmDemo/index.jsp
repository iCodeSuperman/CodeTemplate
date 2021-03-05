<%--
  Created by IntelliJ IDEA.
  User: icodeboy
  Date: 2021/3/5
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试首页</title>
</head>
<body>
    <h3>测试查询所有</h3>
    <a href="account/findAll">测试查询所有用户</a>
    <br/>
    <h3>测试保存用户</h3>
    <form action="account/saveAccount" method="post">
        姓名：<input type="text" name="name" /><br/>
        金额：<input type="text" name="money" /><br/>
        <input type="submit" value="保存"/><br/>
    </form>
</body>
</html>
