package dev.dev.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.dev.domain.User;
import jakarta.persistence.EntityManager;

import static dev.dev.domain.QUser.*;

public class UserRepositoryImpl implements UserRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }




}
