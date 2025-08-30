
package com.digis.IHernandezProgramacionNCapas.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MunicipioJPA {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmunicipio")
    private int IdMunicipio;
    @Column(name="nombre")
    private String Nombre;
    @ManyToOne
    @JoinColumn(name = "idestado")
    public EstadoJPA  Estado;

    public MunicipioJPA() {
    }
    
    

    public MunicipioJPA(int IdMunicipio, String Nombre) {
        this.IdMunicipio = IdMunicipio;
        this.Nombre = Nombre;
    }

    public int getIdMunicipio() {
        return IdMunicipio;
    }

    public void setIdMunicipio(int IdMunicipio) {
        this.IdMunicipio = IdMunicipio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public EstadoJPA getEstado() {
        return Estado;
    }

    public void setEstado(EstadoJPA Estado) {
        this.Estado = Estado;
    }
    
    
}
