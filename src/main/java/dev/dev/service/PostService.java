package dev.dev.service;

import dev.dev.domain.Post;
import dev.dev.domain.PostDto;
import dev.dev.domain.PostResponsePage;
import dev.dev.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public Page<PostResponsePage> getLatest(Pageable pageable) {
        return postRepository.findLatest(pageable);
    }

    public PostDto findPostById(Long id) {
        Post post = postRepository.findDetailById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return PostDto.from(post);
    }
}
