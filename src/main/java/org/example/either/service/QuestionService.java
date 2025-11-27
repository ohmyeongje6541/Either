package org.example.either.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.example.either.entity.Question;
import org.example.either.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question save(Question q) {
        return questionRepository.save(q);
    }

    public Question findById(Long id) {
        return questionRepository.findById(id).
            orElseThrow( () -> new IllegalArgumentException("question not found"));
    }

    public Optional<Question> findRandom() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) return Optional.empty();
        int idx = new Random().nextInt(questions.size());
        return Optional.of(questions.get(idx));
    }
}
