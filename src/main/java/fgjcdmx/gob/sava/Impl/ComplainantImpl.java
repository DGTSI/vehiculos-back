package fgjcdmx.gob.sava.Impl;

import fgjcdmx.gob.sava.Helpers.ComplainantQuerys;
import fgjcdmx.gob.sava.Interfaces.IComplainant;
import fgjcdmx.gob.sava.Models.Entities.Complainant;
import fgjcdmx.gob.sava.Utilities.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComplainantImpl implements IComplainant {

    @Autowired
    @Qualifier("informixJdbcTemplate")
    private JdbcTemplate informixTemplate;

    @Autowired
    @Qualifier("postgresJdbcTemplate")
    private JdbcTemplate postgresTemplate;

    // Buscar denunciante por control universal (ctrluinv)
    @Override
    public Complainant findComplainantByCtrluinv(String ctrluinv) {
        String query = ComplainantQuerys.findComplainantByCtrluinvQuery();
        Object[] params = new Object[]{ ctrluinv };

        MessagesUtil.sout(query);

        List<Complainant> complainants = this.informixTemplate.query(
            query, new BeanPropertyRowMapper<>(Complainant.class), params
        );

        return complainants.isEmpty() ? null : complainants.getFirst();
    }

    // Validar existencia de datos por control universal (ctrluinv)
    @Override
    public Integer validateComplainantByCtrluinv(String ctrluinv) {
        String query = "SELECT COUNT(ctrluinv) FROM tb_denunciante WHERE ctrluinv = ?";
        Object[] params = new Object[]{ ctrluinv };

        MessagesUtil.sout(query);

        return this.postgresTemplate.queryForObject(query, Integer.class, params);
    }

    // Guardar datos del denunciante
    @Override
    public Integer saveComplainant(Complainant ctrluinv) {
        String query = ComplainantQuerys.saveComplainantQuery();
        Object[] params = new Object[]{
                ctrluinv.getNombre(), ctrluinv.getPaterno(), ctrluinv.getMaterno(), ctrluinv.getCurp(), ctrluinv.getCelular(), ctrluinv.getTelefono(),
                ctrluinv.getCorreoe(), ctrluinv.getCodigopostal(), ctrluinv.getCalle(), ctrluinv.getNoexterior(), ctrluinv.getNointerior(),
                1, ctrluinv.getCtrluinv(), ctrluinv.getCtrllave(), ctrluinv.getCvecalidadper()
        };

        MessagesUtil.sout(query);

        return this.postgresTemplate.update(query, params);
    }

    // Guardar datos del contact
    public Integer saveContact(Complainant dto) {
        String query = ComplainantQuerys.saveContactQuery();
        Object[] params = new Object[]{
                dto.getNombre(), dto.getPaterno(), dto.getMaterno(), dto.getCurp(), dto.getCelular(), dto.getTelefono(),
                dto.getCorreoe(), dto.getCodigopostal(), dto.getCalle(), dto.getNoexterior(), dto.getNointerior(),
                1, dto.getCtrluinv(), dto.getCtrllave(), dto.getCvecalidadper()
        };

        MessagesUtil.sout(query);

        return this.postgresTemplate.update(query, params);
    }

    // Verificar la existencia del contacto
    public Integer verifyContact(String ctrluinv) {
        String query = "SELECT COUNT(1) FROM tb_contacto WHERE ctrluinv = ?";
        Object[] params = new Object[]{ ctrluinv };

        MessagesUtil.sout(query);

        return this.postgresTemplate.queryForObject(query, Integer.class, params);
    }

}
