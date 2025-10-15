package com.puzzlix.solid_task.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 수십개의 메서드 쿼리를 지원한다.
}
