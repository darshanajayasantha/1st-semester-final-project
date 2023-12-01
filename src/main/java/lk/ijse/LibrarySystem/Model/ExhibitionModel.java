package lk.ijse.LibrarySystem.Model;

import lk.ijse.LibrarySystem.db.DBConnection;
import lk.ijse.LibrarySystem.dto.Exibition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExhibitionModel {
    public static boolean Save(Exibition exibition) throws SQLException {

        Connection con = DBConnection.getInstance().getConnection();
        String sql = "insert into exibitions values(?,?,?,?)";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1,exibition.getExibitionId());
        stm.setObject(2,exibition.getExibitionDate());
        stm.setObject(3,exibition.getExibitionTime());
        stm.setObject(4,exibition.getExibitionDesc());

        return stm.executeUpdate() > 0;

    }

    public static String genarateTurnId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT ExibitionsId FROM Exibitions ORDER BY ExibitionsId DESC LIMIT 1 ");

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String lastExibitionId = rs.getString(1);

            String[] temp = lastExibitionId.split("E");

            int value = Integer.parseInt(( temp[1] ));
            String nextValue = ( value + 1 ) + "";

            if (nextValue.length() == 1) {
                return "E00" + nextValue;
            } else if (nextValue.length() == 2) {
                return "E0" + nextValue;
            } else {
                return "E";
            }


        }
        return "E001";
    }
    public static ArrayList<String> loadAllExibitionIds() throws SQLException {

        Connection con = DBConnection.getInstance().getConnection();

        String sql = "select ExibitionsId from exibitions";

        PreparedStatement stm = con.prepareStatement(sql);

        ResultSet result = stm.executeQuery();

        ArrayList<String> ExibitionIds = new ArrayList<>();

        while (result.next()) {
            ExibitionIds.add(result.getString(1));
        }
        return ExibitionIds;
    }
    public static Exibition searchFrom(String id) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select * from exibitions where ExibitionsId=?";

        PreparedStatement stm = con.prepareStatement(sql);
        stm.setObject(1,id);

        ResultSet result = stm.executeQuery();

        if (result.next()) {
            Exibition exibition = new Exibition();
            exibition.setExibitionId(result.getString(1));
            exibition.setExibitionDate(result.getString(2));
            exibition.setExibitionTime(result.getString(3));
            exibition.setExibitionDesc(result.getString(4));
            return exibition;
        }
        return null;
    }
}
