package space.artexplorer.api.methods;

public class StringManipulatorUtils {
    public static String generateStringId(String title) {
        return title.replaceAll("[~`?:+!@#$%^&*()_{}/<>,.';\\-\"\\[\\]\\s]", "").toLowerCase();
    }

    public static String generateStringPhotoId(String title) {
        return title.replaceAll("[~`?:+!@#$%^&*()_{}/<>,.';\\-\"\\[\\]\\s]", "");
    }
}
