/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionListener;
import vista.frmConversor;
import modelo.modeloConversor;
import java.awt.event.*;


/**
 *
 * @author Alumno
 */
public class controladorConversor implements ActionListener{
    private frmConversor vista;
    private double tempCalculo;
    
    public controladorConversor(frmConversor vista){
        this.vista = vista;
        this.vista.getCbo().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String convertir = (String) vista.getCbo().getSelectedItem();
        double tempIngresada = Double.parseDouble(vista.getTxtTempIngresada().getText());
        modeloConversor m = new modeloConversor(tempIngresada);
        if (convertir.equals("Celcius a Farenheit")) {
            this.tempCalculo = m.toFarenheit(tempIngresada);
            vista.getlblTemp1().setText("Celcius");
            vista.getlblTemp2().setText("Farenheit");
        }
        else if (convertir.equals("Farenheit a Celcius")){
            this.tempCalculo = m.toCelcius(tempIngresada);
            vista.getlblTemp1().setText("Farenheit");
            vista.getlblTemp2().setText("Celcius");
        }
        vista.getTxtTempCalulada().setText(String.valueOf(this.tempCalculo));
    }
}
