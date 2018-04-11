package com.ymkj.bxld.service.order;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bxld.domain.attachment.AttchmentVO;
import com.ymkj.bxld.domain.order.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lihhuimeng
 * @description:
 * @date: 2017/12/9 15:16
 */
public interface IInsurOrderService {

    /**
     * lihuimeng
     *
     * @param order
     * @return 查询订单列表
     */
    PageResponse<InsurOrderVO> getInsurOrderList(InsurOrderVO order);

    /**
     * 根据单号查询订单
     *
     * @param orderCode
     * @return InsurOrderVO对象
     * lihuimeng
     */
    InsurOrderVO getOrder(String orderCode);

    /**
     * lihuimeng
     *
     * @param order 订单更新
     */
    long updateOrder(InsurOrderVO order);

    /**
     * lihuimeng
     *
     * @param orderCode   (订单编号)
     * @param checkResult (审核结果)
     *                    订单审核
     */
    Response<String> orderCheck(String orderCode, String checkResult, String remark);

    /**
     * lihuimeng
     *
     * @param orderCode (订单编号)
     * @param priceDO   (报价信息)
     *                  订单审核
     */
    Response<String> orderOffer(String orderCode, InsurQuotedPriceDO priceDO, AttchmentVO attchmentVO, String ids);

    /**
     * lihuimeng
     *
     * @param insurOrderVO
     * @param attchmentVO
     *  订单审核
     */
    Response<String> orderPay(InsurOrderVO insurOrderVO, AttchmentVO attchmentVO,String ids);

    /**
     * @param orderCode
     * @discripeion 订单付款确认
     * @Author lihuimeng
     * @date 2017/12/13 15:52
     */
    Response<String> orderConfirmPay(String orderCode);

    /**
     * @param orderCode
     * @discripeion 订单处理补件操作提交
     * @Author lihuimeng
     * @date 2017/12/13 18:18
     * @param orderCode
     * @param remark (补件原因备注)
     * @param makeupFile (补件附件信息)
     *
     */
    Response<String> orderPatch(String orderCode,String remark, String makeupFile);

    /**
     * 微信端查询订单列表
     * @param map
     * @return
     */
	Response<String> getOrderApplicationList(Map<String, Object> map);


    /**
     * @discripeion 订单插入保存
     * @Author lihuimeng
     * @date 2017/12/17 14:40
     * @param
     *
     */
    long saveInsurOrder(InsurOrderDO insurOrderDO);

    /**
     * @discripeion 订单申请提交
     * @Author lihuimeng
     * @date 2017/12/17 14:15
     * @param orderDO
     * @param isurProductVOList (产品对象集合)
     *
     */
    Response<InsurOrderDO> orderApplySubmit(InsurOrderDO orderDO, List isurProductVOList, String openId);

    /**
     * @discripeion 根据订单号码和业务类型获取附件信息
     * @Author lihuimeng
     * @date 2017/12/18 16:11
     * @param businessType(业务类型)
     * @param orderCode(订单编号)
     *
     */
    List<AttchmentVO> getAttchmentByCodeAndBusiness(String orderCode, String businessType);

    /**
     * @discripeion 订单申请提交
     * @Author lihuimeng
     * @date 2017/12/17 14:15
     * @param priceVO
     *
     */
    Response<InsurQuotedPriceVO> getOrdeOfferInfo(InsurQuotedPriceVO priceVO);

    /**
     * @discripeion 微信报价提交
     * @Author lihuimeng
     * @date 2017/12/19 10:26
     * @param priceVO
     */
    Response<String> ordeOfferSubmit(InsurQuotedPriceVO priceVO);

    /**
     * @discripeion 微信补件提交
     * @Author lihuimeng
     * @date 2017/12/19 10:26
     * @param insurOrderVO
     */
    Response<String> ordePatchSubmit(InsurOrderVO insurOrderVO);

    /**
     * @discripeion 微信补件提交
     * @Author lihuimeng
     * @date 2017/12/19 10:26
     * @param insurOrderVO
     */
    Response<String> ordePayVoucherSubmit(InsurOrderVO insurOrderVO);

    /**
     * 微信端查询个人信息
     * @param map
     * @return
     */
	Response<InsurPaymentInfoVO> queryPaymentInformation(Map<String, Object> map);


}
