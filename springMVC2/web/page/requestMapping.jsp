<%--
  Created by IntelliJ IDEA.
  User: 19753
  Date: 2019/9/5
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RequestMapping</title>
</head>
<body>
<div style="width: 700px; margin: auto;padding-top: 20px; padding-bottom: 40px">
    <h1>RequestMapping</h1>

    <h3>value属性</h3>
    <a href="${pageContext.request.contextPath}/testrequestmapping1?user=request1" target="_blank">请求方式1</a>
    <a href="${pageContext.request.contextPath}/testrequestmapping2?user=request2" target="_blank">请求方式2</a>

    <hr>
    <h3>method属性</h3>
    <form action="${pageContext.request.contextPath}/testmethod">
        <input type="test" name="user"><br>
        <input type="submit" value="提交">
    </form>

    <hr>
    <h3>params属性</h3>
    <a href="${pageContext.request.contextPath}/testparams?name=ipso&age=20&user=paramsipso" target="_blank">params</a>

    <hr>
    <h3>headers属性</h3>
    <a href="${pageContext.request.contextPath}/testheaders?user=headers" target="_blank">headers</a>

    <hr>
    <h3>ant风格地址</h3>
    <a href="${pageContext.request.contextPath}/testant" target="_blank">ant</a>
</div>
</body>
</html>
