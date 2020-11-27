package w1d3;

import java.util.ArrayList;
import java.util.List;

public class GroupByPair {
  private String key;
  private List<Integer> values;

  public GroupByPair(String key) {
    this.key = key;
    this.values = new ArrayList<>();
  }

  public String getKey() {
    return key;
  }

  public List<Integer> getValues() {
    return values;
  }

  public void addValue(int value) {
    values.add(value);
  }

  @Override
  public String toString() {
    return "< " + key + " , " + values + " >";
  }
}
