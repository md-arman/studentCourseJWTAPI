package org.its.kfh.service;

import org.its.kfh.exception.StudentNotFoundException;
import org.its.kfh.model.CourseDetails;
import org.its.kfh.model.StudentDetails;
import org.its.kfh.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    //allocating a course to a student
    //if already allocated, returns COurse already opted message
    public String allocateCourse (Integer studID, Integer courseID) {
        StudentDetails studentDetails = this.studentService.getStudent(studID);
        CourseDetails courseDetails = this.courseService.getCourse(courseID);
        List<CourseDetails> studentExistingCourses = studentDetails.getCourseList();
        //adding the course to user
        boolean alreadyExists = false;
        for (int i=0; i<studentExistingCourses.size(); i++) {
            if (studentExistingCourses.get(i).getCourseId()==courseDetails.getCourseId())
                alreadyExists = true;
        }
        if (!alreadyExists) {
            studentDetails.getCourseList().add(courseDetails);

            this.studentRepository.save(studentDetails);
            return "Course successfully allotted to Student";
        }
        else
            return "Course already opted by Student";
    }

    //fetches all the students for a given course id
    public List<StudentDetails> getStudentFromCourse(Integer courseID) {
        CourseDetails courseDetails = this.courseService.getCourse(courseID);
        List<StudentDetails> studentDetailsList = courseDetails.getStudentList();
        if (studentDetailsList.size()==0)
            throw new StudentNotFoundException(404, "No Student has opted for this course.");
        return studentDetailsList;
    }


    public String updateCourseOfStudent(Integer studID, List<CourseDetails> courseList) {
        StudentDetails studentDetails = this.studentService.getStudent(studID);
        List<CourseDetails> studentExistingCourses = studentDetails.getCourseList();
        ////if student does not exist, it will give StudentNotFoundException, else:
        boolean updateFlag=false;
        for (CourseDetails newCourseDetails: courseList) {
            CourseDetails existingCourse = this.courseService.getCourse(newCourseDetails.getCourseId());

            //if course does not exist, it will give CourseNotFoundException,
            //if course already opted by student then silently ignoring (exception can also be thrown)
            boolean alreadyExists = false;
            for (int i=0; i<studentExistingCourses.size(); i++) {
                if (studentExistingCourses.get(i).getCourseId()==existingCourse.getCourseId())
                    alreadyExists = true;
            }
            //else: add course to student
            if (!alreadyExists) {
                updateFlag=true;
                studentDetails.getCourseList().add(newCourseDetails);
            }
        }
        //validation
        //can be more modified based on whether some course updated, some not updated
        if (updateFlag) {
            this.studentRepository.save(studentDetails);
            return "Courses successfully updated for the student";
        }
        else
            return "Courses NOT updated for the student";
    }

}
