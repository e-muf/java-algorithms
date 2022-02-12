public class CompressedGeneTest {
  public static void main(String[] args) {
    final String original = 
    "TAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATATAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATA";
    
    CompressedGene compressed = new CompressedGene(original);
    final String decompressed = compressed.decompress();
    System.out.println(decompressed);
    System.out.println("original is the same as decompressed: " +
      original.equalsIgnoreCase(decompressed));
  }  
}
