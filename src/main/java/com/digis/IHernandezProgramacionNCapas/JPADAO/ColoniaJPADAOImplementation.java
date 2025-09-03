
package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.JPA.ColoniaJPA;
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
    public Result GetAll(int IdMunicipio) {
        Result result = new Result();

        try {
            TypedQuery<ColoniaJPA> queryColonias = entityManager.createQuery("FROM Colonia where Municipio.IdMunicipio = :IdMunicipio ", ColoniaJPA.class);
            queryColonias.setParameter("IdMunicipio", IdMunicipio);
            List<ColoniaJPA> colonias = queryColonias.getResultList();

            result.objects = new ArrayList<>();

            for (ColoniaJPA colonia : colonias) {
                result.objects.add(new com.digis.IHernandezProgramacionNCapas.ML.Colonia(colonia));
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
