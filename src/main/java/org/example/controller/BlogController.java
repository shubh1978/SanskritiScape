package org.example.controller;

import org.example.model.Blog;
import org.example.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/{city}/{state}")
    public List<Blog> getBlogs(@PathVariable String city, @PathVariable String state) {
        return blogService.getBlogsByCityAndState(city, state);
    }

    @PostMapping
    public Blog addBlog(@RequestBody Blog blog) {
        return blogService.saveBlog(blog);
    }
}
