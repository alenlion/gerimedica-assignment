package com.assignment.gerimedicaassignment.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */


@Getter
@Setter
public class CsvDto {
    Long id;
    String source;
    String codeListCode;
    String code;
    String displayValue;
    String longDescription;
    LocalDate fromDate;
    LocalDate toDate;
    Short sortingPriority;
}
