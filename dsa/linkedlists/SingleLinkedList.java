package dsa.linkedlists;

public class SingleLinkedList {
    SNode head = null;
    SNode tail = null;
    SNode temp = null;
    int count = 0;

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        singleLinkedList.createNewNode(data);
        singleLinkedList.insertElementAt(1, 10);
        singleLinkedList.deleteElement(111);
        singleLinkedList.traverseLinkedList();
    }
    private void deleteElement(int index){
        if(index<1 || index>count){
            System.out.println("invalid index or empty list");
            return;
        }
        temp = head;
        for (int i = 1; i < index - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        count--;
    }
    private void insertElementAt(int index, int data) {
        SNode snode = new SNode(data);
        count++;
        if (head == null) {
            head = tail = snode;
            if (index > 0)
                System.out.println("List is empty: inserting as head...");
            else
                System.out.println("inserted the record");
        } else {
            if(index==1){
                snode.next = head;
                head = snode;
            } else {
                temp = head;
                for (int i = 1; i < index - 1; i++) {
                    temp = temp.next;
                }
                snode.next = temp.next;
                temp.next = snode;
            }
        }

    }
    private void traverseLinkedList() {
        for (SNode node = head; node != null; node = node.next) {
            System.out.print(node.data);
            System.out.print(" ");
        }
    }
    private void createNewNode(int[] data) {
        for (int d : data) {
            SNode snode = new SNode(d);
            if (head == null) {
                head = snode;
                tail = snode;
            } else {
                tail.next = snode;
                tail = snode;
            }
            count++;
        }
    }
}
