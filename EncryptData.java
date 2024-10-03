import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EncryptData {
    public static void main(String[] args) {
        try {
            // Read the public key from file
            String publicKeyStr = new String(Files.readAllBytes(Paths.get("public.key")));
            byte[] decodedKey = Base64.getDecoder().decode(publicKeyStr);

            // Create PublicKey object from decoded key
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));

            // The plaintext message to be encrypted
            String message = "Hello, RSA!";

            // Encrypt the message using the public key
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedMessage = cipher.doFinal(message.getBytes());

            // Save the encrypted message to a file
            String encryptedMessageStr = Base64.getEncoder().encodeToString(encryptedMessage);
            FileOutputStream fos = new FileOutputStream("encryptedData.txt");
            fos.write(encryptedMessageStr.getBytes());
            fos.close();

            System.out.println("Data encrypted and saved to encryptedData.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
