package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.LibrarySystem.Model.MemberModel;
import lk.ijse.LibrarySystem.Model.UserModel;
import lk.ijse.LibrarySystem.dto.User;
import lk.ijse.LibrarySystem.util.Regx.Regx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void OnBack(MouseEvent event) {
        try {
            Parent view = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
            Stage primaryStage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(view);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSingUp(MouseEvent event) throws SQLException {
        String name = this.txtName.getText();
        String userName = this.txtUserName.getText();
        String password = this.txtPassword.getText();

        User user = new User();

        user.setName(name);
        user.setUserName(userName);
        user.setPassWord(password);

        boolean users = UserModel.singUp(user);
    }

    public void onName(KeyEvent keyEvent) {
        Pattern compile = Regx.getNamePattern();
        Matcher matcher = compile.matcher(txtName.getText());
        boolean matches = matcher.matches();
        if (matches){
            txtName.setUnFocusColor(Paint.valueOf("green"));
        }else{
            txtName.setUnFocusColor(Paint.valueOf("red"));
        }
    }

    public void OnUserName(KeyEvent keyEvent) {
        Pattern compile = Regx.getUserName();
        Matcher matcher = compile.matcher(txtUserName.getText());
        boolean matches = matcher.matches();
        if (matches){
            txtUserName.setUnFocusColor(Paint.valueOf("green"));
        }else{
            txtUserName.setUnFocusColor(Paint.valueOf("red"));
        }
    }

    public void OnPassword(KeyEvent keyEvent) {
        Pattern compile = Regx.getPasswordPattern();
        Matcher matcher = compile.matcher(txtPassword.getText());
        boolean matches = matcher.matches();
        if (matches){
            txtPassword.setUnFocusColor(Paint.valueOf("green"));
        }else{
            txtPassword.setUnFocusColor(Paint.valueOf("red"));
        }
    }
}
