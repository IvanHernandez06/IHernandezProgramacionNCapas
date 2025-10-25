package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;
import com.digis.IHernandezProgramacionNCapas.JPA.UsuariosJPA;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAOImplementation implements IUsuarioJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inyectamos PasswordEncoder

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {

            TypedQuery<UsuariosJPA> queryUsuarios
                    = entityManager.createQuery("FROM Usuarios ORDER BY IdUsuario",
                            UsuariosJPA.class);

            List<UsuariosJPA> usuarios = queryUsuarios.getResultList();

            result.objects = new ArrayList<>();

            for (UsuariosJPA usuario : usuarios) {

                result.objects.add(new Usuarios(usuario));

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
    public Result Add(Usuarios usuarioML) {

        Result result = new Result();

        try {

            UsuariosJPA usuarioJPA = new UsuariosJPA(usuarioML);
            usuarioJPA.setPassword(passwordEncoder.encode(usuarioJPA.getPassword()));
            entityManager.persist(usuarioJPA);

        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;
        }
        return result;
    }

    @Override
    public Result GetDetail(int idUsuario) {
        Result result = new Result();
        try {
            UsuariosJPA usuarioJPA = entityManager.find(UsuariosJPA.class, idUsuario);
            result.object = new Usuarios(usuarioJPA);
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
    public Result Delete(int IdUsuario) {
        Result result = new Result();
        try {

            UsuariosJPA usuarioJPA = entityManager.find(UsuariosJPA.class, IdUsuario);
            entityManager.remove(usuarioJPA);

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
    public Result Update(Usuarios usuarioML) {
        Result result = new Result();
        try {
            UsuariosJPA usuarioJPA = new UsuariosJPA(usuarioML);
            UsuariosJPA usuarioBD = entityManager.find(UsuariosJPA.class, usuarioML.getIdUsuario());
            usuarioJPA.Direccion = usuarioBD.Direccion;
            entityManager.merge(usuarioJPA);
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.e = e;
            result.errorMenssage = e.getLocalizedMessage();
        }

        return result;
    }

    @Transactional
    @Override
    public Result BajaLogica(int IdUsuario) {
        Result result = new Result();

        try {

            UsuariosJPA usuarioJPA = entityManager.find(UsuariosJPA.class, IdUsuario);
            usuarioJPA.setEstatus(usuarioJPA.getEstatus() == 1 ? 0 : 1);
            entityManager.merge(usuarioJPA);

            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;
        }

        return result;
    }
}
