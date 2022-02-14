import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;

public class GenericSearch {
  public static class Node<T> implements Comparable<Node<T>> {
    final T state;
    Node<T> parent;
    double cost;
    double heuristic;
    
    // for dfs and bfs we won't use cost and heuristic
    Node(T state, Node<T> parent) {
      this.state = state;
      this.parent = parent;
    }

    // for astar we wull use cost and heuristic
    Node(T state, Node<T> parent, double cost, double heuristic) {
      this.state = state;
      this.parent = parent;
      this.cost = cost;
      this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Node<T> o) {
      Double mine = cost + heuristic;
      Double theirs = o.cost + o.heuristic;
      return mine.compareTo(theirs);
    }
  }
  
  public static <T extends Comparable<T>> boolean linearContains(
    List<T> list, T key
  ) {
    for (T item : list) {
      if (item.compareTo(key) == 0) {
        return true; // found a match
      }
    }
    return false;
  }

  // assumes *list* is already sorted
  public static <T extends Comparable<T>> boolean binaryContains(
    List<T> list, T key
  ) {
    int low = 0;
    int high = list.size() - 1;
    while (low <= high) { // while there is still a search space
      int middle = (low + high) / 2;
      int comparison = list.get(middle).compareTo(key);
      if (comparison < 0) {  // middle is < key
        low = middle + 1;
      } else if (comparison > 0) { // middle is > key 
        high = middle - 1;
      } else { // middle is equal to key
        return true;
      }
    }
    return false;
  }

  public static <T> Node<T> dfs(T initial, Predicate<T> goalTest, Function<T, List<T>> successors) {
    // frontier is where we've yet to go
    Stack<Node<T>> frontier = new Stack<>();
    frontier.push(new Node<>(initial, null));
    // explored is where we've been
    Set<T> explored = new HashSet<>();
    explored.add(initial);

    // keep going while there is more to explore
    while (!frontier.isEmpty()) {
      Node<T> currentNode = frontier.pop();
      T currentState = currentNode.state;
      // if we found the goal, we're done
      if (goalTest.test(currentState)) {
        return currentNode;
      }
      // check were we can go next and haven't explored
      for (T child : successors.apply(currentState)) {
        if (explored.contains(child)) {
          continue; // skip children we already explored
        }
        explored.add(child);
        frontier.push(new Node<>(child, currentNode));
      }
    }
    return null;
  }

  public static <T> Node<T> bfs(T initial, Predicate<T> goalTest, Function<T, List<T>> successors) {
    // frontier is where we've yet to go
    Queue<Node<T>> frontier = new LinkedList<>();
    frontier.offer(new Node<>(initial, null));
    // expored is where we've been
    Set<T> explored = new HashSet<>();
    explored.add(initial);

    // keep going while there is more to explore
    while (!frontier.isEmpty()) {
      Node<T> currentNode = frontier.poll();
      T currentState = currentNode.state;
      // if we found the goal, we're done
      if (goalTest.test(currentState)) {
        return currentNode;
      }
      // check where we can go next and haven't explored
      for (T child : successors.apply(currentState)) {
        if (explored.contains(child)) {
          continue;
        }
        explored.add(child);
        frontier.offer(new Node<>(child, currentNode));
      }
    }
    return null; // went through everything and never found a goal
  }

  public static <T> List<T> nodeToPath(Node<T> node) {
    List<T> path = new ArrayList<>();
    path.add(node.state);
    // work backwards from end to front
    while (node.parent != null) {
      node = node.parent;
      path.add(0, node.state); // add to front
    }
    return path;
  }

  public static void main(String[] args) {
    System.out.println(linearContains(List.of(1, 5, 15, 15, 15, 15, 20), 5));
    System.out.println(binaryContains(List.of("a", "d", "e", "f", "z"), "f"));
    System.out.println(binaryContains(List.of("john", "mark", "ronald"), "sheila"));
  }
}
