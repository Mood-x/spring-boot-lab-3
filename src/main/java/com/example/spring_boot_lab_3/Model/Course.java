package com.example.spring_boot_lab_3.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "ID should be not empty")
    @Size(min = 2, message = "ID length must be more than 2")
    private String id;
    
    @NotEmpty(message = "Name course should be not empty")
    @Size(min = 2, message = "Name course length must be more than 2")
    private String name;

    @NotEmpty(message = "Description should be not empty")
    @Size(min = 10, message = "Description length must be more than 10")
    private String description;

    
    private boolean isFree;

    private double rating; 
    
    private double price;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startAt; 

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endAt; 


}
