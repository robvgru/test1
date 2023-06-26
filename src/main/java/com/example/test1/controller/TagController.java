package com.example.test1.controller;

import com.example.test1.entity.Tag;
import com.example.test1.repository.TagRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tags")
public class TagController {
    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping
    Set<String> getTagsForAutoComplete(@RequestParam("startWith") String startWith) {
        Set<Tag> first5ByNameLike = tagRepository.findFirst5ByNameStartingWith(startWith);
        return first5ByNameLike.stream().map(Tag::getName).collect(Collectors.toSet());
    }
}
