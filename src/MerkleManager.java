import java.util.Scanner;

public class MerkleManager {

    // ==============================
    // --- Instance Variables ---
    public static volatile String userWord;
    public static String expectedRoot;
    public static String merkleRoot = null;
    public static int iStrikes = 0;

    // ==============================
    // --- Methods ---
    /**
     * Method that asks user for the expected Merkle root with link to get Merkle root.
     * Instantiates and starts three threads in MerkleThread, RogueThread, and MonitorThread.
     * Continues to ask user for input.
     */
    public void manage() {
        Util oUtil = new Util();

        expectedRoot = oUtil.promptUser("What is the expected Merkle root " +
                "(https://codebeautify.org/sha256-hash-generator) ? Enter:");

        MerkleThread oMerkleThread = new MerkleThread();
        Thread t1 = new Thread(oMerkleThread);
        t1.start();

        RogueThread oRogueThread = new RogueThread();
        Thread t2 = new Thread(oRogueThread);
        t2.start();

        MonitorThread oMonitorThread = new MonitorThread();
        Thread t3 = new Thread(oMonitorThread);
        t3.start();

        while (true) {
            userWord = oUtil.promptUser("What is the word you input? Enter: ");
        }
    }

    /**
     * Method to grab word from user input. Called by either RogueThread or MerkleThread depending on who wakes first.
     * userWord is grabbed, set to tempWord, and then null'd, while RogueThread or MerkleThread will get tempWord.
     * @return tempWord
     */
    public static synchronized String grabWord() {
        String tempWord = userWord;
        userWord = null;
        return tempWord;
    }
}
