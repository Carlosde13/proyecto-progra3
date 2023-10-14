/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planillas.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import planillas.models.Planilla_trabajador;
import planillas.models.Trabajador;
import planillas.models.Estado;
import planillas.models.Planilla;

/**
 *
 * @author deleo
 */
public class ArchivoController {

    private boolean exito;
    private boolean agregarTrabajador;
    private String cuiInexistente = "";

    public String getCuiInexistente() {
        return cuiInexistente;
    }

    public void setCuiInexistente(String cuiInexistente) {
        this.cuiInexistente = cuiInexistente;
    }

    public boolean getAgregarTrabajador() {
        return agregarTrabajador;
    }

    public void setAgregarTrabajador(boolean agregarTrabajador) {
        this.agregarTrabajador = agregarTrabajador;
    }
    private String errorMessage;

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public boolean getExito() {
        return exito;
    }

    public List<Planilla_trabajador> leerArchivo(String rutaArchivo, Planilla planilla, int empresaID) {
        List<Planilla_trabajador> registros = new ArrayList<>();

        List<String> trabajadores_leidos = new ArrayList<>();

        TrabajadorController trabajadorController = new TrabajadorController();
        EstadoController estadoController = new EstadoController();

        VerificacionesController verificacionesController = new VerificacionesController();

        setExito(true);
        setAgregarTrabajador(false);
        setErrorMessage("");
        try {
            // Abre el archivo y prepara el BufferedReader
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                // Divide la línea en campos usando la coma como separador
                String[] campos = linea.split(",");

                if (campos.length == 3) {
                    try {
                        // Parsea los campos a tipos de datos apropiados
                        String persona_cui = (campos[0]);
                        float sueldo = Float.parseFloat(campos[1]);
                        String estado_inicial = (campos[2]);

                        Trabajador trabajador = new Trabajador();
                        trabajador = trabajadorController.getByCUI(persona_cui);

                        int idTrabajador=0;

                        Estado estado = new Estado();
                        estado = estadoController.getByInicial(estado_inicial);
                        int idEstado=0;

                        if (trabajador != null && estado != null) {
                            if(trabajador != null){
                                idTrabajador = trabajador.getId();
                                if (trabajadores_leidos.contains(persona_cui)) {
                                    setExito(false);
                                    setErrorMessage("Error en la linea: " + linea + "\nEl CUI del trabajador aparece mas de una vez en la planilla de este mes");
                                } else {
                                    trabajadores_leidos.add(persona_cui);
                                }
                            }

                            if(estado != null){
                                idEstado = estado.getId();
                                if (idEstado == 2) {
                                    sueldo = (float) 0.00;
                                }
                                int deAlta = verificacionesController.verificarAlta(trabajador.getId());

                                if (idEstado != 1) {
                                    if (deAlta == 0) {
                                        setExito(false);
                                        setErrorMessage("Error en la linea: " + linea + "\nEl Empleado no aparece dado de alta ninguna vez");
                                    }
                                } else if (idEstado == 1) {
                                    int existeAlta = verificacionesController.verificarAltaExistente(trabajador.getId(), empresaID);

                                    if (existeAlta > 0) {
                                        setExito(false);
                                        setErrorMessage("Error en la linea: " + linea + "\nEl Empleado ya ha sido dado de alta anteriormente");
                                    }
                                }
                            }
                        }else{
                            if (trabajador == null) {
                                idTrabajador = 0;
                                setExito(false);
                                setAgregarTrabajador(true);
                                setErrorMessage("Error en la linea: " + linea + "\nNo se existe un trabajador con este CUI");
                                setCuiInexistente(persona_cui);
                            }
                            if (estado == null) {
                                idEstado = 0;
                                setExito(false);
                                setErrorMessage("Error en la linea: " + linea + "\nNo se existe el estado");
                            }
                        }

                        Planilla_trabajador registro = new Planilla_trabajador(planilla.getId(), idTrabajador, sueldo, idEstado);
                        registros.add(registro);
                        System.out.println("Se leyo el registro");
                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear valores en la línea: " + linea);
                        setExito(false);
                        setErrorMessage("Error al convertir valores en la línea: " + linea + "\nVerifique el archivo e intente de nuevo");
                    }
                } else {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    setExito(false);
                    setErrorMessage("Línea con formato incorrecto: " + linea);
                }
            }

            // Cierra el BufferedReader
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return registros;
    }
}
