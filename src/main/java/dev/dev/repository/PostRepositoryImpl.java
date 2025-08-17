package dev.dev.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.dev.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static dev.dev.domain.QPost.*;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryQueryDsl{

    QPost post = QPost.post;
    QUser user = QUser.user;
    QComment comment = QComment.comment;

    private final JPAQueryFactory queryFactory;

    public Page<PostResponsePage> findLatest(Pageable pageable) {

        List<PostResponsePage> content = queryFactory
                .select(Projections.constructor(
                        PostResponsePage.class,
                        post.id,
                        post.title,
                        user.nickname,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(comment.count().intValue())
                                        .from(comment)
                                        .where(comment.post.eq(post)),
                                "commentCount"
                        ),
                        post.createdAt
                ))
                .from(post)
                .join(post.author, user)
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(post.count())
                .from(post)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    @Override
    public Page<PostResponsePage> searchByTitle(String keyword, Pageable pageable) {

        List<PostResponsePage> content = queryFactory
                .select(Projections.constructor(
                        PostResponsePage.class,
                        post.id,
                        post.title,
                        user.nickname,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(comment.count().intValue())
                                        .from(comment)
                                        .where(comment.post.eq(post)),
                                "commentCount"
                        ),
                        post.createdAt
                ))
                .from(post)
                .join(post.author, user)
                .where(
                        keyword == null || keyword.isBlank() ? null : post.title.containsIgnoreCase(keyword)
                )
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(post.count())
                .from(post)
                .where(
                        keyword == null || keyword.isBlank() ? null : post.title.containsIgnoreCase(keyword)
                );

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    @Override
    public Optional<Post> findDetailById(Long id) {
        QUser commentAuthor = new QUser("commentAuthor");

        Post result = queryFactory
                .selectFrom(post)
                .leftJoin(post.author, user).fetchJoin()
                .leftJoin(post.comments, comment).fetchJoin()
                .leftJoin(comment.author, commentAuthor).fetchJoin()
                .where(post.id.eq(id))
                .distinct()
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
