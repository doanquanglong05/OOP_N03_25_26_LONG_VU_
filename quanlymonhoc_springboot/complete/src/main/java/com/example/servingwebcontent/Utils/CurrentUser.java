/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servingwebcontent.Utils;

import org.springframework.stereotype.Component;

/**
 * CurrentUser (Spring Boot compatible)
 * - Giữ nguyên API như cũ (getInstance(), getUsername(), setUsername())
 * - Dùng ThreadLocal để lưu user hiện tại → an toàn khi có nhiều request đồng thời
 * - Có thể dùng trực tiếp trong code hoặc inject vào service khác
 */
@Component
public class CurrentUser {

    private static final ThreadLocal<String> currentUsername = new ThreadLocal<>();

    private static CurrentUser instance;

    private CurrentUser() {}

    /** 
     * Giữ nguyên kiểu singleton để tương thích mã cũ
     */
    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    /** Lấy tên user hiện tại */
    public String getUsername() {
        return currentUsername.get();
    }

    /** Đặt tên user hiện tại */
    public void setUsername(String username) {
        currentUsername.set(username);
    }

    /** Xóa user khi request kết thúc (tránh rò rỉ bộ nhớ) */
    public void clear() {
        currentUsername.remove();
    }
}
