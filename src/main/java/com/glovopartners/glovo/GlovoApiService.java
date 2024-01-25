package com.glovopartners.glovo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.*;

@SpringBootApplication
public class GlovoApiService {
    private static final String API_URL = "https://stageapi.glovoapp.com/webhook/stores/store-01/menu/updates";
    private static final String AUTH_TOKEN = "eb65deba-eae7-450b-b549-b206643c11bc";

    public static void main(String[] args) {
        SpringApplication.run(GlovoApiService.class, args);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://your_database_url", "your_username", "your_password");

            updateGlovoItems(connection);

            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateGlovoItems(Connection connection) throws SQLException, IOException {
        String selectQuery = "SELECT Denumire, Pret FROM Nomenclator n INNER JOIN Preturi p ON n.Cod = p.Cod";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String productName = resultSet.getString("Denumire");
                double price = resultSet.getDouble("Pret");
            }
        }
    }
}

