package utils;

public class Toolbox {
    public static String repeatStars(String input) {
        if (input == null) return "";
        return "*".repeat(input.length());
    }
}
