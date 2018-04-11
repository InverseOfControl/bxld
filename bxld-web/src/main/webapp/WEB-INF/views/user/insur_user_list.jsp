<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<div id="queryUserDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 50px;">
	<form id="manualDispatch_queryForm" >
		<table style="border-collapse: separate; border-spacing: 15px;">
			<table class="table_ui W100 center_m">
				<tr>
					<th>用户姓名:</th>
					<td>
						<input type="text" id="userName" name="userName" class="easyui-textbox input" data-options="prompt:'用户姓名',width:190">
					</td>
					<th>邀请码:</th>
					<td>
						<input type="text" id="inviteCode" name="inviteCode" class="easyui-textbox input" data-options="prompt:'邀请码',width:190">
					</td>
					<th>微信昵称:</th>
					<td>
						<input type="text" id="nickName" name="nickName" class="easyui-textbox input" data-options="prompt:'微信昵称',width:190">
					</td>

					<td colspan="2">
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchUserList();">
							<span style="font-size: 12px">查&nbsp;询</span>
						</a>
						<a class="easyui-linkbutton" iconCls="icon-add" onclick="addUser();">
							<span style="font-size: 12px">新&nbsp;建</span>
						</a>
						<a class="easyui-linkbutton" iconCls="icon-edit" id="restPass" >
							<span style="font-size: 12px">重置密码</span>
						</a>
					</td>
				</tr>				
			</table>
		</table>
	</form>
</div>
<div title="用户查询" style="height: 92px; padding-top: 8px;">
	<table id="user_list_datagrid" toolbar="#queryUserDiv"></table>
</div>

	<!-- 新建 编辑对话框 -->
	<div id="user_dlg" class="easyui-dialog" style="text-align:center;width:800px; " closed="true" buttons="#user_dlg-buttons">
		<form id="fm" method="post">
		<input name="id" id="id" type="hidden">
		<table style="font-size:12px; width:100%; text-align:right;">
			<tr>
				<td><label>登陆账户:</label></td>
				<td align="left">
					<input id="loginAccount"  name="loginAccount" disabled="disabled" style="width:250px" class="easyui-validatebox" maxlength="15" onkeyUp= "value=value.replace(/\D/g,'') "/>
				</td>
				<td><label>用户姓名:</label></td>
				<td align="left"><input id="userName" name="userName" style="width:250px" class="easyui-validatebox"  maxlength="15" required="true" ></td>				
			</tr>
			<tr>
				<td><label>身份证号:</label></td>
				<td align="left">
					<input id="idCard"  name="idCard" style="width:250px" class="easyui-validatebox" required="true" maxlength="15" onkeyUp= "value=value.replace(/\D/g,'') "/>
				</td>
				<td><label>邀请码:</label></td>
				<td align="left"><input id="inviteCode" name="inviteCode" style="width:250px" class="easyui-validatebox"  maxlength="15" required="true" onkeyUp= "value=value.replace(/\D/g,'') " ></td>				
			</tr>
			<tr>
				<td><label>区域:</label></td>
				<td align="left">
					<input id="area"  name="area" style="width:250px" class="easyui-validatebox" required="true" maxlength="15" />
				</td>
				<td><label>手机号:</label></td>
				<td align="left"><input id="phoneNo" name="phoneNo" style="width:250px" class="easyui-validatebox"  maxlength="15" required="true" onkeyUp= "value=value.replace(/\D/g,'') " ></td>				
			</tr>
			<tr>
				<td><label>用户类型:</label></td>
				<td align="left">
					<select type="text" class="easyui-combobox input" id="userType" name="userType" data-options="value:'',width:250,height:20"  required="true">							
							<option value="management">管理用户</option>
							<option value="salesman">认证用户</option>
						</select>
				</td>
				<td><label>微信昵称:</label></td>
				<td align="left"><input id="nickName" name="nickName" style="width:250px" class="easyui-validatebox"  maxlength="15" required="true" ></td>				
			</tr>
			
		</table>
		</form>
	</div>
	<div id="user_dlg-buttons" style="text-align:center;width:800px; ">
		<a href="#" id="confirmButton" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser();">提交</a>
    	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#user_dlg').dialog('close')">取消</a>
    </div>

<script type="text/javascript" src="${ctx}/resources/js/user/insur_user_list.js"></script>


