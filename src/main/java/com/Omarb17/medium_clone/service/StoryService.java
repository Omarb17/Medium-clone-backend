package com.Omarb17.medium_clone.service;

import com.Omarb17.medium_clone.mapper.StoryMapper;
import com.Omarb17.medium_clone.model.dto.request.StoryRequestDto;
import com.Omarb17.medium_clone.model.dto.response.StoryResponseDto;
import com.Omarb17.medium_clone.model.entity.Story;
import com.Omarb17.medium_clone.model.entity.User;
import com.Omarb17.medium_clone.repository.StoryRepository;
import com.Omarb17.medium_clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    private final StoryMapper storyMapper;

    private final UserRepository userRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository, StoryMapper storyMapper, UserRepository userRepository) {
        this.storyRepository = storyRepository;
        this.storyMapper = storyMapper;
        this.userRepository = userRepository;
    }

    public List<StoryResponseDto> getAllStories() {
        return storyRepository.findAll()
                .stream()
                .map(storyMapper::toResponseDto)
                .toList();
    }

    public Optional<StoryResponseDto> getStoryById(Long id) {
        return storyRepository.findById(id).map(storyMapper::toResponseDto);
    }

    public StoryResponseDto  addNewStory(StoryRequestDto storyRequestDto ) {
        User user = userRepository.findById(storyRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Story story = new Story();

        story.setTitle(storyRequestDto.getTitle());
        story.setSubTitle(storyRequestDto.getSubTitle());
        story.setText(storyRequestDto.getText());
        story.setUser(user);

        Story savedStory = storyRepository.save(story);

        return storyMapper.toResponseDto(savedStory);
    }

    public boolean deleteStory(Long id) {
        return storyRepository.findById(id)
                .map(story -> {
                    storyRepository.deleteById(id);
                    return true;
                })
                .orElse(false);

    }

    public Optional<Story> updateStory(Long id, String title, String text) {
        return storyRepository.findById(id).map( story -> {
            story.setTitle(title);
            story.setText(text);
            return storyRepository.save(story);
        });
    }

    public Optional<User> getUserByStoryId(Long storyId) {
        return storyRepository.findById(storyId)
                .map(Story::getUser);
    }


}
