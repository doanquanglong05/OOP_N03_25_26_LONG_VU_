/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    private String id;
    private String name;
    private String gender;
    private int yearOfBirth;
    private String email;

    public Student() {}

    public Student(String id, String name, String gender, int yearOfBirth, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public int getYearOfBirth() { return yearOfBirth; }
    public String getEmail() { return email; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setYearOfBirth(int yearOfBirth) { this.yearOfBirth = yearOfBirth; }
    public void setEmail(String email) { this.email = email; }
}
