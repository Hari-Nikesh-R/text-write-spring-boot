package com.example.text_file_writer.controller;


import com.example.text_file_writer.model.FileData;
import com.example.text_file_writer.services.TextFileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/textfile")
public class TextFileController {

    @Autowired
    private TextFileWriter textFileService;

    @GetMapping("/readAll")
    public ResponseEntity<List<FileData>> readAllData() throws IOException {
        return ResponseEntity.ok(textFileService.readAllData());
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<FileData> readSpecificData(@PathVariable String id) throws IOException {
        Optional<FileData> data = textFileService.readSpecificData(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insert")
    public ResponseEntity<Void> insertData(@RequestBody FileData newData) throws IOException {
        textFileService.insertData(newData);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateData(@PathVariable String id, @RequestBody String newContent) throws IOException {
        textFileService.updateData(id, newContent);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSpecificData(@PathVariable String id) throws IOException {
        textFileService.deleteSpecificData(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllData() throws IOException {
        textFileService.deleteAllData();
        return ResponseEntity.ok().build();
    }
}
