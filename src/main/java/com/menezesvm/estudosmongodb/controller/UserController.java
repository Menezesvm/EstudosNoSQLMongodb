package com.menezesvm.estudosmongodb.controller;

import com.menezesvm.estudosmongodb.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User Vinicius = new User("1", "Vinicius", "menezesvg@gmail.com");
		User Isabela = new User("2", "Isabela", "isabelamenezesvg@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(Vinicius, Isabela));
		return ResponseEntity.ok().body(list);
	}
}
