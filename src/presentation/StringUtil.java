package presentation;

public class StringUtil {
    public static String CleanUpString(String str, int subStringIdx) {
        return str.substring(subStringIdx)
                .replaceAll(" ","")
                .replace("[","")
                .replace("]","");
    }
}
