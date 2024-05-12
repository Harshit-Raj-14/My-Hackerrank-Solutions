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
OR - REDECLARING LINKED LIST
```
static void printLinkedList(SinglyLinkedListNode head) {
        SinglyLinkedListNode current = head;
        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }
    }
```


