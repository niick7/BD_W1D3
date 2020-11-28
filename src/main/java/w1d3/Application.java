package w1d3;

public class Application {
  public static void main(String[] args) {
    WordCount wordCount = new WordCount();
    System.out.println("Number of Input-Splits: " + WordCount.m);
    System.out.println("Number of Reducers: " + WordCount.r);
    for(int i = 0; i < WordCount.m; i++) {
      System.out.println("Mapper " + i + " Input:");
      Mapper mapper = new Mapper();
      mapper.map("assets/input" + (i + 1) + ".txt");
      System.out.println("Mapper " + i + " Output: \n" + mapper);
      wordCount.getMappers()[i] = mapper;
    }
    wordCount.shuffleSort();
    wordCount.createReducerGroupByPairs();
    wordCount.doReduce();
  }
}
