package ma.ac.ensa;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class OrderProcessor {
    private static final String DB_URL = "jdbc:sqlite:data/home/ezzahra/IdeaProjects/DL_Threads/data/database.db"; // Remplacez par le chemin de votre base de données
    private static final String INPUT_FILE = "data/input.json";
    private static final String OUTPUT_FILE = "data/output.json";
    private static final String ERROR_FILE = "data/error.json";
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found.");
        }
    }


    public static void main(String[] args) {
        createDatabase();
        List<Order> orders = readOrdersFromFile(INPUT_FILE);
        processOrders(orders);
    }

    private static void createDatabase() {
        String createCustomerTable = "CREATE TABLE IF NOT EXISTS customer (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " nom TEXT NOT NULL,\n"
                + " email TEXT NOT NULL,\n"
                + " phone TEXT\n"
                + ");";

        String createOrderTable = "CREATE TABLE IF NOT EXISTS orders (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " date TEXT NOT NULL,\n"
                + " amount REAL,\n"
                + " customer_id INTEGER,\n"
                + " FOREIGN KEY (customer_id) REFERENCES customer (id)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createCustomerTable);
            stmt.execute(createOrderTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Order> readOrdersFromFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Order> orders = null;

        try {
            orders = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Order.class));
        } catch (IOException e) {
            System.out.println("Error reading orders from file: " + e.getMessage());
        }

        return orders;
    }

    private static void processOrders(List<Order> orders) {
        if (orders == null) {
            System.out.println("No orders to process.");
            return;
        }

        for (Order order : orders) {
            try {
                if (customerExists(order.getCustomerId())) {
                    insertOrder(order);
                } else {
                    System.out.println("Customer not found for order ID: " + order.getId());
                    // Enregistrez l'erreur dans le fichier d'erreurs si nécessaire
                }
            } catch (SQLException e) {
                System.out.println("Error processing order ID " + order.getId() + ": " + e.getMessage());
                // Enregistrez l'erreur dans le fichier d'erreurs si nécessaire
            }
        }

        // Écrire les résultats dans le fichier de sortie
        writeOrdersToFile(orders, OUTPUT_FILE);
    }

    private static boolean customerExists(int customerId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customer WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt(1) > 0; // Retourne vrai si le client existe
        }
    }

    private static void insertOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (id, date, amount, customer_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getId());
            pstmt.setString(2, order.getDate());
            pstmt.setDouble(3, order.getAmount());
            pstmt.setInt(4, order.getCustomerId());
            pstmt.executeUpdate();
            System.out.println("Order inserted: " + order.getId());
        }
    }

    private static void writeOrdersToFile(List<Order> orders, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Optionnel, pour formater les dates

        try {
            objectMapper.writeValue(new File(filePath), orders);
            System.out.println("Orders written to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing orders to file: " + e.getMessage());
        }
    }
}