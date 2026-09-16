package com.menezesvm.estudosmongodb.instantiation;

import com.menezesvm.estudosmongodb.domain.User;
import com.menezesvm.estudosmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();

		User vinicius = new User(null, "Vinícius", "menezesvgm@gmail.com");
		User flavia = new User(null, "Flávia", "flavia@gmail.com");
		User isabela = new User(null, "Isabela", "isabela@gmail.com");

		userRepository.saveAll(Arrays.asList(vinicius, flavia, isabela));

	}
}
