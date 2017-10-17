<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.entity.Student" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	
	window.onload = function(){
	
		call();
	}
	function call(){
		
		var selectOne = document.getElementById("selectOne");
		
		var showOne = document.getElementById("showOne");
		
		var html_ = "";
		if(selectOne.value=="age"){
		
			html_="<option value='>'>大于</option><option value='>='>大于等于</option><option value='='>等于</option><option value='0'>小于</option><option value='<='>小于等于</option>";
		} else{
			html_="<option value='include'>包含</option><option value='queal'>等于</option>";
		}
	showOne.innerHTML=html_;
	
	
	}
	</script>
  </head>
  
  <body style = "text-align:center">
  <form action="Index" method="post">
       <div style = "text-align:center">
       		<label>&quot;属性:</label>
       		<select name = "attribute" onchange = "call();" id="selectOne">
       			<option value="id">学号</option>
       			<option value="name">姓名</option>
       			<option value="age">年龄</option>
       		</select>
       		<select name = "ch" id="showOne">
       			<!--<option value="include">包含</option>
       			<option value="queal">等于</option>
       		--></select><!--
       		<select name = "min">
       			<option value=">">大于</option>
       			<option value=">=">大于等于</option>
       			<option value="=">等于</option>
       			<option value="0">小于</option>
       			<option value="<=">小于等于</option>
       		</select>
       		--><input type = "text" name = "input_value"/>
       		<input type = "submit" value = "查询" />
       </div>
      </form>
    <%
    if((List<Student>)request.getAttribute("list")!=null){
		List<Student> list = (List<Student>)request.getAttribute("list");
	int size = list.size();
	System.out.println(size);
	%>
	<table style = 'margin-top:20px' border='1' align='center' width='50%' height = '30%'> 
		<tr><td align = 'center'>学号</td><td align = 'center'>姓名</td><td align = 'center'>年龄</td></tr>
	<% 
	for(int i =0; i<size;i++){
		Student stu = list.get(i);
	%>
	<tr><td align = 'center'><%= stu.getId()%></td><td align = 'center'><%=stu.getName()%></td><td align = 'center'><%=stu.getAge()%></td></tr>
	<%}%>
	
	</table>
	<% }%>
  </body>
</html>
