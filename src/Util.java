import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Util {
    /**
     * Gets Merkle root of the four inputted words from user.
     * @param items ArrayList of the four words to hash.
     * @return Merkle root of all four words.
     */
    public String getMerkleRoot(ArrayList<String> items) {

        MerkleNode Node1 = new MerkleNode();
        MerkleNode Node2 = new MerkleNode();
        MerkleNode Node3 = new MerkleNode();
        MerkleNode Node4 = new MerkleNode();
        MerkleNode Node5 = new MerkleNode();
        MerkleNode Node6 = new MerkleNode();
        MerkleNode Node7 = new MerkleNode();

        Node1.sHash = generateHash(items.get(0)); // Word 1 to hash
        Node2.sHash = generateHash(items.get(1)); // Word 2 to hash
        Node3.sHash = generateHash(items.get(2)); // Word 3 to hash
        Node4.sHash = generateHash(items.get(3)); // Word 4 to hash

        populateMerkleNode(Node5, Node1, Node2); // Hashes Node 1 + 2 hashes
        populateMerkleNode(Node6, Node3, Node4); // Hashes Node 3 + 4 hashes
        populateMerkleNode(Node7, Node5, Node6); // Hashes Node 5 + 6 hashes

        return Node7.sHash; // Merkle root
    }

    /**
     * Method to hash two nodes into one hash.
     * @param oNode Node to hold hash of Node 1 and Node 2.
     * @param oLeftNode Node 1 and its hash.
     * @param oRightNode Node 2 and its hash.
     */
    private void populateMerkleNode(MerkleNode oNode, MerkleNode oLeftNode, MerkleNode oRightNode) {
        oNode.oLeft = oLeftNode;
        oNode.oRight = oRightNode;
        oNode.sHash = generateHash(oNode.oLeft.sHash + oNode.oRight.sHash);
    }

    /**
     * Method to hash String with SHA-256 algorithm.
     * @param sOriginal String to hash.
     * @return Hashed string.
     */
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

    /**
     * Method to prompt user with prompt box.
     * @param sQuestion String to display to user in prompt box.
     * @return The String input of user.
     */
    public String promptUser (String sQuestion) {
        JOptionPane oQuestion = new JOptionPane();
        String sAnswer = oQuestion.showInputDialog(sQuestion);
        return sAnswer;
    }

    /**
     * Method that sleeps thread for 3-8 seconds, randomly chosen.
     * Time amount sent to sleep() to sleep thread.
     * @param sThreadName Name of thread that is going to sleep.
     */
    public void sleepRandomTime(String sThreadName){
        // Gets random number between 0 and 5 and then adds 3, meaning between 3 and 8 now.
        int iSleepTime = new SecureRandom().nextInt(5) + 3;

        System.out.println(sThreadName + " sleeping for " + iSleepTime + " seconds.");

        sleep(iSleepTime);
    }

    /**
     * Method to sleep the thread in seconds.
     * @param iSeconds Amount of seconds that thread must sleep for (Multiplied by 1000 due to it being milliseconds).
     */
    public void sleep(int iSeconds){

        try {
            Thread.sleep(iSeconds * 1000); // Multiplied by 1000 to account value is in milliseonds.
        } catch (Exception ex) {
            System.out.println("Error sleeping: " + ex.getMessage());
        }
    }
}