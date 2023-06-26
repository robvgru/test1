package com.example.test1.service;

import com.example.test1.dto.CreatePostRequestDto;
import com.example.test1.dto.UpdatePostRequestDto;
import com.example.test1.dto.ViewPostResponseDto;
import com.example.test1.entity.Post;
import com.example.test1.entity.Tag;
import com.example.test1.exception.PostNotFoundException;
import com.example.test1.repository.PostRepository;
import com.example.test1.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final TagRepository tagRepository;

    public PostService(PostRepository postRepository,
                       PostMapper postMapper, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.tagRepository = tagRepository;
    }

    @Transactional
    public ViewPostResponseDto create(CreatePostRequestDto createPostDto) {
        List<Tag> savedTags = tagRepository.saveAll(createPostDto.tags().stream().map(Tag::new).collect(Collectors.toSet()));
        Post created = postRepository.save(postMapper.toEntity(createPostDto, savedTags));
        return postMapper.toDto(created);
    }

    @Transactional(readOnly = true)
    public Page<ViewPostResponseDto> findAll(List<String> tags, Pageable pageable) {
        if (tags != null) {
            return postRepository.findAllByTagsNameIn(tags, pageable)
                .map(postMapper::toDto);
        }
        return postRepository.findAll(pageable).map(postMapper::toDto);
    }

    @Transactional
    public ViewPostResponseDto update(UpdatePostRequestDto request) {
        Optional<Post> postOpt = postRepository.findById(request.id());
        if (postOpt.isPresent()) {
            List<Tag> savedTags = tagRepository.saveAll(request.tags().stream().map(Tag::new).collect(Collectors.toSet()));
            Post post = postOpt.get();
            post.setTags(new HashSet<>(savedTags));
            return postMapper.toDto(postRepository.save(post));
        }
        throw new PostNotFoundException(request.id());
    }

    @Transactional
    public void delete(Long id) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            postRepository.delete(post);
        } else {
            throw new PostNotFoundException(id);
        }
    }
}
