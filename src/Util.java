import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

public class Util {
    public String getMerkleRoot(ArrayList<String> items) {

        // ==============================
        MerkleNode Node1 = new MerkleNode();
        MerkleNode Node2 = new MerkleNode();
        MerkleNode Node3 = new MerkleNode();
        MerkleNode Node4 = new MerkleNode();
        MerkleNode Node5 = new MerkleNode();
        MerkleNode Node6 = new MerkleNode();
        MerkleNode Node7 = new MerkleNode();

        Node1.hash = generateHash(items.get(0));
        Node2.hash = generateHash(items.get(1));
        Node3.hash = generateHash(items.get(2));
        Node4.hash = generateHash(items.get(3));

        populateMerkleNode(Node5, Node1, Node2);
        populateMerkleNode(Node6, Node3, Node4);
        populateMerkleNode(Node7, Node5, Node6);

        return Node7.hash;
    }

    private void populateMerkleNode(MerkleNode Node, MerkleNode LeftNode, MerkleNode RightNode) {
        Node.LeftNode = LeftNode;
        Node.RightNode = RightNode;
        Node.hash = generateHash(Node.LeftNode.hash + Node.RightNode.hash);
    }

    public synchronized String generateHash(String sOriginal) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] btEncodedhash = digest.digest(
                    sOriginal.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < btEncodedhash.length; i++) {
                sb.append(Integer.toString((btEncodedhash[i] & 0xff) + 0x100,
                        16).substring(1));
            }
            return sb.toString();
        } catch (Exception ex) {
            System.out.println("Error generating hash: " + ex.getMessage());
            return null;
        }
    }
}