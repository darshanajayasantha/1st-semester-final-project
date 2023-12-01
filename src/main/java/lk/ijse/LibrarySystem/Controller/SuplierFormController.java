package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.LibrarySystem.Model.AuthorModel;
import lk.ijse.LibrarySystem.Model.MemberModel;
import lk.ijse.LibrarySystem.Model.SupliersModel;
import lk.ijse.LibrarySystem.dto.Author;
import lk.ijse.LibrarySystem.dto.Suplier;
import lk.ijse.LibrarySystem.util.Regx.Regx;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuplierFormController implements Initializable {

    @FXML
    private JFXTextField SearchId;

    @FXML
    private JFXTextField txtSuplierName;

    @FXML
    private TableView<Suplier> table;

    @FXML
    private TableColumn<Suplier,String> tblId;

    @FXML
    private TableColumn<Suplier,String> tblName;

    @FXML
    private TableColumn<Suplier,String> tblContact;

    @FXML
    private TableColumn<Suplier,String> tblAddress;

    @FXML
    private TableColumn<Suplier,String> tblSuplierBook;

    @FXML
    private JFXTextField txtSuplierId;

    @FXML
    private JFXTextField txtSuplierContact;

    @FXML
    private JFXTextField txtSupliesBook;

    @FXML
    private JFXTextField txtSuplierAddress;

    @FXML
    void delete(ActionEvent event) throws SQLException {
        String searchId = SearchId.getText();
        boolean delete = SupliersModel.Delete(searchId);
        loadTable();
    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        String suplierId = txtSuplierId.getText();
        String suplierName = txtSuplierName.getText();
        String suplierContact = txtSuplierContact.getText();
        String suplierAddress = txtSuplierAddress.getText();
        String suplierBooks = txtSupliesBook.getText();

        Suplier suplier = new Suplier();

        suplier.setId(suplierId);
        suplier.setName(suplierName);
        suplier.setContact(suplierContact);
        suplier.setAddress(suplierAddress);
        suplier.setSuplierBooks(suplierBooks);

        boolean supliers = SupliersModel.Save(suplier);

        txtSuplierName.clear();
        txtSuplierName.clear();
        txtSuplierAddress.clear();
        txtSuplierContact.clear();
        txtSupliesBook.clear();
        loadTable();
    }

    @FXML
    void search(ActionEvent event) throws SQLException {
        String searchId = SearchId.getText();
        Suplier suplier = SupliersModel.Search(searchId);

        txtSuplierId.setText(suplier.getId());
        txtSuplierName.setText(suplier.getName());
        txtSuplierContact.setText(suplier.getContact());
        txtSuplierAddress.setText(suplier.getAddress());
        txtSupliesBook.setText(suplier.getSuplierBooks());
    }

    @FXML
    void update(ActionEvent event) throws SQLException {

        String id = txtSuplierId.getText();
        String Name = txtSuplierName.getText();
        String contact = txtSuplierContact.getText();
        String address = txtSuplierAddress.getText();
        String suplierBooks = txtSupliesBook.getText();

        Suplier suplier = new Suplier();

        suplier.setId(id);
        suplier.setName(Name);
        suplier.setContact(contact);
        suplier.setAddress(address);
        suplier.setSuplierBooks(suplierBooks);

        boolean supliers = SupliersModel.Update(suplier);
        loadTable();
    }
    @Override
    @SneakyThrows
    public void initialize(URL location, ResourceBundle resources) {
        txtSuplierId.setEditable(false);
        setTurnId();
        loadTable();

        ((TableColumn)this.table.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory("id"));
        ((TableColumn)this.table.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory("name"));
        ((TableColumn)this.table.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory("Contact"));
        ((TableColumn)this.table.getColumns().get(3)).setCellValueFactory(new PropertyValueFactory("Address"));
        ((TableColumn)this.table.getColumns().get(4)).setCellValueFactory(new PropertyValueFactory("SuplierBooks"));
    }

    public void loadTable() throws SQLException {
        ArrayList<Suplier> supplier = SupliersModel.LoadAllSupplier();
        this.table.setItems(FXCollections.observableArrayList(supplier));
    }

    private void setTurnId() {
        try {
            String newTurnId = SupliersModel.genarateTurnId();
            txtSuplierId.setText(newTurnId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void suplierContactOnAction(KeyEvent event) {
        Pattern compile = Regx.getMobilePattern();
        Matcher matcher = compile.matcher(txtSuplierContact.getText());
        boolean matches = matcher.matches();
        if (matches) {
            txtSuplierContact.setUnFocusColor(Paint.valueOf("green"));
        } else {
            txtSuplierContact.setUnFocusColor(Paint.valueOf("red"));
        }
    }
    @FXML
    void suplierNameOnAction(KeyEvent event) {
        Pattern compile = Regx.getNamePattern();
        Matcher matcher = compile.matcher(txtSuplierName.getText());
        boolean matches = matcher.matches();
        if (matches){
            txtSuplierName.setUnFocusColor(Paint.valueOf("green"));
        }else{
            txtSuplierName.setUnFocusColor(Paint.valueOf("red"));
        }
    }
    @FXML
    void SuplierAddressOnAction(KeyEvent event) {
            Pattern compile = Regx.getAddressPattern();
            Matcher matcher = compile.matcher(txtSuplierAddress.getText());
            boolean matches = matcher.matches();
            if (matches){
            txtSuplierAddress.setUnFocusColor(Paint.valueOf("green"));
            }else {
                txtSuplierAddress.setUnFocusColor(Paint.valueOf("red"));
            }
    }

    @FXML
    void SuplierBookOnAction(KeyEvent event) {
        Pattern compile = Regx.getNamePattern();
        Matcher matcher = compile.matcher(txtSupliesBook.getText());
        boolean matches = matcher.matches();
        if (matches){
            txtSupliesBook.setUnFocusColor(Paint.valueOf("green"));
        }else{
            txtSupliesBook.setUnFocusColor(Paint.valueOf("red"));
        }
    }
}
