package com.example.quizApp.service;

import com.example.quizApp.model.Question;
import com.example.quizApp.model.QuestionWrapper;
import com.example.quizApp.model.Quiz;
import com.example.quizApp.repo.Repo;
import com.example.quizApp.repo.RepoQ;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    Repo repo1;
    @Autowired
    RepoQ repoq;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title)
    {
        List<Question> questions=repo1.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz =new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        repoq.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);





    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz= repoq.findById(id);
        List<Question> questionFromDb =quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser=new ArrayList<>();
        for(Question c:questionFromDb)
        {
            QuestionWrapper cw=new QuestionWrapper(c.getId(),c.getQuestionTitle(),c.getOption1(),c.getOption2(),c.getOption3(),c.getOption4());
            questionForUser.add(cw);
        }
        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculate(Integer id, List<Response> responses) {

        Quiz quiz=repoq.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right =0;
        int i=0;
        for(Response response: responses)
        {
            if(response.getMessage().equals(questions.get(i).getRightAnswer()))
            {
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
