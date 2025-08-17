-- 1) 유저: nickname 추가 (NOT NULL + UNIQUE)
INSERT INTO users (user_id, name, phone, login_id, nickname, password, role)
VALUES (1, 'Alice', '010-1111-2222', 'alice', '앨리스',
        '$2a$10$uH3.0n5mIuS3l1YpPzscUuQpD0l4VqX0E8sH2q9Kq0N8m5RzKpG2C', 'CLIENT');

INSERT INTO users (user_id, name, phone, login_id, nickname, password, role)
VALUES (2, 'Bob', '010-3333-4444', 'bob', '밥',
        '$2a$10$uH3.0n5mIuS3l1YpPzscUuQpD0l4VqX0E8sH2q9Kq0N8m5RzKpG2C', 'CLIENT');

-- 2) 게시글: FK는 users.user_id
INSERT INTO post (post_id, title, content, user_id) VALUES (1001, '첫 번째 글', '내용입니다', 1);
INSERT INTO post (post_id, title, content, user_id) VALUES (1002, '두 번째 글', '내용입니다', 2);
INSERT INTO post (post_id, title, content, user_id) VALUES (1003, '세 번째 글', '내용입니다', 1);
INSERT INTO post (post_id, title, content, user_id) VALUES (1004, '네 번째 글', '내용입니다', 2);
INSERT INTO post (post_id, title, content, user_id) VALUES (1005, '다섯 번째 글', '내용입니다', 1);
INSERT INTO post (post_id, title, content, user_id) VALUES (1006, '여섯 번째 글', '내용입니다', 2);
INSERT INTO post (post_id, title, content, user_id) VALUES (1007, '일곱 번째 글', '내용입니다', 1);
INSERT INTO post (post_id, title, content, user_id) VALUES (1008, '여덟 번째 글', '내용입니다', 2);
INSERT INTO post (post_id, title, content, user_id) VALUES (1009, '아홉 번째 글', '내용입니다', 1);
INSERT INTO post (post_id, title, content, user_id) VALUES (1010, '열 번째 글', '내용입니다', 2);

-- 3) 시퀀스 충돌 방지: 다음 INSERT부터 겹치지 않게
ALTER SEQUENCE users_seq RESTART WITH 3;
ALTER SEQUENCE post_seq RESTART WITH 1011;
