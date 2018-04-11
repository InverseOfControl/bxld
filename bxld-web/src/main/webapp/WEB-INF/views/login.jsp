<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link id="easyuiTheme" rel="stylesheet" href="${ctx}/resources/easyui/themes/blue/easyui.css">
<link rel="stylesheet" href="${ctx}/resources/easyui/themes/icon.css">
<link rel="stylesheet" href="${ctx}/resources/easyui/themes/color.css">
<link rel="stylesheet" href="${ctx}/resources/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/resources/css/bms.css">
<link rel="stylesheet" href="${ctx}/resources/css/style.css">

<link href="${ctx}/resources/css/login.css" type=text/css rel=stylesheet>

<script type="text/javascript" src="${ctx}/resources/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/extendsEasyui.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/moment.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<title>借款管理登录</title>  
</head>
<script>
<%
String msg = (String)request.getAttribute("msg");
if(null==msg){
	msg="";
}
%>
$(function(){
	
	//验证码刷新
	$("#verifyCodeImageImg").click(function(){
		document.getElementById('verifyCodeImageImg').src='${ctx }/verifyCodeImage?ran='+Math.random();
	});
});



function toVaild(){
	
	var val = document.getElementById("loginAccount").value;
	var valPwd = document.getElementById("password").value;
	if(val == ""|| val=="请输入用户名"){
		//document.getElementById("msg").innerHTML("dsad")
		$('#msg').html("请输入用户名");
		//alert("请输入用户名");
		return false;
	}else if(valPwd=="请输入密码"||valPwd==null||valPwd==""){
		//alert("请输入密码");
		$('#msg').html("请输入密码");
		return false;
	}else{
		return true;
	}
}



</script>
</head>
<body>
	<div class="login_img">
    <img src="${ctx}/resources/images/login_bg.jpg" width="100%">
    </div>
	<div id="layout">
    <div class="layout_img">
    <img src="${ctx}/resources/images/layout_img.jpg" width="100%">
    </div>
	<div class="login">
	
    <form id="loginform" action="${ctx}/login"  method="POST"> 
    <table width="260" border="0" cellspacing="20" cellpadding="1">
      <tr>
        <td class="login_tit" colspan="2">洲际保险订单管理系统</td>
      </tr>
      <tr>
        <td class="login_error" style="color:red" colspan="2" align="center" height="25" id="msg"><%=msg %></td>
      </tr>
      <tr>
        <td align="center">用户名</td>
        <td><input id="loginAccount" name="loginAccount" type="text"></td>
      </tr>
      <tr>
        <td align="center">密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
        <td><input name="password" type="password" ></td>
      </tr>
      <tr>
        <td align="center">验证码</td>
        <td><input id="validCode" name="validCode" type="text" style="width:130px; " onkeydown="submitMe(event);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <img id="verifyCodeImageImg" src="${ctx }/verifyCodeImage" width="54" height="21" align="absmiddle"/></td>
      </tr>
      <tr>
        <td colspan="2">
        <input value="登    录" type="submit" type="button" id="btnNext" style="cursor: hand;" class="btn_login" />
        </td>
      </tr>
    </table>
    </form>
	</div>
	</div>
	

	
	
</body>
</html>