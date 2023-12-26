package org.its.kfh.controller;

import org.its.kfh.model.StudentDetails;
import org.its.kfh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kfh")
public class StudentController {

    @Autowired
    StudentService studentService;


    //registering a student, returns his generated ID
    @PostMapping("/register")
    public String register(@RequestBody StudentDetails studentDetails){
        return studentService.registerStudent(studentDetails);
    }


    //deleting the student
    @DeleteMapping("/deleteStudent/{studID}")
    public String deleteStudent(@PathVariable Integer studID){
        return this.studentService.deleteStudent(studID);
    }

}
