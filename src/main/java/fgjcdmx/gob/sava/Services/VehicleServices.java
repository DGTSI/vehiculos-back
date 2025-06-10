package fgjcdmx.gob.sava.Services;

import fgjcdmx.gob.sava.Impl.VehicleImpl;
import fgjcdmx.gob.sava.Models.Entities.Complainant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class VehicleServices {
    @Autowired
    private VehicleImpl vehicleImpl;

    @Autowired
    private ComplainantService complainantService;

    // Consultar ctrluinv mediante carpeta de investigacion
    public Complainant consultarFolder(String dto) throws SQLException {
        String ctrluinv = vehicleImpl.fechfolderbyFolder(dto);
        return this.complainantService.findComplainantByCtrluinv(ctrluinv);
    }

    // Consultar ctrluinv mediante mediante placas
    public Complainant consultarPlacas(String dto) throws SQLException {
        String ctrluinv = vehicleImpl.fetchPlacasByPlaca(dto);
        return this.complainantService.findComplainantByCtrluinv(ctrluinv);
    }

    // Consultar ctrluinv mediante el numero de serie
    public Complainant consultarNoserie(String dto) throws SQLException {
        String ctrluinv = vehicleImpl.fetchnoSerieBynoSerie(dto);
        return this.complainantService.findComplainantByCtrluinv(ctrluinv);
    }
}
