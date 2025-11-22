package com.digis.IHernandezProgramacionNCapas.Service;

import com.digis.IHernandezProgramacionNCapas.DAO.IRepositoryUsuario;
import com.digis.IHernandezProgramacionNCapas.JPA.UsuariosJPA;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsuario {

    private final IRepositoryUsuario iRepositoryUsuario;
    private final PasswordEncoder passwordEncoder;

    public ServiceUsuario(IRepositoryUsuario iRepositoryUsuario, PasswordEncoder passwordEncoder) {
        this.iRepositoryUsuario = iRepositoryUsuario;
        this.passwordEncoder = passwordEncoder;
    }

    public Result add(UsuariosJPA usuario) {
        Result result = new Result();
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            result.correct = true;
            result.status = 200;
        } catch (Exception e) {
            result.errorMenssage = e.getLocalizedMessage();
            result.correct = false;
            result.status = 500;

            result.e = e;
        }
        return result;
    }

    public Result UpdateContraseña() {
        Result result = new Result();
        try {
            List<UsuariosJPA> usuarios = iRepositoryUsuario.findAll();
            List<UsuariosJPA> usuariosActualizados = new ArrayList<>();

            for (UsuariosJPA usuario : usuarios) {
                String password = usuario.getPassword();

                if (password != null && !password.startsWith("$2a$") && !password.startsWith("$2b$") && !password.startsWith("$2y$")) {
                    String hashed = passwordEncoder.encode(password);
                    usuario.setPassword(hashed);
                    usuariosActualizados.add(usuario);
                }
            }
            if (!usuariosActualizados.isEmpty()) {
                iRepositoryUsuario.saveAll(usuariosActualizados);
            }
            result.mensaje = usuariosActualizados.size() + " usuarios fueron actualizados correctamente.";
            result.correct = true;
            result.status = 200;
        } catch (Exception e) {
            result.errorMenssage = e.getLocalizedMessage();
            result.correct = false;
            result.status = 500;

            result.e = e;
        }
        return result;
    }
}