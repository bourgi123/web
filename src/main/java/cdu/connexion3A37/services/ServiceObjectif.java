package cdu.connexion3A37.services;

import cdu.connexion3A37.entities.Objectif;
import cdu.connexion3A37.tools.Myconnection;
import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceObjectif implements IService<Objectif> {

    private Connection connection;
    private Statement ste;

    public ServiceObjectif(){
        connection = Myconnection.getInstance().getCnx();
    }
    @Override
    public void add(Objectif objectif) {



        String query = "INSERT INTO `objectif`( `user_id`, `sexe`, `age`, `height`, `weight`, `activity_level`, `choix`, `datee`, `calorie`) VALUES (?,?,?,?,?,?,?,?,?)";
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
    }
    public Objectif getById(int id) {
        String query = "SELECT * from objectif WHERE id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Objectif obj = new Objectif(
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getString(8)
                        );
                obj.setUser_id(resultSet.getInt(1));
                return obj;
            }
            return null;

        } catch (SQLException exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }


    @Override
    public void update(Objectif objectif) {
        String query = "UPDATE objectif SET `sexe`=?, `age`=?, `height`=?, `weight`=?, `activity_level`=?, `choix`=?, `calorie`=? WHERE `id`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, objectif.getSexe());
            preparedStatement.setInt(2, objectif.getAge());
            preparedStatement.setInt(3, objectif.getHeight());
            preparedStatement.setInt(4, objectif.getWeight());
            preparedStatement.setString(5, objectif.getActivity_level());
            preparedStatement.setString(6, objectif.getChoix());
            preparedStatement.setInt(7, objectif.getCalorie());
            preparedStatement.setInt(8, objectif.getUser_id()); // Assuming 'id' corresponds to 'user_id'

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Updated successfully.");
            } else {
                System.out.println("Update failed.");
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from objectif where id = ?";
        PreparedStatement preparedStatement =connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Objectif> read() throws SQLException {
        String sql = "select * from user";  //hadhi requête SQL
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



}
