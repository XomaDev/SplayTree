# Splay Tree Algorithm

Whenever an element is accessed by binary search, the element gets closer to the root node or becomes the root node itself.
<br>

This helps to reduce the time taken to find the T element, if accessed frequently by keeping the more frequently accessed elements closer to the node.
<br>
<br>

![img.png](images%2Fimg.png)
(wikipedia image)

````java
// Example of slaying an element, by Zag rotation

Node zagRotation = 
        node(11,
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

// Node output

Node{value=13, // <----- as we searched for Number 13, now it is the root node
        left=Node{value=11, 
           left=Node{value=9, 
               left=Node{value=7, 
                   left=< 1 >, 
                   right=< null >}, 
           right=< 10 >}, 
               right=< 12 >}, 
        right=Node{value=14, 
           left=< null >,
           right=< 29 >}}
````
