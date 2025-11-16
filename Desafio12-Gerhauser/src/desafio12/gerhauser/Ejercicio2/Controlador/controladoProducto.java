/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package desafio12.gerhauser.Ejercicio2.Controlador;

import desafio12.gerhauser.Ejercicio2.DAO.DAOProducto;
import desafio12.gerhauser.Ejercicio2.Modelo.modeloProducto;
import java.util.List;

/**
 *
 * @author Benja
 */
public class controladoProducto {
    DAOProducto DAO = new DAOProducto();
    modeloProducto producto;
    
    public void agregar(String nombre, double precio, int stock, int categoriaId){
        producto = new modeloProducto(nombre,precio,stock,categoriaId);
        int idProd = DAO.agregarProducto(producto);
        producto.setId(idProd);
    }
    public void actualizar(int idProd,String nombre, double precio, int stock, int categoriaId){
        producto = new modeloProducto();
        producto.setNombre(nombre);
        producto.setId(idProd);
        DAO.actualizarProducto(producto);
    }
    public void eliminar(int idProd){
        DAO.eliminarProducto(idProd);
    }
    public List<modeloProducto> listar(){
        return DAO.listarProductos();
    }
    public List<String> listarConCategoria(){
        return DAO.listarProductosConCategoria();
    }
}
