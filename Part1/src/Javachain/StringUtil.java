// 
// StringUtil.java
// @ianpasm(kno30826@gmail.com)
// 2018-04-08 21:51:41
// 
 
package javachain;
import java.security.MessageDigest;
import com.google.gson.GsonBuilder;

public class StringUtil {

    //
    public static String applySha256(String input){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            //
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Ingeter.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                    hexString.append(hex);
                }
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }   

    //
    public static String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    //
    public static String getDifficultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0','0');
    }
}



