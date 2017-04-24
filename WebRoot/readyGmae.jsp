<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'readyGmae.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<script src="../js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js" ></script>
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<script type="text/javascript" src="js/jquery-3.1.1.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/bootstrap.min.js" ></script>

  </head>
  
  <body background="images/bg.jpg">
  <input type="button" class="btn btn-info" value="按钮"/>
  <div style="position:absolute; left:40%; top:200px">
  <h3>等待其他玩家进入.....</h3>
   <img src="images/poke/bg.png">
   </div>
  </body>
  <script type="text/javascript">
  	
  	var aa = window.setInterval(function() {
  		$.post("<%=request.getContextPath()%>/state.do",function(msg){
  			if(msg == "ok"){
  				qingchu();
  				window.setTimeout(location.href = "<%=request.getContextPath()%>/faPai.do");
  			}
  		},"text");
  	}, 3000)  
  	
  	function qingchu(){
  		window.clearInterval(aa);
  	} 
  </script>
</html>
