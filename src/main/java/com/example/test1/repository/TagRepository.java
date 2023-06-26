package com.example.test1.repository;

import com.example.test1.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, String> {
    /**
     * Get tags for autocomplete
     * @param startWith search substring
     * @return set of tags
     */
    Set<Tag> findFirst5ByNameStartingWith(String startWith);
}
