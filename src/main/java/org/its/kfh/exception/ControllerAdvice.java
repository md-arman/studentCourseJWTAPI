package org.its.kfh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    //returning 404, i.e. the requested course could not be found on the server
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException courseNotFoundException) {
        return new ResponseEntity<String>(courseNotFoundException.getErrMessage(), HttpStatus.NOT_FOUND);
    }

    //retruns 400 for any bad request i.e. might not contain the some fields
    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<String> handleCourseNotFoundException(RequestValidationException requestValidationException) {
        return new ResponseEntity<String>(requestValidationException.getErrMessage(), HttpStatus.BAD_REQUEST);
    }

    //returning 404, i.e. the requested student could not be found on the server
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException studentNotFoundException) {
        return new ResponseEntity<String>(studentNotFoundException.getErrMessage(), HttpStatus.NOT_FOUND);
    }
}
