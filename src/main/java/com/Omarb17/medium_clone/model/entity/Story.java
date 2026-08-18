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
@Table(name = "Story")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 70, message = "Title must be between 2 and 70 characters")
    private String title;

    @NotBlank(message = "Text is required")
    private String text;

//    private LocalDateTime createdAt;

    private int readingTime;

    private int likeCount;

//    @PrePersist
//    public void onCreate() {
//        createdAt = LocalDateTime.now();
//    }

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "story_topics",
            joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> topics;

    @ManyToOne
    @JoinColumn(name="publication_id")
    private Publication publication;

    @OneToMany(mappedBy = "story")
    private Set<Comment> comments;
}
