package services;

import entities.Objectif;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import utils.MyDatabase;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceObjectif implements IService<Objectif> {

    private static Connection connection;
    private Statement ste;

    public ServiceObjectif(){ connection = MyDatabase.getInstance().getConnection();
    }





    public void add(Objectif o) throws SQLException {
        String sql = "INSERT INTO Objectif (sexe, age, weight, height, activity_level, choix, calorie) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, o.getSexe());
        statement.setInt(2, o.getAge());
        statement.setInt(3, o.getWeight());
        statement.setInt(4, o.getHeight());
        statement.setString(5, o.getActivity_level());
        statement.setString(6, o.getChoix());
        statement.setInt(7, o.getCalorie());
        statement.executeUpdate();
    }

    /*@Override
    public void add(Objectif objectif) {
        String query = "INSERT INTO objectif`( user_id`, sexe, age, height, weight, activity_level, choix, datee, calorie) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, objectif.getUser_id());
            //preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(2, objectif.getSexe());
            preparedStatement.setInt(3, objectif.getAge());
            preparedStatement.setInt(4, objectif.getHeight());
            preparedStatement.setInt(5, objectif.getWeight());
            preparedStatement.setString(6, objectif.getActivity_level());
            preparedStatement.setString(7, objectif.getChoix());
            LocalDate currentDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
            preparedStatement.setDate(8, sqlDate);
            preparedStatement.setInt(9, objectif.getCalorie());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }*/


    @Override
    public void update(Objectif objectif) throws SQLException {
        String sql = "update objectif set  sexe = ?, age = ?, height = ?, weight = ?, activity_level = ?, choix = ?, calorie = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, objectif.getSexe());
        ps.setInt(2, objectif.getAge());
        ps.setInt(3, objectif.getHeight());
        ps.setInt(4, objectif.getWeight());
        ps.setString(5, objectif.getActivity_level());
        ps.setString(6, objectif.getChoix());
        ps.setInt(7, objectif.getCalorie());
        ps.setInt(8, objectif.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from objectif where id = ?";
        PreparedStatement preparedStatement =connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public  List<Objectif> read() throws SQLException {
        String sql = "select * from objectif";  //hadhi requête SQL
        Statement statement = connection.createStatement();  //3malna connextion bel base de donne
        ResultSet rs = statement.executeQuery(sql);  //exécution taa requête sql w nhotouha fi rs (kan bech naamlou ajout wala modif wala sup nhotou executeUpdate fi blaset executeQuery)
        List <Objectif> objectif = new ArrayList<>();
        while (rs.next()){
            Objectif o = new Objectif();
            o.setId(rs.getInt("id"));
            o.setSexe(rs.getString("sexe"));
            o.setAge(rs.getInt("age"));
            o.setHeight(rs.getInt("height"));
            o.setWeight(rs.getInt("weight"));
            o.setActivity_level(rs.getString("activity_level"));
            o.setChoix(rs.getString("choix"));
            o.setCalorie(rs.getInt("calorie"));
            //tw bech nzidou el user fi liste
            objectif.add(o);
        }
        return objectif;
    }

    public void chargerweight(ComboBox<Integer> comboBox) {
        String requete = "SELECT weight FROM objectif";
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(requete);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                comboBox.getItems().add(rs.getInt("weight"));
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Erreur lors du chargement des noms des éco-dépôts : " + e.getMessage());
            alert.showAndWait();
        }
    }
    public static long getIdWeigthByWiegth(int weight) {
        String requete = "SELECT id FROM objectif WHERE weight = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, weight);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getLong("id");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'ID de l'éco-dépôt par nom : " + e.getMessage());
        }
        return -1; // Retourne -1 si aucun éco-dépôt correspondant n'a été trouvé
    }


}
