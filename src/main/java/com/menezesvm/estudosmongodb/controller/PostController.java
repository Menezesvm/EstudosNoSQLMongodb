package com.menezesvm.estudosmongodb.controller;

import com.menezesvm.estudosmongodb.controller.util.URL;
import com.menezesvm.estudosmongodb.domain.Post;
import com.menezesvm.estudosmongodb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static java.awt.SystemColor.text;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = postService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByIdTitle(@RequestParam(value = "text", defaultValue = "") String text) throws UnsupportedEncodingException {
		text = URL.decodeParam(text);
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) throws UnsupportedEncodingException {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(minDate, new Date(0L));
		List<Post> list = postService.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}
