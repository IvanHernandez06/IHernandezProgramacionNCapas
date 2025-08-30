package com.digis.IHernandezProgramacionNCapas.ML;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public class Usuarios {

    


    private int IdUsuario;
    private String Username;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Email;
    private String Password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date FechaNacimiento;
    private char Sexo;
    private String Telefono;
    private String Celular;
    private String Curp;

    private String Imagen;//Agregar imagen

    public RolML RolML;
    public List<Direccion> Direccion;
    
    public static Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    public Usuarios() {
    }
    
     public Usuarios(com.digis.IHernandezProgramacionNCapas.JPA.UsuariosJPA usuarioJPA) {
        this.IdUsuario = usuarioJPA.getIdUsuario();
        this.Username = usuarioJPA.getUsername();
        this.Nombre = usuarioJPA.getNombre();
        this.ApellidoPaterno = usuarioJPA.getApellidoPaterno();
        this.ApellidoMaterno = usuarioJPA.getApellidoMaterno();
        this.Email = usuarioJPA.getEmail();
        this.Password = usuarioJPA.getPassword();
        this.FechaNacimiento = usuarioJPA.getFechaNacimiento();
        this.Sexo = usuarioJPA.getSexo();
        this.Telefono = usuarioJPA.getTelefono();
        this.Celular = usuarioJPA.getCelular();
        this.Curp = usuarioJPA.getCurp();
        this.Imagen = usuarioJPA.getImagen();

        this.RolML = new RolML();
        this.RolML.setIdRol(usuarioJPA.RolML.getIdRol());
        this.RolML.setNombre(usuarioJPA.RolML.getNombre());
        
        if (usuarioJPA.Direccion != null && usuarioJPA.Direccion.size() > 0){ // para saber si tiene direcciones
            this.Direccion = new ArrayList<>();
        
         for (com.digis.IHernandezProgramacionNCapas.JPA.DireccionJPA Direccione : usuarioJPA.Direccion) {
                Direccion direccion = new Direccion();
                direccion.setIdDireccion(Direccione.getIdDireccion());
                direccion.setCalle(Direccione.getCalle());
                direccion.setNumInterior(Direccione.getNumInterior());
                direccion.setNumExterior(Direccione.getNumExterior());
                
                
                direccion.Colonia = new Colonia();
                direccion.Colonia.setNombre(Direccione.Colonia.getNombre());
                direccion.Colonia.setCodigoPostal(Direccione.Colonia.getCodigoPostal());
                
                direccion.Colonia.Municipio = new Municipio();
                direccion.Colonia.Municipio.setNombre(Direccione.Colonia.Municipio.getNombre());
                
                direccion.Colonia.Municipio.Estado = new Estado();
                direccion.Colonia.Municipio.Estado.setNombre(Direccione.Colonia.Municipio.Estado.getNombre());

                direccion.Colonia.Municipio.Estado.Pais = new Pais();
                direccion.Colonia.Municipio.Estado.Pais.setNombre(Direccione.Colonia.Municipio.Estado.Pais.getNombre());
                
                this.Direccion.add(direccion);}
        }
        
       
    }
    
    public Usuarios(int IdUsuario, String Username, String Nombre, String ApellidoPaterno, String ApellidoMaterno, String Email, String Password, Date FechaNacimiento, char Sexo, String Telefono, String Celular, String Curp) {
        this.IdUsuario = IdUsuario;
        this.Username = Username;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.Email = Email;
        this.Password = Password;
        this.FechaNacimiento = FechaNacimiento;
        this.Sexo = Sexo;
        this.Telefono = Telefono;
        this.Celular = Celular;
        this.Curp = Curp;
    }

    public Usuarios(String Nombre, String ApellidoPaterno, String ApellidoMaterno) {

        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;

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

    public RolML getRolML() {
        return RolML;
    }

    public void setRolML(RolML RolML) {
        this.RolML = RolML;
    }

    public List<Direccion> getDireccion() {
        return Direccion;
    }

    public void setDireccion(List<Direccion> Direccion) {
        this.Direccion = Direccion;
    }

    public String getImagen() {

        return Imagen;

    }

    public void setImagen(String Imagen) {

        this.Imagen = Imagen;

    }

}
