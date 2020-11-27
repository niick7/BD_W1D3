package w1d3;

import java.util.List;
import java.util.Random;

public class WordCount {
  public static final int m = 3;
  public static final int r = 4;
  private Mapper[] mappers;
  private Reducer[] reducers;

  public WordCount() {
    this.mappers = new Mapper[3];
    this.reducers = new Reducer[4];
  }

  public Mapper[] getMappers() { return mappers; }
  public void setMappers(Mapper[] mappers) { this.mappers = mappers; }

  public void shuffleSort() {
    int mapperLength = mappers.length;
    int reducerLength = reducers.length;
    for(int im = 0; im < mapperLength; im++) {
      int n = 0;
      List<Pair> pairs = mappers[im].getPairs();
      String str = "Pairs send from Mapper " + im;
      for(int ir = 0; ir < reducerLength; ir++){
        System.out.println(str + " Reducer " + ir + "");
        Random rand = new Random();
        int randomNumber = rand.nextInt(5);
        int length = n + randomNumber;
        if(ir + 1 == reducerLength) { length = pairs.size(); }
        for(int i = n; i < length; i++) {
          if(i > pairs.size() - 1) break;
          System.out.print(pairs.get(i));
        }
        n = length;
      }
    }
  }

  public int getPartition(String key){
    return (int) key.hashCode() % r;
  }
}
