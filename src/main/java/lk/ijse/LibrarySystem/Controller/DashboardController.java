package lk.ijse.LibrarySystem.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.LibrarySystem.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardController {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane MainPane;

    @FXML
    private AnchorPane DashBoardPane;

    @FXML
    private AnchorPane newPane;

    @FXML
    private Text lblTopic;

    @FXML
    private Text txtTime;

    @FXML
    private Text txtDate;

    @FXML
    private LineChart<?, ?> LineChart;

    @FXML
    void dashboard(ActionEvent event) throws IOException {
        try {
            Parent view = FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml"));
            Stage primaryStage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(view);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logInMemberForm(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/MemberForm.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("MEMBERS");
    }

    @FXML
    void loginAuthorform(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/AuthorForm.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("AUTHORS");
    }

    @FXML
    void loginBookForm(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/BookForm.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("BOOKS");
    }

    @FXML
    void loginDonateform(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/DonetsForm.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("DONATES");
    }

    @FXML
    void loginExibitionform(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/ExibitionForm.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("EXIBITION");
    }

    @FXML
    void loginFinesform(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/fineFormController.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("FINES");
    }

    @FXML
    void loginIssueform(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/issueForm.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("ISSUES");
    }

    @FXML
    void loginPublisheform(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/PublisherForm.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("PUBLISHERS");
    }

    @FXML
    void loginReturnform(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/returnForm.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("RETURNS");
    }

    public void loginSupliersform(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/SuplierForm.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("SUPLIERS");
    }

    @FXML
    void LoginEmail(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/EmailFrom.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("EMAILS");
    }

    @FXML
    void onLogout(ActionEvent event) {
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

    public void initialize(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                txtDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                txtTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        };
        timer.start();

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("1st week");
        series1.getData().add(new XYChart.Data("day1",0));
        series1.getData().add(new XYChart.Data("day2",15));
        series1.getData().add(new XYChart.Data("day3",10));
        series1.getData().add(new XYChart.Data("day4",45));
        series1.getData().add(new XYChart.Data("day5",60));
        series1.getData().add(new XYChart.Data("day6",80));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2nd week");
        series2.getData().add(new XYChart.Data("day1",0));
        series2.getData().add(new XYChart.Data("day2",5));
        series2.getData().add(new XYChart.Data("day3",25));
        series2.getData().add(new XYChart.Data("day4",50));
        series2.getData().add(new XYChart.Data("day5",70));
        series2.getData().add(new XYChart.Data("day6",90));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("3rd week");
        series3.getData().add(new XYChart.Data("day1",0));
        series3.getData().add(new XYChart.Data("day2",5));
        series3.getData().add(new XYChart.Data("day3",30));
        series3.getData().add(new XYChart.Data("day4",55));
        series3.getData().add(new XYChart.Data("day5",80));
        series3.getData().add(new XYChart.Data("day6",90));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("4th week");
        series4.getData().add(new XYChart.Data("day1",0));
        series4.getData().add(new XYChart.Data("day2",10));
        series4.getData().add(new XYChart.Data("day3",30));
        series4.getData().add(new XYChart.Data("day4",55));
        series4.getData().add(new XYChart.Data("day5",70));
        series4.getData().add(new XYChart.Data("day6",97));

        XYChart.Series series5 = new XYChart.Series();
        series5.setName("5th week");
        series5.getData().add(new XYChart.Data("day1",0));
        series5.getData().add(new XYChart.Data("day2",25));
        series5.getData().add(new XYChart.Data("day3",30));
        series5.getData().add(new XYChart.Data("day4",45));
        series5.getData().add(new XYChart.Data("day5",70));
        series5.getData().add(new XYChart.Data("day6",90));

        XYChart.Series series6 = new XYChart.Series();
        series6.setName("6th week");
        series6.getData().add(new XYChart.Data("day1",0));
        series6.getData().add(new XYChart.Data("day2",10));
        series6.getData().add(new XYChart.Data("day3",25));
        series6.getData().add(new XYChart.Data("day4",50));
        series6.getData().add(new XYChart.Data("day5",72));
        series6.getData().add(new XYChart.Data("day6",95));

        LineChart.getData().addAll(series1,series2,series3,series4,series5,series6);

    }

    public void onMemberReport(MouseEvent mouseEvent) throws FileNotFoundException, JRException, SQLException {
        InputStream input=new FileInputStream(new File("D:\\LibrarySystem\\src\\main\\resources\\Report\\Member.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

    public void onBookReport(MouseEvent mouseEvent) throws FileNotFoundException, JRException, SQLException {
        InputStream input=new FileInputStream(new File("D:\\LibrarySystem\\src\\main\\resources\\Report\\Book.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

    public void onPublisherReport(MouseEvent mouseEvent) throws FileNotFoundException, JRException, SQLException {
        InputStream input=new FileInputStream(new File("D:\\LibrarySystem\\src\\main\\resources\\Report\\publisher.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

    public void onAuthorReport(MouseEvent mouseEvent) throws FileNotFoundException, JRException, SQLException {
        InputStream input=new FileInputStream(new File("D:\\LibrarySystem\\src\\main\\resources\\Report\\Author.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

    public void onSuplierReport(MouseEvent mouseEvent) throws FileNotFoundException, JRException, SQLException {
        InputStream input=new FileInputStream(new File("D:\\LibrarySystem\\src\\main\\resources\\Report\\Supliers.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

    public void onReturnReport(MouseEvent mouseEvent) throws FileNotFoundException, JRException, SQLException {
        InputStream input=new FileInputStream(new File("D:\\LibrarySystem\\src\\main\\resources\\Report\\Return.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

    public void onIssueReport(MouseEvent mouseEvent) throws FileNotFoundException, JRException, SQLException {
        InputStream input=new FileInputStream(new File("D:\\LibrarySystem\\src\\main\\resources\\Report\\Issues.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }
}