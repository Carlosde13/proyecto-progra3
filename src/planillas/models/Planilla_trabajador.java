/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planillas.models;

/**
 *
 * @author deleo
 */
public class Planilla_trabajador {
    private int id;
    private int planilla_id;
    private int trabajador_id;
    private float sueldo;
    private int estado_id;

    public Planilla_trabajador(int id, int planilla_id, int trabajador_id, float sueldo, int estado_id) {
        this.id = id;
        this.planilla_id = planilla_id;
        this.trabajador_id = trabajador_id;
        this.sueldo = sueldo;
        this.estado_id = estado_id;
    }
    
    public Planilla_trabajador(int planilla_id, int trabajador_id, float sueldo, int estado_id) {
        this.planilla_id = planilla_id;
        this.trabajador_id = trabajador_id;
        this.sueldo = sueldo;
        this.estado_id = estado_id;
    }

    public Planilla_trabajador() {
    }

    public int getId() {
        return id;
    }

    public int getPlanilla_id() {
        return planilla_id;
    }

    public int getTrabajador_id() {
        return trabajador_id;
    }

    public float getSueldo() {
        return sueldo;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlanilla_id(int planilla_id) {
        this.planilla_id = planilla_id;
    }

    public void setTrabajador_id(int trabajador_id) {
        this.trabajador_id = trabajador_id;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }
}