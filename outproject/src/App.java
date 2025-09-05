public class App {
    public static void main(String[] args) {
        User user = new User("Alice", 21);
        System.out.println("User: " + user.getName() + ", Age: " + user.getAge());

        Time t = new Time(1, 30, 0);
        System.out.println("Total seconds: " + t.totalSeconds());

        System.out.println("Factorial of 5: " + Recursion.factorial(5));
    }
}
