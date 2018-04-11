
Vue.config.productionTip=false;
$(function () {
    initDatagrid();
});

/**
 * description:构建表格和加载表格数据
 * author:lihuimeng
 */
function initDatagrid() {

        $("#order_list_datagrid").datagrid({
        onLoadSuccess : function(data) {
            if (data.total == 0) {
                $.messager.show({
                    title : '结果',
                    msg : '没查到符合条件的数据！',
                    showType : 'slide',
                });
            }
        },
        url : 'insurOrder/getListDate',
        striped : true,
        singleSelect : true,
        rownumbers : true,
        pagination : true,
        fitColumns : true,
        scrollbarSize : 0,
        columns : [ [ {
            field: 'id',
            title: 'id',
            width: 30,
            hidden: true
        }, {
            field: 'orderCode',
            title: '订单号',
            width: 220
        }, {
            field: 'applyTime',
            title: '提交时间',
            width: 220
        }, {
            field: 'consumerTypeText',
            title: '订单类型',
            width: 220,
        }, {
            field: 'applyUserAccount',
            title: '工号',
            width: 220
        }, {
            field: 'consumerName',
            title: '客户姓名',
            width: 220
        }, {
            field: 'consumerIdCard',
            title: '客户身份证',
            width: 220
        }, {
            field: 'plateNumber',
            title: '车牌号码',
            width: 220
        }, {
            field: 'orderStatusText',
            title: '订单状态',
            width: 220
        },  {
            field : 'operation',
            title : '操作',
            formatter : function (value,row,index) {
                return '<a href="javascript:void(0)" onclick="showOrderManageDialog(\''+row.orderCode+'\')">操作 &nbsp;&nbsp;</a>';
            },
            width : 220
        } ] ]
    });
}

/**
 * 订单查询
 * lihuimeng
 */
$("#order_form_query").click(function qualityOrderList() {

    //文本框去空格
    $("#order_queryForm").find('.easyui-textbox').each(function (index, element ) {
        var $self = $(element);
        $($self).textbox('setValue', $.trim($self.textbox('getValue')));
    });

    var queryParams = $("#order_queryForm").serializeObject();
    $("#order_list_datagrid").datagrid('load',queryParams);
});

/**
 * 打开订单办理页面
 */
function showOrderManageDialog(orderCode) {
    $('#order_manager_dialog').dialog({
        title: '订单处理',
        width: 1000,
        height: 800,
        closed: false,
        cache: false,
        href: 'insurOrderHandle/toHandlePage/'+orderCode,
        modal: true,
        resizable:true
    });
}





























