### DECLARING LINKED LIST
```
class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
    }
}

class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertNode(int nodeData) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

        if (this.head == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }

        this.tail = node;
    }
}

class SinglyLinkedListPrintHelper {
    public static void printList(SinglyLinkedListNode node, String sep) {
        while (node != null) {
            System.out.print(node.data);

            node = node.next;

            if (node != null) {
                System.out.print(sep);
            }
        }
    }
}
```

### PRINT LINKED LIST
```
    static void printLinkedList(SinglyLinkedListNode head) {
        while(head!=null){
            System.out.println(head.data+" ");
            head=head.next;
        }
    }
```
OR - REDECLARING A NEW NODE
```
static void printLinkedList(SinglyLinkedListNode head) {
        SinglyLinkedListNode current = head;
        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
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

**The reason ehre we used temp is actually a way to traverse a LL, since, by updating the curr with their next, not onyl we are travelling it but the data gets modified. So, hea dis still our original LL, but wit the finall address pointing to data node.**

### Insert a Node at the Tail of a Linked List - RECURSION
```
    static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data){
        if(head==null) head = new SinglyLinkedListNode(data);
        else head.next = insertNodeAtTail(head.next, data);
        return head;
    }
```

### Insert a node at the head of a linked list
```
    static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode headp, int data) {    //headp -> pointer to the head node of a linked list
        SinglyLinkedListNode node = new SinglyLinkedListNode(data);
        node.next=headp;
        return node;
    }
```

### Insert a node at a specific position in a linked list
```
    public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int pos) {
    SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
    if(pos==0){
        newNode.next = head;
        return newNode;
    }
    SinglyLinkedListNode curr = head;
    for(int i=0;i<pos-1;i++){  //two position before pos
        curr = curr.next;
    }
    newNode.next = curr.next;
    curr.next = newNode;
    return head;
    }
```
read - https://www.prepbytes.com/blog/linked-list/insert-a-node-at-a-specific-position-in-a-linked-list/

### Delete a Node in a Linked List
**ITERATIVE SOLUTION**
```
    public static SinglyLinkedListNode deleteNode(SinglyLinkedListNode head, int pos){
        if(head == null) return null;  // Check if the list is empty
        if(pos == 0) return head.next; // Deleting the head node
        SinglyLinkedListNode curr = head;
        for(int i=0;i<pos-1;i++){
            curr=curr.next;
        }
        curr.next=curr.next.next;
        return head;
    }
```

### Print in Reverse
**USING TAIL RECURSION**
```
    public static void reversePrint(SinglyLinkedListNode head) {
        if(head==null) return;    //base case
        reversePrint(head.next);    //moving forward in linked list
        System.out.println(head.data);    //calling back the print function after eachign end of tree stack
    }
```

### Reverse a linked list
**USING STACK O(n), O(N)**
```
    public static SinglyLinkedListNode reverse(SinglyLinkedListNode head) {
        Stack<Integer> stack = new Stack<>();
        SinglyLinkedListNode curr = head;
        while(curr!=null){
            stack.push(curr.data);
            curr=curr.next;
        }
        curr=head;  //reset curr to head
        while(!stack.isEmpty()){
            curr.data=stack.peek();
            stack.pop();
            curr=curr.next;
        }
        return head;
    }
```

**TAIL RECURSION O(n), O(n)**
```
public static SinglyLinkedListNode reverse(SinglyLinkedListNode head) {
    return solve(head, head, null);
}

public static SinglyLinkedListNode solve(SinglyLinkedListNode head, SinglyLinkedListNode curr, SinglyLinkedListNode prev) {
    if (head == null) return head;
    if (curr.next == null) {
        head = curr;
        curr.next = prev;
        return head;
    }
    SinglyLinkedListNode temp = curr.next;
    curr.next = prev;
    return solve(head, temp, curr); // Capture return value and return it
}
```

**USING RECURSION**
```
    public static SinglyLinkedListNode reverse(SinglyLinkedListNode head){
        if(head==null || head.next==null) return head;
        SinglyLinkedListNode secondpart = reverse(head.next); //reverse the rest list
        head.next.next = head;  //put the first element at the end
        head.next=null;
        return secondpart;
    }
    
    /*
    LOGIC---
    Divide the list in two parts – first node and rest of the linked list.
    Call reverse for the rest of the linked list.
    Link the rest linked list to first.
    Fix head pointer to NULL

SEE PIC - https://media.geeksforgeeks.org/wp-content/uploads/20240502111953/Reverse-a-Linked-List-Recursively.webp
    */
```

**ITERATION O(n), O(1)**
```
    public static SinglyLinkedListNode reverse(SinglyLinkedListNode head){
        SinglyLinkedListNode prev = null;
        SinglyLinkedListNode curr = head;
        SinglyLinkedListNode next = null;
        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }
    
    /*
    Initialize three pointers prev as NULL, curr as head, and next as NULL.
    Iterate through the linked list. In a loop, do the following:
        Before changing the next of curr, store the next node 
            next = curr -> next
        Now update the next pointer of curr to the prev 
            curr -> next = prev 
        Update prev as curr and curr as next 
            prev = curr 
            curr = next     
    */
```


### Merge two sorted linked lists
```
static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2){
        SinglyLinkedList ans = new SinglyLinkedList();
        SinglyLinkedListNode curr1 = head1;
        SinglyLinkedListNode curr2 = head2;
        while(curr1!=null && curr2!=null){
            if(curr1.data>=curr2.data){
                ans.insertNode(curr2.data);
                curr2=curr2.next;
            }
            else if(curr1.data<=curr2.data){
                ans.insertNode(curr1.data);
                curr1=curr1.next;
            }
        }
        while(curr1!=null){
            ans.insertNode(curr1.data);
            curr1=curr1.next;
        }
        while(curr2!=null){
            ans.insertNode(curr2.data);
            curr2=curr2.next;
        }
        return ans.head;    //need to return the pointer of our ans ll -> refers to the head node of this singly linked list.
}
```

