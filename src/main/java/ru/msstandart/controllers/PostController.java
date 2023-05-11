package ru.msstandart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.msstandart.dto.PostDto;
import ru.msstandart.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<PostDto> getAllPosts() {
        return postService.findAllPosts();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void createPost(@RequestBody PostDto postDto) {
        postService.savePost(postDto);
    }

}
