import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Clase para gestionar la lectura y escritura de archivos.
 */
public class FileUtils {

    /**
     * Guarda el texto en el archivo especificado.
     * @param ruta La ruta del archivo donde se guardará el texto.
     * @param texto El texto a guardar en el archivo.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    public static void guardarTextoEnArchivo(String ruta, String texto) throws IOException {
        Files.write(Paths.get(ruta), texto.getBytes());
    }

    /**
     * Lee el texto desde el archivo especificado.
     * @param ruta La ruta del archivo desde donde se leerá el texto.
     * @return El texto leído desde el archivo.
     * @throws IOException Si ocurre un error de entrada/salida al leer desde el archivo.
     */
    public static String leerTextoDesdeArchivo(String ruta) throws IOException {
        return new String(Files.readAllBytes(Paths.get(ruta)));
    }
}
