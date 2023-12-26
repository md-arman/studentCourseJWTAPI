package org.its.kfh.controller;

import org.its.kfh.model.CourseDetails;
import org.its.kfh.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kfh")
public class CourseController {

    @Autowired
    CourseService courseService;


    //fetch the course details based on course id
    @GetMapping("/getCourse/{id}")
    public CourseDetails getCourse(@PathVariable Integer id){
        return courseService.getCourse(id);
    }


    //insert the new course
    @PostMapping("/addCourse")
    public String addCourse(@RequestBody CourseDetails courseDetails){
        return courseService.addCourse(courseDetails);
    }


    //updating course name based on course id
    //Note: for PUT operations ideally all the parametrs are to be passed in requestBody,
    // and hence the update is performed
    //here just updating name taken from path
    @PutMapping("/updateCourse/{id}/{newCourseName}")
    public String updateCourse(@PathVariable Integer id, @PathVariable String newCourseName){
        return courseService.updateCourse(id, newCourseName);
    }

    //this will delete the course based on the provided course id
    @DeleteMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        return courseService.deleteCourse(id);
    }


    //this returns all the list of courses
    @GetMapping("/getCourse")
    public List<CourseDetails> getCourses(){
        return courseService.getCourses();
    }


}
