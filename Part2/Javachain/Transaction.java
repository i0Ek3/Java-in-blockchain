// 
// Transaction.java
// @ianpasm(kno30826@gmail.com)
// 2018-06-02 19:43:01
//
// I have a mistake for word transaction. All the time I think transcation is transaction.....
//
 
package javachain;
import java.security.*;
import java.util.ArrayList;

public class Transaction {
    
    public String transactionId;
    public PublicKey sender, recipient;
    public float value;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int sequence = 0;

    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs;
    }
    
    public boolean processTransaction() {

        if (verifySignature() == false) {
            System.out.println("#Transacation Signature failed to verify!");
            return false;
        }

        for (TransactionInput i : inputs) {
            //事务输出UTXO
            i.UTXO = Javachain.UTXOs.get(i.transactionOutputId);
        }

        if (getInputsValue() < Javachain.minimumTransaction) {
            System.out.println("Transaction inputs too small: " + getInputsValue());
            System.out.println("Please enter the amount greater than " + Javachain.minimumTransaction);
            return false;
        }

        float leftOver = getInputsValue() - value;
        transactionId = calculateHash();
        outputs.add(new TransactionOutput(this.reciepient, value, transactionId));
        outputs.add(new TransactionOutput(this.sender, leftOver, transactionId));
    
        for (TransactionOutput o : outputs) {
            Javachain.UTXOs.put(o.id, o);
        }

        for (TransactionInput i : inputs) {
            if (i.UTXO == null) {
                continue;
            }
            Javachain.UTXOs.remove(i.UTXO.id);
        }
        return true;
    }

    public float getInputsValue() { 
        float total = 0;
        for (TransactionInput i : inputs) {
            if (i.UTXO = null) {
                continue;
            }
            total += i.UTXO.value;
        }
        return total;
    }   

    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toStting(value);
        signature = StringUtil.applyECDSASig(privateKey, data);
    }

    public boolean verifySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        return StringUtil.verifyECDSASig(sender, data, signature); 
    }

    public float getOutputsValue() {
        float total = 0;
        for (TransactionOutput o : outputs) {
            total += o.value;
        }
        return total;
    }

    private String calculateHash() {
        sequence++;
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) + 
                StringUtil.getStringFromKey(recipient) + 
                Float.toString(value) + sequence 
                );
    }
}

