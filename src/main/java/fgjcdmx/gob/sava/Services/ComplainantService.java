package fgjcdmx.gob.sava.Services;

import fgjcdmx.gob.sava.Impl.ComplainantImpl;
import fgjcdmx.gob.sava.Models.Dtos.ComplainantDto;
import fgjcdmx.gob.sava.Models.Entities.CatPostalCode;
import fgjcdmx.gob.sava.Models.Entities.Complainant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplainantService {

    @Autowired
    private ComplainantImpl complainantImpl;

    @Autowired
    private CatalogsService catService;

    // Buscar al denunciante por ctrluinv
    public Complainant findComplainantByCtrluinv(String ctrluinv) {
        Complainant complainant = this.complainantImpl.findComplainantByCtrluinv(ctrluinv);

        if(complainant != null) {
            String cpComplainant = complainant.getCodigopostal();

            if(cpComplainant != null && !cpComplainant.isBlank()) {
                CatPostalCode cp = this.catService.findCpByCp(cpComplainant);
                complainant.setEstado(cp.getEstado());
                complainant.setColonia(cp.getAsentamiento());
                complainant.setMunicipio(cp.getMunicipio());
            }
        }
        return complainant;
    }

    // Validar que haya registros
    public boolean existComplainant(String ctrluinv) {
        Integer response = this.complainantImpl.validateComplainantByCtrluinv(ctrluinv);

        return response != null && response != 0;
    }

    // Guardar denunciante
    public Integer saveComplainant(ComplainantDto dto) {
        boolean exist = this.existComplainant(dto.getCtrluinv());
        if(exist) return 2;

        Integer save = this.complainantImpl.saveComplainant(dto);
        if(save != 1) return 1;

        return 0;
    }
}
