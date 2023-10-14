/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planillas.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import planillas.database.Conexion;
import planillas.models.Estado;

/**
 *
 * @author deleo
 */
public class EstadoController {
    
    public static Estado getById(int id) {
        Connection connection = Conexion.conexion();
        Estado estado = null;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT * FROM ESTADO WHERE id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int estadoID = resultSet.getInt("id");
                    String descripcion = resultSet.getString("descripcion");
                    String inicial = resultSet.getString("inicial");

                    estado = new Estado();
                    estado.setId(estadoID);
                    estado.setDescripcion(descripcion);
                    estado.setInicial(inicial);
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta: " + e.getMessage());
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }

        return estado;
    }
    public static Estado getByInicial(String inicial) {
        Connection connection = Conexion.conexion();
        Estado estado = null;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT * FROM ESTADO WHERE inicial = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, inicial);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int estadoID = resultSet.getInt("id");
                    String descripcion = resultSet.getString("descripcion");
                    String inic = resultSet.getString("inicial");

                    estado = new Estado();
                    estado.setId(estadoID);
                    estado.setDescripcion(descripcion);
                    estado.setInicial(inic);
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta: " + e.getMessage());
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }

        return estado;
    }
}
