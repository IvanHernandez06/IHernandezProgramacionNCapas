package com.digis.IHernandezProgramacionNCapas.JPA;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int IdUsuario;
    @Column(name = "username")
    private String Username;
    @Column(name = "nombre")
    private String Nombre;
    @Column(name = "apellidopaterno")
    private String ApellidoPaterno;
    @Column(name = "apellidomaterno")
    private String ApellidoMaterno;
    @Column(name = "email")
    private String Email;
    @Column(name = "password")
    private String Password;
    @Column(name = "fechanacimiento")
    private Date FechaNacimiento;
    @Column(name = "sexo")
    private char Sexo;
    @Column(name = "telefono")
    private String Telefono;
    @Column(name = "celular")
    private String Celular;
    @Column(name = "curp")
    private String Curp;
    @Lob
    @Column(name = "imagen")
    private String Imagen;//Agregar imagen

    @ManyToOne
    @JoinColumn(name = "idrol")
    public Rol RolML;

    @OneToMany(mappedBy = "Usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Direccion> Direccion = new ArrayList<>();;

    public Usuarios() {
    }

    public Usuarios(com.digis.IHernandezProgramacionNCapas.ML.Usuarios usuarioML) {
        this.IdUsuario = usuarioML.getIdUsuario();
        this.Username = usuarioML.getUsername();
        this.Nombre = usuarioML.getNombre();
        this.ApellidoPaterno = usuarioML.getApellidoPaterno();
        this.ApellidoMaterno = usuarioML.getApellidoMaterno();
        this.Email = usuarioML.getEmail();
        this.Password = usuarioML.getPassword();
        this.FechaNacimiento = usuarioML.getFechaNacimiento();
        this.Sexo = usuarioML.getSexo();
        this.Telefono = usuarioML.getTelefono();
        this.Celular = usuarioML.getCelular();
        this.Curp = usuarioML.getCurp();
        this.Imagen = usuarioML.getImagen();

        this.RolML = new Rol();
        this.RolML.setIdRol(usuarioML.RolML.getIdRol());
        this.RolML.setNombre(usuarioML.RolML.getNombre());

        for (com.digis.IHernandezProgramacionNCapas.ML.Direccion Direccione : usuarioML.Direccion) {
            Direccion direccion = new Direccion();
            direccion.setCalle(Direccione.getCalle());
            direccion.setNumInterior(Direccione.getNumInterior());
            direccion.setNumExterior(Direccione.getNumExterior());

            direccion.Colonia = new Colonia();
            direccion.Colonia.setIdColonia(Direccione.Colonia.getIdColonia());

          
            direccion.Usuario = this;

            this.Direccion.add(direccion);
        }

    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public char getSexo() {
        return Sexo;
    }

    public void setSexo(char Sexo) {
        this.Sexo = Sexo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getCurp() {
        return Curp;
    }

    public void setCurp(String Curp) {
        this.Curp = Curp;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public Rol getRolML() {
        return RolML;
    }

    public void setRolML(Rol RolML) {
        this.RolML = RolML;
    }

}
