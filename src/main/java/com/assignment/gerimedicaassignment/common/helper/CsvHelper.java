package com.assignment.gerimedicaassignment.common.helper;

/**
 * @author Rayan Aksu
 * @since 4/5/2024
 */

import com.assignment.gerimedicaassignment.model.CsvEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvHelper {
    public static String TYPE = "text/csv";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern( "M/d/yyyy" );

    public static boolean hasCSVFormat( MultipartFile file ) {
        if ( !TYPE.equals( file.getContentType() ) ) {
            return false;
        }
        return true;
    }

    public static List<CsvEntity> csvToSvcEntity( InputStream is ) throws IOException {

        BufferedReader fileReader = new BufferedReader( new InputStreamReader( is, "UTF-8" ) );
        CSVParser csvParser = new CSVParser( fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim() );

        List<CsvEntity> csvs = new ArrayList<>();

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();

        for ( CSVRecord csvRecord : csvRecords ) {
            LocalDate dateFrom = null;
            LocalDate toDate = null;
            try {
                dateFrom = LocalDate.parse( csvRecord.get( 5 ), FORMATTER );
            } catch (Exception ignored) {
            }
            try {
                toDate = LocalDate.parse( csvRecord.get( 6 ), FORMATTER );
            } catch (Exception ignored) {
            }
            Short order = null;
            try {
                order = Short.parseShort( csvRecord.get( 7 ) );
            } catch (Exception ignor) {

            }
            CsvEntity csv = new CsvEntity(
                    csvRecord.get( 0 ),
                    csvRecord.get( 1 ),
                    csvRecord.get( 2 ),
                    csvRecord.get( 3 ),
                    csvRecord.get( 4 ),
                    dateFrom,
                    toDate,
                    order
            );
            csvs.add( csv );
        }
        return csvs;

    }
}