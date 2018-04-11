package com.ymkj.bxld.service.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.bxld.common.constants.Constants;
import com.ymkj.bxld.common.constants.InsurOrderEnum;
import com.ymkj.bxld.common.exception.BizErrorCode;
import com.ymkj.bxld.common.util.DateUtil;
import com.ymkj.bxld.common.util.EnumerationUtils;
import com.ymkj.bxld.dao.common.ICommonDao;
import com.ymkj.bxld.dao.order.IInsurOrderDao;
import com.ymkj.bxld.dao.order.InsurPaymentInfoDao;
import com.ymkj.bxld.domain.attachment.AttchmentVO;
import com.ymkj.bxld.domain.order.InsurOrderDO;
import com.ymkj.bxld.domain.order.InsurOrderHistory;
import com.ymkj.bxld.domain.order.InsurOrderVO;
import com.ymkj.bxld.domain.order.InsurPaymentInfoVO;
import com.ymkj.bxld.domain.order.InsurProOrderDO;
import com.ymkj.bxld.domain.order.InsurProductVO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceDO;
import com.ymkj.bxld.domain.order.InsurQuotedPriceVO;
import com.ymkj.bxld.domain.user.InsurUserInfo;
import com.ymkj.bxld.service.attachment.IAttachmentService;
import com.ymkj.bxld.service.order.IInsurOrderHistoryService;
import com.ymkj.bxld.service.order.IInsurOrderService;
import com.ymkj.bxld.service.order.IInsurProOrderService;
import com.ymkj.bxld.service.order.IInsurQuotedPriceService;
import com.ymkj.bxld.service.user.IUserService;

/**
 * @Author: lihhuimeng
 * @description:
 * @date: 2017/12/9 15:17
 */

@Service
public class InsurOrderServiceImpl implements IInsurOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsurOrderServiceImpl.class);

    @Autowired
    private IInsurOrderDao orderDao;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IInsurOrderDao insurOrderDao;

    @Autowired
    private InsurPaymentInfoDao insurPaymentInfoDao;

    @Autowired
    private IInsurOrderHistoryService orderHistoryService;

    @Autowired
    private IInsurQuotedPriceService quotedPriceService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ICommonDao commonDao;

    @Autowired
    private IInsurProOrderService insurProOrderService;

    @Autowired
    private IAttachmentService attachmentService;

    @Override
    public PageResponse<InsurOrderVO> getInsurOrderList(InsurOrderVO orderVO) {
        PageResponse<InsurOrderVO> response = new PageResponse<InsurOrderVO>();

        PageParam pageParam = new PageParam(orderVO.getPageNum(), orderVO.getPageSize());
        Map<String, Object> paramMap = null;

        //实体对象转成请求map
        try {
            paramMap = BeanKit.bean2map(orderVO);
        } catch (Exception e) {
        }
        PageBean<InsurOrderVO> pageBean = insurOrderDao.listPage(pageParam, paramMap);
        List<InsurOrderVO> pageList = pageBean.getRecords();
        response.setRecords(pageList);
        //复制分页信息到响应对象
        BeanUtils.copyProperties(pageBean, response, "records");
        return response;

    }

    @Override
    public InsurOrderVO getOrder(String orderCode) {
        LOGGER.info("根据订单编号查询订单信息,订单编号：<{}>", orderCode);
        InsurOrderVO orderVo = new InsurOrderVO();
        orderVo.setOrderCode(orderCode);
        InsurOrderVO order = insurOrderDao.findOne(orderVo);
        if (null != order) {
            //查询证件附件信息
            List<AttchmentVO> idAttchmentList = getAttchmentByCodeAndBusiness(orderCode, Constants.picLink.APPLY_LINK.getCode());
            order.setIdAttchmentList(idAttchmentList);

            //查询报价附件信息
            List<AttchmentVO> offerAttchmentList = getAttchmentByCodeAndBusiness(orderCode, Constants.picLink.OFFER_LINK.getCode());
            order.setOfferAttchmentList(offerAttchmentList);

            //查询付款附件信息
            List<AttchmentVO> payAttchmentList = getAttchmentByCodeAndBusiness(orderCode, Constants.picLink.PAY_LINK.getCode());
            order.setPayAttchmentList(payAttchmentList);

            //查询付款附件信息
            List<AttchmentVO> payBackAttchmentList = getAttchmentByCodeAndBusiness(orderCode, Constants.picLink.PAY_BACK.getCode());
            order.setPayBackAttchmentList(payBackAttchmentList);

        }
        return order;
    }

    @Override
    public long updateOrder(InsurOrderVO order) {

        LOGGER.info("******更新订单信息:param:<{}>", JSON.toJSONString(order));
        long updateCount = 0;
        try {
            updateCount = insurOrderDao.updateOrder(order);
            LOGGER.info("******更新订单信息:resultCount：<{}>", updateCount);
        } catch (Exception e) {
            LOGGER.error("订单信息更新失败,订单编号：<{}>,exception:", order.getOrderCode(), e);
            return updateCount;
        }

        return updateCount;
    }

    @Override
    public Response<String> orderCheck(String orderCode, String checkResult, String orderHistoryRemark) {
        String auditingStatus = null;
        Response<String> response = new Response<>();

        if (StringUtils.isNotEmpty(checkResult)) {
            if (InsurOrderEnum.CheckResult.PASS.getCode().equals(checkResult)) {//如果点击的是通过，更新订单状态和操作结果为已审核
                auditingStatus = InsurOrderEnum.OrderStatus.SHZ_YSH.getCode();
            } else {//如果点击的是拒绝，那么更新订单状态和操作结果为已关闭
                auditingStatus = InsurOrderEnum.OrderStatus.YCX_YGB.getCode();
            }
        }

        //创建订单信息更新参数
        InsurOrderVO orderParam = new InsurOrderVO();
        orderParam.setOrderCode(orderCode);
        orderParam.setOrderStatus(auditingStatus);
        orderParam.setRemark(orderHistoryRemark);

        //创建订单信息操作历史插入参数
        InsurOrderHistory orderHistoryParam = getInsertHistoryParam(orderCode, auditingStatus, orderHistoryRemark);//创建更新订单历史参数

        Boolean isSuccess = operateOrder(orderParam, orderHistoryParam);//更新订单信息，插入订单操作历史
        if (isSuccess) {
            LOGGER.info("订单审核提交成功, 订单编号：<{}>", orderCode);
            response.setRepMsg("提交成功!!");
        } else {
            LOGGER.info("订单审核提交失败, 订单编号：<{}>", orderCode);
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("审核提交失败!!");
            return response;
        }

        return response;
    }


    @Override
    public Response<String> orderOffer(String orderCode, InsurQuotedPriceDO priceDO, AttchmentVO attchmentVO, String ids) {

        List<MultipartFile> uploadFileList = attchmentVO.getUploadFileList();
        if (uploadFileList.size() == 1 && uploadFileList.get(0).getSize() == 0) {//如果没有选择图片
            attchmentVO.setUploadFileList(null);
        }

        String auditingStatus = InsurOrderEnum.OrderStatus.YSH_YBJ.getCode();
        Response<String> response = new Response<>();

        //创建订单信息更新参数
        InsurOrderVO orderParam = new InsurOrderVO();
        orderParam.setOrderCode(orderCode);
        orderParam.setOrderStatus(auditingStatus);

        //创建更新订单历史参数
        InsurOrderHistory orderHistoryParam = getInsertHistoryParam(orderCode, auditingStatus, "");

        //更新订单信息，插入订单操作历史
        LOGGER.info("订单处理——订单报价提交——更新订单信息和插入操作历史信息, orderParam：<{}>, orderHistoryParam：<{}>", JSON.toJSONString(orderParam), JSON.toJSONString(orderHistoryParam));
        Boolean isSuccess = operateOrder(orderParam, orderHistoryParam);
        if (!isSuccess) {
            LOGGER.info("订单处理——订单报价提交——更新订单信息和插入操作历史信息失败, 订单编号：<{}>", orderCode);
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("提交失败!!");
            return response;
        }

        //更新历史报价信息为无效并插入新的报价信息

        LOGGER.info("订单处理——订单报价提交——更新历史报价信息为无效并插入新的报价信息, param：<{}>", JSON.toJSONString(priceDO));
        long insertCount = updateAndSaveInsurPrice(priceDO);
        if (insertCount > 0) {//插入成功更新历史附件信息
            LOGGER.info("订单处理——订单报价提交——更新历史报价信息为无效并插入新的报价信息成功,insertCount:<{}>", insertCount);
            attchmentVO.setBussinessType(Constants.picLink.OFFER_LINK.getCode());
            attchmentVO.setStatus(Constants.DATA_UNVALID);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("attchmentVO", attchmentVO);
            if (StringUtils.isNotEmpty(ids)) {
                String[] idsArr = ids.split(",");
                paramMap.put("ids", idsArr);
            }

            //更新历史报价附件信息为“禁用”
            LOGGER.info("订单处理——订单报价提交——更新历史附件信息, param：<{}>", JSON.toJSONString(paramMap));
            long updateCount = attachmentService.updateHistoryFileInfo(paramMap);

            if (updateCount >= 0) {//更新成功插入报价附件
                LOGGER.info("订单处理——订单报价提交——更新历史附件信息成功, orderCode：<{}> ,updateCount：<{}>", orderCode, updateCount);
                if (null != attchmentVO.getUploadFileList()) {//附件信息不为空
                    attchmentVO.setBussinessType(Constants.picLink.OFFER_LINK.getCode());
                    attchmentVO.setStatus(Constants.DATA_VALID);
                    Response<Object> upload = attachmentService.upload(attchmentVO);
                    if ("000000".equals(upload.getRepCode())) {//如果上传附件成功
                        LOGGER.info("订单处理——订单报价提交——保存附件成功！！，result：<{}>", JSON.toJSONString(upload.getData()));
                        response.setRepMsg("提交成功!!");
                    } else {
                        LOGGER.info("订单处理——订单报价提交——保存附件失败！！，orderCode：<{}>", orderCode);
                        response.setRepMsg("提交失败!!");
                        return response;
                    }
                }
            } else {
                LOGGER.info("订单处理——订单报价提交——更新历史附件信息失败, orderCode：<{}>", orderCode);
                response.setRepCode(BizErrorCode.EOERR.getErrorCode());
                response.setRepMsg("提交失败!!");
                return response;
            }

        } else {
            LOGGER.info("订单处理——订单报价提交——更新历史报价信息为无效并插入新的报价信息失败, 订单编号：<{}>", orderCode);
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("提交失败!!");
            return response;
        }
        return response;
    }

    @Override
    public Response<String> orderPay(InsurOrderVO orderParam, AttchmentVO attchmentVO, String ids) {

        String auditingStatus = InsurOrderEnum.OrderStatus.DFK_YQR.getCode();
        Response<String> response = new Response<>();

        //创建订单信息更新参数
        orderParam.setOrderStatus(auditingStatus);
        //创建订单信息操作历史插入参数
        InsurOrderHistory orderHistoryParam = getInsertHistoryParam(orderParam.getOrderCode(), auditingStatus, "");//创建更新订单历史参数
        LOGGER.info("订单处理——订单付款信息提交——更新订单信息和插入操作历史信息, orderParam：<{}>, orderHistoryParam：<{}>", JSON.toJSONString(orderParam), JSON.toJSONString(orderHistoryParam));
        Boolean isSuccess = operateOrder(orderParam, orderHistoryParam);//更新订单信息，插入订单操作历史*/
        if (!isSuccess) {
            LOGGER.info("订单处理——订单付款信息提交——更新订单信息和插入操作历史信息失败, 订单编号：<{}>", orderParam.getOrderCode());
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("提交失败!!");
            return response;
        }

        //创建更新附件历史信息参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        attchmentVO.setBussinessType(Constants.picLink.PAY_LINK.getCode());
        attchmentVO.setStatus(Constants.DATA_UNVALID);
        paramMap.put("attchmentVO", attchmentVO);
        if (StringUtils.isNotEmpty(ids)) {
            String[] idsArr = ids.split(",");
            paramMap.put("ids", idsArr);
        }

        LOGGER.info("订单处理——订单付款信息提交——删除历史付款附件信息, param：<{}>", JSON.toJSONString(paramMap));
        long updateCount = attachmentService.updateHistoryFileInfo(paramMap);//更新历史付款附件信息为“禁用”

        if (updateCount >= 0) {
            LOGGER.info("订单处理——订单付款信息提交——删除历史付款附件信息成功, updateCount：<{}>", updateCount);
            List<MultipartFile> uploadFileList = attchmentVO.getUploadFileList();
            if (null != uploadFileList) {//如果附件信息不为空
                if (uploadFileList.size() == 1 && uploadFileList.get(0).getSize() == 0) {
                    LOGGER.info("订单处理——订单付款信息提交——保存附件信息为空,订单编号：<{}>", attchmentVO.getOrderCode());
                } else {
                    attchmentVO.setBussinessType(Constants.picLink.PAY_LINK.getCode());
                    attchmentVO.setStatus(Constants.DATA_VALID);
                    LOGGER.info("订单处理——订单付款信息提交——保存附件信息，param:<{}>", JSON.toJSONString(attchmentVO));
                    Response<Object> upload = attachmentService.upload(attchmentVO);//上传附件
                    if ("000000".equals(upload.getRepCode())) {//如果上传附件成功
                        LOGGER.info("附件插入成功！！，附件信息：<{}>", JSON.toJSONString(upload.getData()));
                        response.setRepMsg("提交成功!!");
                    } else {
                        LOGGER.info("附件插入失败！！，订单编号：<{}>", orderParam.getOrderCode());
                        response.setRepMsg("提交失败!!");
                        return response;
                    }
                }
            }
        } else {
            LOGGER.info("订单处理——订单付款信息提交——删除历史付款附件信息失败, orderCode：<{}>", attchmentVO.getOrderCode());
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("提交失败!!");
            return response;
        }

        response.setRepMsg("提交成功!!");
        return response;
    }

    @Override
    public Response<String> orderConfirmPay(String orderCode) {
        String auditingStatus = InsurOrderEnum.OrderStatus.YFK_YFK.getCode();
        Response<String> response = new Response<>();

        //创建订单信息更新参数
        InsurOrderVO orderParam = new InsurOrderVO();
        orderParam.setOrderCode(orderCode);
        orderParam.setOrderStatus(auditingStatus);

        //创建订单信息操作历史插入参数
        InsurOrderHistory orderHistoryParam = getInsertHistoryParam(orderCode, auditingStatus, "");//创建更新订单历史参数

        Boolean isSuccess = operateOrder(orderParam, orderHistoryParam);//更新订单信息，插入订单操作历史*/
        if (!isSuccess) {
            LOGGER.info("订单处理——订单付款确认操作失败, 订单编号：<{}>", orderCode);
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("提交失败!!");
            return response;
        }

        response.setRepMsg("提交成功!!");
        return response;
    }

    @Override
    public Response<String> orderPatch(String orderCode, String remark, String makeupFile) {
        //设置订单状态为“待补件”
        String auditingStatus = InsurOrderEnum.OrderStatus.SHZ_DBJ.getCode();
        Response<String> response = new Response<>();

        //添加订单信息更新参数
        InsurOrderVO orderParam = new InsurOrderVO();
        orderParam.setOrderCode(orderCode);
        orderParam.setOrderStatus(auditingStatus);
        orderParam.setRemark(remark);
        orderParam.setMakeupFile(makeupFile);

        //创建订单信息操作历史插入参数
        InsurOrderHistory orderHistoryParam = getInsertHistoryParam(orderCode, auditingStatus, remark);//创建更新订单历史参数

        Boolean isSuccess = operateOrder(orderParam, orderHistoryParam);//更新订单信息，插入订单操作历史*/
        if (!isSuccess) {
            LOGGER.info("订单处理——订单请补件操作失败, 订单编号：<{}>", orderCode);
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("提交失败!!");
            return response;
        }

        response.setRepMsg("提交成功!!");
        return response;
    }

    @Override
    public Response<InsurOrderDO> orderApplySubmit(InsurOrderDO orderDO, List insurProductVOList, String openId) {

        String orderCode = commonDao.createOrderNo();//生成唯一订单号
        LOGGER.info("订单申请提交操作,订单号：<{}>", orderCode);
        Response<InsurOrderDO> response = new Response<InsurOrderDO>();
        orderDO.setOrderCode(orderCode);
        orderDO.setStatus(InsurOrderEnum.Status.enable.getCode());
        orderDO.setApplyTime(DateUtil.defaultFormatDate(new Date()));
        orderDO.setOrderStatus(InsurOrderEnum.OrderStatus.SHZ_DSP.getCode());
        orderDO.setCompanyName(EnumerationUtils.getValueByCode(InsurOrderEnum.InsurCompany.class, orderDO.getCompanyCode()));
        //根据openID获取人员信息
        InsurUserInfo userInfo = getUserInfoByOpenId(openId);
        if (null != userInfo) {
            orderDO.setApplyUserAccount(userInfo.getLoginAccount());
        }

        //todo 先写死
//        orderDO.setApplyUserAccount("写死了");
        long saveCount = saveInsurOrder(orderDO);//订单插入

        List<InsurProOrderDO> orderDOListParam = new ArrayList<InsurProOrderDO>();

        if (saveCount > 0) {//如果插入成功
            for (Object object : insurProductVOList) {
                InsurProductVO insurProductVO = JSONObject.parseObject(object.toString(), InsurProductVO.class);
                InsurProOrderDO insurProOrderDO = new InsurProOrderDO();
                insurProOrderDO.setOrderCode(orderCode);
                insurProOrderDO.setProductCode(insurProductVO.getProductCode());
                insurProOrderDO.setProductName("产品名称");
                insurProOrderDO.setIfAbatement(insurProductVO.getIfAbatement());
                insurProOrderDO.setStatus(Constants.DATA_VALID);
                orderDOListParam.add(insurProOrderDO);
            }
        } else {
            LOGGER.info("订单信息插入失败!!");
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("提交失败!!");
            return response;
        }

        long proOrderCount = insurProOrderService.saveInsurProOrder(orderDOListParam);

        if (proOrderCount > 0) {
            LOGGER.info("订单申请提交成功!!,订单号：<{}>", orderCode);
            response.setData(orderDO);
            response.setRepMsg("提交成功!!");
        } else {
            LOGGER.info("订单产品关系插入失败!!");
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("提交失败!!");
            return response;
        }
        return response;
    }

    /**
     * @param openId
     * @discripeion 根据openID获取个人信息
     * @Author lihuimeng
     * @date 2017/12/22 18:06
     */
    private InsurUserInfo getUserInfoByOpenId(String openId) {
        LOGGER.info("根据openid获取人员信息,openId:<{}>", openId);
        Response<InsurUserInfo> insurUserInfoResponse = iUserService.userValidation(openId);
        LOGGER.info("根据openid获取人员信息,result:<{}>", JSON.toJSONString(insurUserInfoResponse));
        if (null != insurUserInfoResponse && insurUserInfoResponse.isSuccess() == true) {
            return insurUserInfoResponse.getData();
        }
        return null;
    }

    /**
     * lihuimeng
     * 创建保存报价信息参数
     *
     * @param priceDO
     */
    private InsurQuotedPriceDO getSaveQuotePriceParam(InsurQuotedPriceDO priceDO) {
        //获取当前登录人信息
//        InsurUserInfo loginIUser = (InsurUserInfo) request.getSession().getAttribute("loginIUser");
        priceDO.setAuditingUserCode("1001");
        priceDO.setStatus(InsurOrderEnum.Status.enable.getCode());
        priceDO.setConfirmStatus(InsurOrderEnum.ConfirmStatus.DGM.getCode());
        return priceDO;

    }

    /**
     * 创建更新订单历史信息参数
     *
     * @param orderCode
     * @param auditingStatus
     * @param remark
     * @return
     */
    private InsurOrderHistory getInsertHistoryParam(String orderCode, String auditingStatus, String remark) {

        //获取当前登录人信息
//        InsurUserInfo loginIUser = (InsurUserInfo) request.getSession().getAttribute("loginIUser");
        InsurOrderHistory orderHistory = new InsurOrderHistory();
        orderHistory.setOrderCode(orderCode);
        orderHistory.setOptType(InsurOrderEnum.OptType.ORDER_CHECK.getCode());
     /*   orderHistory.setAuditingLoginUser(loginIUser.getLoginAccount());
        orderHistory.setAuditingUserName(loginIUser.getUserName());*/
        orderHistory.setAuditingLoginUser("1001");
        orderHistory.setAuditingUserName("张三");
        orderHistory.setStatus(InsurOrderEnum.Status.enable.getCode());
        if (StringUtils.isNotEmpty(auditingStatus)) {
            orderHistory.setAuditingStatus(auditingStatus);
        }
        orderHistory.setCreateTime(new Date());
        orderHistory.setRemark(remark);
        return orderHistory;
    }

    /**
     * 订单操作
     *
     * @param orderParam
     * @param orderHistoryParam
     * @return
     */
    private Boolean operateOrder(InsurOrderVO orderParam, InsurOrderHistory orderHistoryParam) {
        Boolean isSuccess = true;

        long updateOrderCount = updateOrder(orderParam);//更新订单信息主表

        if (updateOrderCount > 0) {//插入订单操作
            long insertCount = orderHistoryService.insertOrderHistory(orderHistoryParam);//插入订单历史信息
            if (insertCount <= 0) {
                LOGGER.info("更新订单历史信息失败, 订单编号：<{}>", orderHistoryParam.getOrderCode());
                isSuccess = false;
            }
        } else {
            LOGGER.info("更新订单信息失败, 订单编号：<{}>", orderParam.getOrderCode());
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * @param priceDO
     * @return long(报价信息插入条数)
     * @discripeion
     * @Author lihuimeng
     * @date 2017/12/19 14:41
     */
    private long updateAndSaveInsurPrice(InsurQuotedPriceDO priceDO) {
        long insertCount = 0;
        InsurQuotedPriceVO priceVO = new InsurQuotedPriceVO();
        priceVO.setOrderCode(priceDO.getOrderCode());
        priceVO.setStatus(Constants.DATA_UNVALID);
        long updateCount = quotedPriceService.updateInsurQuotedPrice(priceVO);
        if (updateCount >= 0) {
            InsurQuotedPriceDO quotedPriceDO = getSaveQuotePriceParam(priceDO);
            insertCount = quotedPriceService.saveInsurQuotedPrice(quotedPriceDO);//插入报价信息
        }
        return insertCount;
    }

    /**
     * 微信端订单列表查询
     *
     * @Author liangj@yuminsoft.com
     */
    @Override
    public Response<String> getOrderApplicationList(Map<String, Object> map) {
        InsurUserInfo userInfo = new InsurUserInfo();
        LOGGER.info("微信端查询订单, openId：<{}>", (String) map.get("openId"));
        userInfo.setOpenId((String) map.get("openId"));
        Response<String> response = new Response<String>();
        Response<InsurUserInfo> validResponse = iUserService.userValidation((String) map.get("openId"));
        if (BizErrorCode.EOERR.getErrorCode().equals(validResponse.getRepCode())) {
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("用户未认证");
            return response;
        }
        userInfo = validResponse.getData();
        LOGGER.info("微信端查询订单, 登录账户为：<{}>", userInfo.getLoginAccount());
        map.put("applyUserAccount", userInfo.getLoginAccount());

        PageParam pageParam = new PageParam(Integer.parseInt((String) map.get("page")), Integer.parseInt((String) map.get("size")));

        PageBean<InsurOrderVO> pageBean = orderDao.listPage(pageParam, map, "getOrderApplicationList", "getOrderApplicationListCount");
        List<InsurOrderVO> pageList = pageBean.getRecords();

        Map<String, Object> temMap = new HashMap<>();
        temMap.put("orderApplicationList", pageList);
        temMap.put("totalCount", pageBean.getTotalCount());
        String json = JSONObject.toJSONString(temMap);

        response.setRepCode(BizErrorCode.SUCCESS.getErrorCode());
        response.setData(json);
        return response;
    }

    @Override
    public long saveInsurOrder(InsurOrderDO insurOrderDO) {

        LOGGER.info("******插入订单信息,param:<{}>", JSON.toJSONString(insurOrderDO));
        long insertCount = 0;
        try {
            insertCount = insurOrderDao.insertOrder(insurOrderDO);
            ;
            LOGGER.info("******插入订单信息:resultCount：<{}>", insertCount);
        } catch (Exception e) {
            LOGGER.error("插入订单信息异常,订单编号：<{}>,exception:", insurOrderDO.getOrderCode(), e);
        }
        return insertCount;
    }

    @Override
    public List<AttchmentVO> getAttchmentByCodeAndBusiness(String orderCode, String businessType) {
        List<AttchmentVO> attchmentList = new ArrayList<AttchmentVO>();
        AttchmentVO attchmentParam = new AttchmentVO();
        attchmentParam.setBussinessType(businessType);
        attchmentParam.setOrderCode(orderCode);
        attchmentParam.setStatus(Constants.DATA_VALID);
        LOGGER.info("根据订单编号和业务类型查询附件信息, param:<{}>", JSONObject.toJSONString(attchmentParam));
        Response<Object> fileInfo = attachmentService.getFileInfo(attchmentParam);

        LOGGER.info("根据订单编号和业务类型查询附件信息, result:<{}>", JSONObject.toJSONString(fileInfo));
        if (fileInfo.isSuccess() && null != fileInfo.getData()) {
            List<Map<String, Object>> fileInfoMapList = (List<Map<String, Object>>) fileInfo.getData();
            for (Map<String, Object> objectMap : fileInfoMapList) {
                AttchmentVO attchmentVO = new AttchmentVO();
                attchmentVO.setOrderCode(objectMap.get("order_code").toString());
                attchmentVO.setAttachmentUrl("file/getImg?id=" + objectMap.get("attachment_id").toString());//拼接访问图片URL
                attchmentVO.setId((Integer) objectMap.get("id"));
                attchmentList.add(attchmentVO);
            }
        }
        return attchmentList;
    }

    @Override
    public Response<InsurQuotedPriceVO> getOrdeOfferInfo(InsurQuotedPriceVO priceVO) {
        Response<InsurQuotedPriceVO> response = new Response<InsurQuotedPriceVO>();
        InsurQuotedPriceVO ordeOfferInfo = quotedPriceService.getOrdeOfferInfo(priceVO);
        if (null != ordeOfferInfo) {
            response.setData(ordeOfferInfo);
        }
        return response;
    }

    @Override
    public Response<String> ordeOfferSubmit(InsurQuotedPriceVO priceVO) {
        Response<String> response = new Response<String>();
        String auditingStatus = InsurOrderEnum.OrderStatus.DFK_DFK.getCode();
        //创建订单信息操作历史插入参数
        InsurOrderHistory orderHistoryParam = getInsertHistoryParam(priceVO.getOrderCode(), auditingStatus, "");//创建更新订单历史参数
        //创建更新订单信息参数
        InsurOrderVO insurOrderParam = new InsurOrderVO();
        insurOrderParam.setOrderCode(priceVO.getOrderCode());
        insurOrderParam.setOrderStatus(auditingStatus);

        Boolean isSuccess = operateOrder(insurOrderParam, orderHistoryParam);//更新订单信息，插入订单操作历史
        if (isSuccess) {
            LOGGER.info("微信订单报价提交成功, 订单编号：<{}>", insurOrderParam.getOrderCode());
            response.setRepMsg("提交成功!!");
        } else {
            LOGGER.info("微信订单报价提交失败, 订单编号：<{}>", insurOrderParam.getOrderCode());
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("报价提交失败!!");
            return response;
        }

        return response;
    }

    @Override
    public Response<String> ordePatchSubmit(InsurOrderVO insurOrderParam) {
        Response<String> response = new Response<String>();
        String auditingStatus = InsurOrderEnum.OrderStatus.SHZ_YBJ.getCode();
        //创建订单信息操作历史插入参数
        InsurOrderHistory orderHistoryParam = getInsertHistoryParam(insurOrderParam.getOrderCode(), auditingStatus, insurOrderParam.getRemark());//创建更新订单历史参数
        //创建更新订单信息参数
        insurOrderParam.setOrderCode(insurOrderParam.getOrderCode());
        insurOrderParam.setOrderStatus(auditingStatus);

        Boolean isSuccess = operateOrder(insurOrderParam, orderHistoryParam);//更新订单信息，插入订单操作历史
        if (isSuccess) {
            LOGGER.info("微信订单补件提交成功, 订单编号：<{}>", insurOrderParam.getOrderCode());
            response.setRepMsg("提交成功!!");
        } else {
            LOGGER.info("微信订单补件提交失败, 订单编号：<{}>", insurOrderParam.getOrderCode());
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("补件提交失败!!");
            return response;
        }
        return response;
    }

    @Override
    public Response<String> ordePayVoucherSubmit(InsurOrderVO insurOrderParam) {
        Response<String> response = new Response<String>();
        String auditingStatus = InsurOrderEnum.OrderStatus.YFK_DFK.getCode();
        //创建订单信息操作历史插入参数
        InsurOrderHistory orderHistoryParam = getInsertHistoryParam(insurOrderParam.getOrderCode(), auditingStatus, insurOrderParam.getRemark());//创建更新订单历史参数
        //创建更新订单信息参数
        insurOrderParam.setOrderCode(insurOrderParam.getOrderCode());
        insurOrderParam.setOrderStatus(auditingStatus);

        Boolean isSuccess = operateOrder(insurOrderParam, orderHistoryParam);//更新订单信息，插入订单操作历史
        if (isSuccess) {
            LOGGER.info("微信订单付款凭证提交成功, 订单编号：<{}>", insurOrderParam.getOrderCode());
            response.setRepMsg("提交成功!!");
        } else {
            LOGGER.info("微信订单付款凭证提交失败, 订单编号：<{}>", insurOrderParam.getOrderCode());
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("付款凭证提交失败!!");
            return response;
        }
        return response;
    }

    /**
     * 微信端付款信息查询
     *
     * @Author liangj@yuminsoft.com
     */
    @Override
    public Response<InsurPaymentInfoVO> queryPaymentInformation(Map<String, Object> map) {
        InsurUserInfo userInfo = new InsurUserInfo();
        LOGGER.info("微信端查询订单, openId：<{}>", (String) map.get("openId"));
        userInfo.setOpenId((String) map.get("openId"));
        Response<InsurPaymentInfoVO> response = new Response<InsurPaymentInfoVO>();
        Response<InsurUserInfo> validResponse = iUserService.userValidation((String) map.get("openId"));
        if (BizErrorCode.EOERR.getErrorCode().equals(validResponse.getRepCode())) {
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("用户未认证");
            return response;
        }

        List<InsurPaymentInfoVO> list = insurPaymentInfoDao.findPaymentInfoByOrderCode((String) map.get("orderCode"));
        if (null == list || list.size() == 0) {
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("订单不存在");
        } else if (list.size() > 1) {
            response.setRepCode(BizErrorCode.EOERR.getErrorCode());
            response.setRepMsg("数据异常,请联系管理员");
        } else {
            InsurPaymentInfoVO insurPaymentInfoVO = list.get(0);
            response.setRepCode(BizErrorCode.SUCCESS.getErrorCode());
            response.setData(insurPaymentInfoVO);
        }

        return response;
    }
}
