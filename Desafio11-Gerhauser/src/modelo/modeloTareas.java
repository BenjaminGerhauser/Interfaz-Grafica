/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Benja
 */
public class modeloTareas {
    private String tarea;
    private boolean completada;
    
    public modeloTareas(String tarea, boolean completada){
        this.tarea = tarea;
        this.completada = completada;
    }
    public String getTarea(){
        return this.tarea;
    }
    public void setCompletada(boolean completada){
        this.completada = completada;
    }
    public String getCompletada(){
        if (this.completada) {
            return "Tarea Completada";
        }
        else{
            return "Tarea sin completar";
        }
    }

}
