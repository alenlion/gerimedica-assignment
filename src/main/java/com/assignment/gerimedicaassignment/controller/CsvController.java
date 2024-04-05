package com.assignment.gerimedicaassignment.controller;

import com.assignment.gerimedicaassignment.common.dto.ResponseDto;
import com.assignment.gerimedicaassignment.common.handler.ResponseHandler;
import com.assignment.gerimedicaassignment.common.helper.CsvHelper;
import com.assignment.gerimedicaassignment.model.CsvDto;
import com.assignment.gerimedicaassignment.service.CsvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */

@RestController
@RequestMapping( path = "/api/v1/csv", produces = MediaType.APPLICATION_JSON_VALUE )
public class CsvController {
    private final CsvService csvService;

    public CsvController( CsvService csvService ) {
        this.csvService = csvService;
    }

    @GetMapping( "/find-all" )
    public ResponseEntity<ResponseDto<List<CsvDto>>> getAll() {
        return ResponseHandler.generateResponse( "request processed successfully",
                HttpStatus.OK, csvService.fetchAll() );
    }

    @DeleteMapping( "/delete-all" )
    public ResponseEntity<ResponseDto<Object>> deleteAll() {
        csvService.deleteAll();
        return ResponseHandler.generateResponse( "request processed successfully",
                HttpStatus.OK );
    }

    @GetMapping( "/find-by-code/{code}" )
    public ResponseEntity<ResponseDto<CsvDto>> fetchByCode( @PathVariable( "code" ) String code ) {
        return ResponseHandler.generateResponse( "request processed successfully",
                HttpStatus.OK, csvService.fetchByCode( code ) );
    }

    @PostMapping( "/upload" )
    public ResponseEntity<String> uploadFile( @RequestParam( "file" ) MultipartFile file ) {
        String message = "";

        if ( CsvHelper.hasCSVFormat( file ) ) {

            try {
                csvService.upload( file );
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status( HttpStatus.OK ).body( "\" message \": \" " + message + " \"" );
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status( HttpStatus.EXPECTATION_FAILED ).body( "\" message \": \" " + message + " \"" );
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "\" message \": \" " + message + " \"" );
    }
}
