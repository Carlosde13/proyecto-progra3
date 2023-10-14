/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planillas.controllers;

import planillas.models.Empresa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import planillas.database.Conexion;

/**
 *
 * @author deleo
 */
public class ConsultasEmpresa {

    public static boolean inscribirEmpresa(String nombre) {
        Connection connection = Conexion.conexion();

        if (connection != null) {
            PreparedStatement statement = null;
            try {
                String query = "INSERT INTO EMPRESA (nombre) VALUES (?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, nombre);
                int rowCount = statement.executeUpdate();

                if (rowCount > 0) {
                    System.out.println("Inserción de empresa exitosa");
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

    public static Empresa getByName(String nombre) {
        Connection connection = Conexion.conexion();
        Empresa empresa = null;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT * FROM EMPRESA WHERE nombre = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, nombre);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int empresaID = resultSet.getInt("id");
                    String nom = resultSet.getString("nombre");

                    empresa = new Empresa();
                    empresa.setId(empresaID);
                    empresa.setNombre(nombre);
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

        return empresa;
    }
    public static Empresa getById(int id) {
        Connection connection = Conexion.conexion();
        Empresa empresa = null;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT * FROM EMPRESA WHERE id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int empresaID = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");

                    empresa = new Empresa();
                    empresa.setId(empresaID);
                    empresa.setNombre(nombre);
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

        return empresa;
    }
    
    public static Empresa getByCodPlanilla(String cod) {
        Connection connection = Conexion.conexion();
        Empresa empresa = null;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT * FROM EMPRESA WHERE cod_planilla = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, cod);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int empresaID = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String cod_planilla = resultSet.getString("cod_planilla");

                    empresa = new Empresa();
                    empresa.setId(empresaID);
                    empresa.setNombre(nombre);
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

        return empresa;
    }
    
    public static boolean setCodigo(String codigo, int id_empresa) {
        Connection connection = Conexion.conexion();

        if (connection != null) {
            PreparedStatement statement = null;
            try {
                String query = "UPDATE EMPRESA SET cod_planilla = ? WHERE id = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, codigo);
                statement.setInt(2, id_empresa);
                
                int rowCount = statement.executeUpdate();

                if (rowCount > 0) {
                    System.out.println("Codigo de planilla para la empresa agregado");
                    return true;
                } else {
                    System.out.println("No se pudo dar un codigo de planilla a la empresa");
                    return false;
                }
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
        }
        return false;
    }
}
