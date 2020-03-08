package com.cursomc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cursomc.domain.enums.PaymentStatus;

@Entity
@Table(name = "CREDIT_CARD_PAYMENT")
public class CreditCardPayment extends Payment {
	
	private static final long serialVersionUID = 1L;
	
	private Integer installmentNumber;
	
	public CreditCardPayment() {
		
	}

	public CreditCardPayment(Integer id, PaymentStatus paymentStatus, Order order, Integer installmentNumber) {
		super(id, paymentStatus, order);
		this.setInstallmentNumber(installmentNumber);
	}

	public Integer getInstallmentNumber() {
		return installmentNumber;
	}

	public void setInstallmentNumber(Integer installmentNumber) {
		this.installmentNumber = installmentNumber;
	}

}
