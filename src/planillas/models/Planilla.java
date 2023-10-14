/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planillas.models;

/**
 *
 * @author deleo
 */
public class Planilla {
    private int id;
    private int anio;
    private int mes;
    private int empresa_id;

    public Planilla(int id, int anio, int mes, int empresa_id) {
        this.id = id;
        this.anio = anio;
        this.mes = mes;
        this.empresa_id = empresa_id;
    }

    public Planilla() {
    }

    public int getId() {
        return id;
    }

    public int getAnio() {
        return anio;
    }

    public int getMes() {
        return mes;
    }

    public int getEmpresa_id() {
        return empresa_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setEmpresa_id(int empresa_id) {
        this.empresa_id = empresa_id;
    }
    
    
}
