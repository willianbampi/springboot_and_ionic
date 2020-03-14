package com.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomc.domain.Address;
import com.cursomc.domain.Category;
import com.cursomc.domain.City;
import com.cursomc.domain.Client;
import com.cursomc.domain.CreditCardPayment;
import com.cursomc.domain.SlipPayment;
import com.cursomc.domain.FederativeUnity;
import com.cursomc.domain.Order;
import com.cursomc.domain.OrderItem;
import com.cursomc.domain.Payment;
import com.cursomc.domain.Product;
import com.cursomc.domain.enums.ClientType;
import com.cursomc.domain.enums.PaymentStatus;
import com.cursomc.repositories.AddressRepository;
import com.cursomc.repositories.CategoryRepository;
import com.cursomc.repositories.CityRepository;
import com.cursomc.repositories.ClientRepository;
import com.cursomc.repositories.FederativeUnityRepository;
import com.cursomc.repositories.OrderItemRepository;
import com.cursomc.repositories.OrderRepository;
import com.cursomc.repositories.PaymentRepository;
import com.cursomc.repositories.ProductRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private FederativeUnityRepository federativeUnityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category category1 = new Category(null, "Informática");
		Category category2 = new Category(null, "Escritório");
		Category category3 = new Category(null, "Acessórios");
		Category category4 = new Category(null, "Cama, Mesa e Banho");
		Category category5 = new Category(null, "Roupas");
		Category category6 = new Category(null, "Feminino");
		Category category7 = new Category(null, "Masculino");
		Category category8 = new Category(null, "Calçados");
		
		Product product1 = new Product(null, "Computador", 2000.00);
		Product product2 = new Product(null, "Impressora", 800.00);
		Product product3 = new Product(null, "Mouse", 80.00);
		Product product4 = new Product(null, "Mesa de Escritório", 300.00);
		Product product5 = new Product(null, "Toalha", 50.00);
		Product product6 = new Product(null, "Colcha", 200.00);
		Product product7 = new Product(null, "TV true color", 1200.00);
		Product product8 = new Product(null, "Roçadeira", 800.00);
		Product product9 = new Product(null, "Abajour", 100.00);
		Product product10 = new Product(null, "Pendente", 180.00);
		Product product11 = new Product(null, "Schampoo", 90.00);
		
		category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		category2.getProducts().addAll(Arrays.asList(product2, product4));
		category3.getProducts().addAll(Arrays.asList(product5, product6));
		category4.getProducts().addAll(Arrays.asList(product1, product2, product3, product7));
		category5.getProducts().addAll(Arrays.asList(product8));
		category6.getProducts().addAll(Arrays.asList(product9, product10));
		category7.getProducts().addAll(Arrays.asList(product11));
		
		product1.getCategories().addAll(Arrays.asList(category1, category4));
		product2.getCategories().addAll(Arrays.asList(category1, category2, category4));
		product3.getCategories().addAll(Arrays.asList(category1, category4));
		product4.getCategories().addAll(Arrays.asList(category2));
		product5.getCategories().addAll(Arrays.asList(category3));
		product6.getCategories().addAll(Arrays.asList(category3));
		product7.getCategories().addAll(Arrays.asList(category4));
		product8.getCategories().addAll(Arrays.asList(category5));
		product9.getCategories().addAll(Arrays.asList(category6));
		product10.getCategories().addAll(Arrays.asList(category6));
		product11.getCategories().addAll(Arrays.asList(category7));
		
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5, category6, category7, category8));
		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5,product6, product7, product8, product9, product10, product11));
		
		FederativeUnity federativeUnity1 = new FederativeUnity(null, "Minas Gerais");
		FederativeUnity federativeUnity2 = new FederativeUnity(null, "São Paulo");
		
		City city1 = new City(null, "Uberlândia", federativeUnity1); 
		City city2 = new City(null, "São Paulo", federativeUnity2); 
		City city3 = new City(null, "Campinas", federativeUnity2);
		
		federativeUnity1.getCities().addAll(Arrays.asList(city1));
		federativeUnity2.getCities().addAll(Arrays.asList(city2, city3));
		
		federativeUnityRepository.saveAll(Arrays.asList(federativeUnity1, federativeUnity2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		
		Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PESSOAFISICA);
		client1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		Address address1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardeim", "38220834", client1, city1);
		Address address2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", client1, city2);
		
		client1.getAddresses().addAll(Arrays.asList(address1, address2));
		
		clientRepository.saveAll(Arrays.asList(client1));
		addressRepository.saveAll(Arrays.asList(address1, address2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order order1 = new Order(null, sdf.parse("30/09/2017 10:32"), client1, address1);
		Order order2 = new Order(null, sdf.parse("10/10/2017 19:35"), client1, address2);
		
		Payment payment1 = new CreditCardPayment(null, PaymentStatus.QUITADO, order1, 6); 
		order1.setPayment(payment1);
		
		Payment payment2 = new SlipPayment(null, PaymentStatus.PENDENTE, order2, sdf.parse("20/10/2017 00:00"), null);
		order2.setPayment(payment2);
		
		client1.getOrders().addAll(Arrays.asList(order1, order2));
		
		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));
		
		OrderItem orderItem1 = new OrderItem(order1, product1, 0.00, 1, 2000.00);
		OrderItem orderItem2 = new OrderItem(order1, product3, 0.00, 2, 80.00);
		OrderItem orderItem3 = new OrderItem(order2, product2, 100.00, 1, 800.00);
		
		order1.getItems().addAll(Arrays.asList(orderItem1, orderItem2));
		order2.getItems().addAll(Arrays.asList(orderItem3));
		
		product1.getItems().addAll(Arrays.asList(orderItem1));
		product2.getItems().addAll(Arrays.asList(orderItem3));
		product3.getItems().addAll(Arrays.asList(orderItem2));
		
		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
		
	}

}