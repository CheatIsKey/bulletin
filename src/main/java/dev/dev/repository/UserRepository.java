package dev.dev.repository;

import dev.dev.domain.User;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQueryDsl  {

//    Optional<User> findByUserIdAndNameAndPhone(String loginId, String name, String phone);
    Optional<User> findByNameAndPhone(String name, String phone);

//    @Query("select u.name from User u where lower(u.name) like lower(concat('%', :name, '%'))")
//    Page<String> findUserListByUserName(@Param("name") String name, Pageable pageable);



}
