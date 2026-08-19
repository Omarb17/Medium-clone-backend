package com.Omarb17.medium_clone.controller;
import com.Omarb17.medium_clone.model.entity.Comment;
import com.Omarb17.medium_clone.model.entity.Story;

import com.Omarb17.medium_clone.model.entity.User;
import com.Omarb17.medium_clone.service.CommentService;
import com.Omarb17.medium_clone.service.StoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stories")
public class StoryController {

    private final StoryService storyService;

    private final CommentService commentService;

    @Autowired
    public StoryController(StoryService storyService, CommentService commentService) {
        this.storyService = storyService;
        this.commentService = commentService;
    }

    @Operation(summary = "Get all stories")
    @GetMapping
    public List<Story> getStoriesList() { return storyService.getAllStories();}

    @Operation(summary = "Get story by id")
    @GetMapping("/{id}")
    public ResponseEntity<Story> getStoryById (@PathVariable Long id) { return storyService.getStoryById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());}

    @Operation(summary = "Add a new story")
    @PostMapping
    public ResponseEntity<Story> addNewStory(@Valid @RequestBody Story story) {
        Story savedStory = storyService.addNewStory(story);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStory);
    }

    @Operation(summary = "Edit Story")
    @PutMapping("/{id}")
    public ResponseEntity<Story> editStory (@PathVariable Long id,@Valid @RequestBody Story story)
    {return storyService.updateStory(id, story.getTitle(), story.getText())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());}

    @Operation(summary = "Delete Story")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory (@PathVariable Long id) {
        return storyService.deleteStory(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();}

    @Operation(summary = "Get comments by story id")
    @GetMapping("/{storyId}/comments")
    public List<Comment> getCommentsByStoryId (@PathVariable Long storyId) {
        return commentService.getAllCommentsByStoryId(storyId);
    }

    @Operation(summary = "Get user by story ID")
    @GetMapping("/{storyId}/user")
    public ResponseEntity<User> getUserByStoryId(@PathVariable Long storyId) {
        return storyService.getUserByStoryId(storyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}


