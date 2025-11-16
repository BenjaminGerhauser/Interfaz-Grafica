/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package desafio12.gerhauser.Ejercicio2.Controlador;

import desafio12.gerhauser.Ejercicio2.DAO.DAOCategoria;
import desafio12.gerhauser.Ejercicio2.Modelo.modeloCategoria;
import java.util.List;

/**
 *
 * @author Benja
 */
public class controladoCategoria {
    DAOCategoria DAO = new DAOCategoria();
    modeloCategoria categoria;
    
    public void agregar(String nombre){
        categoria = new modeloCategoria(nombre);
        int idCategoria = DAO.agregarCategoria(categoria);
        categoria.setId(idCategoria);
    }
    public void actualizar(int idCategoria, String nombre){
        categoria = new modeloCategoria(nombre);
        categoria.setId(idCategoria);
        DAO.actualizarCategoria(categoria);
    }
    public void eliminar(int idCategoria){
        DAO.eliminarCategoria(idCategoria);
    }
    public List<modeloCategoria> listar(){
        return DAO.listarCategorias();
    }
}
