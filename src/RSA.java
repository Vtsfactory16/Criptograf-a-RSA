import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Clase para gestionar la encriptación y desencriptación de texto mediante RSA.
 */
public class RSA {

    private PrivateKey privateKey = null;
    private PublicKey publicKey = null;

    /**
     * Constructor de la clase RSA.
     */
    public RSA() {
    }

    /**
     * Establece la clave privada a partir de una cadena de caracteres en formato Base64.
     * @param key La clave privada en formato Base64.
     * @throws NoSuchAlgorithmException Si no se encuentra el algoritmo especificado.
     * @throws InvalidKeySpecException Si la especificación de la clave es inválida.
     */
    public void setPrivateKeyString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decodedPrivateKey = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.privateKey = keyFactory.generatePrivate(privateKeySpec);
    }

    /**
     * Establece la clave pública a partir de una cadena de caracteres en formato Base64.
     * @param key La clave pública en formato Base64.
     * @throws NoSuchAlgorithmException Si no se encuentra el algoritmo especificado.
     * @throws InvalidKeySpecException Si la especificación de la clave es inválida.
     */
    public void setPublicKeyString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decodedPublicKey = Base64.getDecoder().decode(key);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedPublicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.publicKey = keyFactory.generatePublic(publicKeySpec);
    }

    /**
     * Obtiene la clave privada como una cadena de caracteres en formato Base64.
     * @return La clave privada en formato Base64.
     */
    public String getPrivateKeyString() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    /**
     * Obtiene la clave pública como una cadena de caracteres en formato Base64.
     * @return La clave pública en formato Base64.
     */
    public String getPublicKeyString() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    /**
     * Genera un par de claves RSA con el tamaño especificado.
     * @param size El tamaño del par de claves a generar.
     * @throws NoSuchAlgorithmException Si no se encuentra el algoritmo especificado.
     */
    public void generateKeyPair(int size) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(size);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    /**
     * Encripta un texto utilizando la clave pública.
     * @param plain El texto a encriptar.
     * @return El texto encriptado en formato Base64.
     * @throws NoSuchAlgorithmException Si no se encuentra el algoritmo especificado.
     * @throws NoSuchPaddingException Si no se encuentra el relleno especificado.
     * @throws InvalidKeyException Si la clave proporcionada es inválida.
     * @throws IllegalBlockSizeException Si el tamaño de bloque es incorrecto.
     * @throws BadPaddingException Si se produce un error de relleno.
     */
    public String encrypt(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Desencripta un texto encriptado utilizando la clave privada.
     * @param encrypted El texto encriptado en formato Base64.
     * @return El texto desencriptado.
     * @throws NoSuchAlgorithmException Si no se encuentra el algoritmo especificado.
     * @throws NoSuchPaddingException Si no se encuentra el relleno especificado.
     * @throws InvalidKeyException Si la clave proporcionada es inválida.
     * @throws IllegalBlockSizeException Si el tamaño de bloque es incorrecto.
     * @throws BadPaddingException Si se produce un error de relleno.
     */
    public String decrypt(String encrypted) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
