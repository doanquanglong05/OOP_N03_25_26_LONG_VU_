public class Time {
    private int hour, minute, second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int totalSeconds() {
        return hour * 3600 + minute * 60 + second;
    }
}
