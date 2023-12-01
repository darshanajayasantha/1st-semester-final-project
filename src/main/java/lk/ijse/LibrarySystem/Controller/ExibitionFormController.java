package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.LibrarySystem.Model.AuthorModel;
import lk.ijse.LibrarySystem.Model.ExhibitionModel;
import lk.ijse.LibrarySystem.dto.Exibition;

import java.sql.SQLException;

public class ExibitionFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextArea textArea;

    @FXML
    private JFXTextField txtExibitionId;

    @FXML
    private DatePicker exhibitionDate;

    @FXML
    private ImageView datePicker;

    @FXML
    private JFXTextField txtTime;

    public void btnOnSendEmails(ActionEvent actionEvent) {

    }

    public void btnSave(ActionEvent actionEvent) throws SQLException {
        String ExibitionID = txtExibitionId.getText();
        String ExibitionDate = String.valueOf(exhibitionDate.getValue());
        String ExibitionTime = txtTime.getText();
        String ExibitionDesc = textArea.getText();

        Exibition exibition = new Exibition();
        exibition.setExibitionId(ExibitionID);
        exibition.setExibitionDate(ExibitionDate);
        exibition.setExibitionTime(ExibitionTime);
        exibition.setExibitionDesc(ExibitionDesc);

        boolean e1 = ExhibitionModel.Save(exibition);
    }


//    @FXML
//    void SendEmailFor(MouseEvent event) throws SQLException {
//        String exhibitionId = exibitionId.getText();
//        String exibitionDate = String.valueOf(exhibitionDate.getValue());
//        String exibitionTime = String.valueOf(exhibitiontime.getValue());
//        String exhibitionDiscripshion = textArea.getText();
//
//        Exibition exibition = new Exibition();
//
//        exibition.setId(exhibitionId);
//        exibition.setDate(exibitionDate);
//        exibition.setTime(exibitionTime);
//        exibition.setDiscriptipon(exhibitionDiscripshion);
//
//        boolean exhibitions = ExhibitionModel.Save(exibition);
//    }
//    public void initialize(){
//        exibitionId.setEditable(false);
//        setTurnId();
//    }
//    private void setTurnId() {
//        try {
//            String newTurnId = ExhibitionModel.genarateTurnId();
//            exibitionId.setText(newTurnId);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
}

