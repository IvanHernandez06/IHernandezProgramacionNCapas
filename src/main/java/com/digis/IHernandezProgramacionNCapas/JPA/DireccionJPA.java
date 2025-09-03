package com.digis.IHernandezProgramacionNCapas.JPA;

import com.digis.IHernandezProgramacionNCapas.ML.Colonia;
import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Direccion")
public class DireccionJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccion")
    private int IdDireccion;
    @Column(name = "calle")
    private String Calle;
    @Column(name = "numinterior")
    private String NumInterior;
    @Column(name = "numexterior")
    private String NumExterior;
    @ManyToOne
    @JoinColumn(name = "idcolonia")
    public ColoniaJPA Colonia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = false)
    public UsuariosJPA Usuario;

    public DireccionJPA() {
    }

    public DireccionJPA(Usuarios usuarioML) {

//      usuarioML.Direcciones.get(0) -> DireccionJPA ML
        com.digis.IHernandezProgramacionNCapas.ML.Direccion direccionML= usuarioML.Direccion.get(0);

        this.IdDireccion = direccionML.getIdDireccion();
        this.Calle = direccionML.getCalle();
        this.NumInterior = direccionML.getNumInterior();
        this.NumExterior = direccionML.getNumExterior();
        this.Colonia = new ColoniaJPA();
        this.Colonia.setIdColonia(direccionML.Colonia.getIdColonia());
        this.Usuario = new UsuariosJPA();
        this.Usuario.setIdUsuario(usuarioML.getIdUsuario());

    }
    
    
    
    
    
    public DireccionJPA(int IdDireccion, String Calle, String NumeroInterior, String NumeroExterior, ColoniaJPA Colonia) {
        this.IdDireccion = IdDireccion;
        this.Calle = Calle;
        this.NumInterior = NumeroInterior;
        this.NumExterior = NumeroExterior;
        this.Colonia = Colonia;
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

    public ColoniaJPA getColonia() {
        return Colonia;
    }

    public void setColonia(ColoniaJPA Colonia) {
        this.Colonia = Colonia;
    }
}
