/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package desafio11.gerhauser;

import vista.frmMain;
import controlador.controlador;

/**
 *
 * @author Alumno
 */
public class Desafio11Gerhauser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frmMain vista = new frmMain();
        new controlador(vista);
        vista.setVisible(true);
    
    
    }
    
}
