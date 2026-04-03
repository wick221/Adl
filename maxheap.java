import java.util.Scanner;

class MaxHeap {
    int[] heap;
    int size;
    int capacity;

    MaxHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        size = 0;
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    int leftChild(int i) {
        return 2 * i + 1;
    }

    int rightChild(int i) {
        return 2 * i + 2;
    }

    // Insert operation
    void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap Overflow");
            return;
        }

        int i = size;
        heap[i] = value;
        size++;

        // Heapify up
        while (i != 0 && heap[parent(i)] < heap[i]) {
            int temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = parent(i);
        }
    }

    // Heapify down
    void maxHeapify(int i) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && heap[left] > heap[largest])
            largest = left;

        if (right < size && heap[right] > heap[largest])
            largest = right;

        if (largest != i) {
            int temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            maxHeapify(largest);
        }
    }

    // Delete maximum element (root)
    int extractMax() {
        if (size <= 0) {
            System.out.println("Heap Underflow");
            return -1;
        }

        if (size == 1) {
            size--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        maxHeapify(0);

        return root;
    }

    // Display heap
    void display() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return;
        }

        for (int i = 0; i < size; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }
}

public class MaxHeapDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter heap capacity: ");
        int capacity = sc.nextInt();
        MaxHeap heap = new MaxHeap(capacity);

        while (true) {
            System.out.println("\n1.Insert  2.Delete Max  3.Display  4.Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter element: ");
                    heap.insert(sc.nextInt());
                    break;

                case 2:
                    int max = heap.extractMax();
                    if (max != -1)
                        System.out.println("Deleted Max Element: " + max);
                    break;

                case 3:
                    System.out.print("Heap: ");
                    heap.display();
                    break;

                case 4:
                    System.exit(0);
            }
        }
    }
}