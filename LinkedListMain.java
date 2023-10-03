import java.util.Scanner;

class Node <T> {
    T data;
    Node next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList <T> {
    int size = 0;
	private Node head;
    public LinkedList() {
        this.head = null;
    }

    // Method to insert a new node 
    public void insert(T data, int pos) {
        Node newNode = new Node(data);
        if (pos == 1 ) {
            newNode.next = head;
			head = newNode;
			size++;
            return;
        }
		else if (pos == size+1){
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
			size++;
			return;
		}
		else {
			int count = 1;
			Node current = head;
			while (count < (pos-1)){
	           current = current.next;
	           count++;
	        }
	        newNode.next = current.next;
	        current.next = newNode;
	        size++;
			return;
		}

    }

    /*// Method to delete a node by value
    public void delete(int data) {
        if (head == null) {
            return;
        }

        if (head.data == data) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }*/
	// Method to delete a node by position
	public void delete(int pos) {
        if (head == null) {
            return;
        }
		else if(pos == 1){
			head = head.next;
			size--;
            return;
		}
        else if (pos == size) {
			Node current = head;
			int count = 1;
			while (count <(pos-1)){
				current = current.next;
	        	count++;
            }
            current.next = null;
			size--;
			return;
		}
            
        else{
			Node current = head;
			int count = 1;
			while(count < pos-1) {
				current = current.next;
				count++;
			}
            current.next = current.next.next;
			size--;
			return;
        }
    }
	// Method to print the elements of the list
    public void display() {
        Node current = head;
		System.out.print("[ ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
		System.out.print("]");
        System.out.println();
    }
}
public class LinkedListMain {
    public static void main(String[] args) {
        LinkedList myList = new LinkedList();
        
        myList.insert(100, 1);
        myList.insert(200, 2);
        myList.insert(300, 3);
		myList.insert("Teo", 4);
		myList.insert(201, 3);
		myList.insert(2.3f, 1);
		myList.insert(false, 7);
        myList.display(); 
        myList.delete(2);
        myList.display(); 
		myList.delete(1);
		myList.display(); 
		myList.delete(5);
		myList.display(); 
		
    }
    }
          