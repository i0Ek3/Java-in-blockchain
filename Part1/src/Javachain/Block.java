// 
// Block.java
// @ianpasm(kno30826@gmail.com)
// 2018-04-08 09:26:10
// 
 
package javachain;

import java.util.Date

// class block
public class Block { 
    
    //declaration
    //block information
    public String hash;
    public String previousHash;
    private String data; //message? What means?
    private long timeStamp; //since 1970-01-01 
    private int nonce; //amount to temp?? Just a temporary value?
    
    //block constructor, without decontructor function cause of Java has its own recycle machanism. 
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        //calculate current hash value
        this.hash = calculateHash();
    }

    //calculate new hash based on block contents
    public String calculateHash() { //String is a class? 
        String calculatehash = StringUtil.applySha256(
                previousHash + 
                Long.toString(timeStamp) +
                Integer.toString(nonce) +
                data
                );
        return calculatehash; 
    }

    //mine function, do nonce increases when reached target value equals hash.substring?? 
    public void mineBlock(int difficulty) {
        String target = StringUtil.getDifficultyString(difficulty);
        while(!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}


