import java.util.*;

public class StopWords {

    private static final Set<String> stopWords = new HashSet<>(Arrays.asList(
        "the","is","in","at","of","a","and","to","for","on","with","as","by","an"
    ));

    public static boolean isStopWord(String word) {
        return stopWords.contains(word);
    }
}
