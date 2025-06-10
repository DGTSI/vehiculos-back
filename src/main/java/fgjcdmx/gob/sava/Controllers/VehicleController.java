package fgjcdmx.gob.sava.Controllers;

import fgjcdmx.gob.sava.Models.Entities.Complainant;
import fgjcdmx.gob.sava.Services.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("api/vehicle/")
public class VehicleController {

    @Autowired
    private VehicleServices vehicleServices;

    // Api - lanza ctrluinv mediante carpeta de investigacion
    @GetMapping("consultar-folder")
    public ResponseEntity<?> consultarFolder(@RequestParam String folder) {
        try {
            Complainant complainant = vehicleServices.consultarFolder(folder);

            return ResponseEntity.status(HttpStatus.OK).body(complainant);
        } catch (DataAccessException e) {
            System.err.println(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Api - lanza ctrluinv mediante placas
    @GetMapping("consultar-placas")
    public ResponseEntity<?> consultarPlacas(@RequestParam String placas){

        try {
            Complainant complainant = vehicleServices.consultarPlacas(placas);

            return ResponseEntity.status(HttpStatus.OK).body(complainant);
        } catch (DataAccessException e) {
            System.err.println(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Api - lanza ctrluinv mediante el numero de serie
    @GetMapping("consultar-noserie")
    public ResponseEntity<?> consultarNoserie(@RequestParam String noserie){
        try{
            Complainant complainant = vehicleServices.consultarNoserie(noserie);

            return ResponseEntity.status(HttpStatus.OK).body(complainant);
        } catch (DataAccessException e){
            System.err.println(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
