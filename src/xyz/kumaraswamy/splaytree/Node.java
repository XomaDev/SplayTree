package xyz.kumaraswamy.splaytree;

public class Node {

   public static Node child(Object value) {
      return new Node(value, null, null);
   }

   public static Node node(Object value, Node left, Node right) {
      return new Node(value, left, right);
   }


   public final Object value;
   private Node left;

   private Node right;

   private Node parent;

   public Node(Object value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;

      if (left != null) left.parent = this;
      if (right != null) right.parent = this;
   }

   public void reverseChildren() {
      if (left != null && right != null) {
         Node left = this.left;

         this.left = this.right;
         this.right = left;

         this.left.reverseChildren();
         this.right.reverseChildren();
      }
   }

   public void orphan() {
      parent = null;
   }


   public Node getParent() {
      return parent;
   }

   public void setLeft(Node left) {
      if (left != null) left.parent = this;
      this.left = left;
   }

   public Node getLeft() {
      return left;
   }

   public void setRight(Node right) {
      if (right != null) right.parent = this;
      this.right = right;
   }

   public Node getRight() {
      return right;
   }

   @Override
   public String toString() {
      if (left == null && right == null) {
         return "< " + value + " >";
      }
      return "Node{" +
              "value=" + value +
              ", left=" + left +
              ", right=" + right +
              '}';
   }
}
