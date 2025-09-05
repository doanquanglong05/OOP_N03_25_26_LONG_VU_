interface AnimalInterface {
    void sound();
}

class Cat implements AnimalInterface {
    public void sound() {
        System.out.println("Nhóm Long & Vũ - Mèo kêu: Meow Meow");
    }
}

public class Interfaces {
    public static void main(String[] args) {
        AnimalInterface c = new Cat();
        c.sound();
    }
}
