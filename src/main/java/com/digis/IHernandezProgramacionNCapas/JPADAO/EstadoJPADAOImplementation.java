
package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.JPA.EstadoJPA;
import com.digis.IHernandezProgramacionNCapas.ML.Estado;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoJPADAOImplementation implements IEstadoJPADAO{
    
    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<EstadoJPA> queryEstado
                    = entityManager.createQuery("FROM estado", EstadoJPA.class);
            List<EstadoJPA> estados = queryEstado.getResultList();
            result.objects = new ArrayList<>();
            for (EstadoJPA estado : estados) {
                result.objects.add(new Estado(estado));
            }
            result.correct = true;
        } catch (Exception e) {
            result.e = e;
            result.errorMenssage = e.getLocalizedMessage();
            result.correct = false;
        }
        return result;
    }
    
    
    

    @Override
      public Result GetByIdPais(int idPais) {
        Result result = new Result();

        try {

        } catch (Exception e) {
            result.e = e;
            result.errorMenssage = e.getLocalizedMessage();
            result.correct = false;
        }
        return result;
    }
    
}
