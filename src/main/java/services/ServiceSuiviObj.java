package services;


import entities.SuivieObjectif;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceSuiviObj implements IService<SuivieObjectif>{

    private Connection connection;
    private Statement ste;

    public ServiceSuiviObj(){ connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void add(SuivieObjectif suivieObjectif) throws SQLException {
        String sql = "insert into suivi_objectif (objectif_id, date_suivi, nouveau_poids) values (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, suivieObjectif.getObjectif().getId());
            ps.setDate(2, new java.sql.Date(suivieObjectif.getDate().getTime()));
            ps.setInt(3, suivieObjectif.getNouvPoid());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                suivieObjectif.setId(rs.getInt(1));
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void update(SuivieObjectif suivieObjectif) throws SQLException {
        String sql = "update suivi_objectif set objectif_id = ?, date_suivi = ?, nouveau_poids = ? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, suivieObjectif.getObj_id());
            ps.setDate(2, new java.sql.Date(suivieObjectif.getDate().getTime()));
            ps.setInt(3, suivieObjectif.getNouvPoid());
            ps.setInt(4, suivieObjectif.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from suivi_objectif where id = ?";
        PreparedStatement preparedStatement =connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<SuivieObjectif> read() throws SQLException {
        String sql = "select * from suivi_objectif";  //hadhi requête SQL
        Statement statement = connection.createStatement();  //3malna connextion bel base de donne
        ResultSet rs = statement.executeQuery(sql);  //exécution taa requête sql w nhotouha fi rs (kan bech naamlou ajout wala modif wala sup nhotou executeUpdate fi blaset executeQuery)
        List <SuivieObjectif> suivie_objectif = new ArrayList<>();
        while (rs.next()){
            SuivieObjectif o = new SuivieObjectif();
            o.setId(rs.getInt("id"));
            o.setObj_id(rs.getInt("objectif_id"));
            o.setDate(rs.getDate("date_suivi"));
            o.setNouvPoid(rs.getInt("nouveau_poids"));
            suivie_objectif.add(o);
        }
        return suivie_objectif;
    }
}
