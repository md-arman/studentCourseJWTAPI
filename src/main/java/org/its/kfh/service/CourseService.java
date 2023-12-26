package org.its.kfh.service;

import org.its.kfh.exception.CourseNotFoundException;
import org.its.kfh.exception.RequestValidationException;
import org.its.kfh.model.CourseDetails;
import org.its.kfh.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseDetails> getCourses() {
        //return courses.toString();
        List<CourseDetails> courseDetailsList = new ArrayList<>();
        this.courseRepository.findAll().forEach(courseDetailsList::add);
        if (courseDetailsList.size()==0)
            throw new CourseNotFoundException(404, "No Courses Found");
        return courseDetailsList;
    }


    public CourseDetails getCourse (Integer id) {
        Optional<CourseDetails> course = this.courseRepository.findById(id);
        if (course.isPresent())
             return course.get();
        else
            throw new CourseNotFoundException(400,"Course Not Found for this ID");
    }

    public String addCourse(CourseDetails courseDetails) {
    try {
        if (Objects.isNull(courseDetails.getCourseId()))
            throw new RequestValidationException(404, "Please provide courseID");
        if (this.courseRepository.findById(courseDetails.getCourseId()).isPresent())
            return "Course with this id already exists";
        this.courseRepository.save(courseDetails);
        //courses.add(courseDetails);
        return "Course added";
    }
    catch (Exception e) {
        return "Unable to add course";
    }
    }

    public String updateCourse(Integer id,String newCourseName) {
        Optional<CourseDetails> course = this.courseRepository.findById(id);
        if (course.isPresent()){
            course.get().setCourseName(newCourseName);
            this.courseRepository.save(course.get());
            return "Course name is updated";
        }
        else
            return "Course does not exist";
    }

    public String deleteCourse(Integer id) {
        Optional<CourseDetails> course = this.courseRepository.findById(id);
        if (!course.isPresent()) {
            throw new CourseNotFoundException(404, "Course not found for ID");
        }
        this.courseRepository.delete(course.get());
        return "Course deleted";
    }
}
