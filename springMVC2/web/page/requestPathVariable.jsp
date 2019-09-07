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
    <h1>PathVariable</h1>

    <h3>RestFul风格请求</h3>
    <a href="${pageContext.request.contextPath}/rest/ipso" target="_blank">rest请求</a>

    <hr>
    <h3>form表单发送PUT、DELETE请求</h3>
    <form action="${pageContext.request.contextPath}/testmethod/ipso" method="post">
        <input type="hidden" name="_method" value="put"> <%-- value值：get、put、post、delete等 --%>
        <input type="submit" value="提交">
    </form>

    <hr>
    <h3>@RequestHeader获取头信息</h3>
    <a href="${pageContext.request.contextPath}/testHeader" target="_blank">测试Header</a>

    <hr>
    <h3>@RequestHeader获取头信息中的cookie</h3>
    <a href="${pageContext.request.contextPath}/testCookie" target="_blank">测试Cookie</a>

</div>
</body>
</html>
