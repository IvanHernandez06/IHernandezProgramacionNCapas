package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.DAO.*;
import com.digis.IHernandezProgramacionNCapas.JPA.DireccionJPA;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class DireccionJPADAOImplementation implements IDireccionJPADAO {

    @Autowired
    private EntityManager entityManager;

    
    @Override
    public Result update(Usuarios usuarioML) {

        Result result = new Result();

        try {

            DireccionJPA direccionJPA = new DireccionJPA(usuarioML);

            entityManager.merge(direccionJPA);

            result.correct = true;

        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;

        }

        return result;

    }
    
    

    @Override
    public Result Add(Usuarios usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Result Update(com.digis.IHernandezProgramacionNCapas.ML.Direccion direccion, int IdDireccion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Result GetOne(int IdDireccion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Result Delete(int IdDireccion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
