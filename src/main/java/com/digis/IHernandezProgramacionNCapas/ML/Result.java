package com.digis.IHernandezProgramacionNCapas.ML;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class Result {

    public boolean correct;
    public String errorMenssage;
    public Exception e;
    public Object object;
    public List<Object> objects;

        
    @JsonIgnore
    public int status;
    
    
    public int linea;
    public String dato;
    public String mensaje;

    public Result() {
    }

    public Result(int linea, String dato, String mensaje) {
        this.linea = linea;
        this.dato = dato;
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    
    
}
