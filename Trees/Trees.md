### DECLARING TREES
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

### Depth of a Binary Tree
A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
```
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        else{
            int ldepth = maxDepth(root.left);
            int rdepth = maxDepth(root.right);
            System.out.println(ldepth);
            return Math.max(ldepth, rdepth) + 1;
        }
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
        if (node1 == null || node2 == null) return false;
        // Check if the current nodes have the same data value and recursively check their left and right subtrees
        return ((node1.data == node2.data)
                && isIdentical(node1.left, node2.left)
                && isIdentical(node1.right, node2.right));
    }
```

### Symmetric binary tree
```
class Solution {
    public boolean isSymmetric(Node root) {
        if (root == null) {return true;    //an empty tree would be considered symmetric
        // Call the utility function to check symmetry of subtrees left and right
        return isSymmetricUtil(root.left, root.right);
    }

    private boolean isSymmetricUtil(Node root1, Node root2) {
        if (root1 == null || root2 == null){     //Check if either subtree is null If one subtree is null, the other must also be null for symmetry
            return root1 == root2;
        }
        // Check if the data in the current nodes is equal and recursively check for symmetry in subtrees
        return (root1.data == root2.data)
                && isSymmetricUtil(root1.left, root2.right)
                && isSymmetricUtil(root1.right, root2.left);
    }
}
```

