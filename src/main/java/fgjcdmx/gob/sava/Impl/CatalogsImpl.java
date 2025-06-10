package fgjcdmx.gob.sava.Impl;

import fgjcdmx.gob.sava.Interfaces.ICatalogs;
import fgjcdmx.gob.sava.Models.Entities.CatPostalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatalogsImpl implements ICatalogs {

    @Autowired
    @Qualifier("postgresJdbcTemplate")
    private JdbcTemplate template;

    // Buscar datos del codigo postal por codigo postal
    @Override
    public CatPostalCode fetchCpByCp(String cp) {
        String query = "SELECT * FROM cat_codigopostal WHERE codigopostal = ?";
        Object[] params = new Object[]{ cp };

        List<CatPostalCode> result = this.template.query(
            query, new BeanPropertyRowMapper<>(CatPostalCode.class), params
        );

        return result.isEmpty() ? null : result.getFirst();
    }
}
