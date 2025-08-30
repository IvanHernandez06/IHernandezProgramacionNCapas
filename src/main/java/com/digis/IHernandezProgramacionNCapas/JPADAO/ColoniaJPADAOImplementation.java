
package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.JPA.ColoniaJPA;
import com.digis.IHernandezProgramacionNCapas.ML.Colonia;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ColoniaJPADAOImplementation implements IColoniaJPADAO{
    
    
    
     @Autowired
    EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<ColoniaJPA> queryColonia
                    = entityManager.createQuery("FROM colonia", ColoniaJPA.class);
            List<ColoniaJPA> colonias = queryColonia.getResultList();
            result.objects = new ArrayList<>();
            for (ColoniaJPA colonia : colonias) {
                result.objects.add(new Colonia(colonia));
            }

        } catch (Exception e) {
            result.e = e;
            result.errorMenssage = e.getLocalizedMessage();
            result.correct = false;
        }
        return result;
    }

    @Override
    public Result GetByIdMunicipio(int IdMunicipio) {
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
