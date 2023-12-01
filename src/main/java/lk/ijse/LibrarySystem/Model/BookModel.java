package lk.ijse.LibrarySystem.Model;

import lk.ijse.LibrarySystem.db.DBConnection;
import lk.ijse.LibrarySystem.dto.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookModel {

    public static boolean save(Book book) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO book(bookId, bookName, Author, qty, bookDiscription ) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1,book.getId());
        stm.setObject(2,book.getName());
        stm.setObject(3,book.getAuthor());
        stm.setObject(4,book.getQty());
        stm.setObject(5,book.getDiscription());
//        stm.setObject(6,book.getQr());

        int result = stm.executeUpdate();
        return result > 0;
    }

    public static Book Search (String id) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select * from book where bookId=?";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1,id);

        ResultSet result = stm.executeQuery();

        if(result.next()){
            Book book = new Book();
            book.setId(result.getString(1));
            book.setName(result.getString(2));
            book.setAuthor(result.getString(3));
            book.setQty(Integer.parseInt(result.getString(4)));
            book.setDiscription(result.getString(5));
//            book.setQr(result.getString(5));
            return book;
        }
        return null;
    }

    public static ArrayList<Book> loadAllBook() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select * from book";

        PreparedStatement stm = con.prepareStatement(sql);

        ResultSet result = stm.executeQuery();

        ArrayList books = new ArrayList();

        while (result.next()) {
            Book book = new Book();
            book.setId(result.getString(1));
            book.setName(result.getString(2));
            book.setAuthor(result.getString(3));
            book.setQty(Integer.parseInt(String.valueOf(result.getInt(4))));
            book.setDiscription(result.getString(5));
            books.add(book);
        }

        return books;
    }
    public static ArrayList<String> loadAllBookNames() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select bookName from book";

        PreparedStatement stm = con.prepareStatement(sql);

        ResultSet result = stm.executeQuery();

        ArrayList <String> bookNames = new ArrayList();

        while (result.next()) {
            bookNames.add(result.getString(1));
        }
        return bookNames;
    }

    public static ArrayList<String> loadAllBookIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select bookId from book";

        PreparedStatement stm = con.prepareStatement(sql);

        ResultSet result = stm.executeQuery();

        ArrayList <String> bookIds = new ArrayList<>();

        while (result.next()) {
            bookIds.add(result.getString(1));
        }
        return bookIds;
    }

    public static Boolean Update(Book book) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "update book set bookName = ?, author = ?, qty =?, bookDiscription =? where bookId=?";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1, book.getName());
        stm.setObject(2, book.getAuthor());
        stm.setObject(3, book.getQty());
        stm.setObject(4, book.getDiscription());
        stm.setObject(5, book.getId());

        int result = stm.executeUpdate();

        if (result == 1) {
            return true;
        }
        return null;
    }

    public static boolean Delete(String bookId) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "delete from book where bookId = ?";

        PreparedStatement stm = con.prepareStatement(sql);
        stm.setObject(1, bookId);
        int result = stm.executeUpdate();
        return false;
    }

    public static String genarateTurnId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT bookId FROM book ORDER BY bookID DESC LIMIT 1 ");

        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            String lastMemberId = rs.getString(1);

            String[] temp = lastMemberId.split("B");

            int value = Integer.parseInt((temp[1]));
            String nextValue = (value+1) + "";

            if (nextValue.length() == 1 ){
                return "B00"+ nextValue;
            }else if (nextValue.length() == 2 ){
                return "B0" + nextValue;
            }else {
                return "B";
            }


        }
        return  "B001";

    }
}