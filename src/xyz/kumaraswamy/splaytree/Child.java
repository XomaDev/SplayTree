package xyz.kumaraswamy.splaytree;

public class Child extends Node {

   public static Child child(Object value) {
      return new Child(value);
   }

   public final Object value;

   public Child(Object value) {
      super(value, null, null);
      this.value = value;
   }

   @Override
   public String toString() {
      return "Child{" +
              "value=" + value +
              ", parent=" + getParent().value +
              '}';
   }
}
