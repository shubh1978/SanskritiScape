package org.example.model;
import lombok.Data;

import javax.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

@Data
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String state;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    private String content;

    // Getters and Setters
}
