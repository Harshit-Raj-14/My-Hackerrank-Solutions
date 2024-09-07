## 637. Average of Levels in Binary Tree
```
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        ArrayList<Double> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int nodecountatlevel=queue.size();
            int levellength=queue.size();
            double levelsum=0;
            while(nodecountatlevel-->0){
                TreeNode curr = queue.poll();
                if(curr!=null){
                    levelsum+=curr.val;
                    if(curr.left!=null) queue.add(curr.left);
                    if(curr.right!=null) queue.add(curr.right);
                }
            }
            list.add(levelsum/(levellength));
        }
        return list;
    }
}

/*
LOGIC---
Traverse tree in-order traversal and find average accordingly level wise.
*/
```

### 938. Range Sum of BST
```
class Solution {
    public int rangeSumBST(TreeNode root, int l, int r) {
        return solve(root, l, r);
    }

    public static int solve(TreeNode curr, int l, int r){
        if(curr==null) return 0;    // Base case: if the node is null, return 0
        int ans=0;
        // Add the value of the current node if it's within the range
        if(curr.val>=l && curr.val<=r){
            System.out.println(curr.val);
            ans+= curr.val;
        }
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
*/
```


### 111. Minimum Depth of Binary Tree
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.
```
/* ITERATIVE SOLUTION */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level=1;
        while(!queue.isEmpty()){
            int nodesatlevel=queue.size();
            while(nodesatlevel-->0){
                TreeNode curr = queue.poll();
                if(curr.left==null && curr.right==null) return level;   //a leaf node
                if(curr.left!=null) queue.add(curr.left);
                if(curr.right!=null) queue.add(curr.right);
            }
            level++;
        }
        return 0;
    }
}

/*
LOGIC---
Minimum depth = topmost node with no children
In recursion we were goign through all nodes, but in fact if we move level wise we can encounter the first leaf node
and won't need to check the lower part of tree. this makes it efficient.
*/
```

```
/* RECURSION */
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        if(root.left==null) return 1+minDepth(root.right);
        if(root.right==null) return 1+minDepth(root.left);
        int lheight = 1 + minDepth(root.left);
        int rheight = 1 + minDepth(root.right);
        return Math.min(lheight, rheight);
    }
}
```
