package com.Omarb17.medium_clone.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "topic is required")
    @Size(min = 2, max = 20, message = "Topic must be between 2 and 20 characters")
    private String topic;

    @ManyToMany(mappedBy = "topics")
    private Set<Story> stories;
}
