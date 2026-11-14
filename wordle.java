//import java.io.*;
//import java.nio.file.*;
import java.util.*;
//import java.util.stream.*;

public class wordle {
    // Works out the colors Wordle would show for 'guess' if the answer were 'answer'.
  static String feedback(String guess, String answer) {
        char[] result = new char[5];
        int[] counts = new int[26];

        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                result[i] = 'G';
            } else {
                result[i] = 'B';
                counts[answer.charAt(i) - 'a']++;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (result[i] == 'B') {
                int c = guess.charAt(i) - 'a';
                if (counts[c] > 0) {
                    result[i] = 'Y';
                    counts[c]--;
                }
            }
        }
        return new String(result);
  }

  static String bestGuess(List<String> words) {
    int[] freq = new int[26];
    for (String w : words) {
        for (char c : distinct(w)) freq[c - 'a']++;
    }
    String best = words.get(0);
    int bestScore = -1;
    for (String w : words){
        int score = 0;
        for (char c : distinct(w)) score += freq[c - 'a'];
        if (score > bestScore) {
            bestScore = score;
            best = w;
        }
    }
        return best;
  }

  static Set<Character> distinct(String w) {
        Set<Character> s = new HashSet<>();
        for (char c : w.toCharArray()) s.add(c);
        return s;
  }
}
