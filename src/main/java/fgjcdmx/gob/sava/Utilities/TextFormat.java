package fgjcdmx.gob.sava.Utilities;

import java.lang.reflect.Field;

public class TextFormat {
    /**
     * Convertir los valores de un objeto a mayusculas
     * @param objetos (T) N cantidad de objetos
     * @param <T> Método generico
     * @throws IllegalAccessException
     */
    public static <T> void toUppercase(T... objetos) throws IllegalAccessException {
        for (T objeto : objetos) {
            Field[] campos = objeto.getClass().getDeclaredFields();
            for (Field campo : campos) {
                campo.setAccessible(true);
                if (campo.getType().equals(String.class)) {
                    String valor = (String) campo.get(objeto);
                    if (valor != null) {
                        campo.set(objeto, valor.toUpperCase());
                    }
                }
            }
        }
    }
}
