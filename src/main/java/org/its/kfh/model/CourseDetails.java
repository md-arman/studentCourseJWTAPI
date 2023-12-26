package org.its.kfh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//used lombok jars to reduce boilerplate code

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class CourseDetails {

    //Good practice would be to override equals and hashcode methods as well along with serialVersionUid

    //can also auto generating the Id using @GeneratedValue(strategy= GenerationType.AUTO)

    @Id
    private Integer courseId;

    private String courseName;

    //a particular course can be opted by multiple students
    @JsonIgnore
    @ManyToMany(mappedBy = "courseList")
    private List<StudentDetails> studentList;


}
