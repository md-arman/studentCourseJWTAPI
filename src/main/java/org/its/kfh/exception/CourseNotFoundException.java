package org.its.kfh.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class CourseNotFoundException extends RuntimeException {

    private Integer errCode;
    private String errMessage;
}
