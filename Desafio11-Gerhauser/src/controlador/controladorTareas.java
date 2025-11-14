/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.util.List;
import java.util.ArrayList;
import modelo.modeloTareas;
import vista.vistaTareas;

/**
 *
 * @author Benja
 */
public class controladorTareas {
    
    private vistaTareas vista;
    private modeloTareas modelo;
    private List<modeloTareas> lista;
    private List<String> listaStrings;
    
    public controladorTareas(vistaTareas vista){
        this.vista = vista;
        lista = new ArrayList<>();
        listaStrings = new ArrayList<>();
    }
    
    public void tarea(){
        this.modelo = new modeloTareas(vista.getTxtTarea().getText(), false);
        lista.add(modelo);
    }
    public void completada(boolean completada, int indice){
        modeloTareas tareaActualizar = this.lista.get(indice);
        tareaActualizar.setCompletada(completada);
        System.out.println(lista.size());
    }
    public List<String> obtenerTarea(){
        listaStrings.clear();
        for(modeloTareas modelo : lista){
            String tarea = modelo.getTarea() + ", " + modelo.getCompletada();
            listaStrings.add(tarea);    
        }
        return listaStrings;
    }
}
