// Kattis link: https://open.kattis.com

import java.util.*;

public class ceiling {

    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int len = stdin.nextInt();

        // Will store each tree here.
        node[] trees = new node[n];

        // Read in trees.
        for (int i = 0; i < n; i++) {

            // Create tree i with root node.
            int root = stdin.nextInt();
            trees[i] = new node(root);

            // Insert the rest.
            for (int j = 0; j < len - 1; j++) {
                int cur = stdin.nextInt();
                node tmp = new node(cur);
                trees[i].insert(tmp);
            }
        }

        // First tree is unique.
        int res = 1;

        // Go through rest.
        for (int i = 1; i < n; i++) {

            boolean ok = true;

            // See if tree i is the same as a previous one.
            for (int j = 0; j < i; j++) {
                if (node.equal(trees[j], trees[i])) {
                    ok = false;
                    break;
                }
            }

            // Only add if it's unique.
            if (ok)
                res++;
        }

        // Ta da!
        System.out.println(res);
        stdin.close();
    }
}

class node {

    public int value;
    public node left;
    public node right;

    // Makes a node.
    public node(int v) {
        value = v;
        left = null;
        right = null;
    }

    // Inserts other into this tree.
    public void insert(node other) {

        // Left base case.
        if (other.value < this.value && left == null) {
            left = other;
            return;
        }

        // Right base case.
        if (other.value > this.value && right == null) {
            right = other;
            return;
        }

        // Recurse left or right.
        if (other.value < this.value)
            left.insert(other);
        else
            right.insert(other);
    }

    // Returns true iff a and b are the same structure.
    public static boolean equal(node a, node b) {

        // Easy base cases.
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;

        // Root nodes match. Try both sides and if they both match we're good.
        boolean myleft = equal(a.left, b.left);
        if (!myleft)
            return false;
        return equal(a.right, b.right);
    }
}