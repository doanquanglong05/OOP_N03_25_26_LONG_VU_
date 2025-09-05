public class TestUser {
    public static void main(String[] args) {
        User user = new User("Bob", 25);
        assert user.getName().equals("Bob");
        assert user.getAge() == 25;
        System.out.println("TestUser passed.");
    }
}
