package com.assignment.gerimedicaassignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CsvEntity {
    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    String source;
    String codeListCode;
    String code;
    String displayValue;
    String longDescription;
    LocalDate fromDate;
    LocalDate toDate;
    Short sortingPriority;

    public CsvEntity( String source, String codeListCode, String code, String displayValue, String longDescription, LocalDate fromDate, LocalDate toDate, Short sortingPriority ) {
        this.source = source;
        this.codeListCode = codeListCode;
        this.code = code;
        this.displayValue = displayValue;
        this.longDescription = longDescription;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.sortingPriority = sortingPriority;
    }

}
