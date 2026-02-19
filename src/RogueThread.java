public class RogueThread implements Runnable {

    /**
     * Method to be run once thread starts.
     * Adds 1 to iStrikes from MerkleManager class if RogueThread successfully grabs word.
     */
    public void run() {
        Util oUtil = new Util();

        while (true) {
            oUtil.sleepRandomTime("Rogue");

            String sNewWord = MerkleManager.grabWord();

            if (sNewWord != null) {
                MerkleManager.iStrikes++;

                System.out.println("Rogue grabbed the word " + sNewWord + "! Strike " + MerkleManager.iStrikes + "!");
            }
        }
    }
}
