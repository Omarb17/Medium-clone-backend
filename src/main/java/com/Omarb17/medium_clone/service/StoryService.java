package com.Omarb17.medium_clone.service;

import com.Omarb17.medium_clone.entity.Story;
import com.Omarb17.medium_clone.entity.Topic;
import com.Omarb17.medium_clone.repository.StoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    public Optional<Story> getStoryById(Long id) {
        return storyRepository.findById(id);
    }

    public Story addNewStory(Story story) {
        return storyRepository.save(story);
    }

    public void deleteStory(Long id) {
        storyRepository.deleteById(id);
    }

    public Optional<Story> updateStory(Long id, String title, String text) {
        return storyRepository.findById(id).map( story -> {
           story.setTitle(title);
           story.setText(text);
           return storyRepository.save(story);
       });
    }


}
