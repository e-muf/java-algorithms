import java.util.stream.IntStream;

public class Fib4 {
  private int last = 0, next = 1; // fib(0), fib(1)

  public IntStream stream() {
    return IntStream.generate(() -> {
      int oldLast = last;
      last = next;
      next = oldLast + last;
      return oldLast;
    });
  }

  public static void main(String[] args) {
    Fib4 fib4 = new Fib4();
    fib4.stream().limit(41).forEachOrdered(System.out::println);
  }
}
