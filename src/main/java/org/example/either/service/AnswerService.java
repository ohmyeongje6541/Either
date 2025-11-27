package org.example.either.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.either.entity.Answer;
import org.example.either.entity.Question;
import org.example.either.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    public long countByQuestionAndAnswerText(Question q, String text) {
        return answerRepository.countByQuestionAndAnswerText(q, text);
    }

    // AnswerService에 추가
    public List<Answer> findByQuestion(Question question) {
        return answerRepository.findByQuestion(question);
    }
}
