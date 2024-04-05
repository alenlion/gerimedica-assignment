package com.assignment.gerimedicaassignment.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ProblemDetail;
/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */

@Getter
@Setter
public class ErrorResponseDto {
    ProblemDetail error;
    String message;
    Integer status;
}
