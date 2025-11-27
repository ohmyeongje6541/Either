package org.example.either.controller;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.either.entity.Answer;
import org.example.either.entity.Question;
import org.example.either.service.AnswerService;
import org.example.either.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "questions/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("question", new Question());
        return "questions/create";
    }

    @PostMapping
    public String create(@ModelAttribute Question question) {
        questionService.save(question);
        return "redirect:/questions";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Question q = questionService.findById(id);

        long countA = answerService.countByQuestionAndAnswerText(q, "A");
        long countB = answerService.countByQuestionAndAnswerText(q, "B");
        long total = countA + countB;

        double percentA = (total == 0) ? 0 : (countA * 100.0 / total);
        double percentB = (total == 0) ? 0 : (countB * 100.0 / total);

        model.addAttribute("question", q);
        model.addAttribute("countA", countA);
        model.addAttribute("countB", countB);
        model.addAttribute("percentA", percentA);
        model.addAttribute("percentB", percentB);

        model.addAttribute("answer", new Answer());

        return "questions/detail";
    }

    @PostMapping("/{id}/answers")
    public String submitAnswer(
        @PathVariable Long id,
        @RequestParam("answerText") String answerText,
        @RequestParam("content") String content
    ) {
        Question q = questionService.findById(id);

        Answer answer = new Answer();
        answer.setAnswerText(answerText);
        answer.setContent(content);
        answer.setQuestion(q);

        answerService.save(answer);

        return "redirect:/questions/" + id;
    }

    @GetMapping("/random")
    public String random() {
        Optional<Question> q = questionService.findRandom();
        return q.map(question -> "redirect:/questions/" + question.getId())
            .orElse("redirect:/questions");
    }
}
