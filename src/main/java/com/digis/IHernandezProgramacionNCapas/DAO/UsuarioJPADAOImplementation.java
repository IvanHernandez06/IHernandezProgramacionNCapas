package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.JPA.Usuarios;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
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

            TypedQuery<Usuarios> queryUsuarios
                    = entityManager.createQuery("FROM Usuarios ORDER BY IdUsuario",
                            Usuarios.class);

            List<Usuarios> usuarios = queryUsuarios.getResultList();

            result.objects = new ArrayList<>();

            for (Usuarios usuario : usuarios) {

                result.objects.add(new com.digis.IHernandezProgramacionNCapas.ML.Usuarios(usuario));

            }
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;

        }

        return result;
    }

    @Transactional
    @Override
    public Result Add(com.digis.IHernandezProgramacionNCapas.ML.Usuarios usuarioML) {

        Result result = new Result();

        try {

            Usuarios usuarioJPA = new Usuarios(usuarioML);

            entityManager.persist(usuarioJPA);

        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;
        }
        return result;
    }

    @Transactional
    @Override
    public Result Delete(int IdUsuario) {
        Result result = new Result();
        try {
            
            Usuarios usuarioJPA = entityManager.find(Usuarios.class, IdUsuario);
            entityManager.remove(usuarioJPA);

            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;
        }

        return result;
    }

}
