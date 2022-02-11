public class Fib1 {
  private static int fib1(int n) {
    if (n < 2) { return n; }
    return fib1(n - 1) + fib1(n - 2);
  }

  public static void main(String[] args) {
    System.out.println(fib1(5));
    System.out.println(fib1(10));
    System.out.println(fib1(40));
  }
}
