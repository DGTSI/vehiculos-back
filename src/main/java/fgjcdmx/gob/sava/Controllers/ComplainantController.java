package fgjcdmx.gob.sava.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fgjcdmx.gob.sava.Models.Dtos.DatosvehiculoDto;
import fgjcdmx.gob.sava.Models.Entities.Complainant;
import fgjcdmx.gob.sava.Services.ComplainantService;
import fgjcdmx.gob.sava.Services.VehicleServices;
import fgjcdmx.gob.sava.Utilities.MessagesUtil;
import fgjcdmx.gob.sava.Utilities.TextFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/complainant/")
public class ComplainantController {

    @Autowired
    private ComplainantService complainantService;

    @Autowired
    private VehicleServices vehicleServices;

    // Api - Verificar existencia de datos del denunciante
    @GetMapping("validate-complainant")
    public ResponseEntity<?> validateComplainantByCtrluinv(
            @RequestParam String ctrluinv
    ) {
        try {
            boolean exist = this.complainantService.existComplainant(ctrluinv);
            return ResponseEntity.status(HttpStatus.OK).body(exist);
        } catch (DataAccessException e) {
            MessagesUtil.soutError(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    // Api - Guardar datos del denunciante
    @Transactional
    @PostMapping("save-complainant")
    public ResponseEntity<?> saveComplainant(@RequestBody Map<String, Object> payload) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            Object dtoRaw = payload.get("dto");
            Object folderRaw = payload.get("folder");
            Complainant dto = mapper.convertValue(dtoRaw, Complainant.class);
            DatosvehiculoDto datos = mapper.convertValue(folderRaw, DatosvehiculoDto.class);
            TextFormat.toUppercase(dto);
            dto.setCorreoe(dto.getCorreoe().toLowerCase());

            // Guardar denunciante y contacto
            String ctrluniv = this.vehicleServices.consultarCtrlunivplacas(datos.getPlate());
            Integer save1 = this.complainantService.saveComplainantAndContact(ctrluniv, dto);

            if(save1 == 2) return ResponseEntity.status(HttpStatus.CONFLICT).build();

            boolean validate = save1 == 0;
            return ResponseEntity.status(HttpStatus.OK).body(validate);

        } catch (DataAccessException | IllegalAccessException e) {
            MessagesUtil.soutError(e.getLocalizedMessage());
            // Los datos no cumplen las reglas en BD
            if(e.toString().contains("DataIntegrityViolationException"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
