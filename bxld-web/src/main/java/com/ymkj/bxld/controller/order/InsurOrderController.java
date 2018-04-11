package com.ymkj.bxld.controller.order;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bxld.common.util.Strings;
import com.alibaba.fastjson.JSONObject;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.bxld.controller.base.BaseController;
import com.ymkj.bxld.domain.order.InsurOrderDO;
import com.ymkj.bxld.domain.order.InsurOrderVO;
import com.ymkj.bxld.domain.order.InsurPaymentInfoVO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceVO;
import com.ymkj.bxld.service.order.IInsurOrderService;
import com.ymkj.bxld.service.order.IInsurQuotedPriceService;

import com.ymkj.bxld.service.order.impl.InsurOrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * InsurOrderController
 * <p/>
 * Author:
 * Date: 2017-12-09 14:32:25
 * Mail:
 */
@Controller
@RequestMapping("/insurOrder")
public class InsurOrderController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InsurOrderServiceImpl.class);

    @Autowired
    private IInsurOrderService insurOrderService;

    /**
     * @discripeion 返回订单查询页面
     * @Author lihuimeng
     * @date 2017/12/17 12:57
     */
    @RequestMapping("/toListPage")
    public String toListPage() {
        return "order/insur-order-list";
    }

    /**
     * @discripeion 翻页查询订单列表信息
     * @Author lihuimeng
     * @date 2017/12/17 12:58
     */
    @RequestMapping("/getListDate")
    @ResponseBody
    public PageResponse<InsurOrderVO> getListDate(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "records", defaultValue = "10") int records, InsurOrderVO order) {
        order.setPageNum(page);
        order.setPageSize(records);
        return insurOrderService.getInsurOrderList(order);
    }

    /**
     * 微信端查询订单列表
     *
     * @param request
     * @param response
     * @param order
     * @return
     */
    @RequestMapping(value = "/orderApplicationList")
    @ResponseBody
    public Response<String> orderApplicationList(HttpServletRequest request, HttpServletResponse response, InsurOrderVO order) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOGGER.info("微信查询订单列表,param:<{}>, openId:<{}>", JSONObject.toJSONString(order), request.getParameter("openId"));
        String openId = request.getParameter("openId");
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        //获取查询条件
        String orderStatus = Strings.isNotEmpty(request.getParameter("orderStatus")) ? request.getParameter("orderStatus") : "";
        String consumerName = Strings.isNotEmpty(request.getParameter("consumerName")) ? request.getParameter("consumerName") : "";

        Map<String, Object> map = new HashMap<>();
        map.put("openId", openId);
        map.put("orderStatus", orderStatus);
        map.put("consumerName", consumerName);
        map.put("page", page);
        map.put("size", size);

        return insurOrderService.getOrderApplicationList(map);
    }

    /**
     * @param request
     * @discripeion 微信订单申请提交
     * @Author lihuimeng
     * @date 2017/12/17 13:01
     */
    @RequestMapping("/orderApplySubmit")
    @ResponseBody
    public Response<InsurOrderDO> orderApplySubmit(HttpServletRequest request, HttpServletResponse response, String openId) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOGGER.info("微信微信订单申请提交,insurOrderDO:<{}>,insurProductList:<{}>", JSONObject.toJSONString(request.getParameter("insurOrderDO")),
                JSONObject.toJSONString(request.getParameter("insurProductList")));

        InsurOrderDO insurOrderDO = new InsurOrderDO();
        List isurProductVOList = new ArrayList();

        if (null != request.getParameter("insurOrderDO")) {
            insurOrderDO = JSONObject.parseObject(request.getParameter("insurOrderDO"), InsurOrderDO.class);
        }

        if (null != request.getParameter("insurProductList")) {
            isurProductVOList = JSONObject.parseObject(request.getParameter("insurProductList"), List.class);
        }

        return insurOrderService.orderApplySubmit(insurOrderDO, isurProductVOList,openId);
    }

    /**
     * @param insurQuotedPriceVO
     * @discripeion 微信根据订单编号查询当前报价信息
     * @Author lihuimeng
     * @date 2017/12/19 10:26
     */
    @RequestMapping("/getOrdePatchInfo")
    @ResponseBody
    public Response<InsurQuotedPriceVO> getOrdeOfferInfo(InsurQuotedPriceVO insurQuotedPriceVO, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOGGER.info("微信根据订单编号查询当前报价信息,param:<{}>", JSONObject.toJSONString(insurQuotedPriceVO));
        return insurOrderService.getOrdeOfferInfo(insurQuotedPriceVO);
    }

    /**
     * @param insurQuotedPriceVO
     * @discripeion 微信报价提交
     * @Author lihuimeng
     * @date 2017/12/19 10:26
     */
    @RequestMapping("/ordeOfferSubmit")
    @ResponseBody
    public Response<String> ordeOfferSubmit(InsurQuotedPriceVO insurQuotedPriceVO, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOGGER.info("微信报价提交,param:<{}>", JSONObject.toJSONString(insurQuotedPriceVO));
        return insurOrderService.ordeOfferSubmit(insurQuotedPriceVO);
    }

    /**
     * @param insurOrderVO
     * @discripeion 微信补件提交
     * @Author lihuimeng
     * @date 2017/12/19 10:26
     */
    @RequestMapping("/ordePatchSubmit")
    @ResponseBody
    public Response<String> ordePatchSubmit(InsurOrderVO insurOrderVO, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOGGER.info("微信补件提交,param:<{}>", JSONObject.toJSONString(insurOrderVO));
        return insurOrderService.ordePatchSubmit(insurOrderVO);
    }

    /**
     * @param insurOrderVO
     * @discripeion 微信提交付款凭证
     * @Author lihuimeng
     * @date 2017/12/19 10:26
     */
    @RequestMapping("/ordePayVoucherSubmit")
    @ResponseBody
    public Response<String> ordePayVoucherSubmit(InsurOrderVO insurOrderVO, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOGGER.info("微信提交付款凭证,param:<{}>", JSONObject.toJSONString(insurOrderVO));
        return insurOrderService.ordePayVoucherSubmit(insurOrderVO);
    }

    /**
     * 微信端查询付款信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/paymentInformation")
    @ResponseBody
    public Response<InsurPaymentInfoVO> paymentInformation(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOGGER.info("微信端查询付款信息,orderCode:<{}>,openId:<{}>", request.getParameter("orderCode"), request.getParameter("openId"));
        String openId = request.getParameter("openId");
        String orderCode = request.getParameter("orderCode");
        String userName = Strings.isNotEmpty(request.getParameter("userName")) ? request.getParameter("userName") : "";
        String idCard = Strings.isNotEmpty(request.getParameter("idCard")) ? request.getParameter("idCard") : "";

        Map<String, Object> map = new HashMap<>();
        map.put("openId", openId);
        map.put("orderCode", orderCode);
        map.put("userName", userName);
        map.put("idCard", idCard);

        return insurOrderService.queryPaymentInformation(map);
    }

}