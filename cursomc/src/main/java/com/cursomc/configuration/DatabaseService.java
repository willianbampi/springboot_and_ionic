package com.cursomc.configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Address;
import com.cursomc.domain.Category;
import com.cursomc.domain.City;
import com.cursomc.domain.Client;
import com.cursomc.domain.CreditCardPayment;
import com.cursomc.domain.FederativeUnity;
import com.cursomc.domain.Order;
import com.cursomc.domain.OrderItem;
import com.cursomc.domain.Payment;
import com.cursomc.domain.Product;
import com.cursomc.domain.SlipPayment;
import com.cursomc.domain.enums.ClientType;
import com.cursomc.domain.enums.PaymentStatus;
import com.cursomc.domain.enums.Profile;
import com.cursomc.repositories.AddressRepository;
import com.cursomc.repositories.CategoryRepository;
import com.cursomc.repositories.CityRepository;
import com.cursomc.repositories.ClientRepository;
import com.cursomc.repositories.FederativeUnityRepository;
import com.cursomc.repositories.OrderItemRepository;
import com.cursomc.repositories.OrderRepository;
import com.cursomc.repositories.PaymentRepository;
import com.cursomc.repositories.ProductRepository;

@Service
public class DatabaseService {
	
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
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void instantiateDatabase() throws ParseException {
		
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
		Product product11 = new Product(null, "Shampoo", 90.00);
		Product product12 = new Product(null, "Produto 12", 10.00);
		Product product13 = new Product(null, "Produto 13", 10.00);
		Product product14 = new Product(null, "Produto 14", 10.00);
		Product product15 = new Product(null, "Produto 15", 10.00);
		Product product16 = new Product(null, "Produto 16", 10.00);
		Product product17 = new Product(null, "Produto 17", 10.00);
		Product product18 = new Product(null, "Produto 18", 10.00);
		Product product19 = new Product(null, "Produto 19", 10.00);
		Product product20 = new Product(null, "Produto 20", 10.00);
		Product product21 = new Product(null, "Produto 21", 10.00);
		Product product22 = new Product(null, "Produto 22", 10.00);
		Product product23 = new Product(null, "Produto 23", 10.00);
		Product product24 = new Product(null, "Produto 24", 10.00);
		Product product25 = new Product(null, "Produto 25", 10.00);
		Product product26 = new Product(null, "Produto 26", 10.00);
		Product product27 = new Product(null, "Produto 27", 10.00);
		Product product28 = new Product(null, "Produto 28", 10.00);
		Product product29 = new Product(null, "Produto 29", 10.00);
		Product product30 = new Product(null, "Produto 30", 10.00);
		Product product31 = new Product(null, "Produto 31", 10.00);
		Product product32 = new Product(null, "Produto 32", 10.00);
		Product product33 = new Product(null, "Produto 33", 10.00);
		Product product34 = new Product(null, "Produto 34", 10.00);
		Product product35 = new Product(null, "Produto 35", 10.00);
		Product product36 = new Product(null, "Produto 36", 10.00);
		Product product37 = new Product(null, "Produto 37", 10.00);
		Product product38 = new Product(null, "Produto 38", 10.00);
		Product product39 = new Product(null, "Produto 39", 10.00);
		Product product40 = new Product(null, "Produto 40", 10.00);
		Product product41 = new Product(null, "Produto 41", 10.00);
		Product product42 = new Product(null, "Produto 42", 10.00);
		Product product43 = new Product(null, "Produto 43", 10.00);
		Product product44 = new Product(null, "Produto 44", 10.00);
		Product product45 = new Product(null, "Produto 45", 10.00);
		Product product46 = new Product(null, "Produto 46", 10.00);
		Product product47 = new Product(null, "Produto 47", 10.00);
		Product product48 = new Product(null, "Produto 48", 10.00);
		Product product49 = new Product(null, "Produto 49", 10.00);
		Product product50 = new Product(null, "Produto 50", 10.00);
		
		category1.getProducts().addAll(Arrays.asList(product1, product2, product3, product12, product13, product14,
				                                     product15, product16, product17, product18, product19, product20,
				                                     product21, product22, product23, product24, product25, product26,
				                                     product27, product28, product29, product30, product31, product32,
				                                     product33, product34, product35, product36, product37, product38,
				                                     product39, product40, product41, product42, product43, product44,
				                                     product45, product46, product47, product48, product49, product50));
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
		product12.getCategories().addAll(Arrays.asList(category1));
		product13.getCategories().addAll(Arrays.asList(category1));
		product14.getCategories().addAll(Arrays.asList(category1));
		product15.getCategories().addAll(Arrays.asList(category1));
		product16.getCategories().addAll(Arrays.asList(category1));
		product17.getCategories().addAll(Arrays.asList(category1));
		product18.getCategories().addAll(Arrays.asList(category1));
		product19.getCategories().addAll(Arrays.asList(category1));
		product20.getCategories().addAll(Arrays.asList(category1));
		product21.getCategories().addAll(Arrays.asList(category1));
		product22.getCategories().addAll(Arrays.asList(category1));
		product23.getCategories().addAll(Arrays.asList(category1));
		product24.getCategories().addAll(Arrays.asList(category1));
		product25.getCategories().addAll(Arrays.asList(category1));
		product26.getCategories().addAll(Arrays.asList(category1));
		product27.getCategories().addAll(Arrays.asList(category1));
		product28.getCategories().addAll(Arrays.asList(category1));
		product29.getCategories().addAll(Arrays.asList(category1));
		product30.getCategories().addAll(Arrays.asList(category1));
		product31.getCategories().addAll(Arrays.asList(category1));
		product32.getCategories().addAll(Arrays.asList(category1));
		product33.getCategories().addAll(Arrays.asList(category1));
		product34.getCategories().addAll(Arrays.asList(category1));
		product35.getCategories().addAll(Arrays.asList(category1));
		product36.getCategories().addAll(Arrays.asList(category1));
		product37.getCategories().addAll(Arrays.asList(category1));
		product38.getCategories().addAll(Arrays.asList(category1));
		product39.getCategories().addAll(Arrays.asList(category1));
		product40.getCategories().addAll(Arrays.asList(category1));
		product41.getCategories().addAll(Arrays.asList(category1));
		product42.getCategories().addAll(Arrays.asList(category1));
		product43.getCategories().addAll(Arrays.asList(category1));
		product44.getCategories().addAll(Arrays.asList(category1));
		product45.getCategories().addAll(Arrays.asList(category1));
		product46.getCategories().addAll(Arrays.asList(category1));
		product47.getCategories().addAll(Arrays.asList(category1));
		product48.getCategories().addAll(Arrays.asList(category1));
		product49.getCategories().addAll(Arrays.asList(category1));
		product50.getCategories().addAll(Arrays.asList(category1));
		
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5, category6, category7, category8));
		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5,product6, product7, product8, product9, product10, product11,
				                                product12, product13, product14, product15, product16, product17, product18, product19, product20, product21,
				                                product22, product23, product24, product25, product26, product27, product28, product29, product30, product31,
				                                product32, product33, product34, product35, product36, product37, product38, product39, product40, product41,
				                                product42, product43, product44, product45, product46, product47, product48, product49, product50));
		
		FederativeUnity federativeUnity1 = new FederativeUnity(null, "Minas Gerais");
		FederativeUnity federativeUnity2 = new FederativeUnity(null, "São Paulo");
		
		City city1 = new City(null, "Uberlândia", federativeUnity1); 
		City city2 = new City(null, "São Paulo", federativeUnity2); 
		City city3 = new City(null, "Campinas", federativeUnity2);
		
		federativeUnity1.getCities().addAll(Arrays.asList(city1));
		federativeUnity2.getCities().addAll(Arrays.asList(city2, city3));
		
		federativeUnityRepository.saveAll(Arrays.asList(federativeUnity1, federativeUnity2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		
		Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", bCryptPasswordEncoder.encode("123"), "36378912377", ClientType.PESSOAFISICA);
		client1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Client client2 = new Client(null, "Ana Costa", "ana@gmail.com", bCryptPasswordEncoder.encode("321"), "20778935086", ClientType.PESSOAFISICA);
		client2.addProfiles(Profile.ADMIN);
		client2.getPhones().addAll(Arrays.asList("12312313", "956151515"));
		
		Address address1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardeim", "38220834", client1, city1);
		Address address2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", client1, city2);
		Address address3 = new Address(null, "Avenida Floriano", "2106", null, "Centro", "9971542", client2, city2);
		
		client1.getAddresses().addAll(Arrays.asList(address1, address2));
		client2.getAddresses().addAll(Arrays.asList(address3));
		
		clientRepository.saveAll(Arrays.asList(client1, client2));
		addressRepository.saveAll(Arrays.asList(address1, address2, address3));
		
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