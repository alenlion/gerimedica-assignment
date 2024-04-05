package com.assignment.gerimedicaassignment.service;

import com.assignment.gerimedicaassignment.model.CsvDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */

public interface CsvService {
    void upload( MultipartFile file );

    List<CsvDto> fetchAll();

    CsvDto fetchByCode( String code );

    void deleteAll();
}
