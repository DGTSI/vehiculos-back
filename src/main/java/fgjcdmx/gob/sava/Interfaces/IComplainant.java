package fgjcdmx.gob.sava.Interfaces;

import fgjcdmx.gob.sava.Models.Entities.Complainant;

public interface IComplainant {

    // Buscar denunciante por control universal (ctrluinv)
    public Complainant findComplainantByCtrluinv(String ctrluinv);

    // Validar existencia de datos por control universal (ctrluinv)
    public Integer validateComplainantByCtrluinv(String ctrluinv);

    // Guardar datos del denunciante
    public Integer saveComplainant(Complainant dto);
}
