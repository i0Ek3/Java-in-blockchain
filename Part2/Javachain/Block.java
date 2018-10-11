
package javachain;
import java.util.ArrayList; // new to added
import java.util.Date;

public class Block {
    // Block information
    public String hash;
    public String previousHash;
    
    // change String data to ArrayList<Transcation> transcation
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    public long timeStamp;
    public int nonce; // de-randomNumber

    // Block constructor()
    public Block(String previousHash) { // move data from Part1
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedHash = StringUtil.applySha256(
                previoushHash +
                Long.toString(timeStamp) +
                Integer.toString(nonce) +
                merkleRoot // use merkle tree different with data in Part1
                );
        return calculatedHash;
    }

    public void mineBlock(int difficulty) {
        merkleRoot = StringUtil.getMerkleRoot(transactions); // new to added
        String target = StringUtil.getDifficultyString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash += calculateHash;
        }
        System.out.println("Block mined! : " + hash);
    }

    public boolean addTransaction(Transaction transaction) { // judge the transaction whether added successfully.
        if (transaction == null) {
            return fasle;
        }
        if ((!"0".equals.(previoushHash))) {
            if ((transaction.processTransaction() != true)) {
                System.out.println("Transaction failed to process. Discarded!");
                return false;
            }
        }

        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block!");
        return true;
    }
}
