package com.assignment.gerimedicaassignment.mapper;

import com.assignment.gerimedicaassignment.model.CsvDto;
import com.assignment.gerimedicaassignment.model.CsvEntity;
import org.springframework.stereotype.Component;

/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */
@Component
public class CsvMapper {
    public CsvDto toDto( CsvEntity csv ) {
        CsvDto csvDto = new CsvDto();
        csvDto.setId( csv.getId() );
        csvDto.setCode( csv.getCode() );
        csvDto.setSource( csv.getSource() );
        csvDto.setDisplayValue( csv.getDisplayValue() );
        csvDto.setLongDescription( csv.getLongDescription() );
        csvDto.setFromDate( csv.getFromDate() );
        csvDto.setToDate( csv.getToDate() );
        csvDto.setSortingPriority( csv.getSortingPriority() );
        csvDto.setCodeListCode( csv.getCodeListCode() );
        return csvDto;
    }
    
}
