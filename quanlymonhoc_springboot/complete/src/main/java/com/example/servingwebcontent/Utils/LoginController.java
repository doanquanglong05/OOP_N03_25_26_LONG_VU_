package com.example.servingwebcontent.Utils;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    // Hiển thị trang login
    @GetMapping("/login")
    public String loginForm(Model model, HttpSession session) {
        // nếu đã đăng nhập, chuyển về index
        if (session.getAttribute("username") != null) {
            return "redirect:/";
        }
        return "login";
    }

    // Xử lý submit login
    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          Model model,
                          HttpSession session) {

        // tài khoản cứng: admin/admin
        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("username", username); // lưu session
            // có thể lưu thêm role nếu cần: session.setAttribute("role", "ADMIN");
            return "redirect:/"; // về trang chủ
        }

        model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
        return "login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
