/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package desafio12.gerhauser.Ejercicio2.Modelo;

/**
 *
 * @author Benja
 */
public class modeloCategoria {
    private int id;
    private String nombre;

    public modeloCategoria() {}

    public modeloCategoria(String nombre) {
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
       @Override
    public String toString() {
        return nombre;  
    }
}
