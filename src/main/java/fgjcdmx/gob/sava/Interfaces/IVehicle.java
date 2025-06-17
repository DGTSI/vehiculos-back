package fgjcdmx.gob.sava.Interfaces;

import fgjcdmx.gob.sava.Models.Dtos.DatosvehiculoDto;

public interface IVehicle {

    // Buscar el ctrluinv por carpeta de investigación
    Integer fechfolderbyFolder (String dto);

    // Buscar el ctrluinv por placas
    String fetchPlacasByPlaca (String dto);

    // Buscar el control universal mediante la carpeta de investigacion y placas o numero de serie
    String fetchCtrluinvByFolderAndPlaceORSerial(DatosvehiculoDto dto);
}
