/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planillas.database;

/**
 *
 * @author deleo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author deleo
 */
public class Conexion {
     public static Connection conexion() {
        Connection connection = null;

        try {
            // Cambia estos valores por los adecuados para tu instalación
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "C##PLANILLA";
            String password = "admin";

            // Cargar el controlador JDBC
            Class.forName("oracle.jdbc.OracleDriver");

            // Establecer la conexión
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }

        return connection;
    }
}