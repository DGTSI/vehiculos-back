package fgjcdmx.gob.sava.Utilities;

public class MessagesUtil {
    /**
     * Mandar a imprimir en consola
     * @param mensaje (String) Mñensaje a imprimir
     */
    public static <T> void sout(T mensaje) {
        // System.out.println(mensaje);
    }

    /**
     * Mandar a imprimir en consola
     * @param mensaje (String) Mñensaje a imprimir
     */
    public static <T> void soutError(T mensaje) {
        System.err.println(mensaje);
}
}