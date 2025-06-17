package fgjcdmx.gob.sava.Impl;


import fgjcdmx.gob.sava.Helpers.VehicleQuerys;
import fgjcdmx.gob.sava.Interfaces.IVehicle;
import fgjcdmx.gob.sava.Models.Dtos.DatosvehiculoDto;
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
    private JdbcTemplate informixJdbc;

    // Buscar el ctrluinv por carpeta de investigación
    @Override
    public Integer fechfolderbyFolder(String dto) {
//        String sql = "SELECT ctrluinv, carpetainv FROM expediente:informix.expcontrol WHERE carpetainv = ?";
        String sql = "SELECT COUNT(1) FROM expediente:informix.expcontrol WHERE carpetainv = ?";
        Object[] params = new Object[]{ dto };

        MessagesUtil.sout(sql);

        return this.informixJdbc.queryForObject(sql, Integer.class, params);

}

    // Buscar el ctrluinv por placas
    @Override
    public String fetchPlacasByPlaca(String dto) {
        String sql = "SELECT ctrluinv FROM uinv:informix.cenvehiculos where placas = ?";
        Object[] params = new Object[]{ dto };

        MessagesUtil.sout(sql);

        List<String> placas = this.informixJdbc.query(sql, (rs, rowNum) -> rs.getString("ctrluinv"), params);
        return placas.isEmpty() ? null : placas.getFirst();
    }

    @Override
    public String fetchCtrluinvByFolderAndPlaceORSerial(DatosvehiculoDto dto) {
        String query = VehicleQuerys.fetchFolderAndPlaceOrSerialQuery();
        Object[] params = new Object[] { dto.getFolder(), dto.getPlate(), dto.getSerial() };

        MessagesUtil.sout(query);

        List<String> response = this.informixJdbc.query(query, params, (rs, row) -> rs.getString("ctrluinv"));
        return response.isEmpty() ? null : response.getFirst();
    }
}
