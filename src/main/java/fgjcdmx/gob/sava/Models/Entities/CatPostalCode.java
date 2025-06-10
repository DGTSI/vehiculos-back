package fgjcdmx.gob.sava.Models.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatPostalCode {
    private String codigopostal;
    private String asentamiento;
    private String tipo_asenta;
    private String municipio;
    private String estado;
    private String ciudad;
}
