package com.assignment.gerimedicaassignment.common.handler;

import com.assignment.gerimedicaassignment.common.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.NoSuchElementException;

/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String TIMESTAMP = "timestamp";
    public static final String ERROR_CODE = "errorCode";


    @ExceptionHandler( Exception.class )
    protected ResponseEntity<ErrorResponseDto> exception( Exception e ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail( HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        problemDetail.setType( URI.create( "/problems/internal-server-error" ) );
        problemDetail.setTitle( "INTERNAL_SERVER_ERROR" );
        problemDetail.setProperty( TIMESTAMP, Instant.now() );
        problemDetail.setProperty( ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() );
        return ResponseHandler.generateErrorResponse( HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST, problemDetail );
    }

    @ExceptionHandler( HttpClientErrorException.BadRequest.class )
    protected ResponseEntity<ErrorResponseDto> exception( HttpClientErrorException.BadRequest e ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail( HttpStatus.BAD_REQUEST, e.getMessage() );
        problemDetail.setType( URI.create( "/problems/internal-server-error" ) );
        problemDetail.setTitle( "BAD_REQUEST" );
        problemDetail.setProperty( TIMESTAMP, Instant.now() );
        problemDetail.setProperty( ERROR_CODE, HttpStatus.BAD_REQUEST.getReasonPhrase() );
        return ResponseHandler.generateErrorResponse( HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST, problemDetail );
    }


    @ExceptionHandler( NoSuchElementException.class )
    protected ResponseEntity<ErrorResponseDto> resourceNotFoundException( NoSuchElementException e ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail( HttpStatus.NOT_FOUND, e.getMessage() );
        problemDetail.setType( URI.create( "/problems/error-no-such-element" ) );
        problemDetail.setTitle( "NoSuchElementException" );
        problemDetail.setProperty( TIMESTAMP, Instant.now() );
        problemDetail.setProperty( ERROR_CODE, HttpStatus.NOT_FOUND.getReasonPhrase() );
        return ResponseHandler.generateErrorResponse( HttpStatus.NOT_FOUND.getReasonPhrase(),
                HttpStatus.NOT_FOUND, problemDetail );
    }
}