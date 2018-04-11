<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bms.css"/>
<style>
</style>
<script>
	var basePath = "${ctx}";
	var staticPath = "${ctx}/resources";
	var pmsUrl = '${pmsSystemVO.url}';
	var account = '${currentAccount}';
	var systemCode = '${currSystemCode}';
	var attribute = '';
	//修改密码
	function changePassword(){
		$('#log_dlg').dialog({
		    modal: true
		});
		$('#log_dlg').dialog('open').dialog('setTitle','修改密码');
		//$('#${entityName}_fm').form('clear');
		$('#oldPassword').val('');
		$('#newPassword').val('');
		$('#newPassword2').val('');
	}
	//保存密码
	function savePassword(){
		$('#log_fm').form('submit',{
			url: '${ctx}/changePassword',
			onSubmit: function(){
					return $(this).form('validate');
			},
			success: function(result){
				var result = eval('('+result+')');
				if (result.success) {
					$('#log_dlg').dialog('close');
					$.messager.alert('','修改密码成功！', "info", function () {  
						top.location = "${ctx}/logout"; 
			        });
				} else {
					$.messager.show({
						title: '提示',
						msg: '修改密码失败      '+result.data
					});
				}
			}
		})
	}
	
    // 退出登录
    function logout() {
        $.messager.confirm('提示', '确定要退出?', function (r) {
            if (r) {
                window.location.href = basePath + '/logout';
            }
        });
    }

    // 切换主题
    function changeTheme() {
        var themeValue = $("#theme").val();
        var link = $("#easyuiTheme");
        link.attr("href", staticPath + "/easyui/themes/" + themeValue + "/easyui.css");
        $.getJSON(pmsUrl + "/api/updateDefault?callback=?", {
            "defaultTheme": themeValue,
            "account": account
        }, function (result) {
            if (result.success) {
                $('#switch').dialog('close');
            }
        });
    }

    $(function () {
        $('#header').layout({fit: true});
        $('#systems').delegate('li', 'click', function () {
            var url = $(this).attr("url");
            window.location = url;
        });
        $.extend($.fn.validatebox.defaults.rules, {   
		    passwordEqual: {   
		        validator: function(value){ 
		        	var i =$('#newPassword').val();
		        	var ii =$('#newPassword2').val();
		        	if(i==ii){return true;}
		        },   
		        message: '两次新密码不一致!'  
		    }   
		});
    })
</script>

<div class="header">
    <div style="text-align:right; color:#ffffff; padding:8px 0px;font-size: 14px;">
        <div style="float: left;vertical-align: middle;">
        <span class="logo1" style="display:block;float: left; text-align:left;">
            <img style="height:100%;" src="resources/images/logo.png"></span>
        </div>
        <span data-options="plain:true" style="font-size: 14px;">欢迎您！${loginIUser.userName}</span> │
        <a href="#" class="easyui-menubutton" data-options="menu:'#user',iconCls:'fa fa-user'">个人中心</a> │
        <a id="unreadMessage" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'fa fa-comments'">消息中心
        </a> │
        <div class="nav">
            <span class=" fa fa-dashboard" style="vertical-align: middle;font-size: 14px;">&nbsp;</span>
            <select id="theme" onchange="changeTheme();" >
                <option class='opt' value="blue">主题-蓝色</option>
                <option class='opt' value="orange">主题-橙色</option>
                <option class='opt' value="red">主题-红色</option>
            </select>
        </div>
    </div>
    <div id="user">
        <div id="switchIndex" style="display:none;" data-options="iconCls:'fa fa-home',itemHeight:30"
             onclick="javascript: $('#switch').dialog('open');">配置首页
        </div>
        <div data-options="iconCls:'fa fa-edit'" onclick="changePassword();">修改密码</div>
        <div data-options="iconCls:'fa fa-user-secret'" onclick="logout()">安全退出</div>
    </div>

    <div id="switch" class="easyui-dialog" title="配置默认首页"
         data-options="iconCls: 'fi-home',closed:true, buttons:'#switch-btn'"
         style="width:400px;height:200px;padding:10px">
        <div class="content">
            配置默认首页:
            <select id="defaultIndex">
            </select>
        </div>
    </div>

    <div id="switch-btn">
        <a href="#" class="easyui-linkbutton" iconCls="fa fa-save" onclick="updateDefault()">确定</a>
        <a href="#" class="easyui-linkbutton" iconCls="fa fa-close"
           onclick="javascript: $('#switch').dialog('close');">关闭</a>
    </div>


    <div id="msg" class="easyui-window" title="站内消息" data-options="modal:true,closed:true,iconCls:'fa fa-comments'"
         style="width:60%;height:500px;overflow: hidden;">
        <iframe id="empMessage" src="" style="border: none;width: 100%;height: 100%;"></iframe>
    </div>
    
    		<!-- 修改密码 -->
	<div id="log_dlg" class="easyui-dialog" iconCls="icon-edit" style="width:400px;height:250px;padding:10px 20px" closed="true" buttons="#log_dlg-buttons">
		<div class="subtitle" >修改密码</div> 
		<form id="log_fm" method="post">
		    <table  class="m_table" style="width:100%;">
			   <tr>
	      			<td><label>账号</label></td>
					<td><input type="hidden" name="staffid" value="${loginIUser.id}">${loginIUser.userName}
			        </td>
			   </tr>
			   <tr>
	      			<td><label class="bitian">原密码</label></td>
					<td>
						<input class="easyui-validatebox" type="password" name="oldPassword" id="oldPassword" data-options="required:true"/>
					</td>
			   </tr>
			   <tr>
	      			<td><label class="bitian">新密码</label></td>
					<td>
						<input class="easyui-validatebox" type="password" name="newPassword" id="newPassword" data-options="required:true"/>
					</td>
			   </tr>
			   			   <tr>
	      			<td><label class="bitian">重复新密码</label></td>
					<td>
						<input class="easyui-validatebox" type="password" name="newPassword2" id="newPassword2" data-options="required:true" validType="passwordEqual"/>
					</td>
			   </tr>
			</table>
	    </form>
	</div>
	<!-- 修改密码保存取消 -->
	<div id="log_dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePassword()">保存</a>
		<a href="#" class="easyui-linkbutton"  id="edit_save" iconCls="icon-cancel" onclick="javascript:$('#log_dlg').dialog('close')">取消</a>
	</div>
    
</div>

