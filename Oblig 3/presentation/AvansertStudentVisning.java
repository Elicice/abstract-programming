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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import no.uib.info233.oblig3.datalayer.KarakterDAO;
import no.uib.info233.oblig3.datalayer.StudentDAO;
import no.uib.info233.oblig3.model.Student;

import java.util.Collection;
import java.util.List;

/**
 * Klasse for å vise karakterene til student
 * @author Cecilie Lyshoel
 * @author docs.oracle.com
 * @version Oblig3 v2.0
 */
public class AvansertStudentVisning extends Application {

    private TextField userTextField;
    private Button btn;
    private final TableView table = new TableView();
    private TableView studentTabell;
    private KarakterDAO kdao = new KarakterDAO();
    private Label subWindowTextLabel;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start-metode for å lage vindu der man kan søke opp student basert på studentnummer og få en visning av karakterer for studententen
     * @param primaryStage
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Studentvisning");
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
                    showVindu();
                    setVinduData(s);
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

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        Scene scene = new Scene(grid, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        KarakterDAO dao = KarakterDAO.getInstance();
    }

    //TextField subWindowTextLabel;
    Stage stage;

    /**
     * Metode for data som skal vises i vindu
     * @param student
     */
    public void setVinduData(Student student) {
        subWindowTextLabel.setText("Dette er "+student.getStudentNavn()+" som er student Nr." + student.getStudentId()+"!");
        subWindowTextLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, 25));
        subWindowTextLabel.setTextFill(Color.MEDIUMAQUAMARINE);
    }

    /**
     * Metode for å vise vindu
     */
    public void showVindu() {
        if (stage!=null && stage.isShowing()) return;

        stage = new Stage();
        stage.setTitle("En liten student kom marsjerende bortover mot over edderkoppens fineste spinn...");

        VBox box = new VBox();
        box.setPadding(new Insets(20));

        box.setAlignment(Pos.TOP_CENTER);

        subWindowTextLabel = new Label();

        Label label = new Label("Studentvisning:");
        label.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 30));
        label.setTextFill(Color.ORANGE);

        box.getChildren().add(label);
        box.getChildren().add(subWindowTextLabel);
        Scene scene = new Scene(box, 600, 200);
        stage.setScene(scene);
        stage.show();

    }
}
