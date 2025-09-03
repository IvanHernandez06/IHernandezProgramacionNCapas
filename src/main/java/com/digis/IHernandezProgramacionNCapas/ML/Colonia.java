
package com.digis.IHernandezProgramacionNCapas.ML;

import com.digis.IHernandezProgramacionNCapas.JPA.ColoniaJPA;

public class Colonia {
    private int IdColonia;
    private String Nombre;
    private String CodigoPostal;
    
    public Municipio Municipio;

  
    public Colonia() {
    }

    public Colonia(int IdColonia, String Nombre, String CodigoPostal) {
        this.IdColonia = IdColonia;
        this.Nombre = Nombre;
        this.CodigoPostal = CodigoPostal;
    }
    
    
    
    public Colonia(ColoniaJPA colonia) {
       this.IdColonia = colonia.getIdColonia();
        this.CodigoPostal = colonia.getCodigoPostal();
        this.Nombre = colonia.getNombre();
    }
//
//  public Colonia(ColoniaJPA coloniaJPA) {
//        this.IdColonia = coloniaJPA.getIdColonia();
//        this.Nombre = coloniaJPA.getNombre();
//        this.CodigoPostal = coloniaJPA.getCodigoPostal();
//        
//        this.Municipio = new Municipio();
//        this.Municipio.setIdMunicipio(coloniaJPA.Municipio.getIdMunicipio());
//    }
//   
    
    
    public int getIdColonia() {
        return IdColonia;
    }

    public void setIdColonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    public Municipio getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(Municipio Municipio) {
        this.Municipio = Municipio;
    }
    
    
    
  
}
