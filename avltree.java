import java.util.Scanner;

class AVLNode {
    int data, height;
    AVLNode left, right;

    AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }
}

class AVLTree {
    AVLNode root;

    // Get height
    int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    // Get balance factor
    int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // Right rotation
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotation
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert
    AVLNode insert(AVLNode node, int data) {
        if (node == null)
            return new AVLNode(data);

        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // LL
        if (balance > 1 && data < node.left.data)
            return rightRotate(node);

        // RR
        if (balance < -1 && data > node.right.data)
            return leftRotate(node);

        // LR
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Minimum value node
    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Delete
    AVLNode delete(AVLNode root, int data) {
        if (root == null)
            return root;

        if (data < root.data)
            root.left = delete(root.left, data);
        else if (data > root.data)
            root.right = delete(root.right, data);
        else {
            if ((root.left == null) || (root.right == null)) {
                root = (root.left != null) ? root.left : root.right;
            } else {
                AVLNode temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = delete(root.right, temp.data);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        // LL
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // LR
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RR
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // RL
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Search
    boolean search(AVLNode root, int key) {
        if (root == null)
            return false;
        if (root.data == key)
            return true;
        return key < root.data ? search(root.left, key) : search(root.right, key);
    }

    // Traversals
    void inorder(AVLNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    void preorder(AVLNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    void postorder(AVLNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }
}

public class AVLTreeDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVLTree tree = new AVLTree();

        while (true) {
            System.out.println("\n1.Insert  2.Delete  3.Search  4.Inorder  5.Preorder  6.Postorder  7.Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter element: ");
                    tree.root = tree.insert(tree.root, sc.nextInt());
                    break;

                case 2:
                    System.out.print("Enter element to delete: ");
                    tree.root = tree.delete(tree.root, sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter element to search: ");
                    System.out.println(tree.search(tree.root, sc.nextInt())
                            ? "Key Found" : "Key Not Found");
                    break;

                case 4:
                    System.out.print("Inorder: ");
                    tree.inorder(tree.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Preorder: ");
                    tree.preorder(tree.root);
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Postorder: ");
                    tree.postorder(tree.root);
                    System.out.println();
                    break;

                case 7:
                    System.exit(0);
            }
        }
    }
}