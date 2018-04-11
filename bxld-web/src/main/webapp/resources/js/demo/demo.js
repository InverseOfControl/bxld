$(function() {
	initDatagrid();
});

/**
 * description:构建表格和加载表格数据
 * author:ym10159
 */
function initDatagrid(){
	$("#new_datagrid_bank").datagrid({
        onLoadSuccess : function(data) {
            if (data.total == 0) {
                $.messager.show({
                    title : '结果',
                    msg : '没查到符合条件的数据！',
                    showType : 'slide',
                });
            }
            ;
        },
        url : 'demo/getListData',
        striped : true,
        singleSelect : true,
        rownumbers : true,
        pagination : true,
        fitColumns : false,
        scrollbarSize : 0,
        columns : [ [ {
            field : 'id',
            title : 'id',
            width : 30,
            hidden : true
        }, {
            field : 'code',
            title : '银行编码',
            width : 220
        }, {
            field : 'name',
            title : '银行名称',
            width : 220
        }, {
            field : 'isDisabled',
            title : '是否启用',
            width : 220,
            formatter : isDisabledFieldFmt
        }, {
            field : 'operation',
            title : '操作',
            formatter : operationFieldFmt,
            width : 220
        } ] ]
    });
}

/**
 * description:字段“是否启用”转换
 * author:ym10159
 */
function isDisabledFieldFmt(value,row,index){
	if(value == 0){
		return "是";
	}else{
		return "否";
	}
}

/**
 * description:初始化表格数据的时候，添加操作按钮
 * author:ym10159
 */
function operationFieldFmt(value,row,index){
	var html = '';
	html += '<a href="javascript:void(0)" onclick="loadUpdateBankToWindow('+row.id+')">修改 &nbsp;&nbsp;</a>';
	html += '<a href="javascript:void(0)" onclick="deleteBMSBankInfo('+row.id+')">删除 &nbsp;&nbsp;</a>';
	return html;
}
