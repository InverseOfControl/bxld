package com.ymkj.bxld.domain.order;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class InsurPaymentInfoVO extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3823999367825964230L;


	/**
	 * 订单编号
	 */
	private String orderCode;

	/**
	 * 收款账户名称
	 */
	private String payCardName;

	/**
	 * 收款账户号
	 */
	private String cardNumber;

	/**
	 * 付款二维码Id
	 */
	private String paymentId;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getPayCardName() {
		return payCardName;
	}

	public void setPayCardName(String payCardName) {
		this.payCardName = payCardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

}
