// 
// Transcation.java
// @ianpasm(kno30826@gmail.com)
// 2018-06-02 19:43:01
// 
 
package javachain;
import java.security.*;
import java.util.ArrayList;

public class Transcation {
    
    public String transcationId;
    public PublicKey sender, reciepient;
    public float value;
    public byte[] signature;

    public ArrayList<TranscationInput> inputs = new ArrayList<TranscationInput>();
    public ArrayList<TranscationOutput> inputs = new ArrayList<TranscationOutput>();

    private static int sequence = 0;

    public Transcation(PublicKey from, PublicKey to, float value, ArrayList<TranscationInput> inputs) {
        this.sender = from;
        this.reciepient = to;
        this.value = value;
        this.inputs = inputs;
    }
    
    public boolean processTranscation() {

        if (verifySignature() == false) {
            System.out.println("#Transacation Signature failed to verify!");
            return false;
        }

        for (TranscationInput i : inputs) {
            i.UTXO = Javachain.UTXOs.get(i.TranscationOutputId);
        }

        if (getInputsValue() < Javachain.minimumTranscation) {
            System.out.println("Transcation Inputs too small: " + getInputsValue());
            System.out.println("Please enter the amount greater than " + Javachain.minimumTranscation);
            return false;
        }

        float leftOver = getInputsValue() - value;
        transcationId = calculateHash();
        outputs.add(new TranscationOutput(this.reciepient, value, transcationId));
        outputs.add(new TranscationOutput(this.sender, leftOver, transcationId));
    
        for (TranscationOutput o : outputs) {
            Javachain.UTXOs.put(o.id, o);
        }

        for (TranscationInput i : inputs) {
            if (i.UTXO == null) {
                continue;
            }
            Javachain.UTXOs.remove(i.UTXO.id);
        }

        return true;
    }

    public float getInputsValue() { 
        float total = 0;
        for (TranscationInput i : inputs) {
            if (i.UTXO = null) {
                continue;
            }
            total += i.UTXO.value;
        }
        return total;
    }   

    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toStting(value);
        signature = StringUtil.applyECDSASig(privateKey, data);
    }

    public boolean verifySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value);
        return StringUtil.verifyECDSASig(sender, data, signature); 
    }

    public float getOutputsValue() {
        float total = 0;
        for (TranscationOutput o : outputs) {
            total += o.value;
        }
        return total;
    }

    private String calculateHash() {
        sequence++;
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) + 
                StringUtil.getStringFromKey(reciepient) + 
                Float.toString(value) + sequence 
                );
    }
}

