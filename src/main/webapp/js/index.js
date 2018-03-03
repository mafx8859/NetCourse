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
	   case 1 :obj.innerHTML="<ul id='qd'>基础<hr><br><a href='AllServlet?name=HTML&flag=selectlike'>html</a>&nbsp;&nbsp;<a href='AllServlet?name=CSS&flag=selectlike'>CSS</a>&nbsp;&nbsp;<a href='AllServlet?name=javascript&flag=selectlike'>javascript</a>&nbsp;&nbsp;<a href='AllServlet?name=JQuery&flag=selectlike'>JQuery</a><br><br><br>进阶<hr><br><a href='AllServlet?name=HTML5&flag=selectlike'>Html5</a>&nbsp;&nbsp;<a href='AllServlet?name=CSS3&flag=selectlike'>CSS3</a>&nbsp;&nbsp;<a href='AllServlet?name=Node.js&flag=selectlike'>Node.js</a>&nbsp;&nbsp;<a href=href='AllServlet?name=Angular Js&flag=selectlike'>Angular Js</a>&nbsp;&nbsp;<a href='AllServlet?name=WebApp&flag=selectlike'>WebApp</a><br></ul>";
	   break; 
       case 2 :obj.innerHTML="<ul id='hd'>全部<hr><br><a href='AllServlet?name=PHP&flag=selectlike'>PHP</a>&nbsp;&nbsp;<a href='AllServlet?name=java&flag=selectlike'>java</a>&nbsp;&nbsp;<a href='AllServlet?name=SpringBoot&flag=selectlike'>SpringBoot</a>&nbsp;&nbsp;<a href='AllServlet?name=Python&flag=selectlike'>Python</a>&nbsp;&nbsp;<a href='AllServlet?name=C&flag=selectlike'>C</a>&nbsp;&nbsp;<a href='AllServlet?name=C++&flag=selectlike'>C++</a>&nbsp;&nbsp;<a href='AllServlet?name=GO&flag=selectlike'>GO</a>&nbsp;&nbsp;<a href='AllServlet?name=C#&flag=selectlike'>C#</a>&nbsp;&nbsp;<a href='AllServlet?name=Ruby&flag=selectlike'>Ruby</a></ul>";break;
       case 3 : obj.innerHTML="<ul id='yd'>全部<hr><br><a href='AllServlet?name=Android&flag=selectlike'>Android</a>&nbsp;&nbsp;<a href='AllServlet?name=iOS&flag=selectlike'>iOS</a>&nbsp;&nbsp;<a href='AllServlet?name=unity3D&flag=selectlike'>unity3D</a>&nbsp;&nbsp;<a href='AllServlet?name=concos2d-X&flag=selectlike'>concos2d-X</a></ul>";
       break;   
       case 4 : obj.innerHTML="<ul id='sjk'>全部<hr><br><a href='AllServlet?name=MySql&flag=selectlike'>MySql</a>&nbsp;&nbsp;<a href='AllServlet?name=MongoDB&flag=selectlike'>MongoDB</a>&nbsp;&nbsp;<a href='AllServlet?name=Oracle&flag=selectlike'>Oracle</a>&nbsp;&nbsp;<a href='AllServlet?name=SQL Server&flag=selectlike'>SQL Server</a></ul>";  
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
	function changeYZM(){
		document.getElementById('yzm').src=document.getElementById('yzm').src+"?nocache="+new Date().getTime();
	}
	function changeBorder_div2(){
		var border=document.getElementById('portrait');
		var per=document.getElementById('perCenter');
		border.style.border='2px solid red';
		per.style.display="block";
		per.onmouseover=function(){
			per.style.display="block";
		}
		per.onmouseout=function(){
			per.style.display="none";
		}
	}
	
	function changeBorder_div1(){
		var t=setTimeout("pem()",5000);
		cleanTimeout(t);
	}
	function pem(){
		var per=document.getElementById('perCenter');
		var border=document.getElementById('portrait');
		border.style.border='0px';
		per.style.display='none';
	}
	function creatCz(){
		var cz=document.getElementById('cz');
		if(cz.style.display=='block'){
			cz.style.display='none';
		}else{
			cz.style.display='block';
		}
	}
	function selectAll(){
		var input_allPrice=document.getElementById('input_allPrice');
		var allPrice=document.getElementById('allPrice');
		var allBox=document.getElementsByClassName('shopCar_select');
		var arrayPrice=document.getElementsByClassName('shopCar_font_price');
		var box=document.getElementById('selectAll');
		var sum=0;
		if(box.checked==true){
			for(var i=0;i<allBox.length;i++){
				allBox[i].checked=true;
				sum+=parseInt(arrayPrice[i].innerText);
			}
		}else{
			for(var i=0;i<allBox.length;i++){
				allBox[i].checked=false;
				sum=0;
			}
		}
		allPrice.innerText=sum;
		input_allPrice.value=sum;
	}
	function priceSum(money,object){
		var allPrice=document.getElementById('allPrice');
		var input_allPrice=document.getElementById('input_allPrice');
		if(object.checked==true){
			allPrice.innerText=parseInt(allPrice.innerText)+parseInt(money);
			input_allPrice.value=allPrice.innerText;
		}else{
			allPrice.innerText=parseInt(allPrice.innerText)-parseInt(money);
			input_allPrice.value=allPrice.innerText;
		}
	}
	
	
	