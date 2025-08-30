package com.digis.IHernandezProgramacionNCapas.ML;

import com.digis.IHernandezProgramacionNCapas.JPA.RolJPA;

public class RolML {

    private int IdRol;
    private String Nombre;

    public RolML() {
    }

    public RolML(RolJPA rolJPA) {
        this.IdRol = rolJPA.getIdRol();
        this.Nombre = rolJPA.getNombre();
    }

    public RolML(int IdRol, String Nombre) {
        this.IdRol = IdRol;
        this.Nombre = Nombre;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

}
