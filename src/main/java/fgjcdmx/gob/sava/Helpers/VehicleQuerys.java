package fgjcdmx.gob.sava.Helpers;

public class VehicleQuerys {

    public static String fetchFolderAndPlaceOrSerialQuery() {
        StringBuilder query = new StringBuilder();

        query
                .append("SELECT ec.ctrluinv ")
                .append("FROM expediente:informix.expcontrol ec ")
                .append("JOIN uinv:informix.cenvehiculos cv ")
                .append("ON ec.ctrluinv = cv.ctrluinv ")
                .append("WHERE ec.carpetainv = ? ")
                .append("AND (")
                .append("cv.placas = ? ")
                .append("OR cv.noserie = ?")
                .append(") ")
                .append("AND cv.cvestatusauto = 16");

        return query.toString();
    }
}
