package w1d3;

import java.util.List;
import java.util.Random;

public class WordCount {
  public static final int m = 3;
  public static final int r = 4;
  private Mapper[] mappers;
  private Reducer[] reducers;

  public WordCount() {
    this.mappers = new Mapper[m];
    for(int i = 0; i < m; i++) {
      this.mappers[i] = new Mapper();
    }
    this.reducers = new Reducer[r];
    for(int i = 0; i < r; i++) {
      this.reducers[i] = new Reducer();
    }
  }

  public Mapper[] getMappers() { return mappers; }

  public void shuffleSort() {
    int mapperLength = mappers.length;
    int reducerLength = reducers.length;
    for(int im = 0; im < mapperLength; im++) {
      List<Pair> pairs = mappers[im].getPairs();
      int pairLength = pairs.size();
      String str = "Pairs send from Mapper " + im;
      for(int ir = 0; ir < reducerLength; ir++){
        System.out.println(str + " Reducer " + ir + "");
        for(int i = 0; i < pairLength; i++) {
          Pair pair = pairs.get(i);
          String key = pair.getKey();
          if(ir == getPartition(key)) {
            System.out.print(pair);
            this.reducers[ir].addPair(pair);
          }
        }
      }
    }
    System.out.println("");
  }

  public int getPartition(String key){
    return (int) key.hashCode() % r;
  }

  public void createReducerGroupByPairs() {
    for(int i = 0; i < r; i++) {
      System.out.println("Reducer " + i + " input");
      Reducer reducer = reducers[i];
      reducer.createGroupByPairs();
      reducer.printGroupByPairs();
    }
  }

  public void doReduce() {
    for(int i = 0; i < r; i++) {
      System.out.println("Reducer " + i + " output");
      Reducer reducer = reducers[i];
      reducer.reduce();
    }
  }
}
