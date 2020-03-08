package com.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cursomc.domain.enums.PaymentStatus;

@Entity
@Table(name = "SLIP_PAYMENT")
public class SlipPayment extends Payment {
	
	private static final long serialVersionUID = 1L;
	
	private Date expirationDate;
	private Date paymentDate;
	
	public SlipPayment() {
		
	}

	public SlipPayment(Integer id, PaymentStatus paymentStatus, Order order, Date expirationDate, Date paymentDate) {
		super(id, paymentStatus, order);
		this.expirationDate = expirationDate;
		this.paymentDate = paymentDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
