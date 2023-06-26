package com.example.test1.dto;

import java.util.Set;

public record ViewPostResponseDto(
   Long id,
   String title,
   String content,
   Set<String> tags
) {
}
