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
    
    <title>My JSP 'faPai.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/css/faPai.css">

  </head>
  
  <body background="images/bg.jpg">
  
  <c:choose>
  
  <c:when test="${user.name=='2'}">
     <% int b=400;%>
     <% int b1=400;%>
     <% int b2=400;%>
    <c:forEach items="${ paiPath2}" var="u">
     <img src="${u}" style="position:absolute; top:500px; left:<%=b+=15%>px">
     <img src="images/poke/bg.png" style="position:absolute; top:<%=b1+=5%>px; left:200px">
     <img src="images/poke/bg.png" style="position:absolute; top:<%=b2+=5%>px; left:900px">
     </c:forEach>
     
     </c:when>
  
  
  <c:when test="${user.name=='1'}">
  <c:out value="${user.name}"/>
  <% int a=400;%>
  <% int a1=100;%>
  <% int a2=100;%>
    <c:forEach items="${ paiPath1}" var="u">
     <img src="${u}" style="position:absolute; top:500px; left:<%=a+=15%>px">
     <img src="images/poke/bg.png" style="position:absolute; top:<%=a1+=5%>px; left:200px">
     <img src="images/poke/bg.png" style="position:absolute; top:<%=a1+=5%>px; left:900px">
     </c:forEach>
     
     </c:when>
     
     <c:when test="${user.name=='3'}">
     <% int c=400;%>
     <% int c1=400;%>
     <% int c2=400;%>
    <c:forEach items="${ paiPath3}" var="u">
     <img src="${u}" style="position:absolute; top:500px; left:<%=c+=15%>px">
     <img src="images/poke/bg.png" style="position:absolute; top:<%=c1+=5%>px; left:200px">
     <img src="images/poke/bg.png" style="position:absolute; top:<%=c2+=5%>px; left:900px">
     </c:forEach>
     
     </c:when>
     
     </c:choose>
      
  </body>
</html>
