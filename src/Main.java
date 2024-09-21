import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        readToMap("src/textOne.txt");
    }
    public static void readToMap(String filePath) {
        Map<String, Integer> words = new HashMap<>();
        HashSet<String> commonWords = loadCommonWords("src/commonWords.txt");
        try {
            Scanner scanner = new Scanner(new FileReader(filePath));
            scanner.useDelimiter("[\\p{Punct}\\s&&[^'’]]+");
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                words.put(word, words.getOrDefault(word, 0) + 1);
                //System.out.println(word);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        getTopFive(words, commonWords);
    }
    public static HashSet<String> loadCommonWords(String filePath) {
        HashSet<String> commonWords = new HashSet<>();
        try {
            Scanner scanner = new Scanner(new FileReader(filePath));
            while(scanner.hasNextLine()) {
                commonWords.add(scanner.nextLine().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return commonWords;
    }
    public static void getTopFive(Map<String, Integer> words, HashSet<String> commonWords) {
        // Filter out common words
        Map<String, Integer> filteredWords = words.entrySet().stream()
                .filter(entry -> !commonWords.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Sort by frequency
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(filteredWords.entrySet());
        sortedWords.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Display the top five words by frequency
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedWords) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            count++;
            if (count == 5) {
                break; // Stop after the top five words
            }
        }
    }
}