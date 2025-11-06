/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servingwebcontent.Utils;

import java.io.File;
import java.io.IOException;

/**
 * FileUtils (Spring Boot version)
 * - Bỏ hoàn toàn xử lý XML cũ.
 * - Giữ nguyên các hàm public để tương thích với code cũ.
 * - Có thể mở rộng sau nếu cần lưu JSON, hoặc đọc/ghi file khác.
 */
public class FileUtils {

    /**
     * (Không còn dùng)
     * Ghi object ra file (bỏ XML).
     * Hiện chỉ log ra console để tránh lỗi.
     */
    public static void writeXMLtoFile(String fileName, Object object) {
        try {
            System.out.println("[FileUtils] writeXMLtoFile() - Bỏ qua ghi XML: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * (Không còn dùng)
     * Đọc file XML và trả về object (bỏ qua).
     * Trả về null vì dữ liệu hiện lưu in-memory.
     */
    public static Object readXMLFile(String fileName, Class<?> clazz) {
        try {
            System.out.println("[FileUtils] readXMLFile() - Bỏ qua đọc XML: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * (Không còn dùng)
     * Khởi tạo file trống nếu chưa tồn tại (bỏ qua).
     * Vẫn tạo file vật lý để tương thích nếu code khác kiểm tra sự tồn tại.
     */
    public static void initFileIfNotExists(String fileName, Object emptyObject) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("[FileUtils] Created dummy file: " + fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
