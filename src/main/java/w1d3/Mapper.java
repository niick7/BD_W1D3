package w1d3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Mapper {
  private static final String splitBySpaceOrMinusSign = "[\\s-]+";
  private static final String fileExceptionMessage = "An error occurred.";
  private static final String isNumberOrContainUnderscoreMatcherRegex = "[0-9]|_";
  List<Pair> pairs;
  public Mapper() {
    this.pairs = new ArrayList<>();
  }

  public void map(String fileName) {
    try {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file);
      while(scanner.hasNext()) {
        String line = scanner.nextLine();
        System.out.println(line);
        List<String> wordsInLine = Arrays.asList(line.split(splitBySpaceOrMinusSign));
        for(String word : wordsInLine) {
          word = trimWord(word);
          if(isWord(word))
            this.addPairFromKey(word);
        }
        this.sortPair();
      }
    }
    catch(FileNotFoundException e) {
      System.out.println(fileExceptionMessage);
      e.printStackTrace();
    }
  }

  public void addPairFromKey(String key) {
    Pair pair = new Pair(key.toLowerCase());
    pairs.add(pair);
  }

  public List<Pair> getPairs() {
    return pairs;
  }

  public List<Pair> sortPair() {
    pairs.sort(Comparator.comparing(Pair::getKey));
    return pairs;
  }

  public static String trimWord(String word) {
    word = word.replace(",", "");
    word = word.replace(".", "");
    word = word.replace("\"", "");
    word = word.replace("\'", "");
    return word;
  }

  public static boolean isWord(String word){
    if ("".equals(word) || Pattern.compile(isNumberOrContainUnderscoreMatcherRegex).matcher(word).find()
      || "mumedu".equals(word))
      return false;
    return true;
  }

  @Override
  public String toString() {
    String str = "\n";

    for(Pair p : pairs) {
      str += p;
    }
    return str;
  }
}
