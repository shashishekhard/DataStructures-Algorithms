/**
 * Binary Heap - A complete binary tree satisfying the heap property.
 *
 * A Min-Heap is a complete binary tree where each parent is smaller than or equal to
 * its children. The smallest element is always at the root. Heaps are commonly used
 * for priority queues, heap sort, and finding k-th smallest/largest elements.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Min-Heap Operations
 * ================================================================================================
 *
 * ----- Array Representation of a Complete Binary Tree -----
 *
 *   For node at index i (0-based):
 *     Parent:      (i - 1) / 2
 *     Left child:  2 * i + 1
 *     Right child: 2 * i + 2
 *
 *   Array: [3, 5, 8, 10, 7, 15, 20]
 *   Index:  0  1  2   3  4   5   6
 *
 *   Tree representation:
 *              3          index 0
 *            /   \
 *          5       8      index 1, 2
 *        /   \   /   \
 *      10    7  15   20   index 3, 4, 5, 6
 *
 * ----- INSERT (push) Operation -----
 *
 * Insert 2 into the min-heap:
 *
 *   Step 1: Add 2 at the end (index 7)
 *
 *              3
 *            /   \
 *          5       8
 *        /   \   /   \
 *      10    7  15   20
 *     /
 *   [2]   <-- added here
 *
 *   Step 2: Bubble UP (sift up) - compare with parent, swap if smaller
 *
 *              3                    3                   [2]
 *            /   \                /   \                /   \
 *          5       8           [2]      8            3       8
 *        /   \   /   \        /   \   /   \        /   \   /   \
 *     [2]    7  15   20      5    7  15   20      5    7  15   20
 *     /                     /                    /
 *    10                   10                   10
 *
 *   2 < 10? YES, swap      2 < 5? YES, swap    2 < 3? YES, swap
 *   (2 at idx 3)           (2 at idx 1)        (2 at idx 0) DONE!
 *
 *   Result:       2
 *               /   \
 *             3       8
 *           /   \   /   \
 *          5    7  15   20
 *         /
 *       10
 *
 * ----- EXTRACT MIN (poll) Operation -----
 *
 * Remove minimum (root = 2):
 *
 *   Step 1: Replace root with last element
 *
 *            [10]         <-- moved from last position
 *            /   \
 *          3       8
 *        /   \   /   \
 *       5    7  15   20
 *
 *   Step 2: Bubble DOWN (sift down) - compare with children, swap with smaller child
 *
 *           [10]                  [3]                     3
 *            /   \               /   \                  /   \
 *          [3]     8           [10]    8               [5]    8
 *        /   \   /   \        /   \   /   \          /   \   /   \
 *       5    7  15   20     [5]   7  15   20       10    7  15   20
 *
 *   10 > min(3,8)?          10 > min(5,7)?         10 > min(children)?
 *   YES, swap with 3       YES, swap with 5       No more children, DONE!
 *
 *   Result:       3
 *               /   \
 *             5       8
 *           /   \   /   \
 *         10    7  15   20
 *
 * ----- PEEK Operation -----
 *
 *   Simply return the root element (minimum): O(1)
 *
 *              [3]  <-- peek returns 3
 *             /   \
 *           5       8
 *
 * ================================================================================================
 * Time Complexity:
 *   Insert (push):       O(log n) - Bubble up at most height levels
 *   Extract min (poll):  O(log n) - Bubble down at most height levels
 *   Peek (min):          O(1)     - Root element
 *   Build heap:          O(n)     - Bottom-up heapify
 *
 * Space Complexity: O(n) - Array storage for n elements
 * ================================================================================================
 */
package com.practice.dsalgo.trees;

import java.util.Arrays;

/**
 * Min-Heap implementation using an array.
 */
public class BinaryHeap {

    private int[] heap;
    private int size;
    private int capacity;

    public BinaryHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    public static void main(String[] args) {
        BinaryHeap minHeap = new BinaryHeap(15);

        System.out.println("=== Min-Heap Demo ===\n");

        // Insert elements
        int[] elements = {10, 5, 20, 3, 8, 15, 7};
        System.out.println("Inserting: 10, 5, 20, 3, 8, 15, 7");
        for (int e : elements) {
            minHeap.insert(e);
        }

        System.out.println("Heap array: " + minHeap.tostring());
        System.out.println("Min (peek): " + minHeap.peek());
        System.out.println("Size: " + minHeap.size());

        // Extract min repeatedly (produces sorted order)
        System.out.println("\nExtracting elements in sorted order:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractMin() + " ");
        }
        System.out.println();

        // Build heap from array
        System.out.println("\n=== Build Heap from Array ===");
        int[] arr = {15, 10, 20, 8, 3, 5, 12};
        System.out.println("Input array:  " + Arrays.toString(arr));
        BinaryHeap builtHeap = BinaryHeap.buildHeap(arr);
        System.out.println("Heap array:   " + builtHeap.tostring());
        System.out.println("Min (peek):   " + builtHeap.peek());
    }

    /** Insert a key into the heap. */
    public void insert(int key) {
        if (size == capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
        heap[size] = key;
        siftUp(size);
        size++;
    }

    /** Extract (remove and return) the minimum element. */
    public int extractMin() {
        if (size == 0) throw new RuntimeException("Heap is empty");
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return min;
    }

    /** Peek at the minimum element without removing. */
    public int peek() {
        if (size == 0) throw new RuntimeException("Heap is empty");
        return heap[0];
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    /** Sift up: restore heap property by moving element up. */
    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[i] < heap[parent]) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    /** Sift down: restore heap property by moving element down. */
    private void siftDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size && heap[left] < heap[smallest]) smallest = left;
            if (right < size && heap[right] < heap[smallest]) smallest = right;

            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /** Build a heap from an existing array in O(n) time. */
    public static BinaryHeap buildHeap(int[] arr) {
        BinaryHeap h = new BinaryHeap(arr.length);
        h.heap = Arrays.copyOf(arr, arr.length);
        h.size = arr.length;
        h.capacity = arr.length;

        // Heapify from the last internal node up to the root
        for (int i = (h.size / 2) - 1; i >= 0; i--) {
            h.siftDown(i);
        }
        return h;
    }

    public String tostring() {
        int[] result = Arrays.copyOf(heap, size);
        return Arrays.toString(result);
    }
}
