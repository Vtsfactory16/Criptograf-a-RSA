import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/*
    * Clase para gestionar la lectura y escritura de archivos.
 */
public class FileUtils {

    public static void guardarTextoEnArchivo(String ruta, String texto) throws IOException {
        Files.write(Paths.get(ruta), texto.getBytes());
    }

    public static String leerTextoDesdeArchivo(String ruta) throws IOException {
        return new String(Files.readAllBytes(Paths.get(ruta)));
    }
}
