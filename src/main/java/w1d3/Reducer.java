package w1d3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Reducer {
  private List<Pair> pairs;
  private List<GroupByPair> groupByPairs;

  public Reducer() {
    this.pairs = new ArrayList<>();
  }

  public void reduce() {
    List<Pair> pairs = new ArrayList<>();
    for(GroupByPair g : this.getGroupByPairs()) {
      Pair pair = new Pair(g.getKey());
      pair.setValue(g.getValues().size());
      pairs.add(pair);
    }

    for(Pair p : pairs) {
      System.out.print(p);
    }
  }

  public List<GroupByPair> getGroupByPairs() {
    return groupByPairs;
  }

  public void addPair(Pair pair) {
    this.pairs.add(pair);
  }

  public List<Pair> sortPair() {
    pairs.sort(Comparator.comparing(Pair::getKey));
    return pairs;
  }

  public void createGroupByPairs() {
    List<GroupByPair> groupByPairs = new ArrayList<>();
    String tempKey = "";
    GroupByPair tempGroupByPair = new GroupByPair(tempKey);
    for(Pair pair : sortPair()) {
      String key = pair.getKey();
      GroupByPair groupByPair = new GroupByPair(key);
      if(!tempKey.equals(key)) {
        groupByPairs.add(groupByPair);
        tempKey = key;
        tempGroupByPair = groupByPair;
      } else {
        groupByPair = tempGroupByPair;
      }
      groupByPair.addValue(pair.getValue());
    }
    this.groupByPairs = groupByPairs;
  }

  public void printGroupByPairs() {
    for(GroupByPair groupByPair : groupByPairs) {
      System.out.println(groupByPair);
    }
  }
}
