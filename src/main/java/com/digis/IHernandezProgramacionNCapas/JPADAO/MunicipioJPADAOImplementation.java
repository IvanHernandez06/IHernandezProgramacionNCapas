package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.JPA.MunicipioJPA;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioJPADAOImplementation implements IMunicipioJPADAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetAll(int IdEstado) {
          Result result = new Result();

        try {
            TypedQuery<MunicipioJPA> queryMunicipios = entityManager.createQuery("FROM Municipio where Estado.IdEstado = :IdEstado ", MunicipioJPA.class);
            queryMunicipios.setParameter("IdEstado", IdEstado);
            List<MunicipioJPA> municipios = queryMunicipios.getResultList();

            result.objects = new ArrayList<>();

            for (MunicipioJPA municipio : municipios) {
                result.objects.add(new com.digis.IHernandezProgramacionNCapas.ML.Municipio(municipio));
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
