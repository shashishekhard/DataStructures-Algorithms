/**
 * Segment Tree - A tree for efficient range queries and updates.
 *
 * A Segment Tree is a binary tree where each node stores aggregate information
 * (sum, min, max, etc.) about a range of elements. It allows both range queries
 * and point updates in O(log n) time.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Segment Tree (Range Sum)
 * ================================================================================================
 *
 * Array: [1, 3, 5, 7, 9, 11]
 * Indices: 0  1  2  3  4  5
 *
 * ----- Building the Segment Tree -----
 *
 * Each node stores the SUM of a range [l, r]:
 *
 *                        [36]              range [0,5] sum=1+3+5+7+9+11=36
 *                       /    \
 *                    [9]      [27]          [0,2]=9    [3,5]=27
 *                   /   \    /    \
 *                [4]   [5] [16]  [11]       [0,1]=4  [2,2]=5  [3,4]=16  [5,5]=11
 *               / \       / \
 *             [1] [3]   [7] [9]             [0,0]=1  [1,1]=3  [3,3]=7  [4,4]=9
 *
 * Build process (bottom-up):
 *   Leaves = individual elements: [1], [3], [5], [7], [9], [11]
 *   Internal nodes = sum of children:
 *     [0,1] = 1 + 3 = 4
 *     [3,4] = 7 + 9 = 16
 *     [0,2] = 4 + 5 = 9
 *     [3,5] = 16 + 11 = 27
 *     [0,5] = 9 + 27 = 36
 *
 * ----- QUERY Operation -----
 *
 * Query: sum of range [1, 4] (elements: 3, 5, 7, 9 = 24)
 *
 *                        [36]              [0,5] partially overlaps [1,4]
 *                       /    \
 *                    [9]      [27]          [0,2] partial   [3,5] partial
 *                   /   \    /    \
 *                [4]   [5] [16]  [11]       [0,1] partial  [2,2] TOTAL  [3,4] TOTAL  [5,5] NONE
 *               / \       / \
 *             [1] [3]   [7] [9]             [0,0] NONE  [1,1] TOTAL
 *
 *   Nodes visited:
 *   [0,5] -> partial overlap, recurse both children
 *   [0,2] -> partial overlap, recurse both children
 *   [0,1] -> partial overlap, recurse both children
 *   [0,0] -> NO overlap with [1,4], return 0
 *   [1,1] -> TOTAL overlap with [1,4], return 3
 *   [2,2] -> TOTAL overlap with [1,4], return 5
 *   [3,5] -> partial overlap, recurse both children
 *   [3,4] -> TOTAL overlap with [1,4], return 16
 *   [5,5] -> NO overlap with [1,4], return 0
 *
 *   Answer = 3 + 5 + 16 = 24
 *
 * ----- UPDATE Operation -----
 *
 * Update: change index 2 from 5 to 10 (add 5)
 *
 *   Before:                              After:
 *          [36]                                [41]       +5
 *         /    \                              /    \
 *      [9]      [27]                      [14]     [27]   +5
 *     /   \    /    \                    /   \    /    \
 *   [4]  [5] [16]  [11]              [4]  [10] [16]  [11]  +5
 *
 *   Path updated: leaf [2,2] -> [0,2] -> [0,5]
 *   Only O(log n) nodes updated along the path from leaf to root.
 *
 * ================================================================================================
 * Time Complexity:
 *   Build:   O(n)     - Visit each node once
 *   Query:   O(log n) - At most 2 nodes per level
 *   Update:  O(log n) - Update path from leaf to root
 *
 * Space Complexity: O(n) - Array of size 4*n for the tree
 * ================================================================================================
 */
package com.practice.dsalgo.trees;

/**
 * Segment Tree implementation for range sum queries and point updates.
 */
public class SegmentTree {

    private int[] tree;
    private int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 1, 0, n - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};

        System.out.println("Array: [1, 3, 5, 7, 9, 11]");
        System.out.println("Indices: 0  1  2  3  4  5\n");

        SegmentTree st = new SegmentTree(arr);

        // Range sum queries
        System.out.println("Range Sum Queries:");
        System.out.println("  sum(0, 5) = " + st.query(0, 5) + "  (entire array)");
        System.out.println("  sum(1, 4) = " + st.query(1, 4) + "  (3+5+7+9)");
        System.out.println("  sum(2, 4) = " + st.query(2, 4) + "  (5+7+9)");
        System.out.println("  sum(0, 0) = " + st.query(0, 0) + "  (single element)");

        // Point update
        System.out.println("\nUpdate index 2: value 5 -> 10");
        st.update(2, 10);

        System.out.println("\nRange Sum Queries after update:");
        System.out.println("  sum(0, 5) = " + st.query(0, 5) + "  (1+3+10+7+9+11=41)");
        System.out.println("  sum(1, 4) = " + st.query(1, 4) + "  (3+10+7+9=29)");
        System.out.println("  sum(2, 2) = " + st.query(2, 2) + "  (updated element)");
    }

    /** Build the segment tree from the array. */
    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node, start, mid);
            build(arr, 2 * node + 1, mid + 1, end);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    /** Query the sum of elements in range [l, r]. */
    public int query(int l, int r) {
        return queryRec(1, 0, n - 1, l, r);
    }

    private int queryRec(int node, int start, int end, int l, int r) {
        // No overlap
        if (r < start || end < l) {
            return 0;
        }
        // Total overlap
        if (l <= start && end <= r) {
            return tree[node];
        }
        // Partial overlap
        int mid = (start + end) / 2;
        int leftSum = queryRec(2 * node, start, mid, l, r);
        int rightSum = queryRec(2 * node + 1, mid + 1, end, l, r);
        return leftSum + rightSum;
    }

    /** Update the value at index idx to val. */
    public void update(int idx, int val) {
        updateRec(1, 0, n - 1, idx, val);
    }

    private void updateRec(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                updateRec(2 * node, start, mid, idx, val);
            } else {
                updateRec(2 * node + 1, mid + 1, end, idx, val);
            }
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }
}
