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
public class VerificacionesController {

    public static int verificarAlta(int trabajador_id) {
        Connection connection = Conexion.conexion();
        int registros = -1;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT COUNT(*) AS no_registros\n"
                        + "FROM planilla_trabajador pt\n"
                        + "JOIN planilla pl ON pt.planilla_id = pl.id\n"
                        + "JOIN empresa em ON pl.empresa_id = em.id\n"
                        + "WHERE pt.trabajador_id = ? AND pt.estado_id = 1";

                statement = connection.prepareStatement(query);

                statement.setInt(1, trabajador_id);

                resultSet = statement.executeQuery();

                if (resultSet.next()) {

                    registros = resultSet.getInt("no_registros");
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

        return registros;
    }

    public static int verificarAltaExistente(int trabajador_id, int empresaID) {
        Connection connection = Conexion.conexion();
        int registros = -1;

        if (connection != null) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT COUNT(*) as no_registros\n"
                        + "FROM planilla_trabajador pt_actual\n"
                        + "JOIN planilla p ON pt_actual.planilla_id = p.id\n"
                        + "JOIN empresa emp ON p.empresa_id = emp.id\n"
                        + "WHERE pt_actual.trabajador_id = ? AND  emp.id = ?\n"
                        + "AND pt_actual.estado_id = 1\n"
                        + "AND NOT EXISTS (\n"
                        + "    SELECT 1\n"
                        + "    FROM planilla_trabajador pt_anterior\n"
                        + "    JOIN planilla p_anterior ON pt_anterior.planilla_id = p_anterior.id\n"
                        + "    WHERE pt_anterior.trabajador_id = ?\n"
                        + "    AND pt_anterior.estado_id = 1\n"
                        + "    AND (p.anio > p_anterior.anio OR (p.anio = p_anterior.anio AND p.mes > p_anterior.mes))\n"
                        + ")";

                statement = connection.prepareStatement(query);

                statement.setInt(1, trabajador_id);
                statement.setInt(2, empresaID);
                statement.setInt(3, trabajador_id);

                resultSet = statement.executeQuery();

                if (resultSet.next()) {

                    registros = resultSet.getInt("no_registros");
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

        return registros;
    }
}
