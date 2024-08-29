package com.example.text_file_writer;

import com.example.text_file_writer.model.FileData;
import com.example.text_file_writer.services.impl.TextFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class TextFileWriterApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TextFileService textFileService;

	@BeforeEach
	void setUp() throws IOException {
		textFileService.deleteAllData();
	}

	@Test
	void testInsertAndReadAllData() throws IOException {
		FileData data1 = new FileData();
		data1.setId("1");
		data1.setContent("First Entry");
		textFileService.insertData(data1);

		FileData data2 = new FileData();
		data2.setId("2");
		data2.setContent("Second Entry");
		textFileService.insertData(data2);

		List<FileData> dataList = textFileService.readAllData();
		assertEquals(2, dataList.size());
	}

	@Test
	void testReadSpecificData() throws IOException {
		FileData data = new FileData();
		data.setId("1");
		data.setContent("First Entry");
		textFileService.insertData(data);

		FileData retrievedData = textFileService.readSpecificData("1").orElse(null);
		assertNotNull(retrievedData);
		assertEquals("First Entry", retrievedData.getContent());
	}

	@Test
	void testUpdateData() throws IOException {
		FileData data = new FileData();
		data.setId("1");
		data.setContent("First Entry");
		textFileService.insertData(data);

		textFileService.updateData("1", "Updated Entry");
		FileData updatedData = textFileService.readSpecificData("1").orElse(null);
		assertNotNull(updatedData);
		assertEquals("Updated Entry", updatedData.getContent());
	}

	@Test
	void testDeleteSpecificData() throws IOException {
		FileData data = new FileData();
		data.setId("1");
		data.setContent("First Entry");
		textFileService.insertData(data);

		textFileService.deleteSpecificData("1");
		FileData deletedData = textFileService.readSpecificData("1").orElse(null);
		assertNull(deletedData);
	}

	@Test
	void testDeleteAllData() throws IOException {
		FileData data1 = new FileData();
		data1.setId("1");
		data1.setContent("First Entry");
		textFileService.insertData(data1);

		FileData data2 = new FileData();
		data2.setId("2");
		data2.setContent("Second Entry");
		textFileService.insertData(data2);

		textFileService.deleteAllData();
		List<FileData> dataList = textFileService.readAllData();
		assertEquals(0, dataList.size());
	}
}

