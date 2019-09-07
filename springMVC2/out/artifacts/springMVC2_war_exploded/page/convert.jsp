<%--
  Created by IntelliJ IDEA.
  User: ipso
  Date: 2019/9/4
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>convert</title>
</head>
<body>
<div style="width: 700px; margin: auto;padding-top: 20px; padding-bottom: 40px">
    <h1>中文乱码问题测试</h1>
    <h3>post请求</h3>
    <form action="${pageContext.request.contextPath}/convert" method="post">
        年龄：<input type="text" name="age" ><br>
        日期：<input type="text" name="date"><br>
        姓名：<input type="text" name="name" ><br>
        <input type="submit" value="提交" >
    </form>

    <hr>
    <h3>get请求</h3>
    <form action="${pageContext.request.contextPath}/convert" method="get">
        年龄：<input type="text" name="age" ><br>
        日期：<input type="text" name="date"><br>
        姓名：<input type="text" name="name" ><br>
        <input type="submit" value="提交" >
    </form>
</div>
</body>
</html>
