package com.menezesvm.estudosmongodb.service;

import com.menezesvm.estudosmongodb.domain.User;
import com.menezesvm.estudosmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}
}