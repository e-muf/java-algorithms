public class UnbreakableEncryptionTest {
  public static void main(String[] args) {
    KeyPair kp = UnbreakableEncryption.encrypt("One Time Pad!");
    String result = UnbreakableEncryption.decrypt(kp);
    System.out.println(result);
  }
}
