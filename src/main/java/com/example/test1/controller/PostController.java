package com.example.test1.controller;

import com.example.test1.dto.CreatePostRequestDto;
import com.example.test1.dto.UpdatePostRequestDto;
import com.example.test1.dto.ViewPostResponseDto;
import com.example.test1.exception.PostNotFoundException;
import com.example.test1.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ViewPostResponseDto create(@RequestBody CreatePostRequestDto requestDto) {
        return postService.create(requestDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Page<ViewPostResponseDto> getAll(@RequestParam(value = "tags", required = false) List<String> tags, @PageableDefault Pageable pageable) {
        return postService.findAll(tags, pageable);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ViewPostResponseDto update(@RequestBody UpdatePostRequestDto requestDto) {
        return postService.update(requestDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") Long id) {
        postService.delete(id);
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void handlePostNOtFoundException(PostNotFoundException ex) {
        // log
    }

}
