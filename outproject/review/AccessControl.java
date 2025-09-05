/**
 * Thành viên 1: Đoàn Quang Long - MSSV: 23010377
 * Thành viên 2: Đoàn Hoàng Vũ    - MSSV: 23010534
 * Lớp: OOP - N03 - 25_26
 * Bài thực hành 3 - Access Control
 */

class Alpha {
    private int secret = 42;

    public int getSecret() {
        return secret;
    }
}

public class AccessControl {
    public static void main(String[] args) {
        Alpha a = new Alpha();
        System.out.println("Nhóm Long & Vũ - Giá trị bí mật là: " + a.getSecret());
    }
}
