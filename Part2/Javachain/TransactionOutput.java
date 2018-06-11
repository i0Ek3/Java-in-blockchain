// 
// TransactionOutput.java
// @ianpasm(kno30826@gmail.com)
// 2018-06-04 21:13:14
// 
 
package javachain;
import java.security.PublicKey;

public class TransactionOutput {
    public String id;
    public PublicKey reciepient; //marked new owner
    public float value;
    public String parentTransactionId;

    public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
        this.reciepient = reciepient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient) + Float.toString(value) + parentTransactionId);
    }

    public boolean isMined(PublicKey publicKey) {
        return (publicKey == reciepient);
    }
}
