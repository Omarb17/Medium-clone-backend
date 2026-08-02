package com.Omarb17.medium_clone.controller;

import com.Omarb17.medium_clone.entity.Topic;
import com.Omarb17.medium_clone.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    @Operation(
            summary = "Get all topics",
            description = "Retrieves a list of all topics"
                )
    @GetMapping
    public List<Topic> getTopicsList() {
        return topicService.getAllTopics();
    }

    @Operation(
            summary = "Get topic by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById (@PathVariable Long id) {
        return topicService.getTopicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new topic")
    @PostMapping
    public ResponseEntity<Topic> addNewTopic(@RequestBody Topic topic) {
       Topic savedTopic = topicService.addNewTopic(topic);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedTopic);
    }

}
