<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>出题</title>
<link  rel="stylesheet"  href="css/main.css" type="text/css"/>
<link  rel="stylesheet"  href="css/SetQuestion.css" type="text/css"/>
<script language="javascript" src="js/index.js"></script>
</head>
<body>
<div id="login">
  <div id="zc">
    <ul style="float:right;margin-right:20px;margin-top:20px;">
      <img onclick="closeZc()"id="closeImgzc" onMouseOver="changeImgzc(1)" onMouseOut="changeImgzc(2)" width="20px" height="20px"src="images/gb.jpg"/>
    </ul>
    <table id="ZC-table">
      <form action="AllServlet?flag=register" enctype="multipart/form-data" method="post">
        <tr>
          <td>用户名：</td>
          <td><input type="text" name="username"/></td>
        </tr>
        <tr>
          <td>密码：</td>
          <td><input type="password" name="pwd"/></td>
        </tr>
        <tr>
          <td>邮箱：</td>
          <td><input type="text" name="email"/></td>
        </tr>
        <tr>
          <td>上传头像：</td>
          <td><input type="file" name="photo"/></td>
        </tr>
        <tr>
          <td><input type="radio" name="object" value="seller">
            卖家注册</td>
          <td><input type="radio" name="object" value="buyer">
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
        <td><input type="text" name="userName"/></td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><input type="password" name="pwd"/></td>
      </tr>
      <tr>
        <td>验证码：</td>
        <td><input type="text" name="check"/></td>
        <td><img id="yzm" src="CaseYzmServlet"/><a onclick="changeYZM()" style="color:blue;">换一张</a></td>
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
  <li style="margin-left:10px;padding:0px;border:0px solid blue;margin-top:5px;width:40px;"><img height="60px" width="60px"src="images/3.gif"/></li>
  <li style="margin-left:20px;padding:0px;border:0px solid blue;margin-top:5px;width:40px;"><img height="60px" width="120px"src="images/未标题-1.gif"/></li>
  <li style="margin-left:50px;"><a href="index.jsp">已上传课程</a></li>
  <li><a href="AddCourse.jsp">上传新课程</a></li>
  <li><a href="#">我的学习</a></li>
  <li> 
    <form action="#" method="post">
      <input class="searchClass" type="text" name="search"/>
      <input type="button" value="搜索"/>
    </form>
  </li>
  <li id="portrait" class="Top_ul_right Top_ul_right2">
  <a href="#">
  <c:if test="${empty sessionScope.portrait}">
	      <img onMouseOut="changeBorder_div1()" onMouseOver="changeBorder_div2()" style="border-radius:30px;width:60px;height:40px;"src="images/58e89bee922a2.png"/>
   </c:if>
   <c:if test="${!empty sessionScope.portrait}">
     <img onMouseOut="changeBorder_div1()" onMouseOver="changeBorder_div2()" style="border-radius:30px;width:60px;height:40px;"src="${sessionScope.portrait}"/>
   </c:if>
  </a>
  </li>
  <li class="Top_ul_right Top_ul_right1" ><a href="#"><img style="margin-left:12px;margin-top:5px;"src="images/gouwuche.PNG"><font>购物车</font></a></li>
  <li class="Top_ul_right Top_ul_right3"><a onclick="changZZC()">登录</a></li>
  </ul>
<ul id="perCenter" class="per">
	    <c:if test="${empty sessionScope.portrait}">
	      <li><a><img style="border-radius:30px;width:60px;height:60px;"src="images/58e89bee922a2.png"/></a></li>
	    </c:if>
	    <c:if test="${!empty sessionScope.portrait}">
	      <li><a href="AllServlet?id=${sessionScope.id}"><img style="border-radius:35px;width:70px;height:70px;"src="${sessionScope.portrait}"/></a></li>
	    </c:if>
	    <c:if test="${empty sessionScope.name}">
	      <li style="margin-top:15%;margin-left:15%;"><a>未登录</a></li>
	    </c:if>
	    <c:if test="${!empty sessionScope.name}">
	      <li style="margin-top:15%;margin-left:15%;"><a href="AllServlet?id=${sessionScope.id}">${sessionScope.name}</a></li>
	    </c:if>
	    <c:if test="${!empty sessionScope.money}">
	    <p style="margin-top:110px;" align="center">余额：${sessionScope.money}元&nbsp;积分：100</p>
	    </c:if>
	    <c:if test="${empty sessionScope.money}">
	    <p style="margin-top:110px;" align="center">余额：0元&nbsp;积分：100</p>
	    </c:if>
	    <ul id="portraitDh">
		    <li><a href="">我的课程</a></li>
		    <li><a href="">订单中心</a></li>
		    <li><a href="">积分商城</a></li>
		    <li><a href="">个人设置</a></li>
	    </ul>
	    <ul class="out"><hr><a href="AllServlet?flag=out">安全退出</a></ul>
	  
</ul>

<div class="ct">
   <ul class="select">请选择你需要出的题的类型：<button value="选择题">选择题</button>&nbsp;&nbsp;&nbsp;<button value="非选择题">非选择题</button></ul>
   <ul class="writeQues">
       <form action="AllServlet?flag=setQuestion" method="post">
           <textarea name="quesDec" cols="80" rows="10">请在此处输入题设...</textarea>
           <table class="xuanx">
               <input type="hidden" value="${sessionScope.question}" name="questionId"/>
               <tr><td>选项A:</td><td><input style="width:578px;height:30px;" type="text" name="selectA"/></td></tr>
               <tr><td>选项B:</td><td><input style="width:578px;height:30px;"type="text" name="selectB"/></td></tr>
               <tr><td>选项C:</td><td><input style="width:578px;height:30px;"type="text" name="selectC"/></td></tr>
               <tr><td>选项D:</td><td><input style="width:578px;height:30px;"type="text" name="selectD"/></td></tr>
               <tr><td>答案:</td><td><input style="width:578px;height:30px;"type="text" name="solution"/></td></tr>
               <tr><td></td><td><input type="submit" value="提交此题"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="重写此题"/></td></tr>
           </table>
       </form>
   </ul>
</div>
</body>
</html>