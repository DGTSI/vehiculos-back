package fgjcdmx.gob.sava.Impl;


import fgjcdmx.gob.sava.Interfaces.IVehicle;
import fgjcdmx.gob.sava.Utilities.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleImpl implements IVehicle {

    @Autowired
    @Qualifier("informixJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    // Buscar el ctrluinv por carpeta de investigación
    @Override
    public String fechfolderbyFolder(String dto) {
        String sql = "SELECT ctrluinv, carpetainv FROM expediente:informix.expcontrol WHERE carpetainv = ?";
        Object[] params = new Object[]{ dto };

        MessagesUtil.sout(sql);

        List<String> folder = this.jdbcTemplate.query(sql, (rs, row)->rs.getString("ctrluinv"), params);
        return folder.isEmpty() ? null : folder.getFirst();

}

    // Buscar el ctrluinv por placas
    @Override
    public String fetchPlacasByPlaca(String dto) {
        String sql = "SELECT ctrluinv FROM uinv:informix.cenvehiculos where placas = ?";
        Object[] params = new Object[]{ dto };

        MessagesUtil.sout(sql);

        List<String> placas = this.jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("ctrluinv"), params);
        return placas.isEmpty() ? null : placas.getFirst();
    }


    // Buscar el ctrluinv por numero de seria
    @Override
    public String fetchnoSerieBynoSerie(String dto) {
        String sql = "select ctrluinv from uinv:informix.cenvehiculos where noserie = ?;";
        Object[] params = new Object[]{ dto };

        MessagesUtil.sout(sql);

        List<String> noserie = this.jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("ctrluinv"), params);
        return noserie.isEmpty() ? null : noserie.getFirst();

    }
}
