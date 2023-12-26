package org.its.kfh.exception;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class RequestValidationException extends RuntimeException {

    private Integer errCode;
    private String errMessage;
}
