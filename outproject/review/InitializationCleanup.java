public class InitializationCleanup implements AutoCloseable {
    int a;

    InitializationCleanup() {
        a = 10;
       System.out.println("Nhóm Long & Vũ - Hàm khởi tạo được gọi. a = " + a);
    }

    @Override
    public void close() {
        System.out.println("Nhóm Long & Vũ - Hàm cleanup (finalize) được gọi.");
    }

    public static void main(String[] args) {
        try (InitializationCleanup ic = new InitializationCleanup()) {
            // dùng ic
        } // tự động gọi close() ở đây
    }
}
