/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planillas.models;

/**
 *
 * @author deleo
 */
public class Estado {
    private int id;
    private String descripcion;
    private String inicial;

    public Estado(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Estado() {
    }

    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    public String getInicial() {
        return inicial;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
