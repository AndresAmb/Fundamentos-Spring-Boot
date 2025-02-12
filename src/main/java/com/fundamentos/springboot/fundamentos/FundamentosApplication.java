package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanImplement;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.components.ComponentDependecy;
import com.fundamentos.springboot.fundamentos.components.ComponentImplement;
import com.fundamentos.springboot.fundamentos.components.ComponentTwoImplement;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependecy componentDependecy;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	private UserService userService;
	 public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependecy componentDependecy, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService){
		 this.componentDependecy = componentDependecy;
		 this.myBean = myBean;
		 this.myBeanWithDependency = myBeanWithDependency;
		 this.myBeanWithProperties = myBeanWithProperties;
		 this.userPojo = userPojo;
		 this.userRepository = userRepository;
		 this.userService = userService;
	 }

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		//ejemplosAnteriores();
		saveUserInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){
		 User test1 = new User("test1Transactional1","test1Transactional1@domain.com",LocalDate.now());
		 User test2 = new User("test2Transactional1","test2Transactional1@domain.com",LocalDate.now());
		 User test3 = new User("test3Transactional1","test1Transactional1@domain.com",LocalDate.now());
		 User test4 = new User("test4Transactional1","test4Transactional1@domain.com",LocalDate.now());

		 List<User> users = Arrays.asList(test1, test2, test3, test4);

		 try {
			 userService.saveTransactional(users);
		 }catch (Exception e){
			 LOGGER.error("Esta es una exception dentro del metodo transactional " + e);
		 }

		 userService.getAllUsers().stream()
				 .forEach(user ->
						 LOGGER.info("Este es el usuario mediante el metodo transactional: " + user));
	}

	public void getInformationJpqlFromUser(){
		 /*LOGGER.info("Usuario con el metod findByUserEmail"+ userRepository.findByUserEmail("gaby@mail.com").orElseThrow(()->new RuntimeException("No se encontró el usuario")));

		 userRepository.findAndSort("user", Sort.by("id").ascending()).stream().forEach(user -> LOGGER.info("Usuario con metodo sort " + user));

		 userRepository.findByName("Andres").stream().forEach(user -> LOGGER.info("Usuario con query method" + user));

		 LOGGER.info("Usuario con query method findByEmailAndName" + userRepository.findByEmailAndName("andres@mail.com","Andres").orElseThrow(()-> new RuntimeException("Usuario no encontrado")));

		 userRepository.findByNameLike("%A%")
				 .stream()
				 .forEach(user -> LOGGER.info("Usuario findByNameLike " + user));

		userRepository.findByNameOrEmail("user10",null)
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail " + user));*/

		userRepository
				.findByBirthDateBetween(LocalDate.of(2021,3,1), LocalDate.of(2021,4,2))
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas: " + user));

		userRepository.findByNameContainingOrderByIdDesc("user")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado: " + user));

		LOGGER.info("El usuario a partir del named parameter es: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021,03,20), "andres@mail.com")
				.orElseThrow(()->new RuntimeException("No se encontró el usuario a partir del named parameter")));



	}
	private void saveUserInDataBase(){
		 User user1 = new User("Andres","andres@mail.com", LocalDate.of(2021,03,20));
		 User user2 = new User("Gaby","gaby@mail.com", LocalDate.of(2021,10,28));
		 User user3 = new User("Andres","dani@mail.com", LocalDate.of(2021,07,15));
		 User user4 = new User("Lis","Lis@mail.com", LocalDate.of(2021,02,10));
		 User user5 = new User("Andres","Jose@mail.com", LocalDate.of(2021,03,26));
		 User user6 = new User("user6","user6@mail.com", LocalDate.of(2021,04,16));
		 User user7 = new User("user7","user7@mail.com", LocalDate.of(2021,10,07));
		 User user8 = new User("user8","user8@mail.com", LocalDate.of(2021,11,21));
		 User user9 = new User("user9","user9@mail.com", LocalDate.of(2021,03,28));
		 User user10 = new User("user10","user10@mail.com", LocalDate.of(2021,02,10));
		 User user11 = new User("user11","user11@mail.com", LocalDate.of(2021,12,26));
		 User user12 = new User("user12","user12@mail.com", LocalDate.of(2021,2,23));
		 List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
		 list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependecy.saludar();
		myBean.print();
		myBeanWithDependency.printWhitDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());
		try	{
			//error
			int value = 10/0;
			LOGGER.info("Mi valor: " + value);
		} catch (Exception e) {
			LOGGER.error("Esto es un error al dividir por cero: " + e.getStackTrace());
		}
	}
}
