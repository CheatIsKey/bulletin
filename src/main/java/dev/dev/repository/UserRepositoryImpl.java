package dev.dev.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static dev.dev.domain.QUser.*;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<String> findUserNamePageByUserName(String name, Pageable pageable) {
        List<String> content = queryFactory
                .select(user.name)
                .from(user)
                .where(user.name.containsIgnoreCase(name))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        Long total = queryFactory
                .select(user.count())
                .from(user)
                .where(user.name.containsIgnoreCase(name))
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
