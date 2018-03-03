<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<link  rel="stylesheet"  href="css/main.css" type="text/css"/>
<link  rel="stylesheet"  href="css/studyFromLXZ.css" type="text/css"/>
<script language="javascript" src="js/index.js"></script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'study.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body><div id="bg"> </div>
  <ul class="Top_ul">
  <li style="margin-left:10px;padding:0px;border:0px solid blue;margin-top:5px;width:40px;"><img height="60px" width="60px"src="images/3.gif"/></li>
  <li style="margin-left:20px;padding:0px;border:0px solid blue;margin-top:5px;width:40px;"><img height="60px" width="120px"src="images/未标题-1.gif"/></li>
  <li style="margin-left:70px;"><a href="index.jsp">课程</a></li>
  <li><a href="#">实战</a></li>
  <li><a href="#">我的学习</a></li>
  <li> <a href="#">
    <form action="#" method="post">
      <input class="searchClass" type="text" name="search"/>
      <input type="button" value="搜索"/>
    </form>
    </a> </li>
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
<br>
 <p align=center>${course1.alt}</p>
 <br>
 <div class="giveAheight">
 <div  class="video-wrap">
<video width="700" height="400" controls="controls">
  <source src="${course1.video}" type="video/mp4" />
  <source src="${course1.video}" type="video/ogg" />
  <source src="${course1.video}" type="video/webm" />
  <object data="${course1.video}" width="700" height="400">
    <embed src="${course1.video}" width="700" height="400" />
  </object>
</video>
<div class="pl"> 评论：</div>
<div class="pl-content">
	<table>
  		<c:forEach items="${listcomment}" var="comment" >
  		<tr><td>${comment.issureName}:&nbsp;${comment.content}</td></tr>
  		</c:forEach>
    </table>
    <form action="AllServlet?flag=addcomment&idc=${course1.id}" method="post">
    <div>
    <textarea name="comment" rows="2" cols="80" style="resize:none;" style="overflow-y:hidden;">在这里留下你的评论吧...</textarea>
     </div>
     <div class="back2">
    <input class="t" type="submit" value="提交评论" />
    </div>
   
    
    </form>
</div>

<c:choose>
						<c:when test="${sessionScope.usertype==0}">
                                <a href="SIndex.jsp" class="back"> 返回主页</a>
                   </c:when>
						<c:otherwise>
						 <a href="AllServlet?flag=makeQuestion&question=${sessionScope.studyID}" class="back1">做&nbsp;题</a>
                   <a href="index.jsp" class="back"> 返回主页</a>
                   </c:otherwise>

					</c:choose>
  
</div>
 </div>
  </body>
</html>
