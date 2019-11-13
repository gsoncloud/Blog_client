package com.smokies.blog.dto;

public class Blog {
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String blogTitle;

	private String blogPost;

	public Blog(String blogTitle, String blogPost) {
		this.blogTitle = blogTitle;
		this.blogPost = blogPost;
	}

	public Blog() {

	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogPost() {
		return blogPost;
	}

	public void setBlogPost(String blogPost) {
		this.blogPost = blogPost;
	}

	@Override
	public String toString() {
		return "Blog [blogTitle=" + blogTitle + ", blogPost=" + blogPost + "]";
	}

}
