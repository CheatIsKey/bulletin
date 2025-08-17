package dev.dev.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserRepositoryQueryDsl {

    Page<String> findUserNamePageByUserName(String name, Pageable pageable);

}
