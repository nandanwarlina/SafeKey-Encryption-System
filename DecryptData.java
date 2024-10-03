import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class DecryptData {
    public static void main(String[] args) {
        try {
            // Load the private key from file
            byte[] keyBytes = Files.readAllBytes(Paths.get("private.key"));
            // Decode the private key
            byte[] decodedKey = Base64.getDecoder().decode(keyBytes);

            // Generate the private key
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            // Load the encrypted data from file (assuming it is stored as a Base64 string)
            byte[] encryptedData = Files.readAllBytes(Paths.get("encryptedData.txt"));
            // Decode the encrypted data
            byte[] decodedData = Base64.getDecoder().decode(encryptedData);

            // Decrypt the data
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] originalData = cipher.doFinal(decodedData);

            // Convert decrypted data to string
            String originalMessage = new String(originalData);
            System.out.println("Decrypted message: " + originalMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
