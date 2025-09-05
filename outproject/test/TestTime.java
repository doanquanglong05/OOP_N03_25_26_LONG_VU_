public class TestTime {
    public static void main(String[] args) {
        Time t = new Time(2, 0, 0);
        assert t.totalSeconds() == 7200;
        System.out.println("TestTime passed.");
    }
}
