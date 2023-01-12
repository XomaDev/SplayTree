package xyz.kumaraswamy.splaytree;

public class SplayTree {

   public Node root;

   public SplayTree(Node head) {
      this.root = head;
   }

   public void search(Object value) {
      search(root, value);
   }

   public void search(Node node, Object value) {
      if (value == root.value) {
         // ah, searching for the root?
         return;
      }
      if (value.equals(node.value)) {
         System.out.printf("Found %s, node = %s\n", value, node);
         Node parent = node.getParent();

         if (parent == root) {
            // performs Zig operation on the
            // tree, root is orphaned, with no parents

            boolean isAtLeft = parent.getLeft() == node;
            if (isAtLeft) {
               parent.setLeft(node.getRight());
               node.setRight(parent);
            } else {
               parent.setRight(node.getLeft());
               node.setLeft(parent);
            }
            // now node is orphan, no parents
            node.orphan();
            root = node;
         } else if (ofZigZigOrder(node)) {
            boolean left = zigZagMatchedLeft(node);

            Node initial = node;
            Node zigZagStartNode = null;

            while (true) {
               Node upper = initial.getParent();
               if (upper == null) {
                  // found the root node
                  // so, zigZagStartNode is null,
                  // the result will be set as the root
                  break;
               }
               if ((left ? upper.getLeft() : upper.getRight()) == initial) {
                  // the zig starts from here
                  initial = upper;
               } else {
                  zigZagStartNode = upper;
                  break;
               }
            }

            do {
               Node futureRoot;

               if (left) {
                  futureRoot = initial.getLeft();
                  initial.setLeft(futureRoot.getRight());
                  futureRoot.setRight(initial);
               } else {
                  futureRoot = initial.getRight();
                  initial.setRight(futureRoot.getLeft());
                  futureRoot.setLeft(initial);
               }

               initial = futureRoot;
            } while (initial != node);


            if (zigZagStartNode == null) {
               root = initial;
            } else {
               if (left) {
                  zigZagStartNode.setRight(initial);
               } else {
                  zigZagStartNode.setLeft(initial);
               }
            }
         } else {
            // Zag Zag operation on the node element


            Node grandparent = parent.getParent();
            Node greatGrandParent = grandparent.getParent();


            boolean grandparentAtLeft;
            if (greatGrandParent != null) {
               grandparentAtLeft = greatGrandParent.getLeft() == grandparent;
            } else {
               // we are at the root
               grandparentAtLeft = false;
            }

            grandparent.setLeft(node.getRight());
            parent.setRight(node.getLeft());

            node.setLeft(parent);
            node.setRight(grandparent);

            if (greatGrandParent == null) {
               // we are at the root, so modify the
               // root node

               root = node;
            } else if (grandparentAtLeft) {
               greatGrandParent.setLeft(node);
            } else {
               greatGrandParent.setRight(node);
            }
         }
         return;
      }
      // todo fix it

      Object nValue = node.value;
      int compare = compareValues(value, nValue);

      if (compare == -1) {
         // ah, dont do binary search, its a
         // unknown value type

         if (node.getLeft() != null) search(node.getLeft(), value);
         if (node.getRight() != null) search(node.getRight(), value);
      } else if (compare == 1) {
         search(node.getLeft(), value);
      } else {
         search(node.getRight(), value);
      }
   }

   private static int compareValues(Object value,
                                        Object nValue) {
      switch (value.getClass().getSimpleName()) {
         case "int":
            return (int) nValue > (int) value ? 1 : 0;
         case "float":
            return (float) nValue > (float) value ? 1 : 0;
         case "double":
            return (double) nValue > (double) value ? 1 : 0;
         case "long":
            return (long) nValue > (long) value ? 1 : 0;
      }
      return -1;
   }

   private boolean ofZigZigOrder(Node node) {
      // if the node's parent have the child
      // listed as left, then the parent's parent (node's grandparent)
      // must also hold the parent to the same position
      // as it holds the node
      Node parent = node.getParent();
      Node grandparent = parent.getParent();

      if (parent.getLeft() == node && grandparent.getLeft() == parent) {
         return true;
      }
      return parent.getRight() == node && grandparent.getRight() == parent;
   }

   private boolean zigZagMatchedLeft(Node node) {
      Node parent = node.getParent();
      Node grandparent = parent.getParent();
      
      return parent.getLeft() == node && grandparent.getLeft() == parent;
   }
}
