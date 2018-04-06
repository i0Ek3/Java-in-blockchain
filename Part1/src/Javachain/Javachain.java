package javachain;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class Javachain {

    //just create a new space which is a arraylist of block for blockchain
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5; //difficulty factor

    //like main() in c++
    public static void main(String[] args) {
        
        //like printf() or cout in c/c++
        System.out.println("Tring to Mine block 1... ");
        //add a new block 0, we called it the Creation block
        addBlock(new Block("Hola I am the first block", "0"));

        System.out.println("Tring to Mine block 2... ");
        //calculate hash for block 2
        addBlock(new Block("Bojour I am the second block", blockchain.get(blockchain.size()-1).hash));

        System.out.println("Tring to Mine block 3... ");
        addBlock(new Block("Hey I am the third block", blockchain.get(blockchain.size()-1).hash));
    
        //calling function to check block if valid 
        System.out.println("\nBlockchain is Valid: " + isChainValid());

        //hold on...what mean of this line? The author said that json package what....?
        //later....
        String blockchainJson = StringUtil.getJson(blockchain) ;
        Syetem.out.println("\nThe block chain: ");
        Syetem.out.println(blockchainJson);
    }

    //function defination
    public static Boolean is ChainValid() {
        Block currentBlock; 
        Block previousBlock;
        //hash target? replace '\0'
        String hashTarget = new String(new char[difficulty]).replace('\0','0');
        
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equals");
                return false;
            }

            if (!currentBlock.hash.sunstring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;  
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}
