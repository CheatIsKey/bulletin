package dev.dev.repository;

import dev.dev.domain.Post;
import dev.dev.domain.PostResponsePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepositoryQueryDsl {

    Page<PostResponsePage> findLatest(Pageable pageable);

    Page<PostResponsePage> searchByTitle(String keyword, Pageable pageable);

    Optional<Post> findDetailById(Long id);
}
