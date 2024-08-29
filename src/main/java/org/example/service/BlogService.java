package org.example.service;

import org.example.model.Blog;
import org.example.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getBlogsByCityAndState(String city, String state) {
        return blogRepository.findByCityAndState(city, state);
    }

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }
}
