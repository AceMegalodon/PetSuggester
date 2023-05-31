import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class TreeNode {
    private String value;
    private TreeNode left;
    private TreeNode right;
    private char identifier;
    
    public TreeNode(String value) {
        this.value = value;
    }
    public TreeNode (char identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }
    public TreeNode(String value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public boolean isLeaf() {
        if (left == null && right == null) {
            return true;
        }
        return false;
    }
    public void print(BufferedWriter writer) throws IOException {
        try {
            if (!isLeaf()) {
                identifier = '*'; // if it has a left or right, it therefore must be a question and not a suggestion.
                }
            else {
                identifier = '/';
            }
            writer.write(identifier + value);
            writer.newLine();
            if (left != null) {
                left.print(writer);
            }
            if (right != null) {
                right.print(writer);
            }
            // writes it in preorder
        }
        catch (IOException exception) {
            System.out.println("There has been an error writing the file: " + exception);
        }
    }
    public static TreeNode fromPreorder(BufferedReader reader) throws IOException {
        TreeNode node = null; // this will be accessible during the try and to return if the try throws an exception
        try {
            String line = reader.readLine();
            if (line != null) {
                char identifier = line.charAt(0);
                String value = line.substring(1);
                node = new TreeNode(identifier, value);
                if (identifier == '*') { 
                    node.left = fromPreorder(reader);
                    node.right = fromPreorder(reader);
                }
            }
        } catch (IOException exception) {
            System.out.println("There has been an error reading the file: " + exception);
        }
        return node;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public TreeNode getLeft() {
        return left;
    }
    public void setLeft(TreeNode left) {
        this.left = left;
    }
    public TreeNode getRight() {
        return right;
    }
    public void setRight(TreeNode right) {
        this.right = right;
    }
}
