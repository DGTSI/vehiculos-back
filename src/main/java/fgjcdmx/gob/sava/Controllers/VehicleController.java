package fgjcdmx.gob.sava.Controllers;

import fgjcdmx.gob.sava.Models.Dtos.DatosvehiculoDto;
import fgjcdmx.gob.sava.Services.ComplainantService;
import fgjcdmx.gob.sava.Services.VehicleServices;
import fgjcdmx.gob.sava.Utilities.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vehicle/")
public class VehicleController {

    @Autowired
    private VehicleServices vehicleServices;

    @Autowired
    private ComplainantService complainantService;

    // Api - lanza ctrluinv mediante carpeta de investigacion
    @PostMapping("consultar-folder")
    public ResponseEntity<?> consultarFolder(@RequestBody DatosvehiculoDto dto) {
        try {
            String ctrluinv = this.vehicleServices.fetchCtrluinvByFolderAndPlaceORSerial(dto);
            if(ctrluinv == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();

            boolean existContact = this.complainantService.verifyContact(ctrluinv);
            return ResponseEntity.status(HttpStatus.OK).body(existContact);
        } catch (DataAccessException e) {
            MessagesUtil.soutError(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }
}
