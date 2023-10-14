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
import planillas.models.Planilla;

/**
 *
 * @author deleo
 */
public class PlanillaController {
    
    public static boolean nuevaPlanilla(int anio, int mes, int empresa_id) {
        Connection connection = Conexion.conexion();

        if (connection != null) {
            PreparedStatement statement = null;
            try {
                String query = "INSERT INTO PLANILLA (anio, mes, empresa_id) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(query);
                
                statement.setInt(1, anio);
                statement.setInt(2, mes);
                statement.setInt(3, empresa_id);
                int rowCount = statement.executeUpdate();

                if (rowCount > 0) {
                    System.out.println("Inserción de plantilla exitosa");
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
    
    public static Planilla getPlanilla(int anio, int mes, int empresa_id) {
        Connection connection = Conexion.conexion();
        Planilla planilla = null;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT * FROM PLANILLA WHERE anio = ? AND mes = ? AND empresa_id = ?";
                
                statement = connection.prepareStatement(query);
                
                statement.setInt(1, anio);
                statement.setInt(2, mes);
                statement.setInt(3, empresa_id);
                
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    
                    int id = resultSet.getInt("id");
                    int year = resultSet.getInt("anio");
                    int month = resultSet.getInt("mes");
                    int company_id = resultSet.getInt("empresa_id");
                    
                    

                    planilla = new Planilla(id, year, month, company_id);
                    
                }
            } catch (SQLException e) {
                System.out.println("Error en la 2da consulta: " + e.getMessage());
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

        return planilla;
    }
    public static Planilla getById(int id_planilla) {
        Connection connection = Conexion.conexion();
        Planilla planilla = null;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT * FROM PLANILLA WHERE id = ?";
                
                statement = connection.prepareStatement(query);
                
                statement.setInt(1, id_planilla);
                
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    
                    int id = resultSet.getInt("id");
                    int year = resultSet.getInt("anio");
                    int month = resultSet.getInt("mes");
                    int company_id = resultSet.getInt("empresa_id");
                    
                    

                    planilla = new Planilla(id, year, month, company_id);
                    
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

        return planilla;
    }
}
