package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.LibrarySystem.Model.*;
import lk.ijse.LibrarySystem.dto.Author;
import lk.ijse.LibrarySystem.dto.Issue;
import lk.ijse.LibrarySystem.dto.Return;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReturnFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtSearchIssuesId;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblMemberId;

    @FXML
    private Label lblIssuesDate;

    @FXML
    private JFXTextField txtReturnId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private TableView<Return> table;

    @FXML
    private TableColumn<Return,String> tblIssuesId;

    @FXML
    private TableColumn<Return,String> tblReturnId;

    @FXML
    private TableColumn<Return,String> tblBookId;

    @FXML
    private TableColumn<Return,String> tblMemberId;

    @FXML
    private TableColumn<Return,String> tblIssuesDate;

    @FXML
    private TableColumn<Return,String> tblReturnDate;

    @FXML
    private TableColumn<Return,String> tblQty;


    @FXML
    void onReturn(ActionEvent event) throws SQLException {
        String IssuseId = txtSearchIssuesId.getText();
        String BookQty = txtQty.getText();
        String ReturnId = txtReturnId.getText();
        String BookId = lblBookId.getText();
        String IssuseDate = lblIssuesDate.getText();

        Return returns = new Return();

        returns.setIssuseId(IssuseId);
        returns.setReturnDate(String.valueOf(LocalDate.now()));
        returns.setBookId(BookId);
        returns.setReturnId(ReturnId);
        returns.setRreturnQty(BookQty);
        returns.setIssuseId(IssuseDate);

        System.out.println(returns.getReturnId()+" "+returns.getReturnDate()+" "+returns.getIssuseId()+" "+returns.getRreturnQty());

        boolean R1 = ReturnModel.returnSet(returns,BookQty,BookId,IssuseId);
    }

    @FXML
    void search(ActionEvent event) throws SQLException {
        String searchId = txtSearchIssuesId.getText();

        Issue issue = ReturnModel.Search(searchId);

        lblBookId.setText(issue.getMemberId());
        lblMemberId.setText(issue.getDueDate());
        lblIssuesDate.setText(issue.getIssusDate());

        System.out.println(lblBookId);
        System.out.println(lblMemberId);
    }

    private void setTurnId() {
        try {
            String newTurnId = ReturnModel.genarateTurnId();
            txtReturnId.setText(newTurnId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @SneakyThrows
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//

//    }



    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        table.getColumns().get(0) ).setCellValueFactory(new PropertyValueFactory("ReturnId"));
//        ( (TableColumn) this.table.getColumns().get(1) ).setCellValueFactory(new PropertyValueFactory("IssuseId"));
//        ( (TableColumn) this.table.getColumns().get(2) ).setCellValueFactory(new PropertyValueFactory("RreturnQty"));
//        ( (TableColumn) this.table.getColumns().get(3) ).setCellValueFactory(new PropertyValueFactory("ReturnDate"));
//        ( (TableColumn) this.table.getColumns().get(4) ).setCellValueFactory(new PropertyValueFactory("BookId"));
//        ( (TableColumn) this.table.getColumns().get(5) ).setCellValueFactory(new PropertyValueFactory("IssuseDate"));

        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ReturnId"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("IssuseId"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("RreturnQty"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("BookId"));
        table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("IssuseDate"));

        txtReturnId.setEditable(false);
        LoadTable();
        setTurnId();

    }
    public void LoadTable() throws SQLException {
        ArrayList<Return> returns = ReturnModel.loadAllReturns();
        this.table.setItems(FXCollections.observableArrayList(returns));
    }
}