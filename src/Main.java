import xyz.kumaraswamy.splaytree.Node;
import xyz.kumaraswamy.splaytree.SplayTree;

import static xyz.kumaraswamy.splaytree.Node.child;
import static xyz.kumaraswamy.splaytree.Node.node;

public class Main {
   public static void main(String[] args) {
      Node zagRotation = node(11,
              node(9,
                      node(7,
                              child(1),
                              child(null)),
                      child(10)),
              node(13,
                      child(12),
                      node(14,
                              child(null),
                              child(29))));

      SplayTree tree = new SplayTree(zagRotation);
      tree.search(13);

      Node splayed = tree.root;
      assert (int) splayed.value == 13;
      System.out.println(splayed);
   }
}