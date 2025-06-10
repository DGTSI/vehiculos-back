package fgjcdmx.gob.sava.Interfaces;

import fgjcdmx.gob.sava.Models.Entities.CatPostalCode;

public interface ICatalogs {
    // Buscar datos del codigo postal por codigo postal
    public CatPostalCode fetchCpByCp(String cp);
}
