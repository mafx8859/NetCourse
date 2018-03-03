// JavaScript Document
function changeBack(n){
	   var obj=document.getElementById('classDetail');
	   var arry=document.getElementsByClassName('dh');
	   var arry1=document.getElementsByClassName('dh1');
	   arry[n-1].style.backgroundColor="#CCC";
	   arry1[n-1].style.color="#FFFFFF"
	   obj.style.display="block";
	   obj.onmouseover=function(){
		   obj.style.display="block";
		}
		obj.onmouseout=function(){
			obj.style.display="none";
		}
	   switch(n){
		   case 1 :obj.innerHTML="<ul id='qd'>基础<hr><br><a href=#>html/CSS</a>&nbsp;&nbsp;<a href=#>javascript</a>&nbsp;&nbsp;<a href=#>JQuery</a><br><br><br>进阶<hr><br><a href='#'>Html5</a>&nbsp;&nbsp;<a href='#'>CSS3</a>&nbsp;&nbsp;<a href='#'>Node.js</a>&nbsp;&nbsp;<a href='#'>Angular Js</a>&nbsp;&nbsp;<a href='#'>WebApp</a></ul>";
				   break; 
		   case 2 :obj.innerHTML="<ul id='hd'>全部<hr><br><a href='#'>PHP</a>&nbsp;&nbsp;<a href=#>java</a>&nbsp;&nbsp;<a href=#>SpringBoot</a>&nbsp;&nbsp;<a href=#>Python</a>&nbsp;&nbsp;<a href=#>C</a>&nbsp;&nbsp;<a href=#>C++</a>&nbsp;&nbsp;<a href=#>GO</a>&nbsp;&nbsp;<a href=#>C#</a>&nbsp;&nbsp;<a href=#>Ruby</a></ul>";break;
		   case 3 : obj.innerHTML="<ul id='yd'>全部<hr><br><a href=#>Android</a>&nbsp;&nbsp;<a href=#>iOS</a>&nbsp;&nbsp;<a href=#>unity3D</a>&nbsp;&nbsp;<a href=#>concos2d-X</a></ul>";
		   break;   
		    case 4 : obj.innerHTML="<ul id='sjk'>全部<hr><br><a href=#>MySql</a>&nbsp;&nbsp;<a href=#>MongoDB</a>&nbsp;&nbsp;<a href=#>Oracle</a>&nbsp;&nbsp;<a href=#>SQL Server</a></ul>";  
	   }
   }
	function backColor(n){
		var arry=document.getElementsByClassName('dh');
		var arry1=document.getElementsByClassName('dh1');
		arry[n-1].style.backgroundColor="#2B333B";
		arry1[n-1].style.color="#CCC"
		document.getElementById('classDetail').style.display="none";
	}
	function changZZC(){
	    document.getElementById("bg").style.display="block";
		document.getElementById("login").style.display="block";
	}
	function changBackZZC(){
	    document.getElementById("bg").style.display="none";
		document.getElementById("login").style.display="none";
	}
	function changeImg(n){
		if(n==1)
		  document.getElementById('closeImg').src="images/gb2.jpg";
		 else
		   document.getElementById('closeImg').src="images/gb.jpg";
	}
	function closeZc(){
		document.getElementById("zc").style.display="none";
	}
	function openZc(){
		document.getElementById("zc").style.display="block";
	}
	function changeImgzc(n){
		if(n==1)
		  document.getElementById('closeImgzc').src="images/gb2.jpg";
		 else
		   document.getElementById('closeImgzc').src="images/gb.jpg";
	}
	function changeMt(n){
	    var arry=document.getElementsByClassName('gcsbz');	
		arry[n-1].style.marginTop="20px";
	}
	function backMt(n){
	    var arry=document.getElementsByClassName('gcsbz');	
		arry[n-1].style.marginTop="25px";
	}