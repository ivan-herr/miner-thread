public class MonitorThread implements Runnable {

    Util Util = new Util();

    /**
     * Method to be run once thread starts.
     * Continuously loops until program ends.
     * Prints message depending on final condition of game.
     */
    public void run() {
        while (true) {
            if (MerkleManager.merkleRoot != null) {
                if (MerkleManager.merkleRoot.equals(MerkleManager.expectedRoot)) {
                    System.out.println("YOU WIN! Merkle root is " + MerkleManager.merkleRoot);
                    System.exit(0);
                } else {
                    System.out.print("You lose... Rogue won... Better luck next time!");
                    System.exit(0);
                }
            } else if (MerkleManager.iStrikes == 3) {
                System.out.println("Three strikes... YOU'RE OUT! Better luck next time!");
                System.exit(0);
            }

            Util.sleep(1);
        }
    }
}
