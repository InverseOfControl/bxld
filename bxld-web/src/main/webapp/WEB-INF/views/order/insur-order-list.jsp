<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<script type="text/javascript" src="${ctx}/resources/js/common/vue.js"></script>
<div id="queryOrderDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 100px;">
	<form id="order_queryForm" >
		<table style="border-collapse: separate; border-spacing: 15px;">
			<table class="table_ui W100 center_m">
				<tr>
					<th>姓名:</th>
					<td>
						<input type="text" name="consumerName" class="easyui-textbox input" data-options="prompt:'姓名',width:190">
					</td>
					<th>工号:</th>
					<td>
						<input type="text" name="applyUserAccount" class="easyui-textbox input" data-options="prompt:'工号',width:190">
					</td>
					<th>微信号:</th>
					<td>
						<input type="text" name="customerName" class="easyui-textbox input" data-options="prompt:'微信号',width:190">
					</td>
					<th>车牌号:</th>
					<td>
						<input type="text" name="plateNumber" class="easyui-textbox input" data-options="prompt:'车牌号',width:190">
					</td>

					<td colspan="2"><a class="easyui-linkbutton" id="order_form_query">
						<i class="fa fa-search"></i>查&nbsp;询</a>
					</td>

				</tr>
				<tr>
					<th>订单号:</th>
					<td>
						<input type="text" name="orderCode" class="easyui-textbox input" data-options="prompt:'订单号',width:190">
					</td>
					<th>审批结果:</th>
					<td>
						<select type="text" class="easyui-combobox input" name="orderStatus" data-options="value:'',width:190">
							<option value="0">新申请</option>
							<option value="1">待审核</option>
							<option value="2">待补件</option>
							<option value="3">待付款</option>
							<option value="4">已付款</option>
							<option value="5">已确认</option>
						</select>
					</td>

					<th>订单日期:</th>
					<td>
						<input type="text" name="queryStartDate" class="easyui-datebox input" data-options="width:190">
					</td>
					<th>至:</th>
					<td>
						<input type="text" name="queryEndDate" class="easyui-datebox input" data-options="width:190">
					</td>
					<td colspan="2"><a class="easyui-linkbutton" id="11">
						<i class="fa fa-external-link"></i>导&nbsp;出</a>
					</td>
				</tr>
			</table>
		</table>
	</form>
</div>
<div title="订单查询" style="height: 92px; padding-top: 8px;">
	<table id="order_list_datagrid" toolbar="#queryOrderDiv"></table>
</div>

<div id="order_manager_dialog"> </div>

<script type="text/javascript" src="${ctx}/resources/js/order/insur-order-list.js"></script>


