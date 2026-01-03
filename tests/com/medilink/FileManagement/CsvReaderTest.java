package com.medilink.FileManagement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.medilink.fileManagement.CsvReader;
import com.medilink.model.Patient;
import com.medilink.model.Severity;

class CsvReaderTest {

    @Test
    void testGetAllPatients_ParsesValidData(@TempDir Path tempDir) throws IOException {

        File tempFile = tempDir.resolve("patients.csv").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("1, John Doe, LOW, 2023-01-01T12:00:00\n");
            writer.write("2, Jane Smith, CRITICAL, 2023-01-01T12:05:00\n");
        }

        CsvReader reader = new CsvReader();
        List<Patient> result = reader.getAllPatients(tempFile.getAbsolutePath());


        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals(Severity.CRITICAL, result.get(1).getSeverity());
    }

    @Test
    void testGetAllPatients_SkipsCorruptLines(@TempDir Path tempDir) throws IOException {
        File tempFile = tempDir.resolve("bad_data.csv").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("1, Good Line, LOW, 2023-01-01T12:00:00\n");
            writer.write("This line is total garbage\n"); 
            writer.write("2, Bad Enum, UNKNOWN_LEVEL, 2023-01-01T12:00:00\n"); 
            writer.write("3, Bad Date, LOW, not-a-date\n"); 
        }

        CsvReader reader = new CsvReader();
        List<Patient> result = reader.getAllPatients(tempFile.getAbsolutePath());

        assertEquals(1, result.size(), "Should have skipped the 3 bad lines");
        assertEquals("Good Line", result.get(0).getName());
    }
}