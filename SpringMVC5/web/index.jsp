<%--
  Created by IntelliJ IDEA.
  User: 19753
  Date: 2019/9/9
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>INDEX</title>
  </head>
  <body>
  <div style="width: 700px; margin: auto; padding-top: 20px; padding-bottom: 40px">
    <a href="${pageContext.request.contextPath}/download/my.jpg">下载my.jpg</a><br>
    <a href="${pageContext.request.contextPath}/download/aim.txt">下载aim.txt</a>

    <hr>
    <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
      <input type="file" name="file"><br>

      <input type="submit" value="上传图片">
    </form>

    <hr>
    <a href="${pageContext.request.contextPath}/exception" target="_blank" >测试异常处理</a>

    <hr>
    <hr>
    <a href="${pageContext.request.contextPath}/local" target="_blank" >测试本地化/国际化</a>
  </div>
  </body>
</html>
