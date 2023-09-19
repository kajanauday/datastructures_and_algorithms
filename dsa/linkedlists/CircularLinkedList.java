package dsa.linkedlists;

public class CircularLinkedList {
    static CNode head = null;
    CNode tail = null;
    CNode temp = null;
    int count = 0;

    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] data1 = {11, 12, 13, 14, 15, 16, 17, 18, 19};
        int singleData = 404;
        circularLinkedList.insertData(data1);
        circularLinkedList.insertData(data, 1);
        circularLinkedList.insertData(singleData);
        circularLinkedList.insertData(singleData,1);
        circularLinkedList.traverseCircularLinkedList(head);
    }
    private void insertData(int data) { insertData(new int[] {data}); }
    private void insertData(int data, int index) { insertData(new int[] {data},index); }
    private void insertData(int[] data) { insertData(data, count); }
    private void insertData(int[] data, int index) {
        CNode hold = null;
        CNode startNode = null;
        CNode endNode = null;
        int noOfElemnts = 0;
        temp = head;
        for (int d : data) {
            CNode cnode = new CNode(d);
            if (startNode == null) {
                startNode = cnode;
                endNode =cnode;
            }
            endNode.next = cnode;
            endNode = cnode;
            noOfElemnts++;
        }
        if (head == null && tail == null) {
            head = startNode;
            tail = endNode;
            tail.next = head;
        } else if (index < 2) {
            endNode.next = head;
            tail.next = head = startNode;
        } else if (index < count) {
            for (int i = 1; i < index - 1; i++) {
                temp = temp.next;
            }
            hold = temp.next;
            temp.next = startNode;
            tail = endNode;
            tail.next = hold;
        }
        else if(index==count){
            tail.next=startNode;
            tail = endNode;
            tail.next = head;
        }
        count = count + noOfElemnts;
    }
    private void traverseCircularLinkedList(CNode temp) {
        for (int i = 0; i < count; i++) {
            System.out.print(temp.data + "--> ");
            temp = temp.next;
        }
    }

}
