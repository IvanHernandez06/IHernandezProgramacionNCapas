
package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.JPA.RolJPA;
import com.digis.IHernandezProgramacionNCapas.ML.RolML;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolJPADAOImplementation implements IRolJPADAO{
      @Autowired
        private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            TypedQuery<RolJPA> queryRol
                    = entityManager.createQuery("FROM rol", RolJPA.class);
            List<RolJPA> roles = queryRol.getResultList();

            result.objects = new ArrayList<>();
            for (RolJPA rol : roles) {
                result.objects.add(new RolML(rol));
            }
            result.correct = true;
        } catch (Exception e) {
            result.e = e;
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
        }
            return result;
    }
}
