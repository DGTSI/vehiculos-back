package fgjcdmx.gob.sava.Controllers;

import fgjcdmx.gob.sava.Models.Dtos.ComplainantDto;
import fgjcdmx.gob.sava.Services.ComplainantService;
import fgjcdmx.gob.sava.Utilities.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/complainant/")
public class ComplainantController {

    @Autowired
    private ComplainantService complainantService;

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
    @PostMapping("save-complainant")
    public ResponseEntity<?> saveComplainant(
            @RequestBody ComplainantDto dto
    ) {
        try {
            Integer save = this.complainantService.saveComplainant(dto);
            if(save == 2) return ResponseEntity.status(HttpStatus.CONFLICT).build();

            boolean validate = save == 0;
            return ResponseEntity.status(HttpStatus.OK).body(validate);
        } catch (DataAccessException e) {
            MessagesUtil.soutError(e.getLocalizedMessage());
            // Los datos no cumplen las reglas en BD
            if(e.toString().contains("DataIntegrityViolationException"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
