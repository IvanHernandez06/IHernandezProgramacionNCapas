
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
    public Result GetAll(int IdPais) {
     Result result = new Result();

        try {
            TypedQuery<EstadoJPA> queryEstados = entityManager.createQuery("FROM Estado where Pais.IdPais = :IdPais ", EstadoJPA.class);
            queryEstados.setParameter("IdPais", IdPais);
            List<EstadoJPA> estados = queryEstados.getResultList();

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
  
}
