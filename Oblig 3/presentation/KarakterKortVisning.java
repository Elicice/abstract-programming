package no.uib.info233.oblig3.presentation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.uib.info233.oblig3.datalayer.KarakterDAO;
import no.uib.info233.oblig3.datalayer.KursDAO;
import no.uib.info233.oblig3.datalayer.StudentDAO;
import no.uib.info233.oblig3.model.Karakter;
import no.uib.info233.oblig3.model.Student;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Klasse for å vise karakterene til student
 * @author Cecilie Lyshoel
 * @author docs.oracle.com
 * @version Oblig3 v2.0
 */
public class KarakterKortVisning extends Application {

    private TextField userTextField;
    private Button btn;
    private final TableView table = new TableView();
    private TableView karakterTabell;
    private KarakterDAO kdao = new KarakterDAO();

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start-metode for å lage vindu der man kan søke opp student basert på studentnummer og få en visning av karakterer for studententen
     * @param primaryStage
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Karakterkort");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Karakterkort");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label studentNummer = new Label("Studentnummer:");
        grid.add(studentNummer, 0, 1);

        userTextField = new TextField();
        grid.add(userTextField, 0, 2);

        Label studentOutput = new Label("");
        grid.add(studentOutput, 0,3);

        karakterTabell = new TableView();
        grid.add(karakterTabell,0,4);
        karakterTabell.setPrefWidth(500);
        karakterTabell.setEditable(false);
        TableColumn karakterCol = new TableColumn("Karakter");
        TableColumn kursCol = new TableColumn("Kurskode");
        TableColumn kursNavnCol = new TableColumn("Kursnavn");
        kursNavnCol.setMinWidth(175);
        TableColumn skoleNavnCol = new TableColumn("Skole");
        karakterCol.setCellValueFactory(new PropertyValueFactory<>("karakter"));
        kursCol.setCellValueFactory(new PropertyValueFactory<>("kursKode"));
        kursNavnCol.setCellValueFactory(new PropertyValueFactory<>("kursNavn"));
        skoleNavnCol.setCellValueFactory(new PropertyValueFactory<>("skoleNavn"));

        karakterTabell.getColumns().addAll(karakterCol, kursCol, kursNavnCol, skoleNavnCol);


        //søkeknapp
        btn = new Button("Søk");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.TOP_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 2);

        //action når knapp trykkes
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                StudentDAO dao = StudentDAO.getInstance();
                KursDAO daoKurs = KursDAO.getInstance();
                Student student = dao.getStudentById(Integer.parseInt(userTextField.getText()));
                String studentNavn = student.getStudentNavn();
                String kull = student.getKullKode();
                studentOutput.setText("Navn: " + studentNavn + "  Årskull: " + kull + "   ");

                ArrayList<Karakter> karakterer = new ArrayList<Karakter>(kdao.getKarakterByStudent(Integer.parseInt(userTextField.getText())));

                karakterTabell.setItems(FXCollections.observableArrayList((Collection) karakterer));
                System.out.println(karakterer);
            }
        });

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        Scene scene = new Scene(grid, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

       // KarakterDAO dao = KarakterDAO.getInstance();


    }
    //public void setStudentData(Student student) {
     //   userTextField.setText(student.getStudentNavn());
    //}
}
