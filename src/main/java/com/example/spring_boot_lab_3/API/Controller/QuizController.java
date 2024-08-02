package com.example.spring_boot_lab_3.API.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_lab_3.API.ApiResponse;
import com.example.spring_boot_lab_3.Model.Question;
import com.example.spring_boot_lab_3.Model.Quiz;
import com.example.spring_boot_lab_3.Service.QuizService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;


    @GetMapping("/get")
    public ResponseEntity getAllQuizs(){
        if(quizService.getAllQuizs().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Not uploading quizs yet")); 
        }
        return ResponseEntity.status(200).body(quizService.getAllQuizs()); 
    }


    @PostMapping("/add")
    public ResponseEntity addQuiz(@Valid @RequestBody Quiz quiz, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        boolean idExists = quizService.addQuiz(quiz);
        if(idExists){
            return ResponseEntity.status(200).body("successfully added");
        }
        return ResponseEntity.status(400).body("ID already exists");
    }


    @PutMapping("/{id}/update")
    public ResponseEntity updateQuiz(@PathVariable String id, @Valid @RequestBody Quiz quiz, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message);
        }
        boolean isFound = quizService.updateQuiz(id, quiz); 
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("Successfully update " + quiz.getTitle() + " with this id (" + id + ")")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found this id (" + id + ")")); 
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteQuiz(@PathVariable String id){
        boolean isFound = quizService.deleteQuiz(id); 
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted (" + id + ")")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found this id (" + id + ")")); 
    }


    @GetMapping("/{id}/isEnd")
    public ResponseEntity isEnd(@PathVariable String id){

        boolean isEnd = quizService.isEnd(id);

        if(isEnd){
            return ResponseEntity.status(200).body(true ? "Quiz end" : "Quiz not ended"); 
        }

        return ResponseEntity.status(400).body(new ApiResponse("This quiz whit this id (" + id + ") not found")); 
    }

    @GetMapping("/{id}/getQeustions")
    public ResponseEntity getQuestion(@PathVariable String id){
        if(quizService.getQuestion(id).isEmpty()){
            return ResponseEntity.status(400).body("Not upload questions yet"); 
        }
        return ResponseEntity.status(200).body(quizService.getQuestion(id));
    }


    @PostMapping("/{id}/addQuestion")
    public ResponseEntity addQuestion(@PathVariable String id, @Valid @RequestBody Question question, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        boolean isFound = quizService.addQuestion(id, question);
        
        if(isFound){
            return ResponseEntity.status(200).body("Added question to the quiz with this id (" + id + ")"); 
        }

        return ResponseEntity.status(400).body("Cannot found the quiz with this id (" + id + ") or this id already used"); 
    }

    @PutMapping("/question/{id}/update")
    public ResponseEntity updateQuestion(@PathVariable String id, @Valid @RequestBody Question question, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message);
        }
        boolean isFound = quizService.updateQuestion(id, question); 
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("Successfully update question  with this id (" + id + ")")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found this id (" + id + ")")); 
    }


    @DeleteMapping("/question/{quizId}/{questionId}/delete")
    public ResponseEntity deleteQuestion(@PathVariable String quizId, @PathVariable String questionId){
        boolean isFound = quizService.deleteQuestion(quizId, questionId);  
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted (" + questionId + ")")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found this id (" + questionId + ")")); 
    }

    @GetMapping("/questions/{id}/{level}")
    public ResponseEntity getQuestionsByLevel(@PathVariable String id, @PathVariable String level){
        if(level.equalsIgnoreCase("hard") || level.equalsIgnoreCase("medium") || level.equalsIgnoreCase("easy")){
            return ResponseEntity.status(200).body(quizService.getQuestionsByLevel(id, level)); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Level must be either 'Hard', 'Medium', or 'Easy'")); 
    }
}
