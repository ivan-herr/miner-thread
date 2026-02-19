import java.util.ArrayList;

public class MerkleThread implements Runnable {

    public static volatile ArrayList<String> listOfWords;
    private int iMerkleTreeInputs = 4;

    /**
     * Method that is called after running MerkleThread instance in MerkleManager class.
     * User must input four words, if Merkle root matches with user-inputted root, player wins.
     */
    public void run() {
        Util oUtil = new Util();
        listOfWords = new ArrayList<>();

        // Loops continuously until end of program (Program end handled by MonitorThread class)
        while (true) {
            oUtil.sleepRandomTime("Merkle");

            String sNewWord = MerkleManager.grabWord();

            // Winning condition of game, generates Merkle root of four words.
            if (sNewWord != null) {
                System.out.println("Merkle grabbed the word " + sNewWord + "!");
                listOfWords.add(sNewWord);

                if (listOfWords.size() == iMerkleTreeInputs) {
                    MerkleManager.merkleRoot = oUtil.getMerkleRoot(listOfWords);
                }
            }
        }
    }
}
