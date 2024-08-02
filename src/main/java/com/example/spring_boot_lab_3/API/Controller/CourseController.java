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
import com.example.spring_boot_lab_3.Model.Course;
import com.example.spring_boot_lab_3.Service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor

public class CourseController {

    private final CourseService courseService;


    @GetMapping("/get")
    public ResponseEntity getAllCourses(){
        if(courseService.getAllCourses().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Not uploading courses yet")); 
        }
        return ResponseEntity.status(200).body(courseService.getAllCourses()); 
    }


    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody Course course, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        boolean idExists = courseService.addCourse(course);
        if(idExists){
            return ResponseEntity.status(200).body("successfully added");
        }
        return ResponseEntity.status(400).body("ID already exists");
    }


    @PutMapping("/{id}/update")
    public ResponseEntity updateCourse(@PathVariable String id, @Valid @RequestBody Course course, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message);
        }
        boolean isFound = courseService.updateCourse(id, course); 
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("Successfully update " + course.getName() + " with this id (" + id + ")")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found this id (" + id + ")")); 
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteCourse(@PathVariable String id){
        boolean isFound = courseService.deleteCourse(id); 
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted (" + id + ")")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found this id (" + id + ")")); 
    }


    @GetMapping("/searchByPaymentType/{type}")
    public ResponseEntity findCoursesByType(@PathVariable String type){
        if(type.equalsIgnoreCase("free") || type.equalsIgnoreCase("paid")){
            return ResponseEntity.status(200).body(courseService.findCoursesByType(type).isEmpty() 
                ? new ApiResponse("Not found " + type + " courses") 
                : courseService.findCoursesByType(type));  
        }
        return ResponseEntity.status(200).body(new ApiResponse("Please enter free or paid")); 
    }


    @GetMapping("/getCourses/{name}")
    public ResponseEntity findSimilarCourses(@PathVariable String name){
        return ResponseEntity.status(200).body(courseService.findSimilarCourses(name).isEmpty() 
            ? new ApiResponse(name + " course not uploaded yet") 
            : courseService.findSimilarCourses(name)); 
    }


    @PutMapping("/PaymentType/{id}/{type}/{price}")
    public ResponseEntity changePaymentType(@PathVariable String id, @PathVariable boolean type, @PathVariable double price){
        return ResponseEntity.status(200).body(courseService.changePaymentType(id, type, price));
    }

    @PutMapping("/rating/{id}/{rating}")
    public ResponseEntity addRating(@PathVariable String id, @PathVariable double rating){
        
        boolean isValid = courseService.addRating(id, rating);
        if(isValid){
            return ResponseEntity.status(200).body("Successfully added your rating"); 
        }
        return ResponseEntity.status(400).body("Invalid rating. Rating should be between 0 and 5 inclusive"); 
    }
}