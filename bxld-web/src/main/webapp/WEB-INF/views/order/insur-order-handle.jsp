<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>

<div id="order_handle_div">
    <div id="queryOrderDiv" class="easyui-panel W100">
        <form id="manualDispatch_queryForm">
            <table style="border-collapse: separate; border-spacing: 15px;">
                <table class="table_ui W200 center_m">
                    <tr>
                        <th>客户编号:</th>
                        <td>
                            <input type="text" name="applyUserAccount" class="easyui-textbox input"
                                   value="${insurOrder.applyUserAccount}" data-options="prompt:'姓名',width:190">
                        </td>
                        <th>车主类型:</th>
                        <td>
                            <input type="text" name="consumerType" class="easyui-textbox input"
                                   value="${insurOrder.consumerType}" data-options="prompt:'工号',width:190">
                        </td>
                        <th>承保城市:</th>
                        <td>
                            <input type="text" name="contractCity" class="easyui-textbox input"
                                   value="${insurOrder.contractCity}" data-options="prompt:'微信号',width:190">
                        </td>

                    </tr>

                    <tr>
                        <th>车主姓名:</th>
                        <td>
                            <input type="text" name="consumerName" value="${insurOrder.consumerName}"
                                   class="easyui-textbox input" data-options="prompt:'车牌号',width:190">
                        </td>
                        <th>身份证号:</th>
                        <td>
                            <input type="text" name="consumerIdCard" value="${insurOrder.consumerIdCard}"
                                   class="easyui-textbox input" data-options="prompt:'车牌号',width:190">
                        </td>
                        <th>车牌号码:</th>
                        <td>
                            <input type="text" name="plateNumber" value="${insurOrder.plateNumber}"
                                   class="easyui-textbox input" data-options="prompt:'车牌号',width:190">
                        </td>

                    </tr>

                    <tr>
                        <th>车主手机号:</th>
                        <td>
                            <input type="text" name="consumerPhone" value="${insurOrder.consumerPhone}"
                                   class="easyui-textbox input" data-options="prompt:'车牌号',width:190">
                        </td>
                        <th>保单邮寄地址:</th>
                        <td>
                            <input type="text" name="receiveAddress" value="${insurOrder.receiveAddress}"
                                   class="easyui-textbox input" data-options="prompt:'车牌号',width:190">
                        </td>
                        <th>订单状态:</th>
                        <td>
                            <select type="text" class="easyui-combobox input" name="orderStatus"
                                    value="${insurOrder.orderStatus}" data-options="width:190">
                                <option value="SHZ-DSP">审核中-待审批</option>
                                <option value="SHZ-DBJ">审核中-待补件</option>
                                <option value="SHZ-YBJ">审核中-已补件</option>
                                <option value="SHZ-YSH">已审核-已审核</option>
                                <option value="YSH-YDC">已审核-已导出</option>
                                <option value="YSH-YBJ">已审核-已报价</option>
                                <option value="DFK-DFK">待付款-待付款</option>
                                <option value="DFK-YFK">待付款-已付款</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <%--订单处理按钮--%>
                <table>
                    <tr>
                        <td>
                            <a class="easyui-linkbutton" onclick="showPatchDialog()">补件</a>
                        </td>
                        <td>
                            <a class="easyui-linkbutton" onclick="showCheckDialog()">审核</a>
                        </td>
                        <td>
                            <a class="easyui-linkbutton" onclick="finishExternalCreditDialog('${loanNo}')">导出</a>
                        </td>
                        <td>
                            <a class="easyui-linkbutton" onclick="showOfferDialog()">订单报价</a>
                        </td>
                        <td>
                            <a class="easyui-linkbutton" onclick="showPayDialog()">付款信息</a>
                        </td>
                        <td>
                            <a class="easyui-linkbutton" onclick="showConfirmPayDialog()">付款确认</a>
                        </td>
                    </tr>
                </table>

            </table>
        </form>
    </div>

    <div class="easyui-panel" style="height:100px; margin: 10px;">
        <table class="table_ui W200 ">
            <tr>
                <td v-for="item in orderInfo.idAttchmentList">
                    <img v-on:click="changeImg(item.attachmentUrl)" v-bind:src="item.attachmentUrl" width="110"
                         height="64">
                </td>
            </tr>
        </table>
    </div>
    <div style="margin: 10px;">
        <table>
            <tr>
                <td>
                    <a href="javascript:void(0)" onclick="rotateImg()">旋转 &nbsp;&nbsp;</a>
                </td>
                <td>
                    <a href="javascript:void(0)" onclick="zoom('add')">放大 &nbsp;&nbsp;</a>
                </td>
                <td>
                    <a href="javascript:void(0)" onclick="zoom('reduce')">缩小 &nbsp;&nbsp;</a>
                </td>
            </tr>
        </table>
    </div>
    <div style="text-align: center; height:450px; border:2px solid #1ea3f2; margin: 10px; padding:0 ; top: 50px; bottom: 0px;">
        <img id="handle_imgDetails" v-bind:src="imgUrl">
    </div>

    <%--订单补件弹窗--%>
    <div id="handle_orderPatch_dialog" class="padding_20 display_none">
        <table class="table_ui W100">
            <tr>
                <td>
                    补件信息：
                </td>
            </tr>
            <tr>
                <td>
                    <input type="checkbox" name="makeupFile" v-bind:checked="ifAllSelect" value="车主身份证正面">车主身份证正面
                </td>
                <td>
                    <input type="checkbox" name="makeupFile" v-bind:checked="ifAllSelect" value="车主身份证反面">车主身份证反面
                </td>
            </tr>

            <tr>
                <td>
                    <input type="checkbox" name="makeupFile" v-bind:checked="ifAllSelect" value="行驶证第一页">行驶证第一页
                </td>
                <td>
                    <input type="checkbox" name="makeupFile" v-bind:checked="ifAllSelect" value="行驶证第二页">行驶证第二页
                </td>
            </tr>

            <tr>
                <td>
                    <input type="checkbox" name="makeupFile" v-bind:checked="ifAllSelect" value="企业营业执照">企业营业执照
                </td>
                <td>
                    <input type="checkbox" id="handle_patch_allSelect" name="all" value="全选">全选
                </td>
            </tr>

            <tr>
                <th>补件备注</th>
                <td>
                    <select type="text" id="handle_patch_remark" class="easyui-combobox input" name="remark">
                        <option value="材料缺失">材料缺失</option>
                        <option value="照片不清楚">照片不清楚</option>
                    </select>
                </td>
            </tr>

        </table>
    </div>

    <%--订单审核弹窗--%>
    <div id="handle_orderCheck_dialog" class="padding_20 display_none">
        <table class="table_ui W100">
            <tr>
                <td colspan="2">
                    <span>您审核通过表示提交的材料没有问题，可以继续后面的流程。请给出您的意见？</span>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                    <textarea id="handle_remark" maxlength="200" class="textarea" name="remark"></textarea>
                </td>
            </tr>
        </table>

    </div>

    <%--订单报价--%>
    <div id="handle_orderOffer_dialog" class="padding_20 display_none">

        <form id="order_offer_form" method="post" enctype="multipart/form-data">
            <table style="border-collapse: separate; border-spacing: 15px;">
                <tr>
                    <td>
                        <img src="resources/images/logo/logo_tabx.png" width="80" height="80">
                    </td>
                    <td>
                        <img src="resources/images/logo/logo_tpbx.png" width="80" height="80">
                    </td>
                    <td>
                        <img src="resources/images/logo/logo_zgrb.png" width="80" height="80">
                    </td>
                    <td>
                        <img src="resources/images/logo/logo_zgrs.png" width="80" height="80">
                    </td>
                    <td>
                        <img src="resources/images/logo/logo_zgpa.png" width="80" height="80">
                    </td>
                </tr>

                <tr>
                    <td>
                        <input type="radio" name="companyCode" value="TABX">天安保险
                    </td>
                    <td>
                        <input type="radio" name="companyCode" value="TPBX">太平保险
                    </td>
                    <td>
                        <input type="radio" name="companyCode" value="ZGRB">中国人保
                    </td>
                    <td>
                        <input type="radio" name="companyCode" value="ZGRS">中国人寿
                    </td>
                    <td>
                        <input type="radio" name="companyCode" value="ZGPA">中国平安
                    </td>
                </tr>
                <tr>
                    <th>订单报价</th>
                    <td colspan="4">
                        <input placeholder="订单报价" class="easyui-numberbox input" id="orderOffer_price"
                               name="orderQuotedPrice">
                    </td>
                </tr>
                <tr>
                    <th>推荐指数</th>
                    <td>
                        <input type="radio" name="recommendGrade" value="1">1星
                    </td>
                    <td>
                        <input type="radio" name="recommendGrade" value="2">2星
                    </td>
                    <td>
                        <input type="radio" name="recommendGrade" value="3">3星
                    </td>
                    <td>
                        <input type="radio" name="recommendGrade" value="4">4星
                    </td>
                    <td>
                        <input type="radio" name="recommendGrade" value="5">5星
                    </td>
                    <th></th>
                </tr>

                <tr>
                <tr>
                    <th>交强险期限</th>
                    <td colspan="2">
                        <input type="text" name="ctaliStartTime" class="easyui-datebox input">
                    </td>
                    <th>~</th>
                    <td colspan="2">
                        <input type="text" name="ctaliEndTime" class="easyui-datebox input">
                    </td>
                </tr>

                <th>商业险期限</th>
                <td colspan="2">
                    <input type="text" name="businessStartTime" class="easyui-datebox input" width="80">
                </td>
                <th>~</th>
                <td colspan="2">
                    <input type="text" name="businessEndTime" class="easyui-datebox input">
                </td>
                </tr>
                <tr>
                    <th>上传报价图片</th>
                    <td>
                        选择文件：<input type="file" name="uploadFileList" id="handle_orderOffer_file" multiple>
                    </td>
                </tr>

            </table>
            <div style="display: inline-block; text-align: center;" v-for="(item,index) in orderInfo.offerAttchmentList">
                <img v-bind:src="item.attachmentUrl" width="110" height="80"><br>
                <a href="javascript:void(0)" v-on:click="deletePtoto(index, 'offer')">删除</a>
            </div>

        </form>
    </div>

    <%--订单付款信息--%>
    <div id="handle_orderPay_dialog" class="padding_20 display_none">
        <form id="order_payInfo_form" method="post" enctype="multipart/form-data"
              <%--action="${ctx}/insurOrderHandle/payOrder/${insurOrder.orderCode}"--%>>
            <table class="table_ui W100">
                <tr>
                    <td colspan="2">
                        在这里，您可以上传付款信息，固定付款信息根据选择的公司自动提供
                    </td>
                </tr>
                <tr>
                    <th>收款账户名称</th>
                    <td>
                        ${insurOrder.payCardName}
                    </td>
                </tr>
                <tr>
                    <th>收款账户</th>
                    <td>
                        ${insurOrder.cardNumber}
                    </td>
                </tr>
                <tr>
                    <th>签约日期1</th>
                    <td>
                        <input type="text" id="handle_signTime" name="signTime" class="easyui-datebox input">
                    </td>
                </tr>

                <tr>

                    <th>上传二维码</th>
                    <td>
                        选择文件：
                        <input type="file" name="uploadFileList" id="handle_orderPay_file" multiple>
                    </td>
                </tr>
            </table>

            <div style="display: inline-block; text-align: center;" v-for="(item,index) in orderInfo.payAttchmentList">
                <img v-bind:src="item.attachmentUrl" width="110" height="80"><br>
                <a href="javascript:void(0)" v-on:click="deletePtoto(index, 'pay')">删除</a>
            </div>

        </form>
    </div>
    <%--订单付款确认--%>
    <div id="handle_ConfirmPay_dialog" class="padding_20 display_none">
        <table class="table_ui W100">
            <tr>
                <th>订单编号:</th>
                <td>
                    <%--type="text" name="orderCode" value="${insurOrder.orderCode}" class="easyui-textbox input">--%>
                    ${insurOrder.orderCode}
                </td>
            </tr>
            <tr>
                <th>客户姓名:</th>
                <td>
                    ${insurOrder.consumerName}
                </td>
            </tr>
            <tr>
                <th>订单金额:</th>
                <td>
                    ${insurOrder.orderQuotedPrice}
                </td>
            </tr>
            <tr>
                <th>付款凭证:</th>

                <td v-for="item in orderInfo.payBackAttchmentList">
                    <img v-on:click="changeImg(item.attachmentUrl)" v-bind:src="item.attachmentUrl" width="110"
                         height="64">
                </td>
            </tr>


        </table>
    </div>

    <input type="hidden" id="orderJson_hidden" value='${insurOrderJson}'>
</div>
<script type="text/javascript" src="${ctx}/resources/js/order/insur-order-handle.js"></script>



