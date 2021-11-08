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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import no.uib.info233.oblig3.datalayer.InsertSQL;
import no.uib.info233.oblig3.datalayer.KarakterDAO;
import no.uib.info233.oblig3.datalayer.KullDAO;
import no.uib.info233.oblig3.datalayer.StudentDAO;
import no.uib.info233.oblig3.model.Student;

import java.util.Collection;
import java.util.List;

import static no.uib.info233.oblig3.datalayer.InsertSQL.insertStudent;

/**
 * Klasse for å vise karakterene til student
 * @author Cecilie Lyshoel
 * @author docs.oracle.com
 * @version Oblig3 v2.0
 */
public class RedigerStudent extends Application {

    private TextField userTextField;
    private Button btn;
    private final TableView table = new TableView();
    private TableView studentTabell;
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

        Text scenetitle = new Text("Student");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label skole = new Label("Skole:");
        grid.add(skole, 0, 1);

        userTextField = new TextField();
        grid.add(userTextField, 0, 2);

        Label studentOutput = new Label("");
        grid.add(studentOutput, 0,3);

        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new TableCell();
                    }
                };

        studentTabell = new TableView();

        grid.add(studentTabell,0,4);
        studentTabell.setPrefWidth(500);
        studentTabell.setEditable(true);

        studentTabell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Student s = (Student) studentTabell.getSelectionModel().getSelectedItem();
                if (s!=null) {
                    System.out.println("Hei: " + s.getStudentNavn());
                    subWindowTextField.setText(s.getStudentNavn());
                }
            }
        });

        TableColumn studentNrCol = new TableColumn("Student nr.");
        TableColumn studentNavnCol = new TableColumn("Navn");
        TableColumn kullCol = new TableColumn("Årskull");

        //mulighet for å redigere studentnavn
        studentNavnCol.setCellFactory(TextFieldTableCell.forTableColumn());

        studentNrCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNavnCol.setCellValueFactory(new PropertyValueFactory<>("studentNavn"));
        kullCol.setCellValueFactory(new PropertyValueFactory<>("kullKode"));

        //Ny verdi i tabellen når celle blir redigert
        studentNavnCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Student, String> t) {

                        Student student = t.getRowValue();
                        String newName = t.getNewValue();

                        student.setStudentNavn(newName);

                        StudentDAO.getInstance().saveStudent(student);
                    }
                }
        );


        studentTabell.getColumns().addAll(studentNrCol, studentNavnCol, kullCol);


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
                List<Student> student = dao.getStudentBySkole(userTextField.getText());
                //studentOutput.setText("Navn: " + studentNavn + "  Årskull: " + kull + "   ");

                //ArrayList<Karakter> karakterer = new ArrayList<Karakter>(kdao.getKarakterByStudent(Integer.parseInt(userTextField.getText())));
                studentTabell.setItems(FXCollections.observableArrayList((Collection) student));
                System.out.println(student.size());
            }
        });

        //tekstfelt for å legge til navn
        final TextField addNavn = new TextField();
        addNavn.setMaxWidth(studentNavnCol.getPrefWidth());
        addNavn.setPromptText("Navn");


        //tekstfelt for å legge til kull
        final TextField addKull = new TextField();
        addKull.setMaxWidth(kullCol.getPrefWidth());
        addKull.setPromptText("Kull");

        final Button addButton = new Button("Legg til");

        HBox leggTil = new HBox(20);
        leggTil.setAlignment(Pos.BOTTOM_LEFT);
        leggTil.getChildren().addAll(addNavn, addKull, addButton);
        grid.add(leggTil, 0, 5);


        //Når man trykker på knappen skal man kunne legge til input i textfelt inn i database
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                InsertSQL.insertStudent(StudentDAO.getInstance().getNextStudentId(),
                        addNavn.getText(), addKull.getText());

                if (KullDAO.getInstance().getKullByKullKode(addKull.getText()) == null) {
                    InsertSQL.insertKull(addKull.getText(), userTextField.getText());
                }
            }
        });


        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        Scene scene = new Scene(grid, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        KarakterDAO dao = KarakterDAO.getInstance();
    }

    TextField subWindowTextField;
    Stage stage;
}
