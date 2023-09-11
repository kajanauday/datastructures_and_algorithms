package dsa.linkedlists;

public class SingleLinkedList {
    SNode head = null;
    SNode tail = null;
    SNode temp = null;
    int count = 0;

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        int[] dataArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        singleLinkedList.insertElementAt(1, 10);
        singleLinkedList.insertElementAt(dataArray);
        singleLinkedList.deleteElementAt(111);
        singleLinkedList.searchElement(10);
        singleLinkedList.traverseLinkedList();
    }

    private void deleteElementAt(int index) {
        if (index < 1 || index > count || index < 0) {
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

    private void insertElementAt(int data, int index) {
        SNode snode = new SNode(data);
        count++;
        if (head == null) {
            head = tail = snode;
            if (index > 0)
                System.out.println("list is empty, inserting as head....\n");
            else
                System.out.println("inserted the first record....\n");
        } else {
            temp = head;
            for (int i = 1; i < index - 1; i++) {
                temp = temp.next;
            }
            snode.next = temp.next;
            temp.next = snode;
            System.out.println("inserted new element [" + data + "] at index [" + index + "]\n");
        }

    }

    private void insertElementAt(int[] dataArray) {
        for(int data:dataArray) {
            SNode snode = new SNode(data);
            count++;
            if (head == null) {
                System.out.println("list is empty, inserting as head....\n");
                head = tail = snode;
            } else {
                System.out.println("inserting element at end of list....\n");
                tail.next = snode;
                tail = snode;
            }
        }
    }

    private void traverseLinkedList() {
        if (head == null) {
            System.out.println("Empty List...");
            return;
        }
        for (SNode node = head; node != null; node = node.next) {
            System.out.print(node.data);
            System.out.print(" ");
        }
    }

    private void searchElement(int data){
        int indexOfElement =0;
        for(SNode temp=head;temp!=null;temp=temp.next){
            indexOfElement++;
            if(temp.data == data){
                System.out.println("["+data+"] found at index["+indexOfElement+"]\n");
                return;
            }
        }
        if(indexOfElement==count){
            System.out.println("["+data+"] not there in the list\n");
        }
    }
}
