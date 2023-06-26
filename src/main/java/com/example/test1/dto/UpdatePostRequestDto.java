package com.example.test1.dto;

import java.util.Set;

public record UpdatePostRequestDto(
    Long id,
    Set<String> tags
) {

}
