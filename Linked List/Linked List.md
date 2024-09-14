### TECHNIQUES TO SOLVE LINKED LIST PROBLEMS
### Lead-lag pointers
Two pointers traverse a linked list at the same speed but at different intervals.
```
//declare lead and lag pointer
Node lag=head;
Node lead=head.next.next;  //or can be anywhere on LL
while(lead!=null){
  lead=lead.next;  //both travel with same speed
  lag=lag.next;
}
```
### SLOW AND FAST POINTER
Two pointers traverse a linked list a different speeds.
```
Node slow = head;
Node fast = head;
while(fast!=null && fast.next!=null){  //fast is traversing twice so check it does not become null properly
  fast=fast.next.next;
  slow=slow.next;
}
```

## Linked-List Reversal
The head of linked list becomes tail and all pointers are reversed. => teer piche marne wali technique.
We require three pointers for this.
For visalisation you can see this - https://leetcode.com/problems/reverse-linked-list/solutions/5612752/step-by-step-explained-with-images-easiest-to-understand-java-c-python-javascript-go-codes/
```
Node prev=null;
Node curr = head;
Node next=head;
while(curr!=null){
  next = next.next;      
  curr.next = prev;      // Reverse the link
  prev = curr;           // Move prev one step forward
  curr = next;           // Move curr one step forward
}
return prev;           // prev now points to the new head of the reversed list

```

### 234. Palindrome Linked List
```
/* OPTIMISED APPROACH O(n), O(1) */
class Solution {
    public boolean isPalindrome(ListNode head) {
        //Step I: find middle of linked list => fast and slow pointer apporach
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //Now slow has become the other half of ll
        //Step II: reverse the other half of ll => ll reversal technique
        ListNode prev = null;
        ListNode curr = slow;
        ListNode next = slow;
        while(curr!=null){
            next=next.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        //prev will have the other half of ll in reverse 
        //Step III: compare the ll from the start and the middle => lead and lag pointer technique
        curr=head;
        while(prev!=null){
            if(curr.val!=prev.val) return false;
            curr=curr.next;
            prev=prev.next;
        }
        return true;
    }
}
/*
LOGIC---
Algorithm---
Find the middle of the linked list. => use slow and fast pointer approach
Reverse the second half of the list. => use linked list reversal approach on the second half
Compare the first half with the reversed second half. => use lag lead traversal, basically traverse from start and mid of array and check whether they are same or not
*/

/* BRUTE FORCE O(n), O(n) */
Make a copy of linked lsit. reverse it.
Now compare it against the original linked list.
```


### Middle of a Linked List
```
/* O(n/2) SLOW AND FAST POINTER - ONE PASS */
class Solution {
    public int middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow.data;
    }
}
/*
LOGIC---
Fast pointer travels twice and slower pointer travels once. So, when slow pointer reaches the middle, the last pointer should have reached the read.
So, the vice versa is also true, that if fast pointer reaches end, then slow poitner will be standing in the middle.

FOLLOW UP---
The current code returns the second middle when length is even. How would you have returned the first middle?
Use condititon - while(fast!=null && fast.next!=null && fast.next.next!=null)
*/

/* O(2n) TRAVEL TILL MIDDLE */
class Solution {
    int getMiddle(Node head) {
        int len=0;
        Node curr = head;
        while(curr!=null){
            len++;
            curr=curr.next;
        }
        int mid=len/2;    //even(second middle) and in odd case
        curr=head;
        while(mid-->0){
            curr=curr.next;
        }
        return curr.data;
    }
}

/* BRUTE FORCE O(n), O(n) */
class Solution{
    int getMiddle(Node head){
        ArrayList<Integer> list = new ArrayList<>();
        Node curr = head;
        while(curr!=null){
            list.add(curr.data);
            curr=curr.next;
        }
        return list.get(list.size()/2);
    }
}

/*
LOGIC---
The simple idea is to store all the data values of the linked list nodes in an ArrayList and then directly access the middle element by index. 
*/
```


## 92. Reverse Linked List II
```
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev=dummy;
        ListNode temp=head;
        for (int i=1; i<left; i++){ // Move `prev` to the node before `left`
            prev = prev.next;
            temp=temp.next;
        }

        // Start reversing from `left`
        ListNode curr = prev.next;
        ListNode reversePrev = null;
        ListNode next = prev.next;
        for (int i = left; i <= right; i++) {
            next = next.next;
            curr.next = reversePrev;
            reversePrev = curr;
            curr = next;
        }
        prev.next = reversePrev;    //reconnecting the reversed part to the portion before left.
        temp.next = curr;   
        return dummy.next;
    }
}

/*
LOGIC---
The dummy node is a common technique to simplify the logic when dealing with edge cases, such as when the reversal starts at the head of the list (i.e., when left = 1).
Without dummy, if left == 1, prev would point to null, and it would be harder to handle reconnecting the head of the list after reversing.

temp is used to track the original node at position left before the reversal begins. This node is important because, after the reversal, it will become the last node in the reversed section.
After reversing the section from left to right, temp.next should point to curr (the node immediately after the right position), completing the reconnection to the remaining part of the list.

EXAMPLE---
Let's take an example with a list [1, 2, 3, 4, 5], and we want to reverse between left = 2 and right = 4:

Initial list: 1 -> 2 -> 3 -> 4 -> 5

Using dummy:
    dummy.next will point to 1, and prev will be moved to point to 1 (the node before left = 2).

Using temp:
    temp will point to 2 (the node at left before reversing). 
    After reversing the section [2, 3, 4], the list looks like 1 -> 4 -> 3 -> 2.
    Now, temp.next (which is 2.next) should point to 5 (the node after right = 4). 
    Without temp, we wouldn't know how to reconnect the last node in the reversed section.    
*/
```

### 83. Remove Duplicates from Sorted List
```
class Solution {
    public ListNode deleteDuplicates(ListNode head){
        if (head == null) return null; 
        ListNode curr = head;
        while(curr!=null && curr.next!=null){
            if(curr.val==curr.next.val){    //skip duplicate nodes
                curr.next=curr.next.next;
            }
            else curr=curr.next;
        }
        return head;
    }
}
```

### 21. Merge Two Sorted Lists
```
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode curr1=list1;
        ListNode curr2=list2;
        ListNode ans = new ListNode(0);
        ListNode curr = ans;
        while(curr1!=null && curr2!=null){
            if(curr1.val<=curr2.val){
                curr.next=curr1;
                curr1=curr1.next;
            }
            else{
                curr.next=curr2;
                curr2=curr2.next;
            }
            curr = curr.next;  // Move current pointer
        }
        // Attach the remaining elements of either list
        if (curr1!=null) curr.next = curr1;
        if(curr2!=null) curr.next = curr2;
        return ans.next;
    }
}
```
