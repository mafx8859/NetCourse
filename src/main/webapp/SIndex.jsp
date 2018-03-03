<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link  rel="stylesheet"  href="css/main.css" type="text/css"/>
<script language="javascript" src="js/index.js"></script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'SIndext.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div id="bg"> </div>
  <ul class="Top_ul">
  <li style="margin-left:10px;padding:0px;border:0px solid blue;margin-top:5px;width:40px;"><img height="60px" width="60px"src="images/3.gif"/></li>
  <li style="margin-left:20px;padding:0px;border:0px solid blue;margin-top:5px;width:40px;"><img height="60px" width="120px"src="images/未标题-1.gif"/></li>
  <li style="margin-left:70px;"><a href="SIndex.jsp?issure=${issure}">已上传课程</a></li>
  <li><a href="AddCourse.jsp">上传新课程</a></li>
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
<br><br><br><p class="title1">${message}</p>
<div  class="center-left" id="e">
    <ul class="class-dh" >
      <c:forEach items="${list}" var="t" >
      <li class="dh"><a class="dh1" onMouseOut="backColor(${t.id})" onMouseOver="changeBack(${t.id})" href="AllServlet?h=${t.id}&flag=querycourse&issure=${issure}">${t.type}<span class="right">></span></a></li>
     </c:forEach>  
    </ul>
  </div>
  <div class="right1">
  <table border=1 width="1200px" height=445px>
  <tr>
            <c:forEach items="${list1}"  var="course"  varStatus="stat">
                <td><a href="AllServlet?${course.id}"><img width=100px height=80px src="${course.image}" /></a><br>课程名称为：${course.courseName}<br>价格为：${course.price}</td>
                <c:if test="${(stat.index+1)%4==0}">
                </tr><tr>
                </c:if>
            </c:forEach>
            </tr>
  </table>
  
  </div>
    </div>
     
     <div class="page1">
     <p >第${p}/共${count}页 </p>
        
     <a href="AllServlet?p=0&flag=querycourse&h=${h}&issure=${issure}" >首页</a>
     <a href="AllServlet?p=${p-1}&flag=querycourse&h=${h}&issure=${issure}"> 上一页</a>
     <a href="AllServlet?p=${p+1}&flag=querycourse&h=${h}&issure=${issure}">下一页</a>
     <a href="AllServlet?p=${count}&flag=querycourse&h=${h}&issure=${issure}">尾页</a>
     </div>
</div>
  </body>
</html>
