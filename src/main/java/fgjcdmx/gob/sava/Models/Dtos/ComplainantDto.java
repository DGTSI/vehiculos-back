package fgjcdmx.gob.sava.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainantDto {
    private String ctrluinv;
    private String ctrllave;
    private String cvecalidadper;
    private String nombre;
    private String paterno;
    private String materno;
    private String calle;
    private String noexterior;
    private String nointerior;
    private String referencia;
    private String codigopostal;
    private String telefono;
    private String celular;
    private String correoe;
    private String curp;
}

