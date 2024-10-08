## Binary Search Tree
A tree in which all the elements in left subtree (child nodes in left) is less than the parent node, and all the elements in right subtree (child nodes in the right) are greater than the parent node. This condition will exist through each and every node.
This was done to make searching easier.

=> Each node has a maximum of two elements.

=> Search TC- O(nlogn)

## Search in a Binary Search Tree
```
class Solution {
    public TreeNode searchBST(TreeNode root, int x) {
        return solve(root, x);
    }

    public static TreeNode solve(TreeNode curr, int x){
        if(curr==null) return null;
        if(curr.val==x) return curr;
        if(curr.val>x) return solve(curr.left, x);     //search left side since x is smaller than node
        if(curr.val<x) return solve(curr.right, x);    //try searching right side, x is greater than node
        return null;
    }
}
```

### 108. Convert Sorted Array to Binary Search Tree
A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
```
class Solution {
    public TreeNode sortedArrayToBST(int[] nums){
        int l=0;
        int r=nums.length-1;
        return growTree(nums, l, r);
    }

    public static TreeNode growTree(int nums[], int l, int r){
        if(l>r) return null;
        int mid=(l+r)/2;    //make the middle element our root
        TreeNode root = new TreeNode(nums[mid]);   //all subtrees accumulates in root
        root.left = growTree(nums, l, mid-1);   //smaller elements will happen to the left of mid, so bring r to mid-1
        root.right = growTree(nums, mid+1, r);  //larger element will happen to the right of mid, so bring l to mid+1
        return root;
    }
}

/*
LOGIC---
Since in a height balanced binary search tree, the height can at max differ by 1 between left nad right subtree.
Hence, it makes sense to make the mid element the root node.

Approach---
If the array is empty, return null.
Find the middle element of the array and create a new node with its value.
Recursively construct the left subtree using the left half of the array.
Recursively construct the right subtree using the right half of the array.
Set the left and right child of the node created in step 2 to the root of the left and right subtree respectively.
Return the root node.

*/
```

### 938. Range Sum of BST
Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].
```
class Solution {
    public int rangeSumBST(TreeNode root, int l, int r) {
        return solve(root, l, r);
    }

    public static int solve(TreeNode curr, int l, int r){
        if(curr==null) return 0;    // Base case: if the node is null, return 0
        int ans=0;
        // Add the value of the current node if it's within the range
        if(curr.val>=l && curr.val<=r) ans+= curr.val;
        // Traverse left subtree if the current node's value is greater than l
        if(curr.val>l) ans+= solve(curr.left, l, r);
        // Traverse right subtree if the current node's value is less than r
        if(curr.val<r) ans+= solve(curr.right, l, r);
        return ans;
    }
}

/*
LOGIC---
Travel through BST according to the given range conditions.
TC-O(n), SC-O(n)
*/
```
## Delete a node from Binary Search Tree / Binary Tree
