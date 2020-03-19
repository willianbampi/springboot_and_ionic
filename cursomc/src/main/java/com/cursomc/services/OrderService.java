package com.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomc.domain.Client;
import com.cursomc.domain.Order;
import com.cursomc.domain.OrderItem;
import com.cursomc.domain.SlipPayment;
import com.cursomc.domain.enums.PaymentStatus;
import com.cursomc.repositories.OrderItemRepository;
import com.cursomc.repositories.OrderRepository;
import com.cursomc.repositories.PaymentRepository;
import com.cursomc.security.UserSpringSecurity;
import com.cursomc.services.exceptions.AuthorizationException;
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
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public Order findById(Integer id) {
		Optional<Order> order = rep.findById(id);
		return order.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Order.class.getName()));
	}
	
	@Transactional
	public Order insert(Order order) {
		order.setId(null);
		order.setDate(new Date());
		
		order.setClient(clientService.findById(order.getClient().getId()));
		
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
			orderItem.setProduct(productService.findById(orderItem.getProduct().getId()));
			orderItem.setPrice(orderItem.getProduct().getPrice());
			orderItem.setOrder(order);
		}
		orderItemRepository.saveAll(order.getItems());
		
		//emailService.sendOrderConfirmationEmail(order);
		emailService.sendOrderConfirmationHtmlEmail(order);
		
		return order;
	}
	
	public Page<Order> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		UserSpringSecurity userSpringSecurity = UserService.authenticated();
		if(userSpringSecurity == null) {
			throw new AuthorizationException("Access denied.");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client = clientService.findById(userSpringSecurity.getId());
		return rep.findByClient(client, pageRequest);
 	}

}