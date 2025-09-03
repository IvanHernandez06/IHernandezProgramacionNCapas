package com.digis.IHernandezProgramacionNCapas.ML;

import com.digis.IHernandezProgramacionNCapas.JPA.DireccionJPA;

public class Direccion {

    private int IdDireccion;
    private String Calle;
    private String NumInterior;
    private String NumExterior;

    public Colonia Colonia;
    public Usuarios usuario;

    public Direccion() {
    }

    public Direccion(int IdDireccion, String Calle, String NumInterior, String NumExterior) {
        this.IdDireccion = IdDireccion;
        this.Calle = Calle;
        this.NumInterior = NumInterior;
        this.NumExterior = NumExterior;
    }
    
    
     public Direccion(DireccionJPA direccionJPA) {
        this.IdDireccion = direccionJPA.getIdDireccion();
        this.Calle = direccionJPA.getCalle();
        this.NumInterior = direccionJPA.getNumInterior();
        this.NumExterior = direccionJPA.getNumExterior();
        
        this.Colonia = new Colonia();
        this.Colonia.Municipio = new Municipio();
        this.Colonia.Municipio.Estado = new Estado();
        this.Colonia.Municipio.Estado.Pais = new Pais();
        this.Colonia.Municipio.setIdMunicipio(direccionJPA.Colonia.Municipio.getIdMunicipio());
        this.Colonia.setIdColonia(direccionJPA.Colonia.getIdColonia());
        this.Colonia.Municipio.Estado.setIdEstado(direccionJPA.Colonia.Municipio.Estado.getIdEstado());
        this.Colonia.Municipio.Estado.Pais.setIdPais(direccionJPA.Colonia.Municipio.Estado.Pais.getIdPais());
        this.Colonia.setCodigoPostal(direccionJPA.Colonia.getCodigoPostal());
    }

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

    public void setIdUsuario(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //recibe el menos 1
    public Direccion(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
