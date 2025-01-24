import java.util.Scanner;

public class avl {

    static class Node {
        int val;
        Node left, right;
        int height;

        Node(int val) {
            this.val = val;
            this.height = 1;
        }
    }

    public static Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        return root;
    }

    public static int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public static int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // Check if the tree is AVL balanced
    public static boolean ifBalanced(Node root) {
        if (root == null) {
            return true;
        }
        int balance = getBalance(root);
        if (Math.abs(balance) > 1) {
            return false;
        }
        return ifBalanced(root.left) && ifBalanced(root.right);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); 
        for (int i = 1; i <= n; i++) {
            int num = scanner.nextInt(); 
            int[] inserts = new int[num];
            for (int j = 0; j < num; j++) {
                inserts[j] = scanner.nextInt(); 
            }

            Node root = null;
            boolean res = false;
            for (int val : inserts) {
                root = insert(root, val);
                if (!ifBalanced(root)) {
                    res = true;
                    break;
                }
            }

            System.out.println("Tree #" + i + ": " + (res ? "REMOVE" : "KEEP"));
        }
        scanner.close();
    }
}