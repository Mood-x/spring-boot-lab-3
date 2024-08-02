package com.example.spring_boot_lab_3.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_boot_lab_3.Model.Course;

@Service 
public class CourseService {
    List<Course> courses = new ArrayList<>(); 
    List<Double> ratings = new ArrayList<>(); 

    public List<Course> getAllCourses(){
        for(Course course : courses){
            if(course.isFree()){
                course.setPrice(0);
            }
        }
        return courses; 
    }

    public boolean addCourse(Course course){
        for(Course courseId : courses){
            if(courseId.getId().equalsIgnoreCase(course.getId())){
                return false; 
            }
        }
        courses.add(course); 
        return true; 
    }

    public boolean updateCourse(String id, Course course){
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getId().equalsIgnoreCase(id)){
                courses.set(i, course); 
                return true; 
            }
        }
        return false; 
    }

    public boolean deleteCourse(String id){
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getId().equalsIgnoreCase(id)){
                courses.remove(i); 
                return true; 
            }
        }
        return false; 
    }

    public List<Course> findCoursesByType(String free){
        List<Course> freeCourses = new ArrayList<>();
        List<Course> paidCourses = new ArrayList<>();
        
        for(Course course : courses){
            if(course.isFree()){
                course.setPrice(0.0);
                freeCourses.add(course); 
            }else {
                course.setFree(false);
                paidCourses.add(course); 
            }
        }
        return free.equals("free".toLowerCase()) ? freeCourses : paidCourses; 
    }

    public List<Course> findSimilarCourses(String name){
        List<Course> courseName = new ArrayList<>(); 
        for(Course course : courses){
            if(course.getName().equalsIgnoreCase(name)){
                courseName.add(course); 
            }
        }
        return courseName; 
    }


    public Course changePaymentType(String id, boolean type, double newPrice){
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getId().equalsIgnoreCase(id)){
                courses.get(i).setFree(type); 
                if(courses.get(i).isFree() != true){
                    courses.get(i).setPrice(newPrice);
                }
                return courses.get(i); 
            }
        }
        return null; 
    }

    public boolean addRating(String id, double rating){
        if(rating >= 0 && rating <= 5){
            for(Course course : courses){
                if(course.getId().equals(id)){
                    ratings.add(rating);
                    course.setRating(getAverageRating());
                    return true; 
                }
            }
        }
        return false; 
    }

    public double getAverageRating(){
        if(ratings.isEmpty()){
            return 0.0; 
        }

        double sum = 0.0; 
        for(double rating : ratings){
            sum += rating; 
        }
        
        double average = sum / ratings.size(); 
        return average;
    }
}
