package com.ymkj.bxld.controller.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bxld.domain.attachment.AttchmentVO;
import com.ymkj.bxld.domain.order.InsurOrderDO;
import com.ymkj.bxld.domain.order.InsurOrderVO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceDO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceVO;
import com.ymkj.bxld.service.order.IInsurOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lihhuimeng
 * @description: 订单处理controller
 * @date: 2017/12/10 14:53
 */

@Controller
@RequestMapping("/insurOrderHandle")
public class InsurOrderHandleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InsurOrderHandleController.class);

    @Autowired
    private IInsurOrderService orderService;

    /**
     * 返回订单处理页面
     *
     * @return
     * @author lihuimeng
     */
    @RequestMapping("/toHandlePage/{orderCode}")
    public String toHandlePage(HttpServletRequest request, @PathVariable String orderCode, Model model) {
        LOGGER.info("返回订单处理页面,订单号:<{}>", orderCode);
        InsurOrderVO insurOrder = orderService.getOrder(orderCode);
        model.addAttribute("insurOrder", insurOrder);
        model.addAttribute("insurOrderJson", JSONObject.toJSONString(insurOrder));
        return "order/insur-order-handle";
    }

    /**
     * 订单处理补件提交（后台）
     *
     * @param orderCode
     * @param remark
     * @param makeupFile (补件信息)
     * @author lihuimeng
     */
    @RequestMapping("/orderPatch")
    @ResponseBody
    public Response<String> orderPatch(String orderCode, String remark, String makeupFile) {
        LOGGER.info("订单处理——订单补件提交操作,订单号:<{}>", orderCode);
        return orderService.orderPatch(orderCode, remark, makeupFile);
    }

    /**
     * 订单审核操作
     *
     * @param orderCode
     * @param remark
     * @author lihuimeng
     */
    @RequestMapping("/checkOrder")
    @ResponseBody
    public Response<String> updateOrder(String orderCode, String checkResult, String remark) {
        LOGGER.info("订单处理——订单审核保存操作,订单号:<{}>", orderCode);
        return orderService.orderCheck(orderCode, checkResult, remark);
    }

    /**
     * @param quotedPriceDO (报价信息)
     * @param attchmentVO   (附件信息)
     * @discripeion 报价信息提交
     * @Author lihuimeng
     * @date 2017/12/13 14:01
     */
    @RequestMapping("/offerOrder/{orderCode}")
    @ResponseBody
    public Response<String> offerOrder(@PathVariable String orderCode, InsurQuotedPriceDO quotedPriceDO, AttchmentVO attchmentVO, String ids) {
        LOGGER.info("订单处理——订单报价保存操作入参,quotedPriceDO:<{}>,attchmentVO:<{}>", JSON.toJSONString(quotedPriceDO), JSON.toJSONString(attchmentVO));
        return orderService.orderOffer(orderCode, quotedPriceDO, attchmentVO, ids);
    }

    /**
     * @param orderCode
     * @param attchmentVO
     * @discripeion 付款信息提交
     * @Author lihuimeng
     * @date 2017/12/13 14:01
     */
    @RequestMapping("/payOrder/{orderCode}")
    @ResponseBody
    public Response<String> payOrder(@PathVariable String orderCode, InsurOrderVO insurOrderVO, AttchmentVO attchmentVO, String ids) {
        LOGGER.info("订单处理——订单付款信息保存操作入参,insurOrderVO:<{}>,attchmentVO:<{}>", JSON.toJSONString(insurOrderVO), JSON.toJSONString(attchmentVO));
        return orderService.orderPay(insurOrderVO, attchmentVO,ids);
    }

    /**
     * @param orderCode
     * @discripeion 付款确认提交
     * @Author lihuimeng
     * @date 2017/12/13 14:01
     */
    @RequestMapping("/orderConfirmPay")
    @ResponseBody
    public Response<String> orderConfirmPay(String orderCode) {
        LOGGER.info("订单处理——订单付款确认操作,订单号:<{}>", orderCode);
        return orderService.orderConfirmPay(orderCode);
    }

}
