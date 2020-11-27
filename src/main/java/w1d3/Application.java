package w1d3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Application {
  private static final String fileInput1 = "assets/input1.txt";
  private static final String fileInput2 = "assets/input2.txt";
  private static final String fileInput3 = "assets/input3.txt";
  private static final String splitBySpaceOrMinusSign = "[\\s-]+";
  private static final String fileExceptionMessage = "An error occurred.";
  private static final String isNumberOrContainUnderscoreMatcherRegex = "[0-9]|_";

  public static void main(String[] args) {
    WordCount wordCount = new WordCount();
    System.out.println("Number of Input-Splits: " + WordCount.m);
    System.out.println("Number of Reducers: " + WordCount.r + "\n");
    Mapper mapper1 = new Mapper();
    Mapper mapper2 = new Mapper();
    Mapper mapper3 = new Mapper();
    System.out.println("Mapper 0 input:");
    addPairsToMapper(mapper1, fileInput1);
    System.out.println("Mapper 1 input:");
    addPairsToMapper(mapper2, fileInput2);
    System.out.println("Mapper 2 input:");
    addPairsToMapper(mapper3, fileInput3);
    Mapper[] mappers = { mapper1, mapper2, mapper3 };
    for(int i = 0; i < mappers.length; i++) {
      System.out.println("Mapper " + i + " Output: \n" + mappers[i]);
    }
    wordCount.setMappers(mappers);
    wordCount.shuffleSort();
  }

  public static void addPairsToMapper(Mapper mapper, String fileName) {
    try {
      File file = new File(String.valueOf(fileName));
      Scanner scanner = new Scanner(file);
      while(scanner.hasNext()) {
        String line = scanner.nextLine();
        System.out.println(line);
        List<String> wordsInLine = Arrays.asList(line.split(splitBySpaceOrMinusSign));
        for(String word : wordsInLine) {
          word = trimWord(word);
          if(isWord(word))
            mapper.addPairFromKey(word);
        }
      }
      System.out.println("");
    }
    catch(FileNotFoundException e) {
      System.out.println(fileExceptionMessage);
      e.printStackTrace();
    }
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
}
