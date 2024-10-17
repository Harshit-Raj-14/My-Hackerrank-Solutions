## 124. Binary Tree Maximum Path Sum
```
/* DFS O(n) */
class Solution {
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root){
        solve(root);
        return maxSum;
    }

    public int solve(TreeNode root){
        if(root==null) return 0;
        int ans=Integer.MIN_VALUE;
        int left =Math.max(0, solve(root.left)); // If the sum is negative, ignore the subtree, -ve values
        int right=Math.max(0, solve(root.right));
        //merging at root, completing path
        maxSum = Math.max(maxSum, root.val + left + right);
        //taking only one subtree for one path and carry it upward
        return root.val + Math.max(left, right);
    }
}

/*
LOGIC---
maximum path sum = root node + max of left subtree + max of right subtree
Now we can have this root node or genral node anywhere on tree
    1
   / \
   2 3
Now (2,1,3), (1,2,3), (3,1,2) is a path.
2 is a path in itself, so does 1 or 3.
1,2 is a path, (2,1), (1,3)
So at node 1 we have two choices, either split to left or right.
If we consider only left or right, we can consider it a part and carry it upward if 1 is a child node.
Now if we do 1+2+3. this is a complete path we cant take more.

This will make clear think,
  5
   \
    1
   / \
   2 3

So possible path sum = (2+1+3) | (2+1+5) | (3+1+5)
1,2,3 => is a complete path in itself becuase we took both left and right subtree => merging at 1
2,1,5 => for 5 we only took one path of left on 1 => 5,1,2 => merging at 5
3,1,5 => for 5 we only took one path of right on 1 => 5,1,3 => merging at 5

So, in fact its a choice of getting max between left and right and deciding a joining node

TC- O(n) => every node is travelled once
SC- O(h) -> height of binary tree
*/
```
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


### 1367. Linked List in Binary Tree
Given a binary tree root and a linked list with head as the first node. 
Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.

```
/* ITERATIVE SOLUTION O(n*m), O(n+m) */
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size()!=0){
            int nodesatlevel=queue.size();
            while(nodesatlevel-->0){
                TreeNode curr = queue.poll();
                if(curr.val==head.val){ //if the first value match check whether this can form a downward path
                    if(isDownward(head, curr)) return true;
                }
                if(curr.left!=null) queue.add(curr.left);
                if(curr.right!=null) queue.add(curr.right);
            }
        }
        return false;
    }

    public static boolean isDownward(ListNode head, TreeNode curr){
        // Base cases
        if (head == null) return true; //we reached the end of the linked list, means the entire path matched
        if (curr == null) return false; //binary tree ended before we could match the linked list
        if (head.val != curr.val) return false;
        // Recurse to the left and right subtrees, as eithe rof them can be our downward path from there on
        return isDownward(head.next, curr.left) || isDownward(head.next, curr.right);
    }
}

/*
LOGIC---
Iteratively move through the tree and check whether from a first matching node can you find a downward path while together moving with the linkedlinst.
*/
```

```
/* RECURSIVE SOLUTION O(n*m), O(n+m) */
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root==null) return false;
        //make a recursive check for head and root; make a recursive call of sub path for left and right path to traverse both paths
        return check(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);  
    }

    public boolean check(ListNode head, TreeNode root){
        if(head==null) return true; //covered entire linked list
        if(root==null) return false;
        if(head.val!=root.val) return false;
        return check(head.next, root.left) || check(head.next, root.right); //any of the subtrees could have our path
    }
}
```

## 501. Find Mode in Binary Search Tree
Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
```
/* Count Frequency With Hash Map O(n), O(n) */
class Solution {
    public int[] findMode(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxfreq=0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size()!=0){
            int nodeatlevel=queue.size();
            while(nodeatlevel-->0){
                TreeNode curr = queue.poll();
                map.put(curr.val, map.getOrDefault(curr.val,0)+1);
                maxfreq=Math.max(maxfreq, map.get(curr.val));
                if(curr.left!=null) queue.add(curr.left);
                if(curr.right!=null) queue.add(curr.right);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue()==maxfreq) list.add(entry.getKey());
        }
        int ans[] = new int[list.size()];
        for(int i=0;i<list.size();i++){
            ans[i]=list.get(i);
        }
        return ans;
    }
}

/*
LOGIC---
Find frequency of all node elements and get the maxelement frequency.
*/
```
