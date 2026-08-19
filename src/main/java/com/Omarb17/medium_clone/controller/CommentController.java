package com.Omarb17.medium_clone.controller;
import com.Omarb17.medium_clone.model.entity.Comment;
import com.Omarb17.medium_clone.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Add a new comment")
    @PostMapping
    public ResponseEntity<Comment> addNewComment(@Valid @RequestBody Comment comment) {
        Comment savedComment = commentService.addNewComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @Operation(summary = "Edit comment")
    @PutMapping("/{id}")
    public ResponseEntity<Comment> editComment (@PathVariable Long id, @Valid @RequestBody Comment comment)
    {return commentService.updateComment(id, comment.getContent())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());}

    @Operation(summary = "Delete comment")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment (@PathVariable Long id) {
        return commentService.deleteComment(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();}
}
