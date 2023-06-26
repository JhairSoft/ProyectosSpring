package com.example.jpa;

import com.example.jpa.model.Gender;
import com.example.jpa.model.User;
import com.example.jpa.model.UserProfile;
import com.example.jpa.repository.UserProfileRepository;
import com.example.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

@SpringBootApplication
public class JpaOneToOneDemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaOneToOneDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Limpiar las tablas de la base de datos
		userProfileRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();

		// Creamos una instancia de User
		User user =new User("Jharf", "Lazaro","jahir@gmail.com","MY_SUPER_SECRET_PASSWORD");

		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1993,8,10);

		// Creamos una instancia de UserProfile
		UserProfile userProfile = new UserProfile("+51-999999999", Gender.MALE, dateOfBirth.getTime(),
													  "747", "2nd Cross", "Golf View Road, Kodihalli",
														  "Lima", "Peru", "Peru", "560008");


		// Establecer la referencia secundaria (userProfile) en la entidad principal (user)
		user.setUserProfile(userProfile);

		// Establecer la referencia principal (user) en la entidad secundaria (userProfile)
		userProfile.setUser(user);

		// Guardar la referencia principal (que también guardará la referencia secundaria)
		userRepository.save(user);
	}
}
