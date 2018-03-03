<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<link  rel="stylesheet"  href="css/main.css" type="text/css"/>
<script language="javascript" src="js/index.js"></script>
</head>
<body>
<div id="login">
  <div id="zc">
    <ul style="float:right;margin-right:20px;margin-top:20px;">
      <img onclick="closeZc()"id="closeImgzc" onMouseOver="changeImgzc(1)" onMouseOut="changeImgzc(2)" width="20px" height="20px"src="images/gb.jpg"/>
    </ul>
    <table id="ZC-table">
      <form action="AllServlet?flag=register" method="post">
        <tr>
          <td>用户名：</td>
          <td><input type="text" name="name"/></td>
        </tr>
        <tr>
          <td>密码：</td>
          <td><input type="password" name="password"/></td>
        </tr>
        <tr>
          <td>性别：</td>
          <td><input type="text" name="sex"/></td>
        </tr>
        <tr>
          <td>年龄：</td>
          <td><input type="text" name="age"/></td>
        </tr>
        <tr>
          <td>上传头像：</td>
          <td><input type="file" name="file1"/></td>
        </tr>
        <tr>
          <td><input type="radio" name="type" value="0">
            卖家注册</td>
          <td><input type="radio" name="type" value="1">
            买家注册</td>
        </tr>
        <tr align="center">
          <td align="center"><input type="submit" value="提交注册信息"/></td>
        </tr>
      </form>
    </table>
  </div>
  <ul style="float:right;margin-right:20px;margin-top:20px;">
    <img onclick="changBackZZC()"id="closeImg" onMouseOver="changeImg(1)" onMouseOut="changeImg(2)" width="20px" height="20px"src="images/gb.jpg"/>
  </ul>
  <table id="loginTable">
    <form action="AllServlet?flag=login" method="post">
      <tr>
        <td>用户名：</td>
        <td><input type="text" name="name"/></td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><input type="password" name="password"/></td>
      </tr>
      <tr>
        <td>验证码：</td>
        <td><input type="text" name="check"/></td>
        <td><img src="images/check.PNG"/></td>
      </tr>
      <tr>
        <td></td>
        <td><input style="height:30px;width:60px;" type="submit" name="submit" value="登录"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="openZc()">注册用户</a></td>
      </tr>
    </form>
  </table>
</div>
<div id="bg"> </div>
<ul class="Top_ul">
  <li style="margin-left:10px;padding:0px;border:0px solid blue;margin-top:10px;width:40px;"><img width="120px"src="images/logo.png"/></li>
  <li style="margin-left:50px;"><a href="index.html">课程</a></li>
  <li><a href="#">实战</a></li>
  <li><a href="#">我的学习</a></li>
  <li> <a href="#">
    <form action="#" method="post">
      <input class="searchClass" type="text" name="search"/>
      <input type="button" value="搜索"/>
    </form>
    </a> </li>
  <li class="Top_ul_right Top_ul_right2"><a href="#"><img border-radius="30px"width="60px"height="40px"src="images/58e89bee922a2.png"/></a></li>
  <li class="Top_ul_right Top_ul_right1" ><a href="#"><img style="margin-left:12px;margin-top:5px;"src="images/gouwuche.PNG"><font>购物车</font></a></li>
  <li class="Top_ul_right Top_ul_right3"><a onclick="changZZC()">登录</a></li>
</ul>
<div class="center_div">
  <div id="classDetail" class="classDetail"> </div>
  <div  class="center-left">
    <ul class="class-dh">
      <li class="dh"><a class="dh1" onMouseOut="backColor(1)" onMouseOver="changeBack(1)" href="#">前端开发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
      <li class="dh"><a class="dh1" onMouseOut="backColor(2)" onMouseOver="changeBack(2)" href="#">后端开发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
      <li class="dh"><a class="dh1" onMouseOut="backColor(3)" onMouseOver="changeBack(3)" href="#">移动开发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
      <li class="dh"><a class="dh1" onMouseOut="backColor(4)" onMouseOver="changeBack(4)" href="#">数据库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
      <li class="dh"><a class="dh1" onMouseOut="backColor(5)" onMouseOver="changeBack(5)" href="#">云计算&大数据&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
      <li class="dh"><a class="dh1" onMouseOut="backColor(6)" onMouseOver="changeBack(6)" href="#">运维&测试&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
      <li class="dh"><a class="dh1" onMouseOut="backColor(7)" onMouseOver="changeBack(7)" href="#">UI设计&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
    </ul>
  </div>
  <div class="center-top"><img src="images/59e87b9000013bbf09360316.jpg"/></div>
  <div class="center-bottom">
    <ul id="center-bottom-ul">
        <li class="gcsbz"><a href="#"><img onMouseOut="backMt(1)" onMouseOver="changeMt(1)"src="images/webQd.PNG"/></a></li>
        <li class="gcsbz"><a href="#"><img onMouseOut="backMt(2)" onMouseOver="changeMt(2)"src="images/java.PNG"/></a></li>
        <li class="gcsbz"><a href="#"><img onMouseOut="backMt(3)" onMouseOver="changeMt(3)"src="images/ad.PNG"/></a></li>
        <li class="gcsbz"><a href="#"><img onMouseOut="backMt(4)" onMouseOver="changeMt(4)"src="images/PHP.PNG"/></a></li>
        <li class="gcsbz"><a href="#"><img onMouseOut="backMt(5)" onMouseOver="changeMt(5)"src="images/ios.PNG"/></a></li>
    </ul>
  </div>
</div>
<ul style="margin-top:40px;">
  <img src="images/tag.png">
</ul>
<div class="bottom-div">
  <ul id="course">
    <li> <a href="courseDetail.html" target="_blank"><img style="border-radius:10px;"width="216px" height="121px"src="images/5909a1250001197e05400300-360-202.jpg"/> </a> </li>
  </ul>
</div>
</body>
</html>

