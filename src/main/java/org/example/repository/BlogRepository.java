package org.example.repository;

import org.example.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<org.example.model.Blog> findByCityAndState(String city, String state);
}
