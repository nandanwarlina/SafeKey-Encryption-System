import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.io.FileOutputStream;

public class MyKeyPairGenerator {

    public static void main(String[] args) throws Exception {
        // Generate a key pair with a 2048-bit key size
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // Extract public and private keys
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Encode keys as Base64 and write to files
        saveKeyToFile("public.key", Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        saveKeyToFile("private.key", Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        System.out.println("Keys generated and saved.");
    }

    // Helper method to save keys to file
    private static void saveKeyToFile(String filename, String key) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(key.getBytes());
        }
    }
}
