package com.example.test1.service;

import com.example.test1.dto.CreatePostRequestDto;
import com.example.test1.dto.ViewPostResponseDto;
import com.example.test1.entity.Post;
import com.example.test1.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    public Post toEntity(CreatePostRequestDto dto, List<Tag> savedTags) {
        var post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setTags(Set.copyOf(savedTags));
        return post;
    }

    public ViewPostResponseDto toDto(Post post) {
        return new ViewPostResponseDto(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getTags().stream().map(Tag::getName).collect(Collectors.toSet())
        );
    }
}
