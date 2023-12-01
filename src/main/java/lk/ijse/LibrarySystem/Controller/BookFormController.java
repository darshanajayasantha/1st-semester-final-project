package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.LibrarySystem.Model.AuthorModel;
import lk.ijse.LibrarySystem.Model.BookModel;
import lk.ijse.LibrarySystem.Model.MemberModel;
import lk.ijse.LibrarySystem.dto.Author;
import lk.ijse.LibrarySystem.dto.Book;
import lk.ijse.LibrarySystem.dto.Member;
import lk.ijse.LibrarySystem.util.Regx.Regx;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtBookId;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtBookQty;

    @FXML
    private JFXTextField txtBookDiscription;

    @FXML
    private JFXTextField txtBookQr;

    @FXML
    private JFXTextField SearchBookId;

    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn<Book,String> tblBookId;

    @FXML
    private TableColumn<Book,String> tblBookName;

    @FXML
    private TableColumn<Book,String> tblBookAuthor;

    @FXML
    private TableColumn<Book,Integer> tblBookQty;

    @FXML
    private TableColumn<Book,String> tblBookDiscription;

    @FXML
    private TableColumn<Book,String> tblBookQr;


    @FXML
    private JFXComboBox<Book> cmbBookAuthor;

    @FXML
    void delete(ActionEvent event) throws SQLException {
        String BookId = SearchBookId.getText();
        boolean delete = BookModel.Delete(BookId);
        LoadTable();
    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        String bookId = txtBookId.getText();
        String bookName = txtBookName.getText();
        String bookAuthor = String.valueOf(cmbBookAuthor.getValue());
        String bookQty = txtBookQty.getText();
        String bookDiscription = txtBookDiscription.getText();
//        String bookQr = txtBookQr.getText();

        Book book = new Book();

        book.setId(bookId);
        book.setName(bookName);
        book.setAuthor(bookAuthor);
        book.setQty(Integer.parseInt(bookQty));
        book.setDiscription(bookDiscription);
//        book.setQr(bookQr);

        boolean books = BookModel.save(book);
        LoadTable();

        txtBookId.clear();
        txtBookName.clear();
        txtBookQty.clear();
        txtBookDiscription.clear();
//        txtBookQr.clear();

    }

    @FXML
    void search(ActionEvent event) throws SQLException {
        String searchId = SearchBookId.getText();

        Book book = BookModel.Search(searchId);

        txtBookId.setText(book.getId());
        txtBookName.setText(book.getName());
        cmbBookAuthor.setId(book.getAuthor());
        txtBookQty.setText(String.valueOf(book.getQty()));
        txtBookDiscription.setText(book.getDiscription());
//        txtBookQr.getText(book.setQr());

    }

    @FXML
    void update(ActionEvent event) throws SQLException {
        String Id = this.txtBookId.getText();
        String Name = this.txtBookName.getText();
        String author = String.valueOf(this.cmbBookAuthor.getValue());
        String qty = this.txtBookQty.getText();
        String discription = this.txtBookDiscription.getText();

        Book book = new Book();

        book.setId(Id);
        book.setName(Name);
        book.setAuthor(author);
        book.setQty(Integer.parseInt(qty));
        book.setDiscription(discription);

        boolean books = BookModel.Update(book);
        LoadTable();
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ((TableColumn)this.table.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory("id"));
        ((TableColumn)this.table.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory("name"));
        ((TableColumn)this.table.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory("author"));
        ((TableColumn)this.table.getColumns().get(3)).setCellValueFactory(new PropertyValueFactory("Qty"));
        ((TableColumn)this.table.getColumns().get(4)).setCellValueFactory(new PropertyValueFactory("Discription"));

        txtBookId.setEditable(false);
        setTurnId();
        LoadTable();

        try {
              loadAuthorIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void LoadTable() throws SQLException {
        ArrayList<Book> Books = BookModel.loadAllBook();
        this.table.setItems(FXCollections.observableArrayList(Books));
    }

    public void loadAuthorIds() throws SQLException {

        ArrayList<String> AuthorIds = AuthorModel.loadAllAuthor();

        ObservableList ids = FXCollections.observableArrayList();

        for (String id : AuthorIds){
            ids.add(id);
        }
        cmbBookAuthor.setItems(ids);
   }

    private void setTurnId() {
        String newTurnId = null;
        try {
            newTurnId = BookModel.genarateTurnId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtBookId.setText(newTurnId);

    }
    public void cmbBookAuthor(ActionEvent actionEvent) {
    }

    public void bookName(KeyEvent keyEvent) {
        Pattern compile = Regx.getNamePattern();
        Matcher matcher = compile.matcher(txtBookName.getText());
        boolean matches = matcher.matches();
        if (matches){
            txtBookName.setUnFocusColor(Paint.valueOf("green"));
        }else{
            txtBookName.setUnFocusColor(Paint.valueOf("red"));
        }
    }
}
