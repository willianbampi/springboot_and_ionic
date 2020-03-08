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
import com.cursomc.domain.Payment;
import com.cursomc.domain.Product;
import com.cursomc.domain.enums.ClientType;
import com.cursomc.domain.enums.PaymentStatus;
import com.cursomc.repositories.AddressRepository;
import com.cursomc.repositories.CategoryRepository;
import com.cursomc.repositories.CityRepository;
import com.cursomc.repositories.ClientRepository;
import com.cursomc.repositories.FederativeUnityRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category category1 = new Category(null, "Informática");
		Category category2 = new Category(null, "Escritório");
		
		Product product1 = new Product(null, "Computador", 2000.00);
		Product product2 = new Product(null, "Impressora", 800.00);
		Product product3 = new Product(null, "Mouse", 80.00);
		
		category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		category2.getProducts().addAll(Arrays.asList(product2));
		
		product1.getCategories().addAll(Arrays.asList(category1));
		product2.getCategories().addAll(Arrays.asList(category1, category2));
		product3.getCategories().addAll(Arrays.asList(category2));
		
		categoryRepository.saveAll(Arrays.asList(category1, category2));
		productRepository.saveAll(Arrays.asList(product1, product2, product3));
		
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
		
		Payment payment2 = new SlipPayment(null, PaymentStatus.PENDETE, order2, sdf.parse("20/10/2017 00:00"), null);
		order2.setPayment(payment2);
		
		client1.getOrders().addAll(Arrays.asList(order1, order2));
		
		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));
	}

}