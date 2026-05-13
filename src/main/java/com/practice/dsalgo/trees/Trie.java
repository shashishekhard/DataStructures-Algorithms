/**
 * Trie (Prefix Tree) - An efficient tree structure for string storage and retrieval.
 *
 * A Trie is a tree-like data structure where each node represents a single character.
 * Paths from root to marked nodes represent stored words. Tries are commonly used for
 * autocomplete, spell checking, and IP routing.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Trie Operations
 * ================================================================================================
 *
 * ----- Building a Trie -----
 *
 * Insert words: "cat", "car", "card", "care", "bat", "bar"
 *
 *                     (root)
 *                    /      \
 *                  c          b
 *                 /            \
 *               a               a
 *              / \             / \
 *            t*   r*         t*   r*
 *                / \
 *              d*   e*
 *
 *   * = end-of-word marker
 *
 * Step-by-step insertion of "cat":
 *
 *   (root) --c--> (c) --a--> (a) --t--> (t*)
 *
 *   1. Start at root
 *   2. 'c' -> create child node for 'c'
 *   3. 'a' -> create child node for 'a'
 *   4. 't' -> create child node for 't', mark as end-of-word
 *
 * Step-by-step insertion of "car" (shares prefix "ca" with "cat"):
 *
 *   (root) --c--> (c) --a--> (a) --t--> (t*)
 *                                  \
 *                                   r--> (r*)     <-- new branch
 *
 *   1. Start at root
 *   2. 'c' -> node exists, follow it
 *   3. 'a' -> node exists, follow it
 *   4. 'r' -> create new child node for 'r', mark as end-of-word
 *
 * ----- SEARCH Operation -----
 *
 * Search "car":    root -> c -> a -> r* (found, is end-of-word) -> TRUE
 * Search "ca":     root -> c -> a (found, NOT end-of-word) -> FALSE
 * Search "can":    root -> c -> a -> n (not found) -> FALSE
 *
 *                     (root)
 *                    /
 *                 [c]   <-- follow
 *                 /
 *              [a]      <-- follow
 *              / \
 *            t*  [r*]   <-- follow, check end-of-word = YES
 *
 * ----- PREFIX SEARCH -----
 *
 * startsWith("ca"):  root -> c -> a (found) -> TRUE (prefix exists)
 * startsWith("da"):  root -> d (not found) -> FALSE
 *
 * ----- DELETE Operation -----
 *
 * Delete "car" from the trie:
 *
 *   Before:                            After:
 *        (root)                           (root)
 *       /      \                         /      \
 *     c          b                     c          b
 *    /            \                   /            \
 *   a              a                 a              a
 *  / \            / \               / \            / \
 * t*  r*        t*   r*           t*   r         t*   r*
 *    / \                             / \
 *   d*  e*                          d*  e*
 *
 *   Unmark 'r' as end-of-word (keep node because it has children d, e)
 *
 * Delete "card":
 *
 *   Before:                            After:
 *        (root)                           (root)
 *       /                                /
 *     c                                c
 *    /                                /
 *   a                                a
 *  / \                              / \
 * t*   r                          t*   r
 *     / \                               \
 *    d*  e*                              e*
 *
 *   Remove 'd' node entirely (no children, no other words use it)
 *
 * ================================================================================================
 * Time Complexity (where m = length of key/word):
 *   Insert:       O(m)
 *   Search:       O(m)
 *   Delete:       O(m)
 *   Prefix search: O(m)
 *
 * Space Complexity: O(ALPHABET_SIZE * m * n) where n = number of keys
 *   Each node has up to ALPHABET_SIZE (26) children pointers
 * ================================================================================================
 */
package com.practice.dsalgo.trees;

/**
 * Trie (Prefix Tree) implementation supporting insert, search, prefix search, and delete.
 */
public class Trie {

    private static final int ALPHABET_SIZE = 26;

    /** Trie node with children array and end-of-word flag. */
    private static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            children = new TrieNode[ALPHABET_SIZE];
            isEndOfWord = false;
        }

        boolean hasNoChildren() {
            for (TrieNode child : children) {
                if (child != null) return false;
            }
            return true;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert words
        String[] words = {"cat", "car", "card", "care", "bat", "bar"};
        System.out.println("Inserting words: cat, car, card, care, bat, bar");
        for (String word : words) {
            trie.insert(word);
        }

        // Search
        System.out.println("\nSearch Results:");
        System.out.println("  search(\"cat\"):  " + trie.search("cat"));
        System.out.println("  search(\"car\"):  " + trie.search("car"));
        System.out.println("  search(\"ca\"):   " + trie.search("ca"));
        System.out.println("  search(\"can\"):  " + trie.search("can"));
        System.out.println("  search(\"bat\"):  " + trie.search("bat"));

        // Prefix search
        System.out.println("\nPrefix Search Results:");
        System.out.println("  startsWith(\"ca\"):  " + trie.startsWith("ca"));
        System.out.println("  startsWith(\"car\"): " + trie.startsWith("car"));
        System.out.println("  startsWith(\"da\"):  " + trie.startsWith("da"));
        System.out.println("  startsWith(\"ba\"):  " + trie.startsWith("ba"));

        // Delete
        System.out.println("\nDelete \"car\":");
        trie.delete("car");
        System.out.println("  search(\"car\"):  " + trie.search("car"));
        System.out.println("  search(\"card\"): " + trie.search("card"));
        System.out.println("  search(\"care\"): " + trie.search("care"));

        System.out.println("\nDelete \"card\":");
        trie.delete("card");
        System.out.println("  search(\"card\"): " + trie.search("card"));
        System.out.println("  search(\"care\"): " + trie.search("care"));
        System.out.println("  search(\"cat\"):  " + trie.search("cat"));
    }

    /** Insert a word into the trie. */
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    /** Search for an exact word in the trie. */
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    /** Check if any word in the trie starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    /** Delete a word from the trie. */
    public void delete(String word) {
        deleteRec(root, word, 0);
    }

    private boolean deleteRec(TrieNode node, String word, int depth) {
        if (node == null) return false;

        if (depth == word.length()) {
            if (!node.isEndOfWord) return false;
            node.isEndOfWord = false;
            return node.hasNoChildren();
        }

        int index = word.charAt(depth) - 'a';
        boolean shouldDeleteChild = deleteRec(node.children[index], word, depth + 1);

        if (shouldDeleteChild) {
            node.children[index] = null;
            return !node.isEndOfWord && node.hasNoChildren();
        }
        return false;
    }

    /** Find the node corresponding to the last character of the given string. */
    private TrieNode findNode(String str) {
        TrieNode current = root;
        for (char c : str.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) return null;
            current = current.children[index];
        }
        return current;
    }
}
