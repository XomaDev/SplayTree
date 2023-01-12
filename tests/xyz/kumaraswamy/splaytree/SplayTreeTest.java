package xyz.kumaraswamy.splaytree;


import org.junit.jupiter.api.Test;

import static xyz.kumaraswamy.splaytree.Node.child;
import static xyz.kumaraswamy.splaytree.Node.node;

class SplayTreeTest {
   @Test
   public void testZagRotation() {
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

      System.out.println(zagRotation);
      SplayTree tree = new SplayTree(zagRotation);
      tree.search(13);

      Node splayed = tree.root;
      assert (int) splayed.value == 13;
   }

   @Test
   public void testZigZag() {
      Node node = node(4,
              child(3),
              node(6,
                      child(5),
                      child(7)));
      SplayTree tree = new SplayTree(node);
      tree.search(7);

      Node splayed = tree.root;
      assert (int) splayed.value == 7;
   }

   @Test
   public void testZigZagReverse() {
      Node node = node(4,
              child(3),
              node(6,
                      child(5),
                      child(7)));
      node.reverseChildren();

      SplayTree tree = new SplayTree(node);
      tree.search(7);

      Node splayed = tree.root;
      assert (int) splayed.value == 7;
   }

   @Test
   public void testZagZig() {
      Node node = node(6,
              node(4,
                      child(3),
                      child(5)),
              child(7));

      SplayTree tree = new SplayTree(node);
      tree.search(5);

      Node splayed = tree.root;

      assert (int) splayed.value == 5;
      System.out.println(splayed);
   }

   @Test
   public void tryToBreak() {
      Node node = node(6,
              node(4,
                      child(3),
                      child(5)),
              child(7));

      SplayTree tree = new SplayTree(node);
      tree.search(1);

      Node splayed = tree.root;
      System.out.println(splayed);
   }
}