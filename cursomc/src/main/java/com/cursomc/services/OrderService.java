package com.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Order;
import com.cursomc.domain.OrderItem;
import com.cursomc.domain.SlipPayment;
import com.cursomc.domain.enums.PaymentStatus;
import com.cursomc.repositories.OrderItemRepository;
import com.cursomc.repositories.OrderRepository;
import com.cursomc.repositories.PaymentRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;
import com.cursomc.services.utils.SlipService;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository rep;
	
	@Autowired
	private SlipService slipService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Order findById(Integer id) {
		Optional<Order> order = rep.findById(id);
		return order.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));
	}
	
	public Order insert(Order order) {
		order.setId(null);
		order.setDate(new Date());
		
		order.getPayment().setPaymentStatus(PaymentStatus.PENDENTE);
		order.getPayment().setOrder(order);
		
		if(order.getPayment() instanceof SlipPayment) {
			SlipPayment slipPayment = (SlipPayment) order.getPayment();
			slipService.fillSlipPayment(slipPayment, order.getDate());
		}
		
		order = rep.save(order);
		paymentRepository.save(order.getPayment());
		
		for(OrderItem orderItem : order.getItems()) {
			orderItem.setDiscount(0.00);
			orderItem.setPrice(productService.findById(orderItem.getProduct().getId()).getPrice());
			orderItem.setOrder(order);
		}
		orderItemRepository.saveAll(order.getItems());
		
		return order;
	}

}