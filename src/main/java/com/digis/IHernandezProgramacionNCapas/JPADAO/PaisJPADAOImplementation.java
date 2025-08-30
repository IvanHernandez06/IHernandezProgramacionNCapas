package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.JPA.PaisJPA;
import com.digis.IHernandezProgramacionNCapas.ML.Pais;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaisJPADAOImplementation implements IPaisJPADAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            TypedQuery<PaisJPA> paisQuery
                    = entityManager.createQuery("FROM pais", PaisJPA.class);
            List<PaisJPA> paises = paisQuery.getResultList();
            result.objects = new ArrayList<>();
            for (PaisJPA pais : paises) {
                result.objects.add(new Pais(pais));
            }
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;
        }
        return result;

    }

}
