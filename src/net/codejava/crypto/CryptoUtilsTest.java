package net.codejava.crypto;
 
import java.io.File;
 
/**
 * A tester for the CryptoUtils class.
 * @author www.codejava.net
 * Not my own work just some code to mess around with
 */
public class CryptoUtilsTest {
    public static void main(String[] args) {
        String key = "Mary has one cat1";
        File inputFile = new File("testing.txt");
        File encryptedFile = new File("document.encrypted");
        File decryptedFile = new File("document.decrypted");
         
        try {
            CryptoUtils.encrypt(key, inputFile, encryptedFile);
            CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}