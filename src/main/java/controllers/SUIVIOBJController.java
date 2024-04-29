package controllers;

import entities.Objectif;
import entities.SuivieObjectif;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.ServiceObjectif;
import services.ServiceSuiviObj;
import utils.MyDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class SUIVIOBJController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker date;

    @FXML
    private TextField nouvPoid;

    @FXML
    private ComboBox<Integer> weightComboBox;
    private List<Objectif> objectifs;

    @FXML
    private Button afficherSuivi;


    @FXML
    void afficherSuivi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CRUDSUIVI.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'ouverture de la fenêtre");
            alert.setContentText("Une erreur s'est produite lors de l'ouverture de la fenêtre. Veuillez réessayer.");
            alert.showAndWait();
        }
    }


    @FXML
    void createSuivieObjectif(ActionEvent event) {


        ServiceSuiviObj serviceSuiviObj = new ServiceSuiviObj();

        if (date.getValue() == null || nouvPoid.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tu ne peux pas faire ça. Assure-toi de remplir la date et le nouveau poids.");
            alert.showAndWait();
            return;
        }
        //  int objId = Integer.parseInt(objectif_id.getText());
        //int idValue = Integer.parseInt(id.getText());
        // int nouvPoidValue = Integer.parseInt(nouvPoid.getText());
        // Date dateValue = Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Create a new SuivieObjectif object

        SuivieObjectif suivieObjectif = new SuivieObjectif();
        suivieObjectif.setDate(java.sql.Date.valueOf(date.getValue()));
        suivieObjectif.setNouvPoid(Integer.parseInt(nouvPoid.getText()));
        int selectWiegth = weightComboBox.getValue();
        long selecteId = ServiceObjectif.getIdWeigthByWiegth(selectWiegth);
        if (selecteId == -1) {
            System.out.println("ID de poids non trouvé pour le poids sélectionné: " + selectWiegth);
            return; // Arrêtez l'exécution si l'ID de poids n'est pas trouvé
        }
        Objectif o = new Objectif();
        o.setId((int) selecteId);
        suivieObjectif.setObjectif(o);

        try {
            serviceSuiviObj.add(suivieObjectif);
            if (suivieObjectif.getNouvPoid() > selectWiegth) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Prise de poids, maintenant pour reste sur le meme poids n augmente pas vos calorie !");
                alert.show();
            } else if (suivieObjectif.getNouvPoid() < selectWiegth) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Perte de poids, bien tu peux dimunier les nombre de calorie pours perte du poids!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Ton poids est inchangé!");
                alert.show();
            }



            initialize();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText(e.getMessage());
            alert.show();
            throw new RuntimeException(e);
        }
    }


    private void loadObjectifs() {
        objectifs = new ArrayList<>();
        Statement stmt = null;
        try {
            Connection conn = MyDatabase.getInstance().getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Objectif");
            while (rs.next()) {
                int id = rs.getInt("id");
                String sexe = rs.getString("sexe");
                int age = rs.getInt("age");
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                String activity_level = rs.getString("activity_level");
                String objectif = rs.getString("objectif");
                int calorie = rs.getInt("calorie");
                objectifs.add(new Objectif(id, sexe, age, height, weight, activity_level, objectif, calorie));
                System.out.println("Objectif added: " + objectif); // Debug print statement
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    public void initialize() {
        ServiceObjectif o = new ServiceObjectif();
        o.chargerweight(weightComboBox);

        // Ajouter une validation pour que l'attribut 'nouvPoid' accepte uniquement des chiffres
        nouvPoid.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                if (change.isContentChange()) {
                    if (!change.getControlNewText().matches("\\d*")) {
                        return null;
                    }
                }
                return change;
            }
        }));
    }}

