package fgjcdmx.gob.sava.Interfaces;

public interface IVehicle {

    // Buscar el ctrluinv por carpeta de investigación
    public String fechfolderbyFolder (String dto);

    // Buscar el ctrluinv por placas
    public String fetchPlacasByPlaca (String dto);

    // Buscar el ctrluinv por numero de seria
    public String fetchnoSerieBynoSerie (String dto);
}
