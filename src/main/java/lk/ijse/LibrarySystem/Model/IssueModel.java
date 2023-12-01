package lk.ijse.LibrarySystem.Model;

import lk.ijse.LibrarySystem.db.DBConnection;
import lk.ijse.LibrarySystem.dto.Issue;
import lk.ijse.LibrarySystem.dto.Suplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IssueModel {

    public static boolean Issues(Issue issuse, String qty, String bookID) throws SQLException {
        DBConnection.getInstance().getConnection().setAutoCommit(false);

        Connection con = DBConnection.getInstance().getConnection();
        String sql = "insert into issuse values(?,?,?,?,?,?)";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1,issuse.getIssusId());
        stm.setObject(2,issuse.getBookId());
        stm.setObject(3,issuse.getIssusDate());
        stm.setObject(4,issuse.getMemberId());
        stm.setObject(5,issuse.getDueDate());
        stm.setObject(6,issuse.getIssuseQty());

        int TakeIt = stm.executeUpdate();

        if (TakeIt>0){
            String sql2 = "update book set qty=qty-? where bookId=?";

            PreparedStatement stm2 = con.prepareStatement(sql2);

            stm2.setObject(1,qty);
            stm2.setObject(2,bookID);

            int itemUpdate = stm2.executeUpdate();

            if(itemUpdate>0){
                con.commit();
                con.setAutoCommit(true);
                return true;
            }
            con.rollback();
            con.setAutoCommit(true);
        }
        con.rollback();
        con.setAutoCommit(true);

        return false;
    }
    public static ArrayList<Issue> loadAllIssuse() throws SQLException {

        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select * from issuse";

        PreparedStatement stm = con.prepareStatement(sql);

        ResultSet result = stm.executeQuery();

        ArrayList<Issue> issuses = new ArrayList<>();

        while (result.next()) {
            Issue issuse1 = new Issue();

            issuse1.setIssusId(result.getString(1));
            issuse1.setBookId(result.getString(2));
            issuse1.setIssusDate(result.getString(3));
            issuse1.setMemberId(result.getString(4));
            issuse1.setDueDate(result.getString(5));
            issuse1.setIssuseQty(result.getString(6));

            issuses.add(issuse1);
        }
        return issuses;
    }

    public static String genarateTurnId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT iid FROM issuse ORDER BY iid DESC LIMIT 1 ");

        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            String lastMemberId = rs.getString(1);

            String[] temp = lastMemberId.split("I");

            int value = Integer.parseInt((temp[1]));
            String nextValue = (value+1) + "";

            if (nextValue.length() == 1 ){
                return "I00"+ nextValue;
            }else if (nextValue.length() == 2 ){
                return "I0" + nextValue;
            }else {
                return "I";
            }
        }
        return  "I001";
    }
    public static Issue IssuseSearch(String iid) throws SQLException {

        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select * from issuse where iid = ?";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1, iid);

        ResultSet result = stm.executeQuery();

        if (result.next()) {
            Issue issuses = new Issue();
            issuses.setIssusId(result.getString(1));
            issuses.setBookId(result.getString(2));
            issuses.setIssusDate(result.getString(3));
            issuses.setMemberId(result.getString(4));
            issuses.setDueDate(result.getString(5));
            issuses.setIssuseQty(result.getString(6));

            return issuses;
        }
        return null;
    }
}
