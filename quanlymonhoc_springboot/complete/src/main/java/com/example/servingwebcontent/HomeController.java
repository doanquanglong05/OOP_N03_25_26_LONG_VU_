package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.servingwebcontent.Entity.Student;
import java.time.LocalDate;

@Controller
public class HomeController {

    // Trang chủ
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        Student st = new Student("S001", "Đoàn Quang Long", "Nam",
                                 LocalDate.of(2005, 5, 10), "long@example.com");

        model.addAttribute("message", "Chào mừng bạn đến với hệ thống Quản lý đăng ký môn học!");
        model.addAttribute("student", st);
        model.addAttribute("name", st.getName());
        return "index";
    }
}
