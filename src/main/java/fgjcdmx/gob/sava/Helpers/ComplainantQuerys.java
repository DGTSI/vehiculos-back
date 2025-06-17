package fgjcdmx.gob.sava.Helpers;

public class ComplainantQuerys {

    // Query para buscar datos del denunciante por el control universal (ctrluinv)
    public static String findComplainantByCtrluinvQuery() {
        StringBuilder query = new StringBuilder();

        query
                .append("SELECT ")
                .append("p.ctrluinv, ")
                .append("p.ctrllave, ")
                .append("i.cvecalidadper, ")
                .append("i.nombre, ")
                .append("i.paterno, ")
                .append("i.materno, ")
                .append("d.calle, ")
                .append("d.noexterior, ")
                .append("d.nointerior, ")
                .append("d.referencia, ")
                .append("d.codigopostal, ")
                .append("d.telefono, ")
                .append("d.celular, ")
                .append("d.correoe, ")
                .append("dc.curp ")
                .append("FROM uinv:informix.cenpersona p ")
                .append("INNER JOIN uinv:informix.ceninvolucrado i ")
                .append("ON p.ctrllave = i.ctrllave AND i.cvecalidadper = '1' ")
                .append("LEFT JOIN uinv:informix.cendomicilio d ")
                .append("ON p.ctrllave = d.ctrllave ")
                .append("LEFT JOIN uinv:informix.cendatocomplemento dc ")
                .append("ON p.ctrllave = dc.ctrllave ")
                .append("WHERE p.ctrluinv = ?");

        return query.toString();
    }

    // Query para guardar los datos del denunciante
    public static String saveComplainantQuery() {
        StringBuilder query = new StringBuilder();

        query
                .append("INSERT INTO tb_denunciante ")
                .append("(nombre, paterno, materno, curp, telefono1, telefono2, correo, codigo_postal, calle, num_ext, num_int, status, ctrluinv, ctrllave, cvecalidadper) ")
                .append("VALUES ")
                .append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        return query.toString();
    }

    // Query para guardar los datos de contacto
    public static String saveContactQuery() {
        StringBuilder query = new StringBuilder();

        query
                .append("INSERT INTO tb_contacto ")
                .append("(nombre, paterno, materno, curp, telefono1, telefono2, correo, codigo_postal, calle, num_ext, num_int, status, ctrluinv, ctrllave, cvecalidadper) ")
                .append("VALUES ")
                .append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        return query.toString();
    }

}
