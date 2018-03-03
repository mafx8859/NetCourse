<%@ page language="java" import="java.util.*,conntrol.*" pageEncoding="utf-8"%>
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
      <form action="AllServlet?flag=register" enctype="multipart/form-data" method="post">
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
        <td><input type="text" title="验证码区分大小写" name="check"/></td>
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
  <li style="margin-left:50px;"><a href="index.jsp">课程</a></li>
  <li><a href="enterWar.jsp">实战</a></li>
  <li><a href="#">我的学习</a></li>
  <li> <a href="#">
    <form action="#" method="post">
      <input class="searchClass" type="text" name="search"/>
      <input type="button" value="搜索"/>
    </form>
    </a> </li>
  <li id="portrait" class="Top_ul_right Top_ul_right2">
	  <a href="personCenter.jsp">
	    <c:if test="${empty sessionScope.portrait}">
	      <img onMouseOut="changeBorder_div1()" onMouseOver="changeBorder_div2()" style="border-radius:30px;width:60px;height:40px;"src="images/58e89bee922a2.png"/>
	    </c:if>
	    <c:if test="${!empty sessionScope.portrait}">
	      <img onMouseOut="changeBorder_div1()" onMouseOver="changeBorder_div2()" style="border-radius:30px;width:60px;height:40px;"src="${sessionScope.portrait}"/>
	    </c:if>
	  </a>
  </li>
  <li class="Top_ul_right Top_ul_right1" ><a href="AllServlet?flag=selectShopCar&userId=${sessionScope.id }"><img style= "margin-left:12px;margin-top:5px;" src="images/gouwuche.PNG"><font>购物车</font></a></li>
  <li class="Top_ul_right Top_ul_right3"><a onclick="changZZC()">登录</a></li>
</ul>
<ul id="perCenter" class="per">
	    <c:if test="${empty sessionScope.portrait}">
	      <li><a href="personCenter.jsp"><img style="border-radius:30px;width:60px;height:60px;"src="images/58e89bee922a2.png"/></a></li>
	    </c:if>
	    <c:if test="${!empty sessionScope.portrait}">
	      <li><a href="AllServlet?id=${sessionScope.id}&flag=selectMyCourse"><img style="border-radius:35px;width:70px;height:70px;"src="${sessionScope.portrait}"/></a></li>
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
		    <li><a href="AllServlet?id=${sessionScope.id}&flag=selectMyCourse">我的课程</a></li>
		    <li><a href="">订单中心</a></li>
		    <li><a href="">积分商城</a></li>
		    <li><a href="">个人设置</a></li>
	    </ul>
	    <ul class="out"><hr><a href="AllServlet?flag=out">安全退出</a></ul>
	  
</ul>
<div class="center_div">
  <div id="classDetail" class="classDetail"> </div>
  <div  class="center-left">
  	<ul class="class-dh">
		<li class="dh"><a class="dh1" onMouseOut="backColor(1)"
			onMouseOver="changeBack(1)" href="AllServlet?type=前端开发&flag=typeselect&p=1">前端开发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
		<li class="dh"><a class="dh1" onMouseOut="backColor(2)"
			onMouseOver="changeBack(2)" href="AllServlet?type=后端开发&flag=typeselect&p=1">后端开发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
		<li class="dh"><a class="dh1" onMouseOut="backColor(3)"
			onMouseOver="changeBack(3)" href="AllServlet?type=移动开发&flag=typeselect&p=1">移动开发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
		<li class="dh"><a class="dh1" onMouseOut="backColor(4)"
			onMouseOver="changeBack(4)" href="AllServlet?type=数据库&flag=typeselect&p=1">数据库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
		<li class="dh"><a class="dh1" onMouseOut="backColor(5)"
			onMouseOver="changeBack(5)" href="AllServlet?type=云计算&大数据&flag=typeselect&p=1">云计算&大数据&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
		<li class="dh"><a class="dh1" onMouseOut="backColor(6)"
			onMouseOver="changeBack(6)" href="AllServlet?type=运维&测试&flag=typeselect&p=1">运维&测试&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
		<li class="dh"><a class="dh1" onMouseOut="backColor(7)"
			onMouseOver="changeBack(7)" href="AllServlet?type=UI设计&flag=typeselect&p=1">UI设计&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></a></li>
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
<div class="bottom-div" id="course1">
		<ul id="course">
            <c:if test="${empty listcourse}"><font size=5>还没有此类课程，请查看其他课程</font></c:if>
			<c:forEach items="${listcourse}" var="course" varStatus="stat">
				<li class="li_course"><a href="AllServlet?idcourse=${course.id}&flag=detail"
					><img style="border-radius:10px;" width="216px"
						height="121px" src="${course.image}" /> </a><br>课程名称为：${course.courseName}<a href="AllServlet?flag=addToShopCar&shopId=${course.id}&userId=${sessionScope.id}">加入购物车</a><br>发布人：${course.issure}&nbsp;&nbsp;
					<c:choose>
						<c:when test="${(course.price)==0.0}">
                                    课程免费
                   </c:when>
						<c:otherwise>
                      价格为：${course.price} 
                   </c:otherwise>

					</c:choose></li>
				<c:if test="${(stat.index+1)%4==0}">
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
				</c:if>
			</c:forEach>
		</ul>
		<br> <br>
		
		<c:if test="${(typeflag)==0}">
		<div class="page">
			<p>第${p}/共${count}页</p>
               
			    <a href="AllServlet?p=0&flag=page&v=#course1">首页</a> <a
				href="AllServlet?p=${p-1}&flag=page&v=#course1"> 上一页</a> <a
				href="AllServlet?p=${p+1}&flag=page&v=#course1">下一页</a> <a
				href="AllServlet?p=${count}&flag=page&v=#course1">尾页</a>
		</div>
		</c:if>
		<c:if test="${(typeflag)==1}">
		<div class="page">
			<p>第${p}/共${count}页</p>
            
			<a href="AllServlet?type=${type}&p=0&flag=typeselect&v=#course1">首页</a> <a
				href="AllServlet?type=${type}&p=${p-1}&flag=typeselect&v=#course1"> 上一页</a> <a
				href="AllServlet?type=${type}&p=${p+1}&flag=typeselect&v=#course1">下一页</a> <a
				href="AllServlet?type=${type}&p=${count}&flag=typeselect&v=#course1">尾页</a>
		</div>
		</c:if>
		<c:if test="${(typeflag)==2}"></c:if>
	</div>
</body>
</html>

