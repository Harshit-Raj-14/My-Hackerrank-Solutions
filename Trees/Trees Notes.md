## Trees
root(the top node), children, leaf node(node with no children)
ancestors -> parent, grandparent, and so on to a node are all its ancestors

## Types of Trees
* Full Binary Tree -> every node has 0 or 2 children.
* Complete binary Tree -> all levels are completely filled except the last level -> also the last level needs to have all nodes to the left, and not a missing node in between and then on right
* Perfect Binary Tree -> all leaf nodes are at same level
* Balanced Binary Tree -> maximum  height of tree is log(n)
* Degenerate Tree -> every node has a single children (skewed) (~also can be called a linked list)

## Binary Search Tree
A tree in which all the elements in left subtree (child nodes in left) is less than the parent node, and all the elements in right subtree (child nodes in the right) are greater than the parent node. This condition will exist through each and every node.
This was done to make searching easier.


### levels in trees
Root is at level 0, thne next row will be level 1 and so on.
These levles also help us to define depth of a node. so, if level of a node is 1 => its depth is also 1.
Infact depth is the distance between the node and the root.
Height of binary tree -> the ditance between the root and deepest node. So, it will be equal to the level of deepest node.
Degree of a node -> number of children nodes it is connected to. Hnece degree of a leaf node is 0.



### DECLARING TREES
The root node has an integer value stored, with two more values stored as left pointer and right pointer. Which will further point to left and right child. And so on... 
If it needs to stop it will point to null.
```
class Node{
  int data;
  Node left;
  Node right;

  public Node(key){	//constructor
    data=key;
}

main(){
  Node root = new Node(1);	//1 is created, witht wo refrences(pointer) left and right
  root.left = new Node(2);	//2 is stored at the place where root's left poitner points
  root.right = new Node(3);	//3 is stored at the place where root's right pointe points
  root.right.left = new Node(5);
}
```

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;

 *     TreeNode() {}

 *     TreeNode(int val){ 
         this.val = val; 
       }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
```

### TREE TRAVERSAL
Remember :
* Depth First Search or DFS:-
  * Preorder Traversal : Root | Left | Right
  * Postorder Traversal: Left-Right-Root
  * Inorder Traversal: Left-Root-Right

* Level Order Traversal or Breadth First Search or BFS

### Preorder Traversal : Root | Left | Right
```
class Solution {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root!=null){
            ans.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return ans;
    }
}
```
Time Complexity: O(N) 
Auxiliary Space: O(N) due to recursive stack.

### Postorder Traversal: Left-Right-Root
```
class Solution {
    ArrayList<Integer> ans = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root!=null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            ans.add(root.val);
        }
        return ans;
    }
}
```

### Inorder Traversal: Left-Root-Right
```
class Solution {
    ArrayList<Integer> ans = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root!=null){
            inorderTraversal(root.left);
            ans.add(root.val);
            inorderTraversal(root.right);
        }
        return ans;
    }
}
```

### Level Order Traversal
```
class Solution{
    static ArrayList <Integer> levelOrder(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if(curr!=null){
                list.add(curr.data);
                if(curr.left!=null) queue.add(curr.left);
		if(curr.right!=null) queue.add(curr.right);
            }
        }
        return list;
    }
}

/*
LOGIC---
Level Order traversal-> BFS
We are takign a element and processing it in same order.
First level then enxt level and so on.
And each level is traversed from left to right.

Since we need a specific order, we use Queue -> first in-first out.
So, let's take advantage of this fact.

APPROACH---
Put the root node in queue.
When you pop the node now, push the right and left child in queue.
Repeat the process.
*/
```

### Insert a Node at the Tail of a Linked List - ITERATION
```
    static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data){
        SinglyLinkedListNode node = new SinglyLinkedListNode(data);     // Create a new node with the given data
        if(head==null) return node;
        SinglyLinkedListNode temp = head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=node;    //Since pointer of head and temp are actually the same addresses, by adding data to the end of temp, the same data gets added to head as well
        return head;
    }
```

### Height of a Binary tree
The height of a binary tree is the number of edges between the tree's root and its furthest leaf. Basically the number of lines joining between the root node and farthest  leaf node.

Logic - Recursively calculate the height of the left and the right subtrees of a node and assign height to the node as max of the heights of two children plus 1.
```
public static int height(Node root) {
      	if(root==null) return -1;
        return Math.max(height(root.left), height(root.right)) + 1;
}
```
**MII - Using QUEUE  => IN-ORDER =>Iterative Approach**
```
public static int height(Node root){
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    int levels=0;   //number of levels in tree
    while(!queue.isEmpty()){
        int nodecountatlevel = queue.size();
        while(nodecountatlevel>0){
                Node curr = queue.poll();
                if(curr.left!=null) queue.add(curr.left);
                if(curr.right!=null) queue.add(curr.right);
                nodecountatlevel--;
        }
        levels++;
    }
    return levels-1;
}
/*
LOGIC---
Height of binary tree = levels in a binary tree
TC-O(n), SC-O(n)
We have optimised space by using one queue. While the recurison would use two stack space
*/
```

### Depth of a Binary Tree || same as height of a binary tree
A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
```
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        int depth_l = maxDepth(root.left)+1;
        int depth_r = maxDepth(root.right)+1;
        return Math.max(depth_l, depth_r);
    }
}
```

### Diameter of a Binary Tree
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
```
class Solution {
    int diameter=0;
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter - 1;
    }

    public int height(TreeNode root){
        if(root==null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int dia = leftHeight + rightHeight + 1;
        diameter = Math.max(diameter, dia);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```

Traversal of Each Node: The method uses a depth-first search (DFS) approach to calculate the height of the tree. In this process, each node in the binary tree is visited exactly once.
Height Calculation: For each node, the algorithm computes the heights of the left and right subtrees. 
This operation involves constant time work (basic arithmetic and comparisons) for each node. overall space complexity can be summarized as:
O(H), where H is the height of the tree. In the worst case (skewed tree), this can be O(N), and in the average case (balanced tree), it would be O(log N).

Calculate three options for the diameter:
op1: Diameter of the left subtree.
op2: Diameter of the right subtree.
op3: Sum of depths of left and right subtrees.

### Depth and Height of a given node in a Binary Tree
```

```

### Lowest Common Ancestor 
Lowest Common Ancestor(LCA): The lowest common ancestor is defined between two nodes x and y as the lowest node in T that has both x and y as descendants (where we allow a node to be a descendant of itself.
```
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // result
        if (left == null) return right;
        else if (right == null) return left;
        else return root;  // both left and right are not null, we found our result
    }
}
```

### Invert a tree or Mirror a tree
```
/* USING QUEUE */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode curr = queue.remove();
            if(curr.left != null) queue.add(curr.left);
            if(curr.right != null) queue.add(curr.right);
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
        }
        return root;
    }
}
```
The "node" is the whole space of memory the "node.val" is just the numeric value of the space of memory, if you are changing the "node" and not the value then you return the whole node and not the value.

```
/* Recursive Solution */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return root;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }
}
```
BOTH METHODS HAVE
TC: O(n)
SC: O(n)

### Check if a tree is a binary search tree
```
boolean checkBST(Node root) {
       return checkBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
}
 boolean checkBST(Node root, int minValue, int maxValue){
       if(root == null) return true;
       if(root.data >= maxValue || root.data <= minValue) return false;
       return checkBST(root.left, minValue,root.data) && checkBST(root.right,root.data,maxValue); 
}
```

### Check if the Binary Tree is Balanced Binary Tree
A Binary Tree is balanced if, for all nodes in the tree, the difference between left and right subtree height is not more than 1.
```
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null)  return true;
        // Height Function will return -1, when it’s an unbalanced tree...
		    if (Height(root) == -1)  return false;
		    return true;
	}
    // Create a function to return the “height” of a current subtree using recursion...
	public int Height(TreeNode root) {
		if (root == null)  return 0;  // Base case...
		int leftHeight = Height(root.left);  // Height of left subtree...
		int rightHight = Height(root.right);  // Height of height subtree...
		if (leftHeight == -1 || rightHight == -1)  return -1;  // In case of left subtree or right subtree unbalanced, return -1...
    if (Math.abs(leftHeight - rightHight) > 1)  return -1;  // If their heights differ by more than ‘1’, return -1...
		return Math.max(leftHeight, rightHight) + 1;  // Otherwise, return the height of this subtree as max(leftHeight, rightHight) + 1...
    }
}
```
```
class Solution {
    public boolean isBalanced(Node root) {
        // If the tree is empty, it's balanced
        if (root == null) return true;

      // Calculate the height of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Check if the absolute difference in heights
        // of left and right subtrees is <= 1
        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right)) {
            return true;
        }
        // If any condition fails, the tree is unbalanced
        return false;
    }

   // Function to calculate the height of a subtree
    public int getHeight(Node root) {
        // Base case: if the current node is NULL,
        // return 0 (height of an empty tree)
        if (root == null) return 0;

        // Recursively calculate the height
        // of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Return the maximum height of left and right subtrees
        // plus 1 (to account for the current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```

### Identical trees or not

Two trees are said to be identical if these three conditions are met for every pair of nodes :'
Value of a node in the first tree is equal to the value of the corresponding node in the second tree.
Left subtree of this node is identical to the left subtree of the corresponding node.
Right subtree of this node is identical to the right subtree of the corresponding node.

```
public class Solution {
    public boolean isIdentical(Node node1, Node node2) {
        if (node1 == null && node2 == null) return true;  //If both nodes are NULL, they are identical
        if ((node1==null && node2!=null) || (node1!=null && node2 == null)) return false;
        // Check if the current nodes have the same data value and recursively check their left and right subtrees
        return ((node1.data == node2.data)
                && isIdentical(node1.left, node2.left)
                && isIdentical(node1.right, node2.right));
    }
```

### Symmetric binary tree
```
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isSameTree(root.left, root.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p==null && q==null) return true;
        else if((p!=null && q==null) || (p==null && q!=null)) return false;
        return (p.val==q.val && isSameTree(p.left, q.right) && isSameTree(p.right, q.left));
    }
}

/*
LOGIC---
Symmetric binary tree is checking like the two halves of trees are same or not.
So, we think of the two halves as two seperate trees divided in half. 
If these two divided trees are same they are symmetric.

But just a small differnece, becuase we are checking for mirror => the nodes will be laterally inverted.
So, left will become right and right will become left.
Meaning, left subtree's right node should be same as right subtree's left node.

Recursive conditon will be - isSameTree(p.left, q.right) && isSameTree(p.right, q.left)
*/
```

