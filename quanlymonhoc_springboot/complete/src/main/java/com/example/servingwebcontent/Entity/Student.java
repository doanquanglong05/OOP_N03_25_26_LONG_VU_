package com.example.servingwebcontent.Entity;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class Student {
    private String id;          // Mã SV
    private String name;        // Họ tên
    private String gender;      // "Nam" | "Nữ" | ...
    
    // Dùng LocalDate để lưu ngày sinh đầy đủ
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // để Spring binding từ <input type="date">
    private LocalDate dateOfBirth;

    private String email;

    public Student() { } // bắt buộc có để Spring binding

    public Student(String id, String name, String gender, LocalDate dateOfBirth, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    // ---------- getters & setters ----------
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
