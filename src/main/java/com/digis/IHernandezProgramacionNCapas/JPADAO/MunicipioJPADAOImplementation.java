package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.JPA.MunicipioJPA;
import com.digis.IHernandezProgramacionNCapas.ML.Municipio;
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
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<MunicipioJPA> queryMunicipio
                    = entityManager.createQuery("FROM municipio", MunicipioJPA.class);
            List<MunicipioJPA> municipios = queryMunicipio.getResultList();
            result.objects = new ArrayList<>();
            for (MunicipioJPA municipio : municipios) {
                result.objects.add(new Municipio(municipio));
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
    public Result GetByIdEstado(int IdEstado) {
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
