// 
// TransactionInput.java
// @ianpasm(kno30826@gmail.com)
// 2018-06-04 21:10:16
// 
 
package javachain;

public class TransactionInput {
    public String transactionOutputId;
    public TransactionOutput UTXO; //事务输出UTXO

    public TransactionInput(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }
}
