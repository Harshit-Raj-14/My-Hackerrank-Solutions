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
