package w1d3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mapper {
  List<Pair> pairs;
  public Mapper() {
    this.pairs = new ArrayList<>();
  }

  public void addPairFromKey(String key) {
    Pair pair = new Pair(key.toLowerCase());
    pairs.add(pair);
  }

  public List<Pair> getPairs() {
    return pairs;
  }

  public List<Pair> sortPair() {
    this.getPairs().sort(Comparator.comparing(Pair::getKey));
    return pairs;
  }

  @Override
  public String toString() {
    String str = "";

    for(Pair p : pairs) {
      str += p;
    }
    return str;
  }
}
