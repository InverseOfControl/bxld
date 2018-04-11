<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<div id="querySalesmanDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 50px;">
	<form id="manualDispatch_queryForm" >
		<table style="border-collapse: separate; border-spacing: 15px;">
			<table class="table_ui W100 center_m">
				<tr>
					<th>姓名:</th>
					<td>
						<input type="text" id="userName" name="userName" class="easyui-textbox input" data-options="prompt:'姓名',width:190">
					</td>
					<th>工号:</th>
					<td>
						<input type="text" id="inviteCode" name="inviteCode" class="easyui-textbox input" data-options="prompt:'工号',width:190">
					</td>
					<th>微信昵称:</th>
					<td>
						<input type="text" id="nickName" name="nickName" class="easyui-textbox input" data-options="prompt:'微信昵称',width:190">
					</td>
					<th>认证状态:</th>
					<td>
						<select type="text" class="easyui-combobox input" id="authenticationState" name="authenticationState" data-options="value:'',width:190">
							<option value="">全部</option>
							<option value="0">未认证</option>
							<option value="1">已认证</option>
							<option value="2">已注销</option>
						</select>
					</td>

					<td colspan="2">
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchSalesmanList();">
							<span style="font-size: 12px">查&nbsp;询</span>
						</a>
						<a class="easyui-linkbutton" iconCls="icon-edit" onclick="authenticationBatch();">
							<span style="font-size: 12px">认&nbsp;证</span>
						</a>
					</td>
				</tr>				
			</table>
		</table>
	</form>
</div>
<div title="认证查询" style="height: 92px; padding-top: 8px;">
	<table id="salesman_list_datagrid" toolbar="#querySalesmanDiv"></table>
</div>

<script type="text/javascript" src="${ctx}/resources/js/salesman/salesman_list.js"></script>


