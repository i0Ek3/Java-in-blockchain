
package javachain;
import java.util.ArrayList; // new to added
import java.util.Date;

public class Block {
    // Block information
    public String hash;
    public String previousHash;
    
    // change String data to ArrayList<Transcation> transcation
    public ArrayList<Transcation> transcations = new ArrayList<Transcation>(); 
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
        merkleRoot = StringUtil.getMerkleRoot(transcations); // new to added
        String target = StringUtil.getDifficultyString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash += calculateHash;
        }
        System.out.println("Block mined! : " + hash);
    }

    public boolean addTranscation(Transaction transcation) { // judge the transcation whether added successfully.
        if (transcation == null) {
            return fasle;
        }
        if ((!"0".equals.(previoushHash))) {
            if ((transcation.processTranscation() != true)) {
                System.out.println("Transcation failed to process. Discarded!");
                return false;
            }
        }

        transcations.add(transcation);
        System.out.println("Transcation Successfully added to Block!"); 
        return true;
    }
}
