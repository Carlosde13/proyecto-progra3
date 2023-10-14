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
import planillas.models.Trabajador;

/**
 *
 * @author deleo
 */
public class TrabajadorController {
    
    public static Trabajador getById(int id) {
        Connection connection = Conexion.conexion();
        Trabajador trabajador = null;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT * FROM TRABAJADOR WHERE id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int trabajadorID = resultSet.getInt("id");
                    String persona_cui = resultSet.getString("persona_cui");

                    trabajador = new Trabajador();
                    trabajador.setId(trabajadorID);
                    trabajador.setCui(persona_cui);
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

        return trabajador;
    }
    
    public static Trabajador getByCUI(String cui) {
        Connection connection = Conexion.conexion();
        Trabajador trabajador = null;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT * FROM TRABAJADOR WHERE persona_cui = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, cui);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int trabajadorID = resultSet.getInt("id");
                    String persona_cui = resultSet.getString("persona_cui");

                    trabajador = new Trabajador();
                    trabajador.setId(trabajadorID);
                    trabajador.setCui(persona_cui);
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

        return trabajador;
    }
    
    public static boolean nuevaPersona(String cui, String nombre) {
        Connection connection = Conexion.conexion();

        if (connection != null) {
            PreparedStatement statement = null;
            try {
                String query = "INSERT INTO PERSONA (cui, nombre) VALUES (?, ?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, cui);
                statement.setString(2, nombre);
                int rowCount = statement.executeUpdate();

                if (rowCount > 0) {
                    System.out.println("Inserción de persona exitosa");
                } else {
                    System.out.println("No se insertaron registros");
                }
                return true;
            } catch (SQLException e) {
                System.out.println("Error en la consulta: " + e.getMessage());
                return false;
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        } else {
            return false;
        }

    }
    
    public static boolean nuevoTrabajador(String cui) {
        Connection connection = Conexion.conexion();

        if (connection != null) {
            PreparedStatement statement = null;
            try {
                String query = "INSERT INTO TRABAJADOR (persona_cui) VALUES (?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, cui);
                int rowCount = statement.executeUpdate();

                if (rowCount > 0) {
                    System.out.println("Inserción de trabajador exitosa");
                } else {
                    System.out.println("No se insertaron registros");
                }
                return true;
            } catch (SQLException e) {
                System.out.println("Error en la consulta: " + e.getMessage());
                return false;
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        } else {
            return false;
        }

    }
}
