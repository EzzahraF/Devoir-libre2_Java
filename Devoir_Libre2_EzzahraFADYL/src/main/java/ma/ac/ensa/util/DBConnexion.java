package ma.ac.ensa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnexion {

    // Utilisez l'URL de connexion PostgreSQL de votre projet Supabase
    private static final String URL = "jdbc:postgresql://https://kdwasbqdgiybanphghsi.supabase.co:5432/OH!Vet";
    private static final String USER = "OH!Vet";
    private static final String PASSWORD = "ofshor@1620PO";

    public static Connection getConnection() throws SQLException {
        try {
            // Chargez le driver PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}