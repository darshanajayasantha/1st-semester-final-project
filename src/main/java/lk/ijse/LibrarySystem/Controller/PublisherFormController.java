package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.LibrarySystem.Model.*;
import lk.ijse.LibrarySystem.dto.Author;
import lk.ijse.LibrarySystem.dto.Publisher;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PublisherFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtSearchId;

    @FXML
    private JFXTextField txtPublisherId;

    @FXML
    private JFXTextField txtPublisherName;

    @FXML
    private JFXTextField txtPublishYear;

    @FXML
    private JFXComboBox<Publisher> cmbPublishedBook;

    @FXML
    private TableView<Publisher> table;

    @FXML
    private TableColumn<Publisher , String> tblPublisherId;

    @FXML
    private TableColumn<Publisher , String> tblPublisherName;

    @FXML
    private TableColumn<Publisher , String> tblBookName;

    @FXML
    private TableColumn<Publisher , String> tblPublishedYear;

    @FXML
    void delete(ActionEvent event) throws SQLException {
        String searchId = txtSearchId.getText();
        boolean delete = PublisherModel.delete(searchId);
        LoadTable();
        txtPublishYear.clear();
        txtPublisherId.clear();
        txtPublisherName.clear();

    }

    @FXML
    void search(ActionEvent event) throws SQLException {
        String searchId = txtSearchId.getText();

        Publisher publisher = PublisherModel.Search(searchId);

        txtPublisherId.setText(publisher.getId());
        txtPublisherName.setText(publisher.getName());
        cmbPublishedBook.setId(publisher.getPublishedBook());
        txtPublishYear.setText(publisher.getYear());

    }

    @FXML
    void update(ActionEvent event) throws SQLException {
        String id = txtPublisherId.getText();
        String Name = txtPublisherName.getText();
        String publishYear = txtPublishYear.getText();
        String publishedBook = String.valueOf(cmbPublishedBook.getValue());

        Publisher publisher = new Publisher();

        publisher.setId(id);
        publisher.setName(Name);
        publisher.setYear(publishYear);
        publisher.setPublishedBook(publishedBook);

        boolean p1 = PublisherModel.Update(publisher);
        LoadTable();

        txtPublishYear.clear();
        txtPublisherName.clear();
        txtPublisherId.clear();
    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        String publisherId = txtPublisherId.getText();
        String publisherName = txtPublisherName.getText();
        String publishYear = txtPublishYear.getText();
        String publishedBook = String.valueOf(cmbPublishedBook.getValue());

        Publisher publisher = new Publisher();

        publisher.setId(publisherId);
        publisher.setName(publisherName);
        publisher.setYear(publishYear);
        publisher.setPublishedBook(publishedBook);

        boolean p1 = PublisherModel.Save(publisher);
        LoadTable();

        txtPublisherId.clear();
        txtPublisherName.clear();
        txtPublishYear.clear();
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ((TableColumn)this.table.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory("Id"));
        ((TableColumn)this.table.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory("Name"));
        ((TableColumn)this.table.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory("publishedBook"));
        ((TableColumn)this.table.getColumns().get(3)).setCellValueFactory(new PropertyValueFactory("Year"));

        loadBooksNames();
        LoadTable();
        setTurnId();
        txtPublisherId.setEditable(false);

    }

    public void LoadTable() throws SQLException {
        ArrayList<Publisher> publisher = PublisherModel.LoadAllPublishers();
        this.table.setItems(FXCollections.observableArrayList(publisher));
    }

    public void loadBooksNames() throws SQLException {

        ArrayList<String> BookNames = BookModel.loadAllBookNames();

        ObservableList ids = FXCollections.observableArrayList();

        for (String id : BookNames){
            ids.add(id);
        }
        cmbPublishedBook.setItems(ids);
    }
    private void setTurnId() {
        try {
            String newTurnId = PublisherModel.genarateTurnId();
            txtPublisherId.setText(newTurnId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
