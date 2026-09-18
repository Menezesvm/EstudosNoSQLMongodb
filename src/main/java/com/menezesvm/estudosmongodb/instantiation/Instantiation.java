package com.menezesvm.estudosmongodb.instantiation;

import com.menezesvm.estudosmongodb.domain.Post;
import com.menezesvm.estudosmongodb.domain.User;
import com.menezesvm.estudosmongodb.dto.AuthorDTO;
import com.menezesvm.estudosmongodb.repository.PostRepository;
import com.menezesvm.estudosmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User vinicius = new User(null, "Vinícius", "menezesvgm@gmail.com");
		User flavia = new User(null, "Flávia", "flavia@gmail.com");
		User isabela = new User(null, "Isabela", "isabela@gmail.com");

		userRepository.saveAll(Arrays.asList(vinicius, flavia, isabela));

		Post post1 = new Post(null, sdf.parse("10/09/2026"), "Partiu viagem!", "Vou viajar", new AuthorDTO(vinicius));
		Post post2 = new Post(null, sdf.parse("13/09/2026"), "Bom dia!", "Acordei bem hoje", new AuthorDTO(vinicius));

		postRepository.saveAll(Arrays.asList(post1, post2));

		vinicius.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(vinicius);
	}
}
