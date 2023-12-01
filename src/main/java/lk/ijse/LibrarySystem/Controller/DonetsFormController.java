package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.LibrarySystem.Model.DonetionModel;
import lk.ijse.LibrarySystem.Model.ExhibitionModel;
import lk.ijse.LibrarySystem.dto.Donates;
import lk.ijse.LibrarySystem.dto.Exibition;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DonetsFormController implements Initializable {

        @FXML
        private AnchorPane root;

        @FXML
        private JFXComboBox<?> txtExibitionId;

        @FXML
        private JFXTextField txtDonetionID;

        @FXML
        private TableView<Donates> tblDonetion;

        @FXML
        private TableColumn<?, ?> colDonetion;

        @FXML
        private TableColumn<?, ?> colAmmount;

        @FXML
        private TableColumn<?, ?> colDonetby;

        @FXML
        private TableColumn<?, ?> colReview;

        @FXML
        private TableColumn<?, ?> colExibiyionID;

        @FXML
        private JFXTextField txtDonetionName;

        @FXML
        private JFXTextArea txtReview;

        @FXML
        private JFXTextField txtAmmount;

    @FXML
    private JFXComboBox<?> cmbExibitionID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadExibitionIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void DonetionTable(){
        tblDonetion.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("donetionId"));
        tblDonetion.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Ammount"));
        tblDonetion.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("review"));
        tblDonetion.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("donetionName"));
        tblDonetion.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("ExibitionId"));

        ArrayList<Donates> donates;
        try {
            donates = DonetionModel.loadAllDonetions();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tblDonetion.setItems(FXCollections.observableArrayList(donates));
    }

    public void OnSave(ActionEvent actionEvent) throws SQLException {
        String donetionID = txtDonetionID.getText();
        String ammount = txtAmmount.getText();
        String review = txtReview.getText();
        String donetBy = txtDonetionName.getText();
        String exibitionID = String.valueOf(cmbExibitionID.getValue());

        Donates donetion = new Donates();
        donetion.setDonetionId(donetionID);
        donetion.setDonetionName(donetBy);
        donetion.setAmmount(Double.parseDouble(ammount));
        donetion.setReview(review);
        donetion.setExibitionId(exibitionID);

        boolean d1 = DonetionModel.DonetionAdd(donetion);
    }

    public void OnSelectExibitionID(ActionEvent actionEvent) throws SQLException {
        Exibition exibition  = ExhibitionModel.searchFrom((String) cmbExibitionID.getValue());
    }

    public void loadExibitionIds() throws SQLException {
        ArrayList<String> ExibitionIds = ExhibitionModel.loadAllExibitionIds();

        ObservableList ids = FXCollections.observableArrayList();

        for (String id : ExibitionIds){
            ids.add(id);
        }
        cmbExibitionID.setItems(ids);
    }

}


