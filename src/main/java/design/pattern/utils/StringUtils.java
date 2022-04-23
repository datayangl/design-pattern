package design.pattern.utils;

public class StringUtils {
    public static boolean isBlank(String str) {
        return str == null || "".equals(str);
    }
}
