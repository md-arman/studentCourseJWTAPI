package org.its.kfh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class StudentDetails {
    //ca specify column and table names also

    //Good practice would be to override equals and hashcode methods as well along with serialVersionUid

    //can also auto generating the Id: @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer studentId;
    private String studentNameEn;
    private String studentNameAr;
    private String email;
    //telephone number taking in string for including chars like + country code
    private String telephoneNumber;
    private String address;

    //one student can opt for multiple courses
    //assigning new table name: STUDENT_COURSE_TABLE, column name would be default
    //adding jsonIgnore to resolve infinite loop (stack overflow exception), both entities keep fetching each other
    //using join column, etc. can also be used
    //by default lazy fetching
    @JsonIgnore
    @ManyToMany
    @JoinTable(name="STUDENT_COURSE_TABLE")
    private List<CourseDetails> courseList;

}
