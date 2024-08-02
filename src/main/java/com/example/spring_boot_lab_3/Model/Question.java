package com.example.spring_boot_lab_3.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question {


    @NotEmpty(message = "ID should be not empty")
    @Size(min = 2, message = "ID length must be more than 2")
    private String id; 

    @NotEmpty(message = "Level should be not empty")
    @Pattern(regexp = "^(Hard|Medium|Easy|hard|medium|easy)$", message = "Level must be either 'Hard', 'Medium', or 'Easy'")
    private String level; 

    @NotEmpty(message = "Question should be not empty")
    @Size(min = 10, max = 120)
    private String question; 
}
