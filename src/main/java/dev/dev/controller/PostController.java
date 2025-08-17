package dev.dev.controller;

import dev.dev.domain.PostDto;
import dev.dev.domain.PostResponsePage;
import dev.dev.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Page<PostResponsePage> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return postService.getLatest(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public PostDto findPost(@PathVariable Long id) {
        return postService.findPostById(id);
    }

}
