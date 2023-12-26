package org.its.kfh.exception;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class StudentNotFoundException extends RuntimeException{

    private Integer errCode;
    private String errMessage;
}
