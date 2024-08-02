package com.example.spring_boot_lab_3.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_boot_lab_3.Model.Question;
import com.example.spring_boot_lab_3.Model.Quiz;

@Service
public class QuizService {

    List<Quiz> quizs = new ArrayList<>(); 
    List<Question> questions = new ArrayList<>(); 

    public List<Quiz> getAllQuizs(){
        return quizs; 
    }

    public boolean addQuiz(Quiz quiz){
        for(Quiz quizId : quizs){
            if(quizId.getId().equalsIgnoreCase(quiz.getId())){
                return false; 
            }
        }
        quizs.add(quiz); 
        return true; 
    }

    public boolean updateQuiz(String id, Quiz quiz){
        for(int i = 0; i < quizs.size(); i++){
            if(quizs.get(i).getId().equalsIgnoreCase(id)){
                quizs.set(i, quiz); 
                return true; 
            }
        }
        return false; 
    }

    public boolean deleteQuiz(String id){
        for(int i = 0; i < quizs.size(); i++){
            if(quizs.get(i).getId().equals(id)){
                quizs.remove(i);
                return true; 
            }
        }
        return false; 
    }


    public boolean addQuestion(String id, Question question){
        for(Quiz quizId : quizs){
            if(quizId.getId().equals(id)){
                for(Question questionId : questions){
                    if(questionId.getId().equals(question.getId())){
                        questions.add(question); 
                        return true; 
                    }
                }

                questions.add(question);
                quizId.setQuestions(questions);
                return true; 
            }
        }
        return false; 
    }

    public List<Question> getQuestion(String id){
        for(Quiz quizId : quizs){
            if(quizId.getId().equals(id)){
                return quizId.getQuestions();
            }
        }
        return null; 
    }


    public boolean updateQuestion(String id, Question question){
        for(int i = 0; i < questions.size(); i++){
            if(questions.get(i).getId().equals(id)){
                questions.set(i, question); 
                return true; 
            }
        }
        return false; 
    }

    public boolean deleteQuestion(String quizId, String questionId){

        for(Quiz quiz : quizs){
            if(quiz.getId().equals(quizId)){
                for(int i = 0; i < questions.size(); i++){
                    if(questions.get(i).getId().equals(questionId)){
                        questions.remove(i); 
                        return true; 
                    }
                }
            }
        }

        return false; 
    }

    public List<Question> getQuestionsByLevel(String id, String level){
        List<Question> questionLevel = new ArrayList<>();

        for(Quiz quiz : quizs){
            if(quiz.getId().equals(id)){
                for(Question question : questions){
                    if(question.getLevel().equalsIgnoreCase(level)){
                        questionLevel.add(question); 
                    }
                }
            }
        }
        return questionLevel; 
    }

    public boolean isEnd(String id){
        for(Quiz quiz : quizs){
            if(quiz.getId().equals(id) && quiz.getStartDate().isEqual(quiz.getEndDate())){
                quiz.setEnd(true);
            }
            return true; 
        }
        return false; 
    }
}
