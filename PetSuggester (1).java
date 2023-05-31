import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PetSuggester {

    public static Scanner scanner = new Scanner(System.in);
    
    public static Boolean askYesNo(String question) {
        System.out.println(question + "[y/n]");
        String response = scanner.nextLine();
        
        if (response.equalsIgnoreCase("y")) { // more elegant way of saying either 'y' or 'Y'
            return true;
        }
        return false;
    }
    
    public static void main(String args[]) throws IOException {
        TreeNode treeRoot = null; // able to be accessed from the try, catch and finally.
        try {
            BufferedReader reader = new BufferedReader(new FileReader("suggestions.txt"));
            treeRoot = TreeNode.fromPreorder(reader);
        }
        catch (IOException exception) {
            treeRoot = new TreeNode("Do you like dogs?",
                new TreeNode("Do you prefer big dogs?", // The Left
                    new TreeNode("a golden retriever"), // The left's left
                        new TreeNode ("a chihuahua")), // The left's right
                new TreeNode("an iguana")); // The right
        }
        finally {
            TreeNode temp = treeRoot;
            System.out.println("Welcome! Let's help you find a pet.");
            while (true) {
                if (askYesNo(temp.getValue())) {
                    temp = temp.getLeft();
                }
                else {
                    temp = temp.getRight();
                }
                if (temp.isLeaf()) {
                    System.out.println("Perhaps you would like " + temp.getValue());
                    
                    if (!askYesNo("Is this satisfactory?")) {
                        System.out.println("What would you prefer instead?");
                        String preference = scanner.nextLine();
                        System.out.println("Tell me a question that distinguishes " + temp.getValue() + " from " + preference);
                        temp.setLeft(new TreeNode(preference));
                        temp.setRight(new TreeNode(temp.getValue()));
                        temp.setValue(scanner.nextLine());
                    }
                    
                    if (askYesNo("Would you like to play again?")) {
                        temp = treeRoot;
                    }
                    else {
                        if (askYesNo("Do you want to save the current tree?")) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("suggestions.txt"));
                            treeRoot.print(writer);
                            writer.close();
                        }
                        scanner.close();
                        break;
                    }
                }
            }
        }   
    }
}
