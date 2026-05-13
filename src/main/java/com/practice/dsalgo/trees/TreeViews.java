/**
 * Tree Views - Left, Right, Top, and Bottom views of a Binary Tree.
 *
 * Different "views" of a tree show which nodes are visible when looking at the tree
 * from different directions. These are common interview questions.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Tree Views
 * ================================================================================================
 *
 * Example Tree:
 *
 *                   1
 *                /     \
 *              2         3
 *            /   \     /   \
 *           4     5   6     7
 *                  \
 *                   8
 *
 * Assigning horizontal distances (HD) from root (root = 0):
 *
 *        HD:    -2   -1    0    1    2
 *
 *                         1 (HD=0)
 *                       /     \
 *                 2 (HD=-1)    3 (HD=1)
 *               /    \       /    \
 *         4 (HD=-2) 5(HD=0) 6(HD=0)  7(HD=2)
 *                    \
 *                   8 (HD=1)
 *
 * ----- LEFT VIEW (First node at each level) -----
 *
 *   Looking from the left side:
 *
 *   =>     1              Level 0: first node = 1
 *        /   \
 *   =>  2      3          Level 1: first node = 2
 *      / \    / \
 *   => 4   5  6   7       Level 2: first node = 4
 *          \
 *   =>      8             Level 3: first node = 8
 *
 *   Left View: [1, 2, 4, 8]
 *
 * ----- RIGHT VIEW (Last node at each level) -----
 *
 *   Looking from the right side:
 *
 *        1       <=       Level 0: last node = 1
 *      /   \
 *    2       3   <=       Level 1: last node = 3
 *   / \    / \
 *  4   5  6   7  <=       Level 2: last node = 7
 *       \
 *        8       <=       Level 3: last node = 8
 *
 *   Right View: [1, 3, 7, 8]
 *
 * ----- TOP VIEW (First node at each horizontal distance) -----
 *
 *   Looking from above:
 *
 *         4    2    1    3    7
 *   HD:  -2   -1    0    1    2
 *
 *   Top View: [4, 2, 1, 3, 7]
 *
 *   At HD=0: nodes 1, 5, 6 exist; top view sees 1 (first encountered in BFS)
 *   At HD=1: nodes 3, 8 exist; top view sees 3
 *
 * ----- BOTTOM VIEW (Last node at each horizontal distance) -----
 *
 *   Looking from below:
 *
 *         4    5    8    7
 *        or    or   or
 *              6
 *   HD:  -2   -1    0    1    2
 *
 *   Bottom View: [4, 5, 6, 8, 7]
 *
 *   At HD=0: nodes 1, 5, 6 exist; bottom view sees 6 (last in BFS)
 *   At HD=1: nodes 3, 8 exist; bottom view sees 8
 *
 * ================================================================================================
 * Time Complexity:  O(n) for all views - Level-order traversal visits each node once
 * Space Complexity: O(n) for the queue and map storage
 * ================================================================================================
 */
package com.practice.dsalgo.trees;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Tree views: left, right, top, and bottom views of a binary tree.
 */
public class TreeViews {

    /** Helper class for BFS with horizontal distance. */
    private static class Pair {
        Node node;
        int hd; // horizontal distance

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static void main(String[] args) {
        /*
         * Build tree:
         *            1
         *          /     \
         *        2         3
         *      /   \     /   \
         *     4     5   6     7
         *            \
         *             8
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.right.right = new Node(8);

        System.out.println("Tree:");
        System.out.println("            1");
        System.out.println("          /     \\");
        System.out.println("        2         3");
        System.out.println("      /   \\     /   \\");
        System.out.println("     4     5   6     7");
        System.out.println("            \\");
        System.out.println("             8");
        System.out.println();

        System.out.print("Left View:   ");
        printList(leftView(root));

        System.out.print("Right View:  ");
        printList(rightView(root));

        System.out.print("Top View:    ");
        printList(topView(root));

        System.out.print("Bottom View: ");
        printList(bottomView(root));
    }

    /** Left View: first node at each level (BFS). */
    public static List<Integer> leftView(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (i == 0) result.add(node.key); // first node at this level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }

    /** Right View: last node at each level (BFS). */
    public static List<Integer> rightView(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (i == levelSize - 1) result.add(node.key); // last node at this level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }

    /** Top View: first node at each horizontal distance (BFS). */
    public static List<Integer> topView(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            Node node = pair.node;
            int hd = pair.hd;

            if (!map.containsKey(hd)) {
                map.put(hd, node.key); // first node at this HD
            }

            if (node.left != null) queue.add(new Pair(node.left, hd - 1));
            if (node.right != null) queue.add(new Pair(node.right, hd + 1));
        }

        result.addAll(map.values());
        return result;
    }

    /** Bottom View: last node at each horizontal distance (BFS). */
    public static List<Integer> bottomView(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            Node node = pair.node;
            int hd = pair.hd;

            map.put(hd, node.key); // always overwrite (last node at this HD)

            if (node.left != null) queue.add(new Pair(node.left, hd - 1));
            if (node.right != null) queue.add(new Pair(node.right, hd + 1));
        }

        result.addAll(map.values());
        return result;
    }

    private static void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(", ");
        }
        System.out.println();
    }
}
