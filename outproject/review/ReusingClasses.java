class AnimalBase {
    void speak() {
        System.out.println("Nhóm Long & Vũ - Animal phát ra âm thanh");
    }
}

class Dog extends AnimalBase {
    void speak() {
        System.out.println("Nhóm Long & Vũ - Chó sủa: Gâu Gâu");
    }
}

public class ReusingClasses {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.speak();
    }
}
