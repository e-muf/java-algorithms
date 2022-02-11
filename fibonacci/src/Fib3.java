public class Fib3 {
  private static int fib3(int n) {
    int last = 0, next = 1; // fib(0), fib(1)
    for (int i = 0; i < n; i++) {
      int oldLast = last;
      last = next;
      next = oldLast + last;
    }
    return last;
  }

  public static void main(String[] args) {
    System.out.println(fib3(20));
    System.out.println(fib3(40));
  }
}
