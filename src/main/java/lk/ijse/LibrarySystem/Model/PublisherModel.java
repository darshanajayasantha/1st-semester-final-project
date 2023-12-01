package lk.ijse.LibrarySystem.Model;

import lk.ijse.LibrarySystem.db.DBConnection;
import lk.ijse.LibrarySystem.dto.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PublisherModel {

    public static boolean Save(Publisher publisher) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO publisher (publisherId, name, yearOfPublish,publishedBook) VALUES(?, ?, ?,?)";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1,publisher.getId());
        stm.setObject(2,publisher.getName());
        stm.setObject(3,publisher.getYear());
        stm.setObject(4,publisher.getPublishedBook());

        int result = stm.executeUpdate();
        return result > 0;
    }

    public static Publisher Search(String searchId) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select * from publisher where publisherId=?";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1,searchId);

        ResultSet result = stm.executeQuery();

        if(result.next()) {
            Publisher publisher = new Publisher();
            publisher.setId(result.getString(1));
            publisher.setName(result.getString(2));
            publisher.setYear(result.getString(3));
            publisher.setPublishedBook(result.getString(4));

            return publisher;
        }
        return null;
    }

    public static boolean delete(String searchId) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "delete from publisher where publisherId = ?";

        PreparedStatement stm = con.prepareStatement(sql);
        stm.setObject(1,searchId);
        int result = stm.executeUpdate();

        return false;
    }

    public static Boolean Update(Publisher publisher) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "update publisher set name=?,yearOfPublish=? ,publishedBook=? where publisherId=?";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1,publisher.getName());
        stm.setObject(2,publisher.getYear());
        stm.setObject(3,publisher.getPublishedBook());
        stm.setObject(4, publisher.getId());

        int result = stm.executeUpdate();

        if (result == 1) {
            return true;
        }
        return null;
    }


    public static ArrayList<Publisher> LoadAllPublishers() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select * from publisher";

        PreparedStatement stm = con.prepareStatement(sql);

        ResultSet result = stm.executeQuery();

        ArrayList<Publisher> publishers = new ArrayList<>();

        while(result.next()) {
            Publisher publisher = new Publisher();

            publisher.setId(result.getString(1));
            publisher.setName(result.getString(2));
            publisher.setYear(result.getString(3));
            publisher.setPublishedBook(result.getString(4));

            publishers.add(publisher);
        }
        return publishers;
    }
    public static String genarateTurnId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT publisherId FROM publisher ORDER BY publisherId DESC LIMIT 1 ");

        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            String lastPublisherId = rs.getString(1);

            String[] temp = lastPublisherId.split("P");

            int value = Integer.parseInt((temp[1]));
            String nextValue = (value+1) + "";

            if (nextValue.length() == 1 ){
                return "P00"+ nextValue;
            }else if (nextValue.length() == 2 ){
                return "P0" + nextValue;
            }else {
                return "P";
            }


        }
        return  "P001";

    }
}
