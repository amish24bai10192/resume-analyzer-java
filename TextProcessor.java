import java.util.*;

public class TextProcessor {

    public static String process(String text) {
        text = text.toLowerCase().replaceAll("[^a-z ]", "");

        String[] words = text.split("\\s+");
        List<String> filtered = new ArrayList<>();

        for (String word : words) {
            if (!StopWords.isStopWord(word) && word.length() > 2) {
                filtered.add(word);
            }
        }

        return String.join(" ", filtered);
    }
}
