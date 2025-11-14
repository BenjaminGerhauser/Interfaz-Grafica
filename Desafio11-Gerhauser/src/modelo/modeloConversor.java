/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Alumno
 */
public class modeloConversor {
    private double tempIngresada;
    
    public modeloConversor(double tempÌngresada){
        this.tempIngresada = tempÌngresada;
    }
    
    public double getTemp(){
        return this.tempIngresada;
    }
    
    public double toCelcius(double temp){
        double tempCalculada = ((temp - 32) * 0.56);
        return tempCalculada;
    }
    
    public double toFarenheit(double temp){
        double tempCalculada = ((temp * 1.8) + 32);
        return tempCalculada;
    }
}
