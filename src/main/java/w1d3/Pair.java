package w1d3;

public class Pair {
  private final String key;
  private int value = 1;

  public Pair(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "< " + key + " , " + value +" >\n";
  }
}
