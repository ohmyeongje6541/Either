package org.example.either;

import org.example.either.entity.Question;
import org.example.either.service.QuestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EitherApplication {

    public static void main(String[] args) {
        SpringApplication.run(EitherApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(QuestionService questionService) {
        return args -> {
            // 이미 데이터가 있으면 추가하지 않음
            if (questionService.findAll().isEmpty()) {

                Question q1 = new Question();
                q1.setTitle("점심 메뉴는?");
                q1.setOptionA("햄버거");
                q1.setOptionB("피자");
                questionService.save(q1);

                Question q2 = new Question();
                q2.setTitle("여름 휴가지는?");
                q2.setOptionA("바다");
                q2.setOptionB("산");
                questionService.save(q2);

                Question q3 = new Question();
                q3.setTitle("커피 vs 차");
                q3.setOptionA("커피");
                q3.setOptionB("차");
                questionService.save(q3);

                Question q4 = new Question();
                q4.setTitle("반려동물 선택");
                q4.setOptionA("강아지");
                q4.setOptionB("고양이");
                questionService.save(q4);

                Question q5 = new Question();
                q5.setTitle("영화 vs 드라마");
                q5.setOptionA("영화");
                q5.setOptionB("드라마");
                questionService.save(q5);

                Question q6 = new Question();
                q6.setTitle("치킨 맛은?");
                q6.setOptionA("후라이드");
                q6.setOptionB("양념");
                questionService.save(q6);

                System.out.println("✅ 샘플 데이터 6개가 생성되었습니다!");
            }
        };
    }
}
