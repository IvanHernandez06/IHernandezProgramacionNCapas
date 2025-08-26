package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.JPA.UsuariosML;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAOImplementation implements IUsuarioJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {

            TypedQuery<UsuariosML> queryAlumno = entityManager.createQuery("FROM Usuarios", UsuariosML.class);

            List<UsuariosML> usuarios = queryAlumno.getResultList();
            
            
            
            
        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;

        }

        return result;
    }

}
