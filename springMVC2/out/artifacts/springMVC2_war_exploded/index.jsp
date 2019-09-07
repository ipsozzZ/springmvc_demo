<%--
  Created by IntelliJ IDEA.
  User: 19753
  Date: 2019/9/4
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>index</title>
  </head>
  <body>
  <div style="width: 700px; margin: auto;padding-top: 20px; padding-bottom: 40px">
    <a href="${pageContext.request.contextPath}/show1.action" target="_blank">go to show1</a> <br>
    <a href="${pageContext.request.contextPath}/show2.action" target="_blank">go to show2</a> <br>

    <hr>
    <h3>使用原生的request对象接收参数demo</h3>
    <a href="${pageContext.request.contextPath}/show3?id=11" target="_blank">go to show3</a> <br>

    <hr>
    <h3>接收简单参数类型与@RequestParam</h3>
    <a href="${pageContext.request.contextPath}/show4?id=11&name=ipso" target="_blank">go to show4</a> <br>

    <hr>
    <h3>javaBean接收参数</h3>
    <form action="${pageContext.request.contextPath}/show5">
      user: <input type="text" name="user" ><br>
      age: <input type="text" name="age"><br>
      <input type="submit" value="提交">
    </form>

    <hr>
    <h3>数组接收参数</h3>
    <form action="${pageContext.request.contextPath}/show6">
      user1: <input type="text" name="name" ><br>
      user2: <input type="text" name="name"><br>
      <input type="submit" value="提交">
    </form>

    <hr>
    <h3>javaBean与数组结合使用</h3>
    <form action="${pageContext.request.contextPath}/show7">
      user: <input type="text" name="user" ><br>
      hobbit:<input type="checkbox" name="hobbit" value="lq">篮球
      <input type="checkbox" name="hobbit" value="ppq">乒乓球
      <input type="checkbox" name="hobbit" value="zq">足球
      <input type="checkbox" name="hobbit" value="bq">棒球<br>
      age: <input type="text" name="age"><br>
      <input type="submit" value="提交">
    </form>

    <hr>
    <h3>javaBean、数组和包装类结合使用</h3>
    <form action="${pageContext.request.contextPath}/show8">
      user: <input type="text" name="user" ><br>
      hobbit:<input type="checkbox" name="hobbit" value="篮球">篮球
      <input type="checkbox" name="hobbit" value="乒乓球">乒乓球
      <input type="checkbox" name="hobbit" value="足球">足球
      <input type="checkbox" name="hobbit" value="棒球">棒球<br>
      age: <input type="text" name="age"><br>

      --------宠物--------<br>
      宠物名称：<input type="text" name="dog.name"><br>
      宠物颜色：<input type="text" name="dog.color"><br>
      <input type="submit" value="提交">
    </form>

    <hr>
    <h3>javaBean、数组和包装类、List集合结合使用</h3>
    <form action="${pageContext.request.contextPath}/show9">
      user: <input type="text" name="user" ><br>
      hobbit:<input type="checkbox" name="hobbit" value="篮球">篮球
      <input type="checkbox" name="hobbit" value="乒乓球">乒乓球
      <input type="checkbox" name="hobbit" value="足球">足球
      <input type="checkbox" name="hobbit" value="棒球">棒球<br>
      age: <input type="text" name="age"><br>

      --------宠物--------<br>
      宠物名称：<input type="text" name="dog.name"><br>
      宠物颜色：<input type="text" name="dog.color"><br>
      --------宠物1--------<br>
      宠物名称：<input type="text" name="dogs[0].name"><br>
      宠物颜色：<input type="text" name="dogs[0].color"><br>
      --------宠物2--------<br>
      宠物名称：<input type="text" name="dogs[1].name"><br>
      宠物颜色：<input type="text" name="dogs[1].color"><br>
      --------宠物3--------<br>
      宠物名称：<input type="text" name="dogs[2].name"><br>
      宠物颜色：<input type="text" name="dogs[2].color"><br>
      <input type="submit" value="提交">
    </form>
  </div>
  </body>
</html>
