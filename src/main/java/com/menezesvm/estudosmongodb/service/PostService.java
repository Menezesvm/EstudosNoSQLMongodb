package com.menezesvm.estudosmongodb.service;

import com.menezesvm.estudosmongodb.domain.Post;
import com.menezesvm.estudosmongodb.repository.PostRepository;
import com.menezesvm.estudosmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<Post> findByTitle (String text) {
		return postRepository.findByTitle(text);
	}

}