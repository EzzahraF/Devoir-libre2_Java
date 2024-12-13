package ma.ac.ensa.DAO;

import ma.ac.ensa.Models.Incident;
import ma.ac.ensa.util.DBConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

public class IncidentDAOImpl {

    public void  inserer(Incident i){
        String query ="INSERT INTO Incident(reference,time,status) VALUES(?,?,?)";
        try(Connection connexion= DBConnexion.getConnection(); PreparedStatement stmt= connexion.prepareStatement(query)) {
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
public ArrayList<Object> inser(Set is){
        ArrayList<Object> Incident= new ArrayList<>();
        String query = "SELECT * FROM Incident ";
        try (Connection connexion= DBConnexion.getConnection(); PreparedStatement stmt= connexion.prepareStatement(query); ResultSet rs= stmt.executeQuery(query)){
        while (rs.next()){
           Incident ((ArrayList<Object>) Incident).add(new Incident),rs.getInt("reference")));
        }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return Incident;
}

    private Calendar Incident(ArrayList<Object> incident) {
    }
}
