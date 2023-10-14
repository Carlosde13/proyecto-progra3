/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planillas.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import planillas.database.Conexion;

/**
 *
 * @author deleo
 */
public class ReportesController {

    public static final float SUELDO_MINIMO = (float) 2900.00;

    public static List<String[]> getBySueldo(String tipo) {
        List<String[]> resultados = new ArrayList<>();
        Connection connection = Conexion.conexion();

        String parte_consulta = "";
        String condicion = "";
        if (tipo == "3mil") {
            condicion = "sueldo > 3000";
        } else if (tipo == "susp") {
            condicion = "pt.estado_id = 2";
        }
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT t.id AS trabajador_id, p.cui AS persona_cui, p.nombre AS nombre_persona, pt.sueldo, e.nombre AS nombre_empresa\n"
                        + "FROM trabajador t\n"
                        + "JOIN persona p ON t.persona_cui = p.cui\n"
                        + "JOIN planilla_trabajador pt ON t.id = pt.trabajador_id\n"
                        + "JOIN (\n"
                        + "        SELECT pl2.id AS planilla_id, pl2.anio, pl2.mes, pl2.empresa_id\n"
                        + "        FROM planilla pl2\n"
                        + "        WHERE pl2.id = (\n"
                        + "            SELECT MAX(pl3.id)\n"
                        + "            FROM planilla pl3\n"
                        + "            WHERE pl3.empresa_id = pl2.empresa_id\n"
                        + "        )\n"
                        + "    ) pl ON pt.planilla_id = pl.planilla_id\n"
                        + "JOIN empresa e ON pl.empresa_id = e.id\n"
                        + "WHERE " + condicion;
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int trabajador_id = resultSet.getInt("trabajador_id");
                    String persona_cui = resultSet.getString("persona_cui");
                    String nombre_persona = resultSet.getString("nombre_persona");
                    float sueldo = resultSet.getFloat("sueldo");
                    String nombre_empresa = resultSet.getString("nombre_empresa");

                    String[] fila = {String.valueOf(trabajador_id), persona_cui, nombre_persona, String.valueOf(sueldo), nombre_empresa};
                    resultados.add(fila);
                }

                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error en la consulta: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }

        return resultados;
    }

    public static List<String[]> getMinimo() {
        List<String[]> resultados = new ArrayList<>();
        Connection connection = Conexion.conexion();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT t.id AS trabajador_id, p.cui AS persona_cui, p.nombre AS nombre_persona, es.descripcion as estado, e.nombre AS nombre_empresa\n"
                        + "FROM trabajador t\n"
                        + "JOIN persona p ON t.persona_cui = p.cui\n"
                        + "JOIN planilla_trabajador pt ON t.id = pt.trabajador_id\n"
                        + "JOIN estado es ON pt.estado_id = es.id\n"
                        + "JOIN(\n"
                        + "        SELECT pl2.id AS planilla_id, pl2.anio, pl2.mes, pl2.empresa_id\n"
                        + "        FROM planilla pl2\n"
                        + "        WHERE pl2.id = (\n"
                        + "            SELECT MAX(pl3.id)\n"
                        + "            FROM planilla pl3\n"
                        + "            WHERE pl3.empresa_id = pl2.empresa_id\n"
                        + "        )\n"
                        + "    ) pl ON pt.planilla_id = pl.planilla_id\n"
                        + "JOIN empresa e ON pl.empresa_id = e.id \n"
                        + "WHERE pt.sueldo = 2900";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int trabajador_id = resultSet.getInt("trabajador_id");
                    String persona_cui = resultSet.getString("persona_cui");
                    String nombre_persona = resultSet.getString("nombre_persona");
                    String estado = resultSet.getString("estado");
                    String nombre_empresa = resultSet.getString("nombre_empresa");

                    String[] fila = {String.valueOf(trabajador_id), persona_cui, nombre_persona, estado, nombre_empresa};
                    resultados.add(fila);
                }

                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error en la consulta: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }

        return resultados;
    }
}
