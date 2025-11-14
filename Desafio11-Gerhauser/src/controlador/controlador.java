/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionListener;
import vista.frmMain;
import modelo.modelo;
import java.awt.event.*;


/**
 *
 * @author Alumno
 */
public class controlador implements ActionListener{
    private frmMain vista;
    private double tempCalculo;
    
    public controlador(frmMain vista){
        this.vista = vista;
        this.vista.getCbo().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String convertir = (String) vista.getCbo().getSelectedItem();
        double tempIngresada = Double.parseDouble(vista.getTxtTempIngresada().getText());
        modelo m = new modelo(tempIngresada);
        if (convertir.equals("Celcius a Farenheit")) {
            this.tempCalculo = m.toFarenheit(tempIngresada);
        }
        else if (convertir.equals("Farenheit a Celcius")){
            this.tempCalculo = m.toCelcius(tempIngresada);
        }
        vista.getTxtTempCalulada().setText(String.valueOf(this.tempCalculo));
    }
}
