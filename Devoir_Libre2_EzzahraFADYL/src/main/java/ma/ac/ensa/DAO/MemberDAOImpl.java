package ma.ac.ensa.DAO;
import ma.ac.ensa.Models.Incident;
import ma.ac.ensa.Models.Membre;
import ma.ac.ensa.util.DBConnexion;
import java.sql.SQLException;

import java.sql.*;
import java.util.List;
public class MembeDAOImpl {

    public <SQlException extends Throwable> void insere(Membre m) throws SQLException {
        String query="INSERT INTO Membre (identifaint, nom, prenom, email, phone VALUES(2,Ba3loka,Barhoucha,ba3louka@gmail.com,0513457689";
        Connection conn;
        try (DBConnexion conn = DBConnexion.getConnection()) {
            try (preparedStatement stmt = conn.prepareStatement(query)) {
                stmt.executeUpdate();
            }
        } catch (SQlException e) {
            e.printStackTrace();
        }
        
    }
    public  List<Incident> chargerListIncidents(){
        return List.of();
    }

}
