package fgjcdmx.gob.sava.Services;

import fgjcdmx.gob.sava.Impl.CatalogsImpl;
import fgjcdmx.gob.sava.Models.Entities.CatPostalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogsService {
    @Autowired
    private CatalogsImpl catImpl;

    // Buscar los datos del codigo postal por codigo postal
    public CatPostalCode findCpByCp(String cp) {
        return this.catImpl.fetchCpByCp(cp);
    }
}
