$(function() {
    initDatagrid();
    
	//密码重置
	$('#restPass').click(function(){
		var row = $('#user_list_datagrid').datagrid('getSelected');
		if (row){
	    	$.messager.confirm('确认', '您确定要重置密码吗?', function(r) {
				if (r) {
					$.ajax({ 
						url: "user/modifPasswordById",
						data: {id:row.id},
						success: function(v){
						if (v.success) {
								$.messager.show({
									title : '提示',
									msg : '重置成功,密码为"12345678"'
								});
								/* $("#user/modifPasswordById").datagrid('reload'); */
							} else {
								$.messager.show({
									title : '提示',
									msg : result.repMsg
								});
							}
						}
					});
					
				}
			});
	    } else {
	        $.messager.show({
	            title:'提示', 
	            msg:'未选中行!'
	        });
	    }
		
	});
    
});

/**
 * description:构建表格和加载表格数据
 * author:ym10159
 */
function initDatagrid(){
	debugger
    $("#user_list_datagrid").datagrid({
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
        url : 'user/listPage',
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
            field : 'loginAccount',
            title : '登陆账户',
            width : 220
        }, {
            field : 'userName',
            title : '用户姓名',
            width : 220
        }, {
            field : 'idCard',
            title : '身份证号',
            width : 220,
        }, {
            field : 'inviteCode',
            title : '邀请码',
            width : 220
        }, {
            field : 'area',
            title : '区域',
            width : 220
        }, {
            field : 'phoneNo',
            title : '手机号',
            width : 220
        } , {
            field : 'userType',
            title : '用户类型',
            width : 220, 
            formatter : function (value,row,index) {
                if ("management" == value) {
                    return "管理用户";
                }
                if ("salesman" == value) {
                    return "认证用户";
                }
            }
        } , {
            field : 'nickName',
            title : '微信昵称',
            width : 220
        } , {
            field : 'operation',
            title : '操作',
            width : 220,
            formatter : builderOperationLinks
        }  ] ]
    });
}

//查询按钮
function searchUserList() {
	var queryParams = $('#user_list_datagrid').datagrid('options').queryParams;
	queryParams.userName = $("#userName").val();
	queryParams.inviteCode = $("#inviteCode").val();
	queryParams.nickName = $("#nickName").val();
	queryParams.phoneNo = $("#phoneNo").val();
	
	$('#user_list_datagrid').datagrid('options').queryParams = queryParams;
	$("#user_list_datagrid").datagrid('reload');
}

//构建操作链接
function builderOperationLinks(value,rec){
	var id = rec.id,links;
	links = '<a href="#" onclick="editUser('+id+');">编辑</a>';
	/*links =links + '|<a href="#" onclick="modifPassword('+id+');">重置密码</a>';*/
	return links;
}

//显示新增用户对话框
function addUser(){
	$('#user_dlg').dialog({modal: true});
	$('#user_dlg').dialog('open').dialog('setTitle', '新建用户');
	$('#fm').form('clear');
	$('#loginAccount').removeAttr("disabled");
}

//显示编辑用户对话框
function editUser(id){
	$('#user_dlg').dialog({modal: true});
	$('#user_dlg').dialog('open').dialog('setTitle','修改用户');
	$('fm').form('clear');
	$('#loginAccount').attr("disabled",true);
	$.post('user/findUserById', {id : id}, function(result) {
		
		//表单赋值
		$('#fm').form('load',result.data);
				
			
	}, 'json');
}


//保存用户
function saveUser() {
	/*var nextKpiBatchStr = $.trim($('#nextKpiBatchStr').val());
	$("#nextKpiBatchStr").val(nextKpiBatchStr);*/
	$('#fm').form('submit', {
		url : 'user/saveUser',
		onSubmit : function() {
			if(!$('#fm').form('validate')){
				return false;
			}
			if($.trim($('#loginAccount').val())==''){
				$.messager.alert('温馨提示','登录工号为必填!','info');
				return false;
			}
			if(!/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test($('#idCard').val())){
				$.messager.alert('温馨提示','身份证号码格式错误!','info');
				return false;
			}
			if(!(/^(13|15|18|14|17)\d{9}$/i.test($('#phoneNo').val()))){
				$.messager.alert('温馨提示','手机号码格式错误!','info');
				return false;
			}
			$('#confirmButton').attr("onclick","");
		},
		success : function(result) {
			$('#confirmButton').attr("onclick","saveUser();");
			var result = eval('(' + result + ')');
			if (result.success) {
				$('#user_dlg').dialog('close');
				//保持当前查询条件及页数刷新数据
				$('#user_list_datagrid').datagrid('reload', {
					'userName' : $('#userName').val(),
					'inviteCode' : $('#inviteCode').val(),
					'nickName' : $('#nickName').val(),
					'phoneNo' : $('#phoneNo').val() });
			} 
			$.messager.show({title : '提示',msg : result.repMsg});
		}
	})
}

