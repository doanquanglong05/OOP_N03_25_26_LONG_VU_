package com.example.servingwebcontent.Utils;

import com.example.servingwebcontent.Entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileDbService {

    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final Path dataDir = Paths.get("data");
    private final Path studentsFile = dataDir.resolve("students.json");

    @PostConstruct
    public void init() throws IOException {
        if (!Files.exists(dataDir)) Files.createDirectories(dataDir);
        if (!Files.exists(studentsFile)) Files.writeString(studentsFile, "[]");
    }

    public synchronized List<Student> loadStudents() {
        try {
            List<Student> arr = mapper.readValue(
                    studentsFile.toFile(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Student.class)
            );
            return new ArrayList<>(arr);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public synchronized void saveStudents(List<Student> students) {
        try {
            mapper.writeValue(studentsFile.toFile(), students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
