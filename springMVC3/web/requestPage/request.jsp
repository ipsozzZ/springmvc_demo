<%--
  Created by IntelliJ IDEA.
  User: 19753
  Date: 2019/9/6
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request</title>
</head>
<body>
<div style="width: 700px; margin: auto; padding-top: 20px; padding-bottom: 40px">
    <h1>请求测试页</h1>

    <h3>测试ModelAndView</h3>
    <a href="${pageContext.request.contextPath}/testModelAndView?data=ModelAndView">testModelAndView</a>

    <hr>
    <h3>测试Model</h3>
    <a href="${pageContext.request.contextPath}/testModel?data=Model">testModel</a>

    <hr>
    <h3>测试@SessionAttributes</h3>
    <a href="${pageContext.request.contextPath}/testSession">testSessionAttributes</a>

    <hr>
    <h3>测试@SessionAttribute</h3>
    <a href="${pageContext.request.contextPath}/testSession2">testSessionAttribute</a>

    <hr>
    <h3>测试@ModelAttribute</h3>
    <form action="${pageContext.request.contextPath}/testModelAttribute">
        商品名：   <input type="text" name="name"> <br>
        商品价格： <input type="text" name="price"> <br>
        <input type="submit" value="提交">
    </form>
</div>
</body>
</html>
