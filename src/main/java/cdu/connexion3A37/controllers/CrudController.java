package cdu.connexion3A37.controllers;

import cdu.connexion3A37.entities.Objectif;
import cdu.connexion3A37.services.ServiceObjectif;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class CrudController {

    @FXML
    private TableColumn<?, ?> ColActivity_level;

    @FXML
    private TableColumn<?, ?> ColAge;

    @FXML
    private TableColumn<?, ?> ColCalorie;

    @FXML
    private TableColumn<?, ?> ColHeight;

    @FXML
    private TableColumn<?, ?> ColID;

    @FXML
    private TableColumn<?, ?> ColObjectif;

    @FXML
    private TableColumn<?, ?> ColSexe;

    @FXML
    private TableColumn<?, ?> ColWeight;

    @FXML
    private Button btnAjout;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;


    @FXML
    private TextField tActivity_level;

    @FXML
    private TextField tAge;

    @FXML
    private TextField tCalorie;

    @FXML
    private TextField tHeight;

    @FXML
    private TextField tID;

    @FXML
    private TextField tObjectif;

    @FXML
    private TextField tSexe;

    @FXML
    private TextField tWeight;

    @FXML
    private TableView<Objectif> tableview;

    ServiceObjectif os = new ServiceObjectif();

    @FXML
    void Ajouter(ActionEvent event) {

        Objectif o = new Objectif(Integer.parseInt(tAge.getText()),tSexe.getText(),Integer.parseInt(tHeight.getText()),Integer.parseInt(tWeight.getText()),tActivity_level.getText(),Integer.parseInt(tCalorie.getText()),tObjectif.getText());
        try {
            os.add(o);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("User insérée avec succés!");
            alert.show();

            initialize();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText(e.getMessage());
            alert.show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Modifier(ActionEvent event) {

        Objectif o = new Objectif(Integer.parseInt(tID.getText()),Integer.parseInt(tAge.getText()),tSexe.getText(),Integer.parseInt(tHeight.getText()),Integer.parseInt(tWeight.getText()),tActivity_level.getText(),Integer.parseInt(tCalorie.getText()),tObjectif.getText());
        try {
            os.update(o);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("User modifier avec succés!");
            alert.show();

            initialize();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText(e.getMessage());
            alert.show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Supprimer(ActionEvent event) {
        int id1 = Integer.parseInt(tID.getText());
        try {
            os.delete(id1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("User supprimer avec succés!");
            alert.show();

            initialize();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText(e.getMessage());
            alert.show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() throws SQLException {
        try {
            ObservableList<Objectif> observableliste = FXCollections.observableList(os.read());
            tableview.setItems(observableliste);
            // Remplir les colonnes avec les propriétés des objets user
            ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
            ColSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            ColAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            ColHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
            ColWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
            ColActivity_level.setCellValueFactory(new PropertyValueFactory<>("activity_level"));
            ColObjectif.setCellValueFactory(new PropertyValueFactory<>("choix"));
            ColCalorie.setCellValueFactory(new PropertyValueFactory<>("calorie"));

            // Configurer la gestion des événements de clic sur la TableView
            tableview();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tableview() {
        tableview.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Objectif selectedObj = tableview.getSelectionModel().getSelectedItem();
                if (selectedObj != null) {
                    tID.setText(String.valueOf(selectedObj.getId()));
                    tSexe.setText(selectedObj.getSexe());
                    tAge.setText(String.valueOf(selectedObj.getAge()));
                    tHeight.setText(String.valueOf(selectedObj.getHeight()));
                    tWeight.setText(String.valueOf(selectedObj.getWeight()));
                    tActivity_level.setText(String.valueOf(selectedObj.getActivity_level()));
                    tObjectif.setText(selectedObj.getChoix());
                    tCalorie.setText(String.valueOf(selectedObj.getCalorie()));
                }
            }
        });
    }

}
