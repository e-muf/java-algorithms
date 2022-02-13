import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Gene {
  public enum Nucleotide {
    A, C, G, T
  }

  public static class Codon implements Comparable<Codon> {
    public final Nucleotide first, second, third;
    private final Comparator<Codon> comparator = 
      Comparator.comparing((Codon c) -> c.first)
        .thenComparing((Codon c) -> c.second)
        .thenComparing((Codon c) -> c.third);
    
    public Codon(String codonStr) {
      first = Nucleotide.valueOf(codonStr.substring(0, 1));
      second = Nucleotide.valueOf(codonStr.substring(1, 2));
      third = Nucleotide.valueOf(codonStr.substring(2, 3));
    }

    @Override
    public int compareTo(Codon other) {
      // first is compared first, then second, etc.
      // IOW first takes precedence over second
      // and second over third
      return comparator.compare(this, other);
    }
  }

  private ArrayList<Codon> codons = new ArrayList<>();

  public Gene(String geneStr) {
    for (int i = 0; i < geneStr.length() - 3; i += 3) {
      // take every 3 characters in the String and form a Codon
      codons.add(new Codon(geneStr.substring(i, i + 3)));
    }
  }

  public boolean linearContains(Codon key) {
    for (Codon codon : codons) {
      if (codon.compareTo(key) == 0) {
        return true; // found a match
      }
    }
    return false;
  }

  public boolean binaryContains(Codon key) {
    // binary search only works on sorted collections
    ArrayList<Codon> sortedCodons = new ArrayList<>(codons);
    Collections.sort(sortedCodons);
    int low = 0;
    int high = sortedCodons.size() - 1;
    while (low <= high) { // while there is still a search space
      int middle = (low + high) / 2;
      int comparison = codons.get(middle).compareTo(key);
      if (comparison < 0) { // middle codon is less than key
        low = middle + 1;
      } else if (comparison > 0) { // middle codon is > key
        high = middle - 1;
      } else { // middle codon is equal to key
        return true;
      }
    }
    return false;
  }
}