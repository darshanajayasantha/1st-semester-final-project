package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import lk.ijse.LibrarySystem.Model.EmailModel;
import lk.ijse.LibrarySystem.Model.MemberModel;
import lk.ijse.LibrarySystem.dto.Member;

import javax.mail.MessagingException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmailFromController implements Initializable {

    @FXML
    private JFXComboBox<?> cmbEmail;

    @FXML
    private TextArea txtWrite;

    @FXML
    private JFXTextField fromtextfield;

    public void OnCmbEmail(ActionEvent actionEvent) throws SQLException {
        Member member = MemberModel.Search((String) cmbEmail.getValue());
    }

    public void OnSend(ActionEvent actionEvent) throws MessagingException {
        EmailModel.sendMail("sensipeducationalinstitus@gmail.com" , "ocxceiwknltqdvhd" , String.valueOf(cmbEmail.getValue()), txtWrite.getText());
                try{
                new Alert(Alert.AlertType.CONFIRMATION, "Email Send!").show();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    public void loadAllEmail() throws SQLException {
        ArrayList<String> EmailIds = MemberModel.loadAllMemberEmails();

        ObservableList mails = FXCollections.observableArrayList();

        for (String id : EmailIds){
            mails.add(id);
        }
        cmbEmail.setItems(mails);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromtextfield.setEditable(false);
        try {
            loadAllEmail();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
