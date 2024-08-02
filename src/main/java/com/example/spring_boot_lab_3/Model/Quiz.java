package com.example.spring_boot_lab_3.Model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Quiz {

    @NotEmpty(message = "ID should be not empty")
    @Size(min = 2, message = "ID length must be more than 2")
    private String id;

    @NotEmpty(message = "ID should be not empty")
    @Size(min = 4, message = " length must be more than 4")
    private String title;

    @NotEmpty(message = "Description should be not empty")
    @Size(min = 10, message = "Description length must be more than 10")
    private String description;

    @NotNull(message = "Score must be not empty")
    @Positive
    private double maxScore;

    private List<Question> questions;

    @JsonFormat(pattern = "yyyy-MM-dd:hh:mm")
    private LocalDateTime startDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd:hh:mm")
    private LocalDateTime endDate;

    private boolean isEnd = false; 


}
