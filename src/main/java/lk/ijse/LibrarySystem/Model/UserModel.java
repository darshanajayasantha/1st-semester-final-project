package lk.ijse.LibrarySystem.Model;

import lk.ijse.LibrarySystem.db.DBConnection;
import lk.ijse.LibrarySystem.dto.Member;
import lk.ijse.LibrarySystem.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    public static User SearchUser(String name) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "select * from user where userName = ?";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1, name);

        ResultSet result = stm.executeQuery();

        if (result.next()) {
            User user = new User();
            user.setName(result.getString(1));
            user.setUserName(result.getString(2));
            user.setPassWord(result.getString(3));
            return user;
        }

        return null;
    }

    public static boolean singUp(User user) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO user(name , userName, password) VALUES(?, ?, ?)";

        PreparedStatement stm = con.prepareStatement(sql);

       stm.setObject(1,user.getName());
       stm.setObject(2,user.getUserName());
       stm.setObject(3,user.getPassWord());

        int result = stm.executeUpdate();
        return result > 0;
    }
}
