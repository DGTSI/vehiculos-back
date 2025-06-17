package fgjcdmx.gob.sava.Services;

import fgjcdmx.gob.sava.Impl.VehicleImpl;
import fgjcdmx.gob.sava.Models.Dtos.DatosvehiculoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServices {
    @Autowired
    private VehicleImpl vehicleImpl;

    public String fetchCtrluinvByFolderAndPlaceORSerial(DatosvehiculoDto dto) {
        return this.vehicleImpl.fetchCtrluinvByFolderAndPlaceORSerial(dto);
    }

    // Consultar ctrluinv por placas
    public String consultarCtrlunivplacas(String placas) {
        return this.vehicleImpl.fetchPlacasByPlaca(placas);
    }
}
