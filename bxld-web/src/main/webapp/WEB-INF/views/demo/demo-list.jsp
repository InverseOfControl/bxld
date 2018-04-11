<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/order/insur-order-list.js"></script>
<div id="queryOrderDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 100px;">
	<form id="manualDispatch_queryForm">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td><span style="margin-left: 10px;margin-right: 10px">银行名称:</span><input type="text" class="easyui-textbox input" name="name" ></td>
				<th style="display: none;">银行编码:</th>
				<td style="display: none;"><input type="text" class="easyui-textbox input" name="code"></td>
			</tr>
			<tr>
				<td colspan="3">
				 <a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSBankInfo();" style="margin-left: 10px;margin-right: 10px">
				 	<span style="font-size: 12px">查&nbsp;询</span>
				 </a>
          		 <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addBMSBankInfo()" style="margin-left: 10px;margin-right: 10px">
          			<span style="font-size: 12px">新&nbsp;建</span>
          		 </a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div title="银行查询" style="height: 92px; padding-top: 8px;">
	<table id="order_list_dategrid" toolbar="#queryOrderDiv"></table>
</div>


