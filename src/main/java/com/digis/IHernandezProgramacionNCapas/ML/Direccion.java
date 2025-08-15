
package com.digis.IHernandezProgramacionNCapas.ML;

public class Direccion {
    private int IdDireccion;
    private String Calle;
    private String NumInterior;
    private String NumExterior;
    
    public Colonia Colonia;
    public UsuariosML usuario;

    public int getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public String getNumInterior() {
        return NumInterior;
    }

    public void setNumInterior(String NumInterior) {
        this.NumInterior = NumInterior;
    }

    public String getNumExterior() {
        return NumExterior;
    }

    public void setNumExterior(String NumExterior) {
        this.NumExterior = NumExterior;
    }

    public Colonia getColonia() {
        return Colonia;
    }

    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }
    
    
   
}
