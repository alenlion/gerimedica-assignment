package com.assignment.gerimedicaassignment.service;

import com.assignment.gerimedicaassignment.common.helper.CsvHelper;
import com.assignment.gerimedicaassignment.mapper.CsvMapper;
import com.assignment.gerimedicaassignment.model.CsvDto;
import com.assignment.gerimedicaassignment.model.CsvEntity;
import com.assignment.gerimedicaassignment.repository.CsvRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */
@Service
public class CsvServiceImpl implements CsvService {

    private final CsvRepository csvRepository;
    private final CsvMapper csvMapper;

    public CsvServiceImpl( CsvRepository csvRepository, CsvMapper csvMapper ) {
        this.csvRepository = csvRepository;
        this.csvMapper = csvMapper;
    }

    @Override
    public void upload( MultipartFile file ) {
        List<CsvEntity> csv = null;
        try {
            csv = CsvHelper.csvToSvcEntity( file.getInputStream() );
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        csvRepository.saveAll( csv );
    }

    @Override
    public List<CsvDto> fetchAll() {
        List<CsvEntity> csvList = csvRepository.findAll();
        List<CsvDto> csvDtoList = new ArrayList<>();
        for ( CsvEntity csvEntity : csvList ) {
            csvDtoList.add( csvMapper.toDto( csvEntity ) );
        }
        return csvDtoList;
    }

    @Override
    public CsvDto fetchByCode( String code ) {
        return csvMapper.toDto( csvRepository.findByCode( code ).get() );
    }

    @Override
    public void deleteAll() {
        csvRepository.deleteAll();
    }
}
