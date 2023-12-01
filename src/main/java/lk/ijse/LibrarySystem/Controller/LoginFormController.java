package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.LibrarySystem.Model.UserModel;
import lk.ijse.LibrarySystem.dto.User;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLOutput;

public class LoginFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassWord;

    @FXML
    private JFXButton btnLogin;

    public static AnchorPane rootcopy;

    public static String userId;

    public void initialize(){
        rootcopy = root;
    }

    @FXML
    void onLogin(ActionEvent event) throws IOException {
        System.out.println(txtPassWord.getText());
        System.out.println(txtUserName.getText());
//        try {
//            Parent view =
//            Stage primaryStage = (Stage) root.getScene().getWindow();
//            Scene scene = new Scene(view);
//            primaryStage.setScene(scene);
//            primaryStage.centerOnScreen();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            User user = UserModel.SearchUser(txtUserName.getText());

            if (user != null && user.getPassWord().equals(txtPassWord.getText())) {
                this.userId = user.getName();
                root.getChildren().clear();
                try {
//                    root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml")));

                    Stage stage = (Stage)root.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml"))));
                    stage.centerOnScreen();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.ERROR,
                        "UserName or Password Error",
                        ButtonType.OK
                ).show();

                txtUserName.clear();
                txtPassWord.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,
                    "Sql Error",
                    ButtonType.OK
            ).show();
            txtUserName.clear();
            txtPassWord.clear();
        }
    }

    @FXML
    void passwordOnAction(ActionEvent event) throws IOException {
        onLogin(new ActionEvent());
    }

    @FXML
    void userNameOnAction(ActionEvent event) throws IOException {
        onLogin(new ActionEvent());
    }

    @FXML
    void OnForgotPassword(ActionEvent event) {
        try {
            Parent view = FXMLLoader.load(this.getClass().getResource("/view/UserForm.fxml"));
            Stage primaryStage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(view);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnSingup(ActionEvent event) {
        try {
            Parent view = FXMLLoader.load(this.getClass().getResource("/view/UserForm.fxml"));
            Stage primaryStage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(view);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
