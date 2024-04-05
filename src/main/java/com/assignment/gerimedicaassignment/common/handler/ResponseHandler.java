package com.assignment.gerimedicaassignment.common.handler;


import com.assignment.gerimedicaassignment.common.dto.ErrorResponseDto;
import com.assignment.gerimedicaassignment.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */

public class ResponseHandler {
    private ResponseHandler() {
    }

    public static ResponseEntity<ErrorResponseDto> generateErrorResponse( String message,
                                                                          HttpStatus status,
                                                                          ProblemDetail problemDetail ) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setError( problemDetail );
        responseDto.setStatus( status.value() );
        responseDto.setMessage(  message  );
        return new ResponseEntity<>( responseDto, status );

    }
    public static <T> ResponseEntity<ResponseDto<T>> generateResponse( String message,
                                                                             HttpStatus status,
                                                                             T responseObj ) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setData( responseObj );
        responseDto.setStatus( status.value() );
        responseDto.setMessage(  message  );
        return new ResponseEntity<>( responseDto, status );

    }    public static <T> ResponseEntity<ResponseDto<T>> generateResponse( String message,
                                                                             HttpStatus status) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setStatus( status.value() );
        responseDto.setMessage(  message  );
        return new ResponseEntity<>( responseDto, status );

    }
}