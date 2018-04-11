<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>

<div id="addBMSBankInfo" title="新增银行信息" class="easyui-window"
	class="easyui-window" closed="true"
	style="width: 800px; height: 400px;">
	<div style="padding: 10px 10px 10px 10px">
		<form id="addBMSBankInfoForm" name="addBMSBankInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W80 center_m">
				<tr>
					<th>银行编码:</th>
					<td><input type="text" class="easyui-textbox input"  id="addCode"
						name="code" width="100px"></td>
				</tr>
				<tr>
					<th>银行名称:</th>
					<td><input type="text" class="easyui-textbox input" id="addName"
						name="name" width="100px"></td>
				</tr>
				<tr>
				<th>是否启用:</th>
					<td> <input type="radio" name="redio" value="0" id="0"/>是
						 <input type="radio" name="redio" value="1" id="1"/>否
					</td>
 				</tr>
			</table>
		</form>
		<br />
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveBMSBankInfo();">保存</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="closeForm('add');">取消</a>
		</div>
	</div>
</div>
