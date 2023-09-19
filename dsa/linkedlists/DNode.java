package dsa.linkedlists;

public class DNode {
    int data;
    DNode prev;
    DNode next;
    public DNode(int data){
        this.data = data;
        next = null;
        prev = null;
    }
}
