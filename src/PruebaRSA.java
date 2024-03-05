import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class PruebaRSA {

    public static void main(String[] args) {
        RSA rsa = new RSA();
        Scanner scanner = new Scanner(System.in);

        try {
            // Generar un par de claves RSA
            rsa.generateKeyPair(2048);
            System.out.println("Se han generado las claves RSA.");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error al generar las claves RSA: " + e.getMessage());
            return;
        }

        System.out.println("Introduce el texto a encriptar:");
        String texto = scanner.nextLine();

        try {
            // Encriptar el texto y guardarlo en un archivo
            String textoEncriptado = rsa.encrypt(texto);
            System.out.println("Texto encriptado: " + textoEncriptado);
            FileUtils.guardarTextoEnArchivo("texto_encriptado.txt", textoEncriptado);
            System.out.println("Texto encriptado guardado en el archivo 'texto_encriptado.txt'.");
        } catch (Exception e) {
            System.out.println("Error al encriptar y guardar el texto: " + e.getMessage());
        }

        System.out.println("¿Desea desencriptar el texto? (s/n)");
        String opcion = scanner.nextLine().toLowerCase();
        if (opcion.equals("s")) {
            try {
                // Leer el texto encriptado desde el archivo
                String textoEncriptado = FileUtils.leerTextoDesdeArchivo("texto_encriptado.txt");
                // Desencriptar el texto
                String textoDesencriptado = rsa.decrypt(textoEncriptado);
                System.out.println("Texto desencriptado: " + textoDesencriptado);
            } catch (Exception e) {
                System.out.println("Error al desencriptar el texto: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
