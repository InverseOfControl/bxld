Vue.config.productionTip = false


var vm = new Vue({
    el: '#order_handle_div',
    data: {
        orderInfo: $.parseJSON($("#orderJson_hidden").val()),
        ifAllSelect: false,
        imgUrl: 'resources/images/logo/logo_tabx.png',
        pricePhotoUrl: '',
        payQRCodeUrl: '',
    },
    methods: {
        changeImg: function (url) {
            $("#handle_imgDetails").rotate({animateTo: 0});
            return this.imgUrl = url;
        },
        deletePtoto: function (index, type) {
            if ('offer' == type) {
                vm.orderInfo.offerAttchmentList.splice(index, 1);
            } else {
                vm.orderInfo.payAttchmentList.splice(index, 1);
            }

        }
    }

})


$(function () {
    patchAllSelect();
    previewFile();//初始化文件选择事件

})

/**
 * 订单补件窗口
 * lihuimeng
 *
 */
function showPatchDialog() {
    $("#handle_orderPatch_dialog").removeClass("display_none").dialog({
        title: "补件",
        modal: true,
        width: 500,
        height: 400,
        buttons: [{
            text: '补件',
            iconCls: 'fa fa-check',
            handler: function () {
                orderPatch();
            }
        }]
    })
}

/**
 * 订单审核窗口
 * lihuimeng
 *
 */
function showCheckDialog() {
    $("#handle_orderCheck_dialog").removeClass("display_none").dialog({
        title: "订单审核",
        modal: true,
        width: 400,
        height: 300,
        buttons: [{
            text: '通过',
            iconCls: 'fa fa-check',
            handler: function () {
                orderCheck('pass');
            }
        }, {
            text: '拒绝',
            iconCls: 'fa fa-reply',
            handler: function () {
                orderCheck('reject');
            }
        }]
    })
}

/**
 * 订单报价窗口
 * lihuimeng
 *
 */
function showOfferDialog() {
    $("#handle_orderOffer_dialog").removeClass("display_none").dialog({
        title: "订单报价",
        modal: true,
        width: 1000,
        height: 600,
        // href: 'get_content.php',
        buttons: [{
            text: '保存',
            iconCls: 'fa fa-check',
            handler: function () {
                orderOffer();
                $("#handle_orderOffer_dialog").dialog("close")
            }
        }]
    })
}

/**
 * 订单付款信息窗口
 * lihuimeng
 *
 */
function showPayDialog() {

    var date = new Date();
    var ctime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
    $('#handle_signTime').datebox('setValue', ctime);

    $("#handle_orderPay_dialog").removeClass("display_none").dialog({
        title: "付款信息",
        modal: true,
        width: 1000,
        height: 600,
        buttons: [{
            text: '保存',
            iconCls: 'fa fa-check',
            handler: function () {
                savePayInfo();
                $.info("提示", "提交成功", 'info');
                $("#handle_orderPay_dialog").dialog("close");
            }
        }]
    })
}

/**
 * 订单确认付款窗口
 * lihuimeng
 *
 */
function showConfirmPayDialog() {

    $("#handle_ConfirmPay_dialog").removeClass("display_none").dialog({
        title: "付款确认",
        modal: true,
        width: 400,
        height: 600,
        buttons: [{
            text: '确认付款',
            iconCls: 'fa fa-check',
            handler: function () {
                orderConfirmPay();
            }
        }]
    })
}


/**
 * 订单审核提交
 * lihuimeng
 *
 */
function orderCheck(checkResult) {

    var remark = $("#handle_remark").val();//获取审核备注信息
    $.ajax({
        type: "POST",
        url: 'insurOrderHandle/checkOrder',
        data: {"orderCode": vm.orderInfo.orderCode, "checkResult": checkResult, "remark": remark},
        success: function (data) {
            if (true == data.success) {
                $.info("提示", data.repMsg, 'info');
                $("#handle_orderCheck_dialog").dialog("close");
            }
        }
    });
}

/**
 * 订单报价提交
 * lihuimeng
 *
 */
function orderOffer() {
    // debugger;


    var files = $("#handle_orderOffer_file")[0].files;

    var offerAttchmentList = vm.orderInfo.offerAttchmentList;
    var ids = [];
    var i;
    for (i = 0; i < offerAttchmentList.length; i++) {
        if (offerAttchmentList[i].id){
            ids += offerAttchmentList[i].id + ','
        }
    }
    var formData = new FormData($("#order_offer_form")[0]);
    formData.append("ids", ids.slice(0, -1));

    // return;
    $.ajax({
        type: "POST",
        processData: false,
        cache: false,
        contentType: false,
        url: "insurOrderHandle/offerOrder/" + vm.orderInfo.orderCode,
        data:formData,
        success: function (data) {
            if (true == data.success) {
                $.info("提示", data.repMsg, 'info');
                $("#handle_orderOffer_dialog").dialog("close");
            }
        }
    });
}

/**
 * @discripeion 付款信息保存
 * @Author lihuimeng
 * @date 2017/12/13 14:08
 *
 */
function savePayInfo() {

    var offerAttchmentList = vm.orderInfo.offerAttchmentList;
    var ids = [];
    var i;
    for (i = 0; i < offerAttchmentList.length; i++) {
        if (offerAttchmentList[i].id){
            ids += offerAttchmentList[i].id + ','
        }
    }
    var formData = new FormData($("#order_payInfo_form")[0]);
    formData.append("ids", ids.slice(0, -1));

    $.ajax({
        type: "POST",
        processData: false,
        cache: false,
        contentType: false,
        url: 'insurOrderHandle/payOrder/' + vm.orderInfo.orderCode,
        data: formData,
        success: function (data) {
            if (true == data.success) {
                $.info("提示", data.repMsg, 'info');
                $("#handle_orderPay_dialog").dialog("close");
            }
        }
    });
}

/**
 * @discripeion 图片上传预览
 * @Author lihuimeng
 * @date 2017/12/14 19:54
 *
 */
function previewFile() {

    /**
     * 报价图片上传预览
     */
    $('#handle_orderOffer_file').on('change', function (e) {
        var files = $('#handle_orderOffer_file')[0].files;

        function readAndPreview(file) {
            // 确保 `file.name` 符合我们要求的扩展名
            if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                var reader = new FileReader();
                reader.addEventListener("load", function () {
                    vm.orderInfo.offerAttchmentList.push({"attachmentUrl": reader.result});
                }, false);
                reader.readAsDataURL(file);
            }else {
                $.info("提示", "请选择正确的图片格式[jpg、png、gif]", 'warning');
                $('#handle_orderOffer_file').val("");
                return false;
            }
        }

        if (files) {
            [].forEach.call(files, readAndPreview);
        }

    })

    /**
     * 付款信息图片上传预览
     */
    $('#handle_orderPay_file').on('change', function (e) {
        // var preview = $('#preview')[0];
        var files = $('#handle_orderPay_file')[0].files;

        function readAndPreview(file) {
            // 确保 `file.name` 符合我们要求的扩展名
            if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                var reader = new FileReader();
                reader.addEventListener("load", function () {
                    vm.orderInfo.payAttchmentList.push({"attachmentUrl": reader.result});
                }, false);
                reader.readAsDataURL(file);
            }else {
                $.info("提示", "请选择正确的图片格式[jpg、png、gif]", 'warning');
                $('#handle_orderPay_file').val("");
                return false;
            }
        }

        if (files) {
            [].forEach.call(files, readAndPreview);
        }

    })
}


/**
 * @discripeion 付款确认
 * @Author lihuimeng
 * @date 2017/12/13 14:08
 *
 */
function orderConfirmPay() {
    $.ajax({
        type: "POST",
        url: 'insurOrderHandle/orderConfirmPay',
        data: {"orderCode": vm.orderInfo.orderCode},
        success: function (data) {
            if (true == data.success) {
                $.info("提示", data.repMsg, 'info');
                $("#handle_ConfirmPay_dialog").dialog("close");
            }
        }
    });
}

/**
 * @discripeion 补件提交
 * @Author lihuimeng
 * @date 2017/12/13 14:08
 *
 */
function orderPatch() {
    var remark = $("#handle_patch_remark").combobox('getValue');
    var makeupFileStr = '';
    $("input[name = 'makeupFile']:checked").each(function (index, val) {
        makeupFileStr += $(val).val() + ','
    })

    $.ajax({
        type: "POST",
        url: 'insurOrderHandle/orderPatch',
        data: {"orderCode": vm.orderInfo.orderCode, "remark": remark, "makeupFile": makeupFileStr.slice(0, -1)},
        success: function (data) {
            if (true == data.success) {
                $.info("提示", data.repMsg, 'info');
                $("#handle_orderPatch_dialog").dialog("close");
            }
        }
    });
}

/**
 * @discripeion 补件全选事件
 * @Author lihuimeng
 * @date 2017/12/13 17:34
 *
 */
function patchAllSelect() {
    $("#handle_patch_allSelect").change(function () {
        if (true == $(this).is(':checked')) {//全选
            vm.ifAllSelect = true;
        } else {//全部反选
            vm.ifAllSelect = false;
        }
    });
}

/**
 * @discripeion 图片旋转
 * @Author lihuimeng
 * @date 2017/12/14 14:57
 *
 */
var degrees = 0;

function rotateImg() {
    degrees += 45;
    $("#handle_imgDetails").rotate({animateTo: degrees})
}

/**
 * @discripeion 放大或缩小图片
 * @Author lihuimeng
 * @date 2017/12/14 15:11
 *
 */
function zoom(flag) {
    var img = $("#handle_imgDetails");
    var owith = img.width();
    var oheight = img.height();
    if ('add' == flag) {
        img.width(owith * 1.2);
        img.height(oheight * 1.2);
    }
    if ('reduce' == flag) {
        img.width(owith / 1.2);
        img.height(oheight / 1.2);
    }

}

/**
 * @discripeion 付款二维码上传预览
 * @Author lihuimeng
 * @date 2017/12/14 19:54
 *
 */
function uploadQRCodePhoto() {
    // var preview = document.querySelector('img');
    var file = document.querySelector('input[name="payQRCodePhoto"]').files[0];
    var reader = new FileReader();

    reader.addEventListener("load", function () {
        orderInfo.offerAttchmentList.push({attachmentUrl: reader.result})

        // vm.payQRCodeUrl = reader.result;
    }, false);

    if (file) {
        reader.readAsDataURL(file);
    }
    vm.ifDelete = true;
}








