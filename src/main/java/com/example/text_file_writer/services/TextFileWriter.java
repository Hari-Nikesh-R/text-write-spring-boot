package com.example.text_file_writer.services;

import com.example.text_file_writer.model.FileData;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TextFileWriter {
    List<FileData> readAllData() throws IOException;
    Optional<FileData> readSpecificData(String id) throws IOException;
    void insertData(FileData newData) throws IOException;
    void updateData(String id, String newContent) throws IOException;
    void deleteSpecificData(String id) throws IOException;
    void deleteAllData() throws IOException;
}
