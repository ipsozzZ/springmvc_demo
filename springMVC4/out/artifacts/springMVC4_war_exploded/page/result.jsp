<%--
  Created by IntelliJ IDEA.
  User: 19753
  Date: 2019/9/8
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>
<html>
<head>
    <title>Result</title>
</head>
<body>
<div style="width: 700px; margin: auto; padding-top: 20px; padding-bottom: 40px">
    <h1>Result</h1>
    <%-- 如果不写modelAttribute="user"，默认到model中找的是modelAttribute="command",如果没有就报错 --%>
    <%-- action默认是从什么地址来返回什么地址，一般都是重新设置 --%>
    <fm:form modelAttribute="user" action="${pageContext.request.contextPath}/update2">
        姓名：<fm:input path="name"/> <br>
        年龄：<fm:input path="age"/> <br>

        <%-- 单选框 path对应属性名，value对应属性值 --%>
        性别：<fm:radiobutton path="gender" value="0" label="男" />
        <fm:radiobutton path="gender" value="1" label="女" /><br>

        <%-- 复选框 path是user的hobby属性的值，items是所有爱好的集合 --%>
        爱好：<fm:checkboxes path="hobby" items="${allhobby}" /> <br>

        <%-- 下拉列表 path:设置当把表单提交的时候把itemValue的值传给pet对象对应的属性 --%>
        <%-- items:数据来源 --%>
        <%-- itemValue:提交的值 --%>
        <%-- itemLabel:视图显示的值 --%>
        <fm:select path="pet.id" items="${petList}" itemValue="id" itemLabel="name" /><br>

        <input type="submit" value="修改">
    </fm:form>
</div>
</body>
</html>
