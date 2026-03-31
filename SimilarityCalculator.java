import java.util.*;

public class SimilarityCalculator {

    public static double calculate(String t1, String t2) {
        Map<String, Integer> freq1 = getFrequency(t1);
        Map<String, Integer> freq2 = getFrequency(t2);

        Set<String> allWords = new HashSet<>();
        allWords.addAll(freq1.keySet());
        allWords.addAll(freq2.keySet());

        double dot = 0, mag1 = 0, mag2 = 0;

        for (String word : allWords) {
            int v1 = freq1.getOrDefault(word, 0);
            int v2 = freq2.getOrDefault(word, 0);

            dot += v1 * v2;
            mag1 += v1 * v1;
            mag2 += v2 * v2;
        }

        return dot / (Math.sqrt(mag1) * Math.sqrt(mag2) + 1e-9);
    }

    private static Map<String, Integer> getFrequency(String text) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : text.split("\\s+")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }

    public static String commonWords(String t1, String t2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(t1.split("\\s+")));
        Set<String> set2 = new HashSet<>(Arrays.asList(t2.split("\\s+")));

        set1.retainAll(set2);
        return String.join(", ", set1);
    }
}
