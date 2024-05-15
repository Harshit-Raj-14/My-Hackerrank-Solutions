### DECLARING LINKED LIST
```
class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;

    SinglyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
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

### 
