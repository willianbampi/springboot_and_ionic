package com.cursomc.services.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.cursomc.domain.SlipPayment;

@Service
public class SlipService {
	
	public void fillSlipPayment(SlipPayment slipPayment, Date orderDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orderDate);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		slipPayment.setExpirationDate(calendar.getTime());
	}

}