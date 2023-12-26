package org.its.kfh.service;

import org.its.kfh.exception.StudentNotFoundException;
import org.its.kfh.model.StudentDetails;
import org.its.kfh.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String registerStudent(StudentDetails studentDetails) {
        //some field validations can also be put manually here,
        // or in the model class itself using @NotNull, @NotEmpty, etc.
        StudentDetails studentDetailsSaved = this.studentRepository.save(studentDetails);
        return "Student successfully registered! Your generated ID is: "+studentDetailsSaved.getStudentId();
    }

    public StudentDetails getStudent (Integer id) {
        Optional<StudentDetails> student = this.studentRepository.findById(id);
        if (student.isPresent())
            return student.get();
        else
            throw new StudentNotFoundException(400, "Student Not Found for this ID");
    }

    public String deleteStudent(Integer studID) {
        Optional<StudentDetails> student = this.studentRepository.findById(studID);
        if (student.isPresent()) {
            this.studentRepository.deleteById(studID);
            return "Student deleted successfully";
        }
        else
            throw new StudentNotFoundException(400, "Student Not Found for this ID");
    }


}
