package lk.ijse.LibrarySystem.Model;

import lk.ijse.LibrarySystem.db.DBConnection;
import lk.ijse.LibrarySystem.dto.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorModel {
    public static boolean Save(Author author) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO author(authorId, Name, bookName) VALUES(?, ?, ?)";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1,author.getId());
        stm.setObject(2,author.getName());
        stm.setObject(3,author.getBookName());

        int result = stm.executeUpdate();
        return result > 0;
    }

    public static Author Search(String searchId) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select * from author where authorId=?";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1,searchId);

        ResultSet result = stm.executeQuery();

        if(result.next()) {
            Author author = new Author();
            author.setId(result.getString(1));
            author.setName(result.getString(2));
            author.setBookName(result.getString(3));

            return author;
        }
        return null;
    }

    public static ArrayList<String> loadAllAuthor() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select  AuthorId from author";

        PreparedStatement stm = con.prepareStatement(sql);

        ResultSet result = stm.executeQuery();

        ArrayList <String> authorIds = new ArrayList();

        while (result.next()) {
            authorIds.add(result.getString(1));
//            Author author = new Author();
//            author.setId(result.getString(1));
//            author.setName(result.getString(2));
        }
        return authorIds;
    }

    public static boolean delete(String searchId) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "delete from author where authorId = ?";

        PreparedStatement stm = con.prepareStatement(sql);
        stm.setObject(1,searchId);
        int result = stm.executeUpdate();

        return false;
    }

    public static ArrayList<Author> LoadAllAuthors() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select * from author";

        PreparedStatement stm = con.prepareStatement(sql);

        ResultSet result = stm.executeQuery();

        ArrayList<Author> authors = new ArrayList<>();

        while(result.next()) {
            Author author = new Author();

            author.setId(result.getString(1));
            author.setName(result.getString(2));
            author.setBookName(result.getString(3));

            authors.add(author);
        }
        return authors;
    }

    public static Boolean Update(Author author) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "update author set name=?,bookName=? where AuthorId=?";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1, author.getName());
        stm.setObject(2, author.getBookName());
        stm.setObject(3, author.getId());

        int result = stm.executeUpdate();

        if (result == 1) {
            return true;
        }
        return null;
    }

    public static String genarateTurnId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT authorId FROM author ORDER BY authorId DESC LIMIT 1 ");

        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            String lastMemberId = rs.getString(1);

            String[] temp = lastMemberId.split("A");

            int value = Integer.parseInt((temp[1]));
            String nextValue = (value+1) + "";

            if (nextValue.length() == 1 ){
                return "A00"+ nextValue;
            }else if (nextValue.length() == 2 ){
                return "A0" + nextValue;
            }else {
                return "A";
            }


        }
        return  "A001";
    }
}
