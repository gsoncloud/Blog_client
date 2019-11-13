package com.smokies.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.smokies.blog.dto.Blog;

@Controller
public class BlogController {
	
	@Value("${blog.server.url}")
	private String blogServerURL;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/")
	public String showSignUpForm(Blog blog) {
		return "add-blog";
	}

	@PostMapping("/addblog")
	public String addBlog(@Valid Blog blog, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-blog";
		}

		System.out.println("posting blog");
		restTemplate.postForObject(blogServerURL, blog, Blog.class);
		System.out.println("retrieving blogs");
		ResponseEntity<Blog[]> responseEntity = restTemplate.getForEntity(blogServerURL, Blog[].class);
		Blog[] blogs = responseEntity.getBody();
		System.out.println("retrieving blogs " + blogs.length + " " + blogs[3].getBlogPost());
//		model.addAllAttributes(restTemplate.getForObject("http://localhost:9090/blog", List.class));
		model.addAttribute("blogs", blogs);
		return "index";
	}
}
