

package com.digis.IHernandezProgramacionNCapas.ML;

import java.util.ArrayList;
import org.springframework.format.annotation.DateTimeFormat;


public class UsuariosML{
    private int IdUsuario;
    private String Username;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Email;
    private String Password;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private String FechaNacimiento;
    private char Sexo;
    private String Telefono;
    private String Celular;
    private String Curp;
    
    public RolML RolML;
    public ArrayList<Direccion> Direccion;
    
   

    public UsuariosML() {
    }

    public UsuariosML(int IdUsuario, String Username, String Nombre, String ApellidoPaterno, String ApellidoMaterno, String Email, String Password, String FechaNacimiento, char Sexo, String Telefono, String Celular, String Curp) {
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

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
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

    public ArrayList<Direccion> getDireccion() {
        return Direccion;
    }

    public void setDireccion(ArrayList<Direccion> Direccion) {
        this.Direccion = Direccion;
    }

    
    
    
    
}
