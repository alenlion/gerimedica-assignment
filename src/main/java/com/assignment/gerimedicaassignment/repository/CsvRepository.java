package com.assignment.gerimedicaassignment.repository;

import com.assignment.gerimedicaassignment.model.CsvEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */

public interface CsvRepository extends JpaRepository<CsvEntity, Long> {
    Optional<CsvEntity> findByCode( String code );
}
