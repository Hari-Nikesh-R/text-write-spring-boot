package com.example.text_file_writer.services.impl;



import com.example.text_file_writer.model.FileData;
import com.example.text_file_writer.services.TextFileWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TextFileService implements TextFileWriter {
    private final String filePath = "src/main/resources/data.txt";

    public List<FileData> readAllData() throws IOException {
        List<FileData> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length >= 2) {
                    FileData data = new FileData();
                    data.setId(parts[0]);
                    data.setContent(parts[1]);
                    dataList.add(data);
                }
            }
        }
        return dataList;
    }

    public Optional<FileData> readSpecificData(String id) throws IOException {
        return readAllData().stream()
                .filter(data -> data.getId().equals(id))
                .findFirst();
    }

    public void insertData(FileData newData) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(newData.getId() + "," + newData.getContent());
            writer.newLine();
        }
    }

    public void updateData(String id, String newContent) throws IOException {
        List<FileData> dataList = readAllData().stream()
                .peek(data -> {
                    if (data.getId().equals(id)) {
                        data.setContent(newContent);
                    }
                })
                .collect(Collectors.toList());
        writeDataListToFile(dataList);
    }

    public void deleteSpecificData(String id) throws IOException {
        List<FileData> dataList = readAllData().stream()
                .filter(data -> !data.getId().equals(id))
                .collect(Collectors.toList());
        writeDataListToFile(dataList);
    }

    public void deleteAllData() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");
        }
    }

    private void writeDataListToFile(List<FileData> dataList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (FileData data : dataList) {
                writer.write(data.getId() + "," + data.getContent());
                writer.newLine();
            }
        }
    }
}

