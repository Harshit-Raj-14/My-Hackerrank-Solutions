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
