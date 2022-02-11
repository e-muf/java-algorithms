import java.util.HashMap;
import java.util.Map;

public class Fib2 {
  // Map.of() was introduced in Java 9 but returns an immutable Map
  // This creates a map with 0->0 and 1->1 which represent our base cases
  static Map<Integer, Integer> memo = new HashMap<>(Map.of(0, 0, 1, 1));

  private static int fib2(int n) {
    if (!memo.containsKey(n)) {
      // memoization step
      memo.put(n, fib2(n - 1) + fib2(n - 2));
    }
    return memo.get(n);
  }

  public static void main(String[] args) {
    System.out.println(fib2(5));
    System.out.println(fib2(10));
    System.out.println(fib2(40));
  }
}
