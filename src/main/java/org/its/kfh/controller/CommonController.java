package org.its.kfh.controller;

import org.its.kfh.model.CourseDetails;
import org.its.kfh.model.StudentDetails;
import org.its.kfh.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kfh")
public class CommonController {

    @Autowired
    CommonService commonService;


    //allocate student having studID to course having courseID
    @PostMapping("/allocateCourse/{studID}/{courseID}")
    public String allocateCourse(@PathVariable Integer studID, @PathVariable Integer courseID){
        return this.commonService.allocateCourse(studID,courseID);
    }


    //get all the students with selected course
    @GetMapping("/getStudents/{courseID}")
    public List<StudentDetails> getStudentByCourse(@PathVariable Integer courseID){
        return this.commonService.getStudentFromCourse(courseID);
    }


    //update course (adding) for one student
    //can be modified to remove course as well
    @PutMapping("/updateCourseOfStudent/{studID}")
    public String updateCourseOfStudent(@PathVariable Integer studID, @RequestBody List<CourseDetails> courseList){
        return this.commonService.updateCourseOfStudent(studID, courseList);

    }


}
