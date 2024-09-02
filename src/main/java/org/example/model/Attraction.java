package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String state;

    @ElementCollection
    private List<String> monuments;

    @ElementCollection
    private List<String> famousFood;

    @ElementCollection
    private List<String> literature;

    @ElementCollection
    private List<String> language;

    @ElementCollection
    private List<String> attire;

    @ElementCollection
    private List<String> music;

    @ElementCollection
    private List<String> dance;

    @ElementCollection
    private List<String> festivals;

    @ElementCollection
    private List<String> traditional_events;

    // Getters and Setters
}
