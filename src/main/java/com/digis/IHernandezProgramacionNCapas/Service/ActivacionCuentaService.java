package com.digis.IHernandezProgramacionNCapas.Service;

import com.digis.IHernandezProgramacionNCapas.Component.JwtUtil;
import com.digis.IHernandezProgramacionNCapas.DAO.IRepositoryUsuario;
import com.digis.IHernandezProgramacionNCapas.JPA.UsuariosJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivacionCuentaService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IRepositoryUsuario iRepositoryUsuario;

    public String activarCuenta(String token) {
        if (jwtUtil.esTokenValido(token)) {
            int idUsuario = jwtUtil.obtenerIdUsuario(token);
            UsuariosJPA usuario = iRepositoryUsuario.findById(idUsuario).orElse(null);
            if (usuario != null) {
                usuario.setEstatus(1); // Activar cuenta
                iRepositoryUsuario.save(usuario);
                return "Cuenta activada exitosamente.";
            } else {
                return "Usuario no encontrado.";
            }
        } else {
            return "Token inválido o expirado.";
        }
    }
}
