/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package desafio12.gerhauser.Ejercicio4.Controlador;

import desafio12.gerhauser.Ejercicio4.DAO.DAOusuario;
import desafio12.gerhauser.Ejercicio4.Modelo.modeloUsuario;
import java.util.List;

/**
 *
 * @author Benja
 */
public class controladorValidacion {
    DAOusuario DAO = new DAOusuario();
    modeloUsuario modelo;
    
    public int agregar(String username, String password, String email){
        int idUser = DAO.registrarUsuario(username, password, email);
        return idUser;
    }
    public boolean validarLogin(String username, String pass){
        modelo = DAO.validarLogin(username, pass);
        return !modelo.getUsername().equals("");
    }
    public void actualizarAcceso(int id){
        DAO.actualizarUltimoAcceso(id);
    }
    public boolean cambiarPassword(int id, String newPassword){
        return DAO.cambiarPassword(id, newPassword);
    }
    public List<modeloUsuario>listar(){
        return DAO.listarUsuarios();
    }
    public int buscarUser(String username){
        modelo = DAO.obtenerPorUsuario(username);
        return modelo.getId();
    }
}
