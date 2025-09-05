class Shape {
    void draw() {
        System.out.println("Nhóm Long & Vũ - Vẽ hình cơ bản");
    }
}

class Circle extends Shape {
    void draw() {
        System.out.println("Nhóm Long & Vũ - Vẽ hình tròn");
    }
}

public class Polymorphism {
    public static void main(String[] args) {
        Shape s = new Circle();
        s.draw();
    }
}
