/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planillas.models;

/**
 *
 * @author deleo
 */
public class Empresa {
    private int id;
    private String nombre;
    private String cod_planilla;

    public Empresa(int id, String nombre, String cod_planilla) {
        this.id = id;
        this.nombre = nombre;
        this.cod_planilla = cod_planilla;
    }

    public void setCod_planilla(String cod_planilla) {
        this.cod_planilla = cod_planilla;
    }

    public String getCod_planilla() {
        return cod_planilla;
    }

    public Empresa() {
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
