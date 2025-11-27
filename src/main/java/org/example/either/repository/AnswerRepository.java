package org.example.either.repository;

import java.util.List;
import org.example.either.entity.Answer;
import org.example.either.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    long countByQuestionAndAnswerText(Question q, String text);

    // AnswerRepository에 추가
    List<Answer> findByQuestion(Question question);
}
