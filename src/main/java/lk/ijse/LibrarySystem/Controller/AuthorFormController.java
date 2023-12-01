package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.LibrarySystem.Model.AuthorModel;
import lk.ijse.LibrarySystem.Model.BookModel;
import lk.ijse.LibrarySystem.Model.IssueModel;
import lk.ijse.LibrarySystem.Model.MemberModel;
import lk.ijse.LibrarySystem.db.DBConnection;
import lk.ijse.LibrarySystem.dto.Author;
import lk.ijse.LibrarySystem.dto.Book;
import lk.ijse.LibrarySystem.dto.Member;
import lk.ijse.LibrarySystem.util.Regx.Regx;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtSearchAuthor;

    @FXML
    private JFXTextField txtAuthorId;

    @FXML
    private JFXTextField txtAuthorName;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private TableView<Author> table;

    @FXML
    private TableColumn<Author,String> tblAuthorId;

    @FXML
    private TableColumn<Author,String> tblAuthorName;

    @FXML
    private TableColumn<Author,String> tblBookName;


    @FXML
    void delete(ActionEvent event) throws SQLException {
        String searchId = txtSearchAuthor.getText();
        boolean delete = AuthorModel.delete(searchId);
        LoadTable();

        txtAuthorId.clear();
        txtAuthorName.clear();
        txtBookName.clear();
    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        String authorId = txtAuthorId.getText();
        String name = txtAuthorName.getText();
        String bookName = txtBookName.getText();

        Author author = new Author();

        author.setId(authorId);
        author.setName(name);
        author.setBookName(bookName);

        boolean authors = AuthorModel.Save(author);
        LoadTable();

        txtAuthorId.clear();
        txtAuthorName.clear();
        txtBookName.clear();
    }

    @FXML
    void search(ActionEvent event) throws SQLException {
        String searchId = txtSearchAuthor.getText();

        Author author = AuthorModel.Search(searchId);

        txtAuthorId.setText(author.getId());
        txtAuthorName.setText(author.getName());
        txtBookName.setText(author.getBookName());
    }

    @FXML
    void update(ActionEvent event) throws SQLException {
        String id = txtAuthorId.getText();
        String Name = txtAuthorName.getText();
        String BookName = txtBookName.getText();

        Author author = new Author();

        author.setId(id);
        author.setName(Name);
        author.setBookName(BookName);

        boolean authors = AuthorModel.Update(author);
        LoadTable();
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ((TableColumn)this.table.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory("id"));
        ((TableColumn)this.table.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory("name"));
        ((TableColumn)this.table.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory("bookName"));

        txtAuthorId.setEditable(false);
        LoadTable();
        setTurnId();
//        table.refresh();
    }
    private void LoadTable() throws SQLException {
        ArrayList<Author> authors = AuthorModel.LoadAllAuthors();
        this.table.setItems(FXCollections.observableArrayList(authors));
    }
    private void setTurnId() {
        try {
            String newTurnId = AuthorModel.genarateTurnId();
            txtAuthorId.setText(newTurnId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void BookName(KeyEvent keyEvent) {
        Pattern compile = Regx.getNamePattern();
        Matcher matcher = compile.matcher(txtBookName.getText());
        boolean matches = matcher.matches();
        if (matches){
            txtBookName.setUnFocusColor(Paint.valueOf("green"));
        }else{
            txtBookName.setUnFocusColor(Paint.valueOf("red"));
        }
    }

    public void AuthorName(KeyEvent keyEvent) {
        Pattern compile = Regx.getNamePattern();
        Matcher matcher = compile.matcher(txtAuthorName.getText());
        boolean matches = matcher.matches();
        if (matches){
            txtAuthorName.setUnFocusColor(Paint.valueOf("green"));
        }else{
            txtAuthorName.setUnFocusColor(Paint.valueOf("red"));
        }
    }
}
