var ids = [];

$(function() {
    initDatagrid();
    
});

/**
 * description:构建表格和加载表格数据
 * author:ym10159
 */
function initDatagrid(){
    $("#salesman_list_datagrid").datagrid({
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
        url : 'salesman/listPage',
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
            formatter : controllIfSelected
        },  {
            field : 'userName',
            title : '姓名',
            width : 220
        },{
            field : 'inviteCode',
            title : '工号',
            width : 220
        }, {
            field : 'idCard',
            title : '身份证号',
            width : 220,
        }, {
            field : 'nickName',
            title : '微信昵称',
            width : 220
        } , {
            field : 'phoneNo',
            title : '手机号',
            width : 220
        } ,{
            field : 'area',
            title : '所属组织',
            width : 220
        },  {
            field : 'authenticationState',
            title : '认证状态',
            width : 220, 
            formatter : function (value,row,index) {
                if ("0" == value) {
                    return "未认证";
                }
                if ("1" == value) {
                    return "已认证";
                }
                if ("2" == value) {
                    return "已注销";
                }
            }
        } ,  {
            field : 'operation',
            title : '操作',
            width : 220,
            formatter : builderOperationLinks
        }  ] ]
    });
}

//查询按钮
function searchSalesmanList() {
	var queryParams = $('#salesman_list_datagrid').datagrid('options').queryParams;
	queryParams.userName = $("#userName").val();
	queryParams.inviteCode = $("#inviteCode").val();
	queryParams.nickName = $("#nickName").val();
	queryParams.authenticationState = $("#authenticationState").combobox('getValue');;
	
	$('#salesman_list_datagrid').datagrid('options').queryParams = queryParams;
	$("#salesman_list_datagrid").datagrid('reload');
}

//构建操作链接
function builderOperationLinks(value,rec){
	var id = rec.id,links;
	if(rec.authenticationState=="0"){
		links = '<a href="#" onclick="authentication('+id+');">认证</a>';
	}else if(rec.authenticationState=="1"){
		links = '<a href="#" onclick="cancellation('+id+');"><span style="color:red;">注销</span></a>';
	}else{
		links = '';
	}
	
	return links;
}

function authenticationBatch() {
	if (ids.length == 0) {
		$.messager.alert('温馨提示','请至少选择一条数据!','info');
		return;
	}
	
	var yesorno = $.messager.confirm("认证","你选择了" + ids.length + "条数据，确认认证？",function(r){
	    if (r){
	    	$.ajax({ 
				url: "salesman/authenticationBatch?ids="+ids,
				type:"Post",  
				dataType:"json",
				success: function(result){
				if (result.success) {
						$('#salesman_list_datagrid').datagrid('reload');
    					ids = [];
    					$.messager.show({
    						title : '提示',
    						msg : result.repMsg
    					});
					} 
				}
			});
	    		    	
	    	}
		}
	);
}

function authentication(id) {
		$.messager.confirm('确认', '您确定要认证吗?', function(r) {
			if (r) {
				$.ajax({ 
					url: "salesman/authentication?id="+id,
					success: function(result){
					if (result.success) {
						$('#salesman_list_datagrid').datagrid('reload');
						}
					$.messager.show({
						title : '提示',
						msg : result.repMsg
					});
					}
				});
				
			}
		});
}

function cancellation(id) {
	$.messager.confirm('确认', '您确定要注销吗?', function(r) {
		if (r) {
			$.ajax({ 
				url: "salesman/cancellation?id="+id,
				success: function(result){
				if (result.success) {
					$('#salesman_list_datagrid').datagrid('reload');
					}
				$.messager.show({
					title : '提示',
					msg : result.repMsg
				});
				}
			});
			
		}
	});
}



